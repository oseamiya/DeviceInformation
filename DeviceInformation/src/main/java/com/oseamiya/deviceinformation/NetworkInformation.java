package com.oseamiya.deviceinformation;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.RouteInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class NetworkInformation {
    private Context context;
    private static final String TAG = "NetworkInformation";
    public NetworkInformation(Context context){
        this.context = context;
    }
    /**
     * Get the Ip address of the device.
     * The first method  doesn't require ACCESS_WIFI_STATE permission
     * if exception is caught then it will implement other method
     * @return ipAddress in String
     */
    @SuppressLint("DefaultLocale")
    public String getIpAddress(boolean useIPv4){
        int delim = 0;
        String finalAdress = "";
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for(NetworkInterface intf : interfaces){
                List<InetAddress> addresses = Collections.list(intf.getInetAddresses());
                for(InetAddress addr : addresses){
                    if(!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        boolean isIPv4 = sAddr.indexOf(':') < 0 ;
                        if(useIPv4){
                            if(isIPv4)
                                finalAdress = sAddr;
                        } else {
                            if(!isIPv4){
                                delim = sAddr.indexOf('%');
                                finalAdress =  delim<0 ? sAddr.toUpperCase() : sAddr.substring(0 , delim);
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            WifiManager wifiManager = (WifiManager) this.context.getSystemService(Context.WIFI_SERVICE);
            int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
            finalAdress = String.format("%d.%d.%d.%d", (ipAddress & 0xff),(ipAddress >> 8 & 0xff),(ipAddress >> 16 & 0xff),(ipAddress >> 24 & 0xff));
        }
        return finalAdress;
    }
    private static final String[] FACTORY_DNS_SERVERS = {
            "8.8.8.8",
            "8.8.4.4"
    };
    private static final String METHOD_EXEC_PROP_DELIM = "]: [";
    /**
     * To get all the DNS servers available(With all the methods)
     * Got this code from https://stackoverflow.com/a/48973823/15176346
     * Thanks to Grigore Madani for the codes :)
     * @return arrays of DNS Servers
     */
    public String [] getServers() {
        String[] result;
        result = getServersMethodSystemProperties();
        if (result != null && result.length > 0) {
            return result;
        }
        result = getServersMethodConnectivityManager();
        if (result != null && result.length > 0) {

            return result;

        }
        result = getServersMethodExec();
        if (result != null && result.length > 0) {

            return result;

        }
        return FACTORY_DNS_SERVERS;

    }
    private String [] getServersMethodConnectivityManager() {
        // This code only works on LOLLIPOP and higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            try {
                ArrayList<String> priorityServersArrayList  = new ArrayList<>();
                ArrayList<String> serversArrayList          = new ArrayList<>();

                ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService(CONNECTIVITY_SERVICE);
                if (connectivityManager != null) {

                    // Iterate all networks
                    // Notice that android LOLLIPOP or higher allow iterating multiple connected networks of SAME type
                    for (Network network : connectivityManager.getAllNetworks()) {

                        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
                        if (networkInfo.isConnected()) {

                            LinkProperties linkProperties    = connectivityManager.getLinkProperties(network);
                            List<InetAddress> dnsServersList = linkProperties.getDnsServers();

                            // Prioritize the DNS servers for link which have a default route
                            if (linkPropertiesHasDefaultRoute(linkProperties)) {

                                for (InetAddress element: dnsServersList) {

                                    String dnsHost = element.getHostAddress();
                                    priorityServersArrayList.add(dnsHost);

                                }

                            } else {

                                for (InetAddress element: dnsServersList) {

                                    String dnsHost = element.getHostAddress();
                                    serversArrayList.add(dnsHost);

                                }

                            }

                        }

                    }

                }

                // Append secondary arrays only if priority is empty
                if (priorityServersArrayList.isEmpty()) {

                    priorityServersArrayList.addAll(serversArrayList);

                }

                // Stop here if we have at least one DNS server
                if (priorityServersArrayList.size() > 0) {

                    return priorityServersArrayList.toArray(new String[0]);

                }

            } catch (Exception ex) {

                Log.d(TAG, "Exception detecting DNS servers using ConnectivityManager method", ex);

            }

        }

        // Failure
        return null;

    }

    private String [] getServersMethodSystemProperties() {


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {

            // This originally looked for all lines containing .dns; but
            // http://code.google.com/p/android/issues/detail?id=2207#c73
            // indicates that net.dns* should always be the active nameservers, so
            // we use those.
            final String re1 = "^\\d+(\\.\\d+){3}$";
            final String re2 = "^[0-9a-f]+(:[0-9a-f]*)+:[0-9a-f]+$";
            ArrayList<String> serversArrayList = new ArrayList<>();
            try {

                @SuppressLint("PrivateApi") Class SystemProperties = Class.forName("android.os.SystemProperties");
                Method method = SystemProperties.getMethod("get", new Class[]{String.class});
                final String[] netdns = new String[]{"net.dns1", "net.dns2", "net.dns3", "net.dns4"};
                for (int i = 0; i < netdns.length; i++) {

                    Object[] args = new Object[]{netdns[i]};
                    String v = (String) method.invoke(null, args);
                    if (v != null && (v.matches(re1) || v.matches(re2)) && !serversArrayList.contains(v)) {
                        serversArrayList.add(v);
                    }

                }

                // Stop here if we have at least one DNS server
                if (serversArrayList.size() > 0) {

                    return serversArrayList.toArray(new String[0]);

                }

            } catch (Exception ex) {

                Log.d(TAG, "Exception detecting DNS servers using SystemProperties method", ex);

            }

        }

        // Failed
        return null;

    }

    /**
     * Detect android DNS servers by executing getprop string command in a separate process
     *
     * Notice there is an android bug when Runtime.exec() hangs without providing a Process object.
     * This problem is fixed in Jelly Bean (Android 4.1) but not in ICS (4.0.4) and probably it will never be fixed in ICS.
     * https://stackoverflow.com/questions/8688382/runtime-exec-bug-hangs-without-providing-a-process-object/11362081
     *
     * @return Dns servers array
     */
    private String [] getServersMethodExec() {

        // We are on the safe side and avoid any bug
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

            try {

                Process process = Runtime.getRuntime().exec("getprop");
                InputStream inputStream = process.getInputStream();
                LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(inputStream));
                Set<String> serversSet = methodExecParseProps(lineNumberReader);
                if (serversSet != null && serversSet.size() > 0) {

                    return serversSet.toArray(new String[0]);

                }

            } catch (Exception ex) {

                Log.d(TAG, "Exception in getServersMethodExec", ex);

            }

        }

        // Failed
        return null;

    }

    /**
     * Parse properties produced by executing getprop command
     * @param lineNumberReader
     * @return Set of parsed properties
     * @throws Exception
     */
    private Set<String> methodExecParseProps(BufferedReader lineNumberReader) throws Exception {

        String line;
        Set<String> serversSet = new HashSet<String>(10);

        while ((line = lineNumberReader.readLine()) != null) {
            int split = line.indexOf(METHOD_EXEC_PROP_DELIM);
            if (split == -1) {
                continue;
            }
            String property = line.substring(1, split);

            int valueStart  = split + METHOD_EXEC_PROP_DELIM.length();
            int valueEnd    = line.length() - 1;
            if (valueEnd < valueStart) {

                // This can happen if a newline sneaks in as the first character of the property value. For example
                // "[propName]: [\n…]".
                Log.d(TAG, "Malformed property detected: \"" + line + '"');
                continue;

            }

            String value = line.substring(valueStart, valueEnd);

            if (value.isEmpty()) {

                continue;

            }

            if (property.endsWith(".dns") || property.endsWith(".dns1") ||
                    property.endsWith(".dns2") || property.endsWith(".dns3") ||
                    property.endsWith(".dns4")) {

                // normalize the address
                InetAddress ip = InetAddress.getByName(value);
                if (ip == null) continue;
                value = ip.getHostAddress();

                if (value == null) continue;
                if (value.length() == 0) continue;

                serversSet.add(value);

            }

        }

        return serversSet;

    }

    /**
     * Returns true if the specified link properties have any default route
     * @param linkProperties
     * @return true if the specified link properties have default route or false otherwise
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean linkPropertiesHasDefaultRoute(LinkProperties linkProperties) {

        for (RouteInfo route : linkProperties.getRoutes()) {
            if (route.isDefaultRoute()) {
                return true;
            }
        }
        return false;

    }
    // New methods are added from here

    /**
     * @return whether adb debugging is enabled or not in boolean
     */
    public boolean isADBDebuggingEnabled(){
        return Settings.Secure.getInt(this.context.getContentResolver(), "adb_enabled" , 0) > 0 ;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isVpnConnection(){
        return Settings.Secure.getInt(this.context.getContentResolver(), "vpn_state", 0) == 1 || isvpn1() || isvpn2();
    }
    private boolean isvpn1() {
        String iface = "";
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.isUp())
                    iface = networkInterface.getName();
                Log.d("DEBUG", "IFACE NAME: " + iface);
                if ( iface.contains("tun") || iface.contains("ppp") || iface.contains("pptp")) {
                    return true;
                }
            }
        } catch (SocketException e1) {
            e1.printStackTrace();
        }

        return false;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isvpn2() {
        ConnectivityManager cm = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network activeNetwork = cm.getActiveNetwork();
        NetworkCapabilities caps = cm.getNetworkCapabilities(activeNetwork);
        boolean vpnInUse = caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
        return vpnInUse;
    }




}

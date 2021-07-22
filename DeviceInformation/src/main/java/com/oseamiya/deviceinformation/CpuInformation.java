package com.oseamiya.deviceinformation;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.MatchResult;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class CpuInformation implements GLSurfaceView.Renderer {
    private final Context context;
    private final HashMap<String,String> hashMap = new HashMap<String,String>();
    public CpuInformation(Context context){
        this.context = context;
    }


    /**
     * It will get the total number of cores of Android CPU
     * @return number of cores in int
     */
    public int getNumberOfCores(){
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * Get all the supported ABIs (Application Binary Interfaces)
     * @return in String Arrays
     */
    public static String[] getSupportedABIs(){
        return Build.SUPPORTED_ABIS;
    }

    /**
     * To get the minimum cpu frequency
     * @return in kiloHertz.
     */
    public static int getMinimumFrequency(){
        return CpuInformation.readSystemFileAsInt("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq");
    }

    /**
     * To get the maximum cpu frequency
     * @return in kiloHertz.
     */
    public static int getMaximumFrequency(){
        return CpuInformation.readSystemFileAsInt("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
    }

    /**
     * BogoMips (from "bogus" and MIPS) is a crude measurement of CPU speed made by the Linux kernel
     * when it boots to calibrate an internal busy-loop.
     * @return float
     * @throws Exception
     */
    public static float getBogoMips() throws Exception {
        final MatchResult matchResult = CpuInformation.matchSystemFile("/proc/cpuinfo", "BogoMIPS[\\s]*:[\\s]*(\\d+\\.\\d+)[\\s]*\n", 1000);

        try {
            if(matchResult.groupCount() > 0) {
                return Float.parseFloat(matchResult.group(1));
            } else {
                throw new Exception();
            }
        } catch (final NumberFormatException e) {
            throw new Exception(e);
        }
    }

    /**
     * To get the clock speed of the CPU
     * @return in int
     */
    public static int getClockSpeed(){
        return CpuInformation.readSystemFileAsInt("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
    }

    /**
     * To check whether your device is 34-bit or 64-bit
     * @return boolean
     */
    public static boolean is64Bit(){
        return Build.SUPPORTED_64_BIT_ABIS.length > 0;
    }

    /**
     * Get minimum scaling frequency of the CPU
     * @return kiloHertz(in int)
     */
    public static int getMinScalingFrequency(){
        return CpuInformation.readSystemFileAsInt("/sys/devices/system/cpu/cpu0/cpufreq/scaling_min_freq");
    }

    /**
     * Get maximum scaling frequency of the cpu
     * @return kiloHertz(in int)
     */
    public static int getMaxScalingFrequency(){
        return CpuInformation.readSystemFileAsInt("/sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq");
    }

    /**
     * Get GPU Renderer
     * @return String
     */
    public String getGPURenderer(){
        getGPUInformation();
        return hashMap.get("RENDERER");
    }

    /**
     * Get GPU Vendor
     * @return String
     */
    public String getGPUVendor(){
        getGPUInformation();
        return hashMap.get("VENDOR");
    }

    /**
     * To get the GPU Extensions
     * @return String
     */
    public String getGPUExtensions(){
        getGPUInformation();
        return hashMap.get("EXTENSIONS");
    }

    /**
     * To get GPU Version
     * @return String
     */
    public String getGPUVersion(){
        getGPUInformation();
        return hashMap.get("VERSION");
    }

    /**
     * To check if GPU is supported or not
     * @return boolean6
     */
    public boolean isGPUSupported(){
        final ActivityManager activityManager =  (ActivityManager)this.context.getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager
                .getDeviceConfigurationInfo();
        return configurationInfo.reqGlEsVersion >= 0x20000;
    }
    public String getCpuInformation(){
        ProcessBuilder processBuilder;
        Process process;
        String[] DATA = {"/system/bin/cat" , "/proc/cpuinfo"};
        InputStream inputStream;
        byte[] byteArray;
        StringBuilder Holder = new StringBuilder();

        processBuilder = new ProcessBuilder(DATA);
        byteArray = new byte[1024];
        try {
            process = processBuilder.start();
            inputStream = process.getInputStream();
            while (inputStream.read(byteArray) != -1){
                Holder.append(new String(byteArray));
            }
            inputStream.close();
            return Holder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Exception Occurred";
        }
    }


    private static int readSystemFileAsInt(final String systemFile){
        InputStream inputStream = null;
        try {
            final Process process = new ProcessBuilder(new String[] {"/system/bin/cat" , systemFile}).start();
            inputStream = process.getInputStream();
            final String content = readFully(inputStream);
            return Integer.parseInt(content);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public static final String readFully(final InputStream pInputStream) throws IOException {
        final StringBuilder sb = new StringBuilder();
        final Scanner sc = new Scanner(pInputStream);
        while(sc.hasNextLine()) {
            sb.append(sc.nextLine());
        }
        return sb.toString();
    }
    private static MatchResult matchSystemFile(final String systemFile, final String pattern, final int horizon) throws Exception {
        InputStream in = null;
        try {
            final Process process = new ProcessBuilder(new String[] { "/system/bin/cat", systemFile }).start();

            in = process.getInputStream();
            final Scanner scanner = new Scanner(in);

            final boolean matchFound = scanner.findWithinHorizon(pattern, horizon) != null;
            if(matchFound) {
                return scanner.match();
            } else {
                throw new Exception();
            }
        } catch (final IOException e) {
            throw new Exception(e);
        }

    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        hashMap.clear();
        hashMap.put("RENDERER" , gl.glGetString(GL10.GL_RENDERER));
        hashMap.put("VENDOR" , gl.glGetString(GL10.GL_VENDOR));
        hashMap.put("VERSION" , gl.glGetString(GL10.GL_VERSION));
        hashMap.put("EXTENSIONS" , gl.glGetString(GL10.GL_EXTENSIONS));
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {

    }
    private  void getGPUInformation(){
        final ActivityManager activityManager =  (ActivityManager)this.context.getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager
                .getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;
        //
        GLSurfaceView glSurfaceView = new GLSurfaceView(this.context);
            glSurfaceView.setRenderer(this);
    }
}

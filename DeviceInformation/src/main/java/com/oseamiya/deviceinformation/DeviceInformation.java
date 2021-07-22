package com.oseamiya.deviceinformation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;

import java.io.File;


public class DeviceInformation  {
    private final Context context;
    public DeviceInformation(Context context){
        this.context = context;
    }


    /**
     * It returns the device model name
     */

    public String getModelName() {
        return Build.MODEL;
    }

    /**
     * It returns the consumer friendly device name
     */

    public String getDeviceName() {
        String manafacture = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manafacture)) {
            return capitalize(model);
        } else {
            return capitalize(manafacture);
        }
    }

    /** Returns the device manafacturer name */

    public String getManafacturerName(){
        return Build.MANUFACTURER;
    }

    /** Returns the board name of the device */

    public String getBoardName(){
        return Build.BOARD;
    }

    /** Returns the hardware name of the device */
    public String getHardwareName(){
        return Build.HARDWARE;
    }

    /** Returns the brand name of the device */
    public String getBrandName(){
        return Build.BRAND;
    }

    /** Returns the android device id  */

    @SuppressLint("HardwareIds")
    public String getDeviceId(){
        return Settings.Secure.getString(this.context.getContentResolver() , Settings.Secure.ANDROID_ID);
    }

    /** Returns the build fingerprint of the android device */
    public String getBuildFingerPrint(){
        return Build.FINGERPRINT;
    }

    /** Returns Phone type either it is GSM  or CDMA or SIP */
    public String getDeviceType(){
        TelephonyManager telephonyManager = (TelephonyManager) this.context.getSystemService(Context.TELEPHONY_SERVICE);
        int phoneType = telephonyManager.getPhoneType();
        if(phoneType == TelephonyManager.PHONE_TYPE_CDMA){
            return "CDMA";
        } else if(phoneType == TelephonyManager.PHONE_TYPE_GSM){
            return  "GSM";
        } else if(phoneType == TelephonyManager.PHONE_TYPE_SIP){
            return "SIP";
        } else{
            return "";
        }
    }

    /** Checks whether USB Host is supported or not */
    public boolean isUsbHostSupported(){
        return this.context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_USB_HOST);
    }

    /** Returns the number of sim slot available in android device */
    public  int getNumberOfSimSlot() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            SubscriptionManager subscriptionManager =SubscriptionManager.from(this.context);
            return subscriptionManager.getActiveSubscriptionInfoCountMax();
        }
        else{
            // A method have to be implemented to get number of simSlot in less than Lollipop
            return 1;
        }

    }
    /*
     * Returns the device imei number of the particular slot
     * Remember the slot number should starts with 1
     * To access Imei in android sdk version more than 29 ,
     * Special permissions should be required ..
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"MissingPermission", "HardwareIds"})
    @Deprecated
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public String getImei(int slotNumber){
        TelephonyManager telephonyManager = (TelephonyManager) this.context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId(slotNumber);
    }

    /*
     * Returns build time
     */
    public long getBuildTime(){
        return Build.TIME;
    }

    public String getProductName(){
        return Build.PRODUCT;
    }

    public String getCodeName(){
        return Build.VERSION.CODENAME;
    }

    public String getRadioVersion(){
        return Build.getRadioVersion();
    }

    public String getDisplayVersion(){
        return Build.DISPLAY;
    }

    public String getHost(){
        return Build.HOST;
    }

    public String getBuildUser(){
        return Build.USER;
    }

    public String getSerial(){
        return Build.SERIAL;
    }

    /**
     * It will check whether device is rooted or not
     * @return boolean
     */
    public boolean isRooted(){
        String[] locations = {"/sbin" , "/system/bin" , "/system/xbin/" , "/system/sd/xbin" , "/system/bin/failsafe/" , "/data/local/xbin/" , "/data/local/bin/" , "/data/local/"};
        for(String location : locations){
            if(new File(location + "su").exists()){
                return true;
            }
        }
        return false;
    }


    private String capitalize(String s) {
        if (TextUtils.isEmpty(s)) {
            return s;
        }
        char[] arr = s.toCharArray();
        boolean capitalizeNext = true;
        String phrase = "";
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase += c;
        }
        return phrase;
    }
}

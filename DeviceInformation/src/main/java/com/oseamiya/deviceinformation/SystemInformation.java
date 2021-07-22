package com.oseamiya.deviceinformation;

import android.content.Context;
import android.os.Build;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SystemInformation {
    private final Context context;

    public SystemInformation( Context context){
        this.context = context;
    }
    /** Returns API Level of the android device */
    public int getApiLevel(){
        return Build.VERSION.SDK_INT;
    }

    /** Returns Android Version Name */
    public String getVersionName() {
        Field[] fields = Build.VERSION_CODES.class.getFields();
        String name = fields[Build.VERSION.SDK_INT + 1 ].getName();

        if(name.equals("O")) name = "Oreo";
        if(name.equals("N")) name = "Nougat";
        if(name.equals("M")) name = "Marshmallow";
        if(name.startsWith("O_")) name = "Oreo++";
        if(name.startsWith("N_")) name = "Nougat++";

        return name;
    }

    /**
     * It returns the security patch date
     * Since Build.VERSION.SECURITY_PATCH is added in api 23
     * Added other method to get the latest security patch date
     */
    public String getSecurityPathDate() {
        if (Build.VERSION.SDK_INT >= 23) {
            return Build.VERSION.SECURITY_PATCH;
        } else {
            try {
                Process process = new ProcessBuilder()
                        .command("/system/bin/getprop")
                        .redirectErrorStream(true)
                        .start();
                InputStream is = process.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuilder str = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    str.append(line).append("\n");
                    if (str.toString().contains("security_patch")) {
                        String[] split = line.split(":");
                        if (split.length == 2) {
                            return split[1];
                        }
                        break;
                    }

                }

                br.close();
                process.destroy();
            } catch (IOException e) {
                e.printStackTrace();


            }
        }
        return "";
    }
    /**
     * It will return the api level's release date of the android
     * I've written it manually
     * @Return Date ifNotFound then null
     */

    public Date getAndroidSdkReleaseDate(){
        int sdk = Build.VERSION.SDK_INT;
        Calendar calendar = Calendar.getInstance();
        if(sdk == 1){
            calendar.set(Calendar.MONTH , 8);
            calendar.set(Calendar.DATE , 23);
            calendar.set(Calendar.YEAR , 2008);
            return calendar.getTime();
        } else if(sdk == 2){
            calendar.set(Calendar.MONTH , 1);
            calendar.set(Calendar.DATE , 9);
            calendar.set(Calendar.YEAR , 2009);
            return calendar.getTime();
        } else if(sdk == 3){
            calendar.set(Calendar.MONTH , 3);
            calendar.set(Calendar.DATE , 27);
            calendar.set(Calendar.YEAR , 2009);
            return calendar.getTime();
        } else if(sdk == 4){
            calendar.set(Calendar.MONTH , 8);
            calendar.set(Calendar.DATE , 15);
            calendar.set(Calendar.YEAR , 2009);
            return calendar.getTime();
        }else if(sdk == 5){
            calendar.set(Calendar.MONTH , 9);
            calendar.set(Calendar.DATE , 27);
            calendar.set(Calendar.YEAR , 2009);
            return calendar.getTime();
        }else if(sdk == 6){
            calendar.set(Calendar.MONTH , 11);
            calendar.set(Calendar.DATE , 3);
            calendar.set(Calendar.YEAR , 2009);
            return calendar.getTime();
        }else if(sdk == 7){
            calendar.set(Calendar.MONTH , 0);
            calendar.set(Calendar.DATE , 11);
            calendar.set(Calendar.YEAR , 2010);
            return calendar.getTime();
        }else if(sdk == 8){
            calendar.set(Calendar.MONTH , 4);
            calendar.set(Calendar.DATE , 20);
            calendar.set(Calendar.YEAR , 2010);
            return calendar.getTime();
        } else if(sdk == 9){
            calendar.set(Calendar.MONTH , 11);
            calendar.set(Calendar.DATE , 6);
            calendar.set(Calendar.YEAR , 2010);
            return calendar.getTime();
        }else if(sdk == 10){
            calendar.set(Calendar.MONTH , 1);
            calendar.set(Calendar.DATE , 9);
            calendar.set(Calendar.YEAR , 2011);
            return calendar.getTime();
        }else if(sdk == 11){
            calendar.set(Calendar.MONTH , 1);
            calendar.set(Calendar.DATE , 22);
            calendar.set(Calendar.YEAR , 2011);
            return calendar.getTime();
        }else if(sdk == 12){
            calendar.set(Calendar.MONTH , 4);
            calendar.set(Calendar.DATE , 10);
            calendar.set(Calendar.YEAR , 2011);
            return calendar.getTime();
        }else if(sdk == 13){
            calendar.set(Calendar.MONTH , 6);
            calendar.set(Calendar.DATE , 15);
            calendar.set(Calendar.YEAR , 2011);
            return calendar.getTime();
        }else if(sdk == 14){
            calendar.set(Calendar.MONTH , 9);
            calendar.set(Calendar.DATE , 18);
            calendar.set(Calendar.YEAR , 2011);
            return calendar.getTime();
        }else if(sdk == 15){
            calendar.set(Calendar.MONTH , 11);
            calendar.set(Calendar.DATE , 16);
            calendar.set(Calendar.YEAR , 2011);
            return calendar.getTime();
        }else if(sdk == 16){
            calendar.set(Calendar.MONTH , 6);
            calendar.set(Calendar.DATE , 9);
            calendar.set(Calendar.YEAR , 2012);
            return calendar.getTime();
        }else if(sdk == 17){
            calendar.set(Calendar.MONTH , 10);
            calendar.set(Calendar.DATE , 13);
            calendar.set(Calendar.YEAR , 2012);
            return calendar.getTime();
        }else if(sdk == 18){
            calendar.set(Calendar.MONTH , 6);
            calendar.set(Calendar.DATE , 24);
            calendar.set(Calendar.YEAR , 2013);
            return calendar.getTime();
        }else if(sdk == 19){
            calendar.set(Calendar.MONTH , 9);
            calendar.set(Calendar.DATE , 31);
            calendar.set(Calendar.YEAR , 2013);
            return calendar.getTime();
        }else if(sdk == 20){
            calendar.set(Calendar.MONTH , 5);
            calendar.set(Calendar.DATE , 25);
            calendar.set(Calendar.YEAR , 2014);
            return calendar.getTime();
        }else if(sdk == 21){
            calendar.set(Calendar.MONTH , 10);
            calendar.set(Calendar.DATE , 4);
            calendar.set(Calendar.YEAR , 2014);
            return calendar.getTime();
        }else if(sdk == 22){
            calendar.set(Calendar.MONTH , 2);
            calendar.set(Calendar.DATE , 2);
            calendar.set(Calendar.YEAR , 2015);
            return calendar.getTime();
        }else if(sdk == 23){
            calendar.set(Calendar.MONTH , 9);
            calendar.set(Calendar.DATE , 2);
            calendar.set(Calendar.YEAR , 2015);
            return calendar.getTime();
        }else if(sdk == 24){
            calendar.set(Calendar.MONTH , 7);
            calendar.set(Calendar.DATE , 22);
            calendar.set(Calendar.YEAR , 2016);
            return calendar.getTime();
        }else if(sdk == 25){
            calendar.set(Calendar.MONTH , 9);
            calendar.set(Calendar.DATE , 4);
            calendar.set(Calendar.YEAR , 2016);
            return calendar.getTime();
        }else if(sdk == 26){
            calendar.set(Calendar.MONTH , 6);
            calendar.set(Calendar.DATE , 21);
            calendar.set(Calendar.YEAR , 2017);
            return calendar.getTime();
        }else if(sdk == 27){
            calendar.set(Calendar.MONTH , 11);
            calendar.set(Calendar.DATE , 5);
            calendar.set(Calendar.YEAR , 2017);
            return calendar.getTime();
        }else if(sdk == 28) {
            calendar.set(Calendar.MONTH , 6);
            calendar.set(Calendar.DATE , 6);
            calendar.set(Calendar.YEAR , 2018);
            return calendar.getTime();
        }else if(sdk == 29){
            calendar.set(Calendar.MONTH , 8);
            calendar.set(Calendar.DATE , 7);
            calendar.set(Calendar.YEAR , 2019);
            return calendar.getTime();
        }else if(sdk == 30){
            calendar.set(Calendar.MONTH , 8);
            calendar.set(Calendar.DATE , 8);
            calendar.set(Calendar.YEAR , 2020);
            return calendar.getTime();
        }else {
            return null;
        }
    }

    /**
     * To get the bootloader version
     */

    public String getBootloader(){
        return Build.BOOTLOADER;
    }

    public String getKernalVersion(){
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("uname -a");
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is = null;
        try {
            if(process.waitFor() == 0){
                is = process.getInputStream();
            } else {
                is = process.getErrorStream();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();
            br.close();
            return line;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Error";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public String getLanguage(){
        return Locale.getDefault().getLanguage(); //en
    }
    public String getIso3Language(){
        return Locale.getDefault().getISO3Language(); // eng
    }
    public String getCountry(){
        return Locale.getDefault().getCountry(); // US
    }
    public String getIso3Country(){
        return Locale.getDefault().getISO3Country(); //USA
    }
    public String getDisplayCountry(){
        return Locale.getDefault().getDisplayCountry(); // United States
    }
    public String getDisplayName(){
        return Locale.getDefault().getDisplayName(); // English(United States)
    }
    public String getDisplayLanguage(){
        return Locale.getDefault().getDisplayLanguage(); // English
    }
    public String getLanguageTag(){
        return Locale.getDefault().toLanguageTag(); // en-US
    }

}
package com.oseamiya.deviceinformation;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.util.List;

public class AppsInformation {
    private final Context context;
    public AppsInformation(Context context){
        this.context = context;
    }

    public List<ApplicationInfo> getListOfApplicationInfo(){
        PackageManager packageManager = this.context.getPackageManager();
        List<ApplicationInfo> packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        return packages;
    }

    /**
     * It will return the lists of application packages names in String array
     * To get package name one by one , use for-each method
     * @return packagesNames in String array
     */
    public String[] getAllAppsPackageName(){
        List<ApplicationInfo> infoList = getListOfApplicationInfo();
        String[] packageNames = new String[infoList.size()];
        int i ;
        for(i=0 ; i < infoList.size() ; i++){
            packageNames[i] = infoList.get(i).packageName;
        }
        return packageNames;
    }

    /**
     * It will return the application name from package name
     * @param packageName in String ( For example use for-each from getAllAppsPackageName)
     * @return application label in String
     */
    public String getApplicationName(String packageName){
        PackageManager packageManager = this.context.getPackageManager();
        try {
            return (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName , PackageManager.GET_META_DATA));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "Unknown";
        }
    }

    /**
     * It will return version name from package name
     * @param packageName
     * @return versionName in String
     */
    public String getVersionName(String packageName){
      return getApplicationInfo(packageName , 1);
    }

    /**
     * It will return version code from package name
     * @param packageName
     * @return versionCode in int(in exception it will return 0)
     */
    public int getVersionCode(String packageName){
        return Integer.parseInt(getApplicationInfo(packageName , 2));
    }

    /**
     * It will return application icon image in drawable
     * @param packageName
     * @return Drawable
     */
    public Drawable getApplicationIcon(String packageName){
        try {
            return this.context.getPackageManager().getApplicationIcon(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * It will return first installed time in milliseconds
     * @param packageName
     * @return long
     */
    public long getInstalledTime(String packageName){
        PackageManager packageManager = this.context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName , PackageManager.GET_META_DATA);
            return packageInfo.firstInstallTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long getLastUpdatedTime(String packageName){
        PackageManager packageManager = this.context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName , PackageManager.GET_META_DATA);
            return packageInfo.lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * To get the requested permission names of android application
     * @param packageName
     * @return String[]
     */
    public String[] getRequestedPermissions(String packageName){
        PackageManager packageManager = this.context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName , PackageManager.GET_META_DATA);
            return packageInfo.requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    private String getApplicationInfo(String packageName , int identity){
        PackageManager packageManager = this.context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA);
            if(identity == 1){
                return packageInfo.versionName;
            }else if(identity == 2 ) {
                return Integer.toString(packageInfo.versionCode);
            }else{
                return "0";
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "0";
        }
    }
}

package com.oseamiya.deviceinformation;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

public class MemoryInformation {
    private Context context;
    public void MemoryInformation(Context context){
        this.context = context;
    }
    /**
     * To get the total RAM of the device
     * Remember , these terms :
     * long kb = 1024
     * long mb = kb*1024
     * long Gb = mb * 1024
     * @return total ram in long
     */
    public long getTotalRam(){
        return memoryInfo().totalMem;
    }

    /**
     * To get the total available RAM
     * @return remaining ram in long
     */
    public long getAvailableRam(){
        return memoryInfo().availMem;
    }

    /**
     * To get the used ram of the device
     * @return used ram in long
     */
    public long getUsedRam(){
        return getTotalRam() - getAvailableRam() ;
    }

    /**
     * To check if external memory is available or not
     * @return boolean
     */
    public boolean isExternalMemoryAvailable(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * Get available internal memory size
     * @return long
     */
    public long getAvailableInternalMemorySize(){
        File path = Environment.getDataDirectory();
        StatFs statFs = new StatFs(path.getPath());
        long blockSize =  statFs.getBlockSizeLong();
        long availableBlocks = statFs.getAvailableBlocksLong();
        return availableBlocks * blockSize;
    }

    /**
     * Get total internal memory size
     * @return long
     */
    public long getTotalInternalMemorySize(){
        File path = Environment.getDataDirectory();
        StatFs statFs = new StatFs(path.getPath());
        long blockSize =  statFs.getBlockSizeLong();
        long totalBlocks = statFs.getBlockCountLong();
        return blockSize * totalBlocks ;
    }

    /**
     * Get the used internal memory size
     * @return long
     */
    public long getUsedInternalMemorySize(){
        if(getTotalInternalMemorySize() > getAvailableInternalMemorySize()){
            return getTotalInternalMemorySize() - getAvailableInternalMemorySize();
        }
        else{
            return -11111111;
        }
    }

    /**
     * Get the available external memory size
     * @return long (if memory is not available then it will return -111111111
     */
    public long getTotalExternalStorageSize(){
        if(isExternalMemoryAvailable()){
            File path = Environment.getExternalStorageDirectory();
            StatFs statFs = new StatFs(path.getPath());
            long blockSize =  statFs.getBlockSizeLong();
            long totalBlocks = statFs.getBlockCountLong();
            return blockSize * totalBlocks;
        }else{
            return -1111111111;
        }
    }

    /**
     * Get the total external storage available
     * @return long
     */
    public long getAvailableExternalStorageSize() {
        if (isExternalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs statFs = new StatFs(path.getPath());
            long blockSize = statFs.getBlockSizeLong();
            long availableBlocks = statFs.getAvailableBlocksLong();
            return blockSize * availableBlocks;
        }else{
            return -1111111111;
        }
    }

    /**
     * Get the used external memory space
     * @return long
     */
    public long getUsedExternalStorageSize(){
        if(isExternalMemoryAvailable()){
            if(getTotalExternalStorageSize() > getAvailableExternalStorageSize()){
                return  getTotalExternalStorageSize() - getAvailableExternalStorageSize();
            }else{
                return -1111111111;
            }
        }else{
            return -1111111111;
        }
    }

    private ActivityManager.MemoryInfo memoryInfo(){
        ActivityManager activityManager = (ActivityManager) this.context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo;
    }

}

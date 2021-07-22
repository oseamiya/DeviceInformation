package com.oseamiya.deviceinformation;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;
import androidx.annotation.RequiresApi;

public class DisplayInformation {
    private Context context;

    public DisplayInformation(Context context) {
        this.context = context;

    }

    /**
     * To get the resolution of the device's display's height
     * It does include the navigation bar height
     * If you wanted to remove navigation bar height then subtract display height and navigation height
     * @return display height in pixels
     */
    public int getDisplayHeight() {
        return (this.context.getResources().getDisplayMetrics().heightPixels) + getNavigationBarHeight();
    }

    /**
     * To get the resolution of the device's  display's width
     * @return display width in pixels
     */
    public int getDisplayWidth() {
        return this.context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * In some cases , you might have to include or exclude the navigation bar height or width
     * So this method can be used to get the navigation bar height
     * @return navigationBarHeight
     */
    public int getNavigationBarHeight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) this.context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int usableHeight = metrics.heightPixels;
            windowManager.getDefaultDisplay().getRealMetrics(metrics);
            int realHeight = metrics.heightPixels;
            if (realHeight > usableHeight) {
                return realHeight - usableHeight;
            } else {
                return 0;
            }
        }
        return 0;
    }

    /**
     * To get the physical size of the device
     * @return physicalSize in double
     */
    public double getPhysicalSize() {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) this.context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(getDisplayWidth() / dm.xdpi, 2);
        double y = Math.pow(getDisplayHeight() / dm.ydpi, 2);
        return Math.sqrt(x + y);
    }

    /**
     * To get the font scale of the display
     * @return font scale in float
     */
    public float getFontScale() {
        return this.context.getResources().getConfiguration().fontScale;
    }

    /**
     * To get the device's display's refresh rate in frame per second
     * @return display refresh rate in float
     */
    public float getRefreshRate() {
        Display display = ((WindowManager) this.context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        return display.getRefreshRate();
    }

    /**
     * To get the device's rotation angle
     * Portrait ----- 1
     * Landscape ---- 2
     * Undefined ---- 0
     * @return orientation in the int
     */
    public int getOrientation() {
        return this.context.getResources().getConfiguration().orientation;
    }

    /**
     * To get rotation of the device
     * This will give orientation like normal and reverse
     * @return rotation angle in integer
     */
    public int getRotation() {
        WindowManager windowManager = (WindowManager) this.context.getSystemService(Context.WINDOW_SERVICE);
        int angle = 0;
        int rotation = windowManager.getDefaultDisplay().getRotation();
        switch (rotation) {
            case Surface.ROTATION_90:
                angle = 90; //-90
                break;
            case Surface.ROTATION_180:
                angle = 180;
                break;
            case Surface.ROTATION_270:
                angle = 270; //+90
                break;
            default:
                angle = 0;
                break;

        }
        return angle;
    }

    /**
     * To check Hdr Capabilities of the screen
     * @return hdr capabilities in boolean
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean isHdrCapable() {
        Configuration configuration = new Configuration();
        configuration = this.context.getResources().getConfiguration();
        return configuration.isScreenHdr();
    }

    /**
     * To check if night mode is active or not
     * @return status of night mode in boolean
     */
    @RequiresApi(api = Build.VERSION_CODES.R)
    public boolean isNightModeActive() {
        Configuration configuration = new Configuration();
        configuration = this.context.getResources().getConfiguration();
        return configuration.isNightModeActive();
    }

    /**
     * To check if Screen is rounded or not
     * @return status of the rounded screen in boolean
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isScreenRound() {
        Configuration configuration = new Configuration();
        configuration = this.context.getResources().getConfiguration();
        return configuration.isScreenRound();
    }

    /**
     * To check if screen wide color gamut or not
     * @return boolean
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean isScreenWideColorGamut() {
        Configuration configuration = new Configuration();
        configuration = this.context.getResources().getConfiguration();
        return configuration.isScreenWideColorGamut();
    }

    /**
     * To check if automatic brightness mode is active or not
     * @return boolean (if autoMode then true else false)
     */
    public boolean isBrightnessAutoMode() {
        try {
            int brightnesses = Settings.System.getInt(this.context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
            if (brightnesses == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * To get the active brightness level of the screen
     * There might not be any ways to get the brightness at auto-mode so(From StackOverFlow),
     * what we can do is triggerBrightnessMode to Manual and then measure screen brightness and
     * again change them to automatic
     * @return brightness level from 0 to 225
     */
    public int getBrightnessLevel() {
        ContentResolver contentResolver = this.context.getContentResolver();
        int a = 0;
        int mode = 0;
        try {
            mode = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        if (mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
            a = 1;
            Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        }
        int brightnessLevel = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, 125);
        if (a == 1) {
            Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
        }
        return brightnessLevel;
    }

    /**
     * To trigger brightness mode (automatic to manual and vice-versa)
     * @return void
     */
    public void triggerBrightnessMode() {
        ContentResolver contentResolver = this.context.getContentResolver();
        try {
            int mode = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE);
            if (mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            } else {
                Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * To get the screen timeout value of the android device
     * @return int (will return 0 if exception is caught)
     */
    public int getScreenTimeout() {
        try {
            return Settings.System.getInt(this.context.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
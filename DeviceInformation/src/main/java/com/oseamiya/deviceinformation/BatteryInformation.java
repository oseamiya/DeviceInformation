package com.oseamiya.deviceinformation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;

public class BatteryInformation {
    private final Context context;

    public BatteryInformation(Context context) {
        this.context = context;
    }

    private Intent getBatteryStatus() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        return context.registerReceiver(null, filter);
    }

    /**
     * Get battery percentage of the device
     * @return it will return the battery percentage (by @oseamiya)
     */
    public final int getPercentage() {
        Intent status = getBatteryStatus();
        int batteryPercentage = 0;
        if (status != null) {
            int level = status.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = status.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            batteryPercentage = (int) ((level / (float) scale) * 100);
        }
        return batteryPercentage;
    }

    /**
     * To check whether device is charging or not
     * if charging then return true else false
     * It returns boolean.
     * Added wireless support , that was added in Version more than JellyBean(16)
     * Also for AndroidM and greater , Battery Status can be known from getSystemService()
     * @return if battery is charging or not
     */
    public boolean isCharging() {
        boolean isCharging = false;
        Intent intent = getBatteryStatus();
        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        isCharging = plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            isCharging = isCharging || plugged == BatteryManager.BATTERY_PLUGGED_WIRELESS;
        }
        return isCharging;
    }

    /**
     * To get the battery health
     * Statuses :
     *
     * BatteryHealthCold ---- 7
     * BatteryHealthDead ---- 4
     * BatteryHealthGood ---- 2
     * BatteryHealthOverheat ---- 3
     * BatteryHealthOverVoltage ---- 5
     * BatteryHealthUnknown ---- 1
     *
     * @return int ; see above to find the status of the battery
     */

    public final int getHealth() {
        Intent intent = getBatteryStatus();
        int health = 0;
        if (intent != null) {
            health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
        }
        return health;
    }

    /**
     * Get the technology used in the battery
     * @return the technology String
     */

    public String getTechnology() {
        Intent intent = getBatteryStatus();
        return intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
    }

    /**
     * To get  the battery temperature in fahrenheit
     * @return the battery temperature
     */
    public float getBatteryTemperature() {
        float temperature = 0.0f;
        Intent intent = getBatteryStatus();
        if (intent != null) {
            temperature = (float) (intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10);
        }
        return temperature;
    }

    /**
     * To get the battery voltage
     * @return the battery voltage in int
     */

    public int getBatteryVoltage() {
        int voltage = 0;
        Intent intent = getBatteryStatus();
        if (intent != null) {
            voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
        }
        return voltage;
    }
    /**
     * To get whether battery is present in the device or not
     * @return boolean whether battery is present or not
     */

    public boolean isBatteryAvailable() {
        return getBatteryStatus().getExtras() != null && getBatteryStatus().getExtras().getBoolean(BatteryManager.EXTRA_PRESENT);
    }

    /**
     * To get the charging source of the battery
     * @return the charging source
     */

    public String getChargingSource() {
        Intent intent = getBatteryStatus();
        int chargingSource = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);

        switch (chargingSource) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                return "AC";
            case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                return "WIRELESS";
            case BatteryManager.BATTERY_PLUGGED_USB:
                return "USB";
            default:
                return "UNKNOWN";
        }
    }

    /**
     * To get the battery capacity of android
     * @return double
     */
    @SuppressLint("PrivateApi")
    public double getBatteryCapacity() {
        Object powerProfile;
        double batteryCapacity = 0;
        final String POWER_PROFILE_CLASS = "com.android.internal.os.PowerProfile";

        try {
            powerProfile = Class.forName(POWER_PROFILE_CLASS)
                    .getConstructor(Context.class)
                    .newInstance(this.context);
            batteryCapacity = (double) Class.forName(POWER_PROFILE_CLASS)
                    .getMethod("getBatteryCapacity")
                    .invoke(powerProfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return batteryCapacity;
    }
}

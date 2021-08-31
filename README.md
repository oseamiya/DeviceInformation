<div align="center">
  <img src="assests/DeviceInformationLogo.png" height="128" />
</div>
<h1 align="center">DeviceInformation</h1>
<div align="center">
<strong> An android library to get the device information in an easy way</strong>
<p>The library is built for simplicity to eliminates most boilerplate code for dealing with device information,and  also provides an easy and simple API to retrieve the device informations</p>
</div>
<div align="center">
<img src="https://img.shields.io/badge/oseamiya-Device%20Information-green">
<img src="https://img.shields.io/github/issues/oseamiya/DeviceInformation" >
<img src = "https://img.shields.io/badge/API-19%2B-red.svg?style=plastic">
<img src="https://img.shields.io/github/forks/oseamiya/DeviceInformation">
<img src="https://img.shields.io/github/stars/oseamiya/DeviceInformation">
<div align="center">
 <sub>Built with ❤︎ by <a href="https:\\github.com\oseamiya">Oseamiya</a></sub>
</div></div>

<h2>How to integrate library in your application?</h2>
<h3> Gradle Dependency: </h3>

```Gradle
dependencies {
implementation 'com.github.oseamiya:deviceinformation:1.0.0'
}

````

<h3> Maven Dependency : </h3>

````xml

<dependency>
	    <groupId>com.github.oseamiya</groupId>
	    <artifactId>DeviceInformation</artifactId>
	    <version>1.0.0</version>
</dependency>
  
````
<h3> Download AAR and use it</h3>
<a href="https://github.com/oseamiya/DeviceInformation/raw/main/assests/DeviceInformation-release.aar">Download Latest Version</a>

<b>Usages </b>
* DeviceInformation
* DisplayInformation
* AppsInformation
* BatteryInformation
* CameraInformation
* CpuInformation
* LocationInformation
* MemoryInformation
* NetworkInformation
* SensorInformation
* SystemInformation



<h2> DeviceInformation </h2>

```Java
DeviceInformation displayInformation = new DisplayInformation(/*Context*/ this);
String modelName = displayInformation.getModelName();
```
| Method                | Description                                            | Return Type | Params     | ParamsType |
|-----------------------|--------------------------------------------------------|-------------|------------|------------|
| `getModelName`        | To get the model name of android device                | String      |            |            |
| `getDeviceName`       | To get the device name of the android device           | String      |            |            |
| `getManafacturerName` | To get the manafacturer name of the android device     | String      |            |            |
| `getBoardName`        | To get the board name                                  | String      |            |            |
| `getHardwareName`     | To get the hardware name                               | String      |            |            |
| `getBrandName`        | To get the brand name                                  | String      |            |            |
| `getDeviceId`         | To get the Android Id of the device                    | String      |            |            |
| `getBuildFingerPrint` | To get the fingerprint of the device                   | String      |            |            |
| `getDeviceType`       | To get whether the device is GSM, CDMA or SIP          | String      |            |            |
| `isUsbHostSupported`  | Check if usb host is supported or not                  | boolean     |            |            |
| `getNumberOfSimSlot`  | To get the number of sim slots available in the device | int         |            |            |
| `getImei`             | To get the imei number of particular sim slot          | String      | slotNumber | int        |
| `getBuildTime`        | To get the build time of the device                    | String      |            |            |
| `getProductName`      | To get the product name of the device                  | String      |            |            |
| `getVersionCode`      | To get the version code of the device                  | String      |            |            |
| `getRadioVersion`     | To get the radio version of the device                 | String      |            |            |
| `getDisplayVersion`   | To get the display version of the device               | String      |            |            |
| `getDisplayVersion`   | To get the display version of the device               | String      |            |            |
| `getHost`             | To get the host of the device                          | String      |            |            |
| `getBuildUser`        | To get the build user of the device                    | String      |            |            |
| `getSerial`           | To get the serial id  of the device                    | String      |            |            |
| `isRooted`            | To check if device is rooted or not                    | boolean     |            |            |



<h2>
DisplayInformation
</h2>
  
```Java
  DisplayInformation displayInformation = new DisplayInformation(/*Context*/ this);
  int displayHeight = displayinformation.getDisplayHeight();
  ```
  
| Method                   | Description                                                                            | Return Type | Params | ParamsType |
|--------------------------|----------------------------------------------------------------------------------------|-------------|--------|------------|
| `getDisplayHeight`       | To get the resolution of the device height including navigation bar in pixels          | int         |        |            |
| `getDisplayWidth`        | To get the resolution of the device width in pixels                                    | int         |        |            |
| `getNavigationBarHeight` | To get the navigation bar height (if there is no navigation height , it will return 0) | int         |        |            |
| `getPhysicalSize`        | To get the physical size of the device                                                 | double      |        |            |
| `getFontScale`           | To get the font scale of the display                                                   | float       |        |            |
| `getRefreshRate`         | To get the refresh rate of the display                                                 | float       |        |            |
| `getOrientation`         | To get the display's orientation (1--Portrait , 2--Landscape , 0--Undefined)           | int         |        |            |
| `getRotation`            | To get the rotation angle of the display                                               | int         |        |            |
| `isHdrCapable`           | To check if display supports HDR  or not                                               | boolean     |        |            |
| `isNightModeActive`      | To check if night mode is active or not                                                | boolean     |        |            |
| `isScreenRound`          | To check if screen is curved or not                                                    | boolean     |        |            |
| `isScreenWideColorGamut` | To check if screen is wide color gamut or not                                          | boolean     |        |            |
| `isBrightnessAutoMode`   | To check if auto mode of brightness is active or not                                   | boolean     |        |            |
| `getBrightnessLevel`     | To get the brightness level of the screen from 0 to 225                                | int         |        |            |
| `triggerBrightnessMode`  | Trigger the brightness mode from auto to manual or vice-versa                          | void        |        |            |
| `getScreenTimeout`       | To get the screen time-out of the screen(return 0 if exception is caught)              | int         |        |            |


<h2>
 AppsInformation
</h2>
  
```Java
AppsInformation appsInformation = new AppsInformation(/*Context*/ this);
String[] allAppsPackages = appsInformation.getAllAppsPackageName();
  
```
  
| Method                    | Description                                                           | Return Type | Params      | ParamsType |
|---------------------------|-----------------------------------------------------------------------|-------------|-------------|------------|
| `getAllAppsPackageName`   | To get package name of all the application in the device              | String[]    |             |            |
| `getApplicationName`      | To get the application name from application package                  | String      | packageName | String     |
| `getVersionName`          | To get the version of the application                                 | String      | packageName | String     |
| `getVersionCode`          | To get the version code of the application                            | int         | packageName | String     |
| `getApplicationIcon`      | To get the application icon from the package name                     | Drawable    | packageName | String     |
| `getInstalledTime`        | To get the application first installed time in the long(milliseconds) | long        | packageName | String     |
| `getLastUpdatedTime`      | To get the application last updated time in the long(milliseconds)    | long        | packageName | String     |
| `getRequestedPermissions` | To get the requested permissions of the application                   | String[]    | packageName | String     |


<h2>
BatteryInformation
</h2>
  
```Java
BatteryInformation batteryInformation = new BatteryInformation(/*Context*/ this);
int batteryPercentage = batteryInformation.getBatteryPercentage();

```
  
| Method                  | Description                                                                                                                                                                                                                                                                       | Return Type | Params | ParamsType |
|-------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------|--------|------------|
| `getPercentage`         | To get the battery percentage of the device                                                                                                                                                                                                                                       | int         |        |            |
| `isCharging`            | To check if battery is charging or not                                                                                                                                                                                                                                            | boolean     |        |            |
| `getHealth`             | To get the health of the device's battery<p><a href = "https://github.com/oseamiya/DeviceInformation/blob/0ddeafcfeb20f19715ef57e77d762bcf04504c53/DeviceInformation/src/main/java/com/oseamiya/deviceinformation/BatteryInformation.java#L65" > See More For Constants Value</a> | int         |        |            |
| `getTechnology`         | To get the technology used in the battery(Example Li-On)                                                                                                                                                                                                                          | String      |        |            |
| `getBatteryTemperature` | To get the battery temperature of the device                                                                                                                                                                                                                                      | float       |        |            |
| `getBatteryVoltage`     | To get the battery's voltage                                                                                                                                                                                                                                                      | int         |        |            |
| `isBatteryAvailable`    | To check if battery is available on the device or not                                                                                                                                                                                                                             | boolean     |        |            |
| `getChargingSource`     | To get the charging source of the battery                                                                                                                                                                                                                                         | String      |        |            |
| `getBatteryCapacity`    | To get the capacity of the device's battery                                                                                                                                                                                                                                       | double      |        |            |


<h2> CameraInformation</h2>

```Java
CameraInformation cameraInformation = new CameraInformation(/*Context*/ this);
int numberofCameras = cameraInformation.getNumberOfCameras();

```

| Method                                | Description                                                                                                                                                                                                                                                                               | Return Type | Params   | Params Type |
|---------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------|----------|-------------|
| `isCameraAvailable`                   | To check if camera is available or not                                                                                                                                                                                                                                                    | boolean     |          |             |
| `isFlashAvailable`                    | To check if flash is available or not                                                                                                                                                                                                                                                     | boolean     |          |             |
| `getCameraIds`                        | To get camera id of all the cameras in device                                                                                                                                                                                                                                             | String[]    |          |             |
| `getNumberOfCameras`                  | To get the number of cameras available in the device                                                                                                                                                                                                                                      | int         |          |             |
| `getAntibandingModes`                 | To get all the anti-banding modes of the camera. <p><a href="https://github.com/oseamiya/DeviceInformation/blob/0ddeafcfeb20f19715ef57e77d762bcf04504c53/DeviceInformation/src/main/java/com/oseamiya/deviceinformation/CameraInformation.java#L54">See More About Constants </a>         | int[]       | cameraId | String      |
| `getAberrationModes`                  | To get all the aberration modes of the camera. <p><a href="https://github.com/oseamiya/DeviceInformation/blob/0ddeafcfeb20f19715ef57e77d762bcf04504c53/DeviceInformation/src/main/java/com/oseamiya/deviceinformation/CameraInformation.java#L67"> See More About Constants</a>           | int[]       | cameraId | String      |
| `getAutoExposureModes`                | To get all the auto exposure modes of the camera. <p><a href="https://github.com/oseamiya/DeviceInformation/blob/0ddeafcfeb20f19715ef57e77d762bcf04504c53/DeviceInformation/src/main/java/com/oseamiya/deviceinformation/CameraInformation.java#L89">See More About Constants </a>        | int[]       | cameraId | String      |
| `getAutoFocusModes`                   | To get all the auto focus modes of the camera. <p><a href="https://github.com/oseamiya/DeviceInformation/blob/0ddeafcfeb20f19715ef57e77d762bcf04504c53/DeviceInformation/src/main/java/com/oseamiya/deviceinformation/CameraInformation.java#L104"> See More About Constants</a>          | int[]       | cameraId | String      |
| `getEffects`                          | To get all the effects available on the camera. <p><a href="https://github.com/oseamiya/DeviceInformation/blob/0ddeafcfeb20f19715ef57e77d762bcf04504c53/DeviceInformation/src/main/java/com/oseamiya/deviceinformation/CameraInformation.java#L120"> See More About Constants </a>        | int[]       | cameraId | String      |
| `getWhiteBalanceModes`                | To get all the white balance modes of the camera.<p><a href="https://github.com/oseamiya/DeviceInformation/blob/0ddeafcfeb20f19715ef57e77d762bcf04504c53/DeviceInformation/src/main/java/com/oseamiya/deviceinformation/CameraInformation.java#L124">See More About Constants</a>         | int[]       | cameraId | String      |
| `getVideoStabilizationModes`          | To get all the video stabilization modes of the camera. <p> <a href="https://github.com/oseamiya/DeviceInformation/blob/0ddeafcfeb20f19715ef57e77d762bcf04504c53/DeviceInformation/src/main/java/com/oseamiya/deviceinformation/CameraInformation.java#L124">See More About Constants</a> | int[]       | cameraId | String      |
| `getMaximumAutoFocusRegions`          | To get the maximum region of the metering regions that can be used by auto-focus routine                                                                                                                                                                                                  | int         | cameraId | String      |
| `getMaximumAutoExposureRegions`       | To get the maximum region of the metering regions that can be used by auto-exposure routine                                                                                                                                                                                               | int         | cameraId | String      |
| `getMaximumAutoWhiteBalancingRegions` | To get the maximum region of the metering regions that can be used by auto-white-balancing routing                                                                                                                                                                                        |             |          |             |


<h2> CPUInformation</h2>

```Java
CpuInformation cpuInformation = new CpuInformation(/*Context*/ this);
int numberOfCpuCores = cpuInformation.getNumberOfCores();

```

| Method                   | Description                                        | Return Type | Params | Params Type |
|--------------------------|----------------------------------------------------|-------------|--------|-------------|
| `getNumberOfCores`       | To get the total number of cores of android cpu    | int         |        |             |
| `getSupportedABIs`       | To get all the supported ABIS of CPU               | String[]    |        |             |
| `getMinimumFrequency`    | To get the minimum cpu frequency in kilo-hertz     | int         |        |             |
| `getMaximumFrequency`    | To get the maximum cpu frequency in kilo-hertz     | int         |        |             |
| `getBogoMips`            | To get the crude measurement of CPU Speed          | float       |        |             |
| `getClockSpeed`          | To get the clock speed of CPU                      | int         |        |             |
| `is64Bit`                | To check whether device cpu is 32bits or 64bits    | boolean     |        |             |
| `getMinScalingFrequency` | To get the minimum scaling frequency of CPU        | int         |        |             |
| `getMaxScalingFrequency` | To get the maximum scaling frequency of CPU        | int         |        |             |
| `getGPURenderer`         | To get the GPU Renderer                            | String      |        |             |
| `getGPUVersion`          | To get the GPU Version                             | String      |        |             |
| `getGPUExtension`        | To get the GPU Extensions                          | String      |        |             |
| `getGPUVendor`           | To get the GPU Vendor                              | String      |        |             |
| `isGPUSupported`         | To check whether GPU is supported on device or not | String      |        |             |
| `getCPUInformation`      | To get all the CPU Informations at once            | String      |        |             |

<h2> LocationInformation</h2>

```Java
LocationInformation locationInformation = new LocationInformation(/*Context*/ this);
double latitude = locationInformation.getCurrentLatitude();

```

| Method                      | Description                                           | Return Type | Params               | ParamsType      |
|-----------------------------|-------------------------------------------------------|-------------|----------------------|-----------------|
| `getCurrentLatitude`        | To get the current latitude of your location          | double      |                      |                 |
| `getCurrentLongitude`       | To get the current longitude of your location         | double      |                      |                 |
| `getStreetAddress`          | To get the street address from latitude and longitude | String      | latitude , longitude | double , double |
| `getCity`                   | To get the city name from latitude and longitude      | String      | latitude , longitude | double , double |
| `getCountryName`            | To get the country name from latitude and longitude   | String      | latitude , longitude | double , double |
| `getPostalCode`             | To get the postal code from latitude and longitude    | String      | latitude , longitude | double , double |
| `IsLocationServicesEnabled` | To check if location services is enabled or not       | boolean     |                      |                 |


<h2> MemoryInformation </h2>

```Java
MemoryInformation memoryInformation = new MemoryInformation(/*Context*/ this);
long totalRam = memoryInformation.getTotalRam();

```

| Method                            | Description                                                               | Return Type | Params | Params Type |
|-----------------------------------|---------------------------------------------------------------------------|-------------|--------|-------------|
| `getTotalRam`                     | To get the total ram in the device                                        | long        |        |             |
| `getAvailableRam`                 | To get the total free ram available in the device                         | long        |        |             |
| `getUsedRam`                      | To get the total used ram in the device                                   | long        |        |             |
| `isExternalMemoryAvailable`       | To check whether there is present of external memory or not in the device | boolean     |        |             |
| `getAvailableInternalMemorySize`  | To get the available size of internal memory                              | long        |        |             |
| `getTotalInternalMemorySize`      | To get the total size of internal memory                                  | long        |        |             |
| `getUsedInternalMemorySize`       | To get the total used internal memory size                                | long        |        |             |
| `getTotalExternalStorageSize`     | To get the total external memory size                                     | long        |        |             |
| `getAvailableExternalStorageSize` | To get the available external memory size                                 | long        |        |             |
| `getUsedExternalStorageSize`      | To get the used external storage size                                     | long        |        |             |


<h2>NetworkInformation</h2>

```Java
NetworkInformation networkInformation = new NetworkInformation(/*Context*/ this);
String ipAddress = networkInformation.getIpAddress(true);

```

| Method                  | Description                                | Return Type | Params  | Params Type |
|-------------------------|--------------------------------------------|-------------|---------|-------------|
| `getIpAddress`          | To get the ip-adress of the network        | String      | useIPv4 | boolean     |
| `getServers`            | To get all the DNS servers within network  | String[]    |         |             |
| `isADBDebuggingEnabled` | To check if ADBDebugging is enabled or not | boolean     |         |             |
| `isVpnConnection`       | To check if VPN is enabled or not          | boolean     |         |             |



<h2>SensorInformation</h2>

```Java
SensorInformation sensorInformation = new SensorInformation(/*Context*/ this);
List<Sensor> sensorList = sensorInformation.getSensorList();

```
| Method                    | Description                                            | Return Type  | Params | Params Type |
|---------------------------|--------------------------------------------------------|--------------|--------|-------------|
| `getSensorList`           | To get all the sensor's lists available in the device  | List<Sensor> |        |             |
| `getTotalNumberOfSensors` | To get the total number of sensor in the device        | int          |        |             |
| `getSensorVendor`         | To get the sensor's vendor name                        | String       | sensor | Sensor      |
| `getSensorVersion`        | To get the sensor's version name                       | int          | sensor | Sensor      |
| `getSensorPower`          | To get the sensor's power                              | float        | sensor | Sensor      |
| `getSensorResolution`     | To get the sensor's resolution                         | float        | sensor | Sensor      |
| `getSensorMaximumRange`   | To get the sensor's maximum range                      | float        | sensor | Sensor      |
| `getSensorName`           | To parse sensor to String sensor name                  | String       | sensor | Sensor      |


<h2> SystemInformation </h2>

```Java
SystemInformation systemInformation = new SystemInformation(/*Context*/ this);
int apiLevel = systemInformation.getApiLevel();

```

| Method                     | Description                                         | Return Type | Params | Params Type |
|----------------------------|-----------------------------------------------------|-------------|--------|-------------|
| `getApiLevel`              | To get the api level of android                     | int         |        |             |
| `getVersionName`           | To get the name of android's version                | String      |        |             |
| `getSecurityPatchDate`     | To get the security patch date of system            | String      |        |             |
| `getAndroidSdkReleaseDate` | To get the android sdk release date                 | Date        |        |             |
| `getBootloader`            | To get the bootloader version of the android        | String      |        |             |
| `getKernalVersion`         | To get the kernal version of the android            | String      |        |             |
| `getLanguage`              | To get the language active on device //en           | String      |        |             |
| `getIso3Language`          | To get the iso3 language //eng                      | String      |        |             |
| `getDisplayCountry`        | To get the country active on device //United States | String      |        |             |
| `getCountry`               | To get the country //US                             | String      |        |             |
| `getIso3Country`           | To get iso3 of country // USA                       | String      |        |             |
| `getDisplayName`           | TO get display name // English(United States)       | String      |        |             |
| `getDisplayLanguage`       | To get the display language // English              | String      |        |             |
| `getLanguageTag`           | To get the language tag // en-US                    | String      |        |             |
  
  
# Created & Maintained By :
  Oseamiya 
  * <a href="https://www.instagram.com/oseamiya/" > Instagram </a>

package utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class ThreadManager {
    private static ThreadLocal<AndroidDriver<WebElement>> androidDriver = new ThreadLocal<>();
    private static ThreadLocal<IOSDriver<WebElement>>  iOSDriver = new ThreadLocal<>();
    private static ThreadLocal<String> deviceUdid = new ThreadLocal<>();
    private static ThreadLocal<Integer> appiumPort = new ThreadLocal<>();
    private static ThreadLocal<Integer> systemPort = new ThreadLocal<>();
    private static ThreadLocal<AppiumServiceBuilder> appiumServiceBuilder = new ThreadLocal<AppiumServiceBuilder>();
    private static ThreadLocal<AppiumDriverLocalService> appiumLocalService = new ThreadLocal<AppiumDriverLocalService>();
    private static ThreadLocal<String> deviceName = new ThreadLocal<>();
    private static ThreadLocal<String> platformVersion = new ThreadLocal<>();
    private static ThreadLocal<Integer> wdaLocalPort = new ThreadLocal<>();

    /**
     * Get iOS DeviceName
     * @return DeviceName
     */
    public static synchronized String getDeviceName() {
        return (String) deviceName.get();
    }

    /**
     * Get iOS PlatformVersion
     * @return PlatformVersion
     */
    public static synchronized String getPlatformVersion() {
        return (String) platformVersion.get();
    }

    /**
     * Get iOS wdaLocalPort
     * @return  wdaLocalPort
     */
    public static synchronized int getWdaLocalPort() {
        return wdaLocalPort.get();
    }

    /**
     *  Set iOS DeviceName
     * @param devicename DeviceName
     */
    public static synchronized void setDeviceName(String devicename) {
        deviceName.set(devicename);
    }

    /**
     * Set iOS platformVersion
     * @param platformversion platformVersion
     */
    public static synchronized void  setPlatformVersion(String platformversion) {
        platformVersion.set(platformversion);
    }

    /**
     * Set iOS WdaLcalPort
     * @param wdalocalport WdaLcalPort
     */

    public static synchronized void setWdaLocalPort(String wdalocalport) {
        wdaLocalPort.set(Integer.parseInt(wdalocalport));
    }

    /**
     * Set androidDriver
     * @param driver androidDriver
     */
    public static synchronized  void setDriver(AppiumDriver<WebElement> driver) {
        if(Constants.MOBILE_OS== Platform.ANDROID)
        {
            androidDriver.set((AndroidDriver<WebElement>)driver);
        }
        else
        {
            iOSDriver.set((IOSDriver<WebElement>)driver);
        }
    }

    /**
     * Get androidDriver
     * @return androidDriver
     */
    public static synchronized AppiumDriver<WebElement> getDriver() {

        if(Constants.MOBILE_OS==Platform.ANDROID)
        {
            return androidDriver.get();
        }
        else
        {
            return iOSDriver.get();
        }
    }

    /**
     * Set deviceUdid
     * @param udid deviceUdid
     */
    public static synchronized void setDeviceUdid(String udid) {
        deviceUdid.set(udid);
    }

    /**
     * Get deviceUdid
     * @return deviceUdid
     */
    public static synchronized String getDeviceUdid() {
        return (String) deviceUdid.get();
    }

    /**
     * Set appiumPort
     * @param portValue appiumPort
     */
    public static synchronized void setAppiumPort(String portValue) {
        appiumPort.set(Integer.parseInt(portValue));
    }

    /**
     * Get appiumPort
     * @return appiumPort
     */
    public static synchronized int getAppiumPort() {
        return (int) appiumPort.get();
    }

    /**
     * Set systemPort
     * @param portValue systemPort
     */
    public static synchronized void setSystemPort(String portValue) {
        systemPort.set(Integer.parseInt(portValue));
    }

    /**
     * Get systemPort
     * @return systemPort
     */
    public static synchronized int getSystemPort() {
        return (int) systemPort.get();
    }

    /**
     * Set appiumServiceBuilder
     * @param service appiumServiceBuilder
     */
    public static synchronized void setAppiumServiceBuilder(AppiumServiceBuilder service) {
        appiumServiceBuilder.set(service);
    }

    /**
     * Get appiumServiceBuilder
     * @return appiumServiceBuilder
     */
    public synchronized static AppiumServiceBuilder getAppiumServiceBuilder() {
        return appiumServiceBuilder.get();
    }

    /**
     * Set appiumLocalService
     * @param service appiumLocalService
     */
    public static synchronized void setAppiumLocalService(AppiumDriverLocalService service) {
        appiumLocalService.set(service);
    }

    /**
     * Get appiumLocalService
     * @return appiumLocalService
     */
    public static synchronized AppiumDriverLocalService getAppiumLocalService() {
        return appiumLocalService.get();
    }

    /**
     * Set deviceUdid,appiumPort,systemPort
     * @param deviceUdid device udid
     * @param appiumPort appium port
     * @param systemPort system port
     */
    public static synchronized void setAllForAndroid(String deviceUdid, String appiumPort, String systemPort)
    {
        setDeviceUdid(deviceUdid);
        setAppiumPort(appiumPort);
        setSystemPort(systemPort);
    }

    /**
     * Set deviceName,platformVersion,wdaLocalPort,appiumPort
     * @param deviceName device name
     * @param platformVersion platform version
     * @param wdaLocalPort wdaLocal port
     * @param appiumPort appium port
     */
    public static synchronized void setAllForiOS(String deviceName, String platformVersion, String wdaLocalPort, String appiumPort)
    {
        setDeviceName(deviceName);
        setPlatformVersion(platformVersion);
        setWdaLocalPort(wdaLocalPort);
        setAppiumPort(appiumPort);
    }

}

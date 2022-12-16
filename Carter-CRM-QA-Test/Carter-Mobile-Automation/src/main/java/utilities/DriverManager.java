package utilities;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DriverManager 
{

	public void capabilities() throws IOException
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();

		if(Constants.MOBILE_OS==Platform.ANDROID){
			capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Constants.PLATFORM_VERSION);
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Constants.DEVICE_NAME);
			capabilities.setCapability(MobileCapabilityType.APP, Constants.CARTER_APK_FULLPATH);
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, Constants.NEW_COMMAND_TIMEOUT);
			capabilities.setCapability("uiautomator2ServerLaunchTimeout", Constants.SERVER_LUANCH_TIMEOUT);
			capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);

			capabilities.setCapability("systemPort", ThreadManager.getSystemPort());
			capabilities.setCapability(MobileCapabilityType.UDID, ThreadManager.getDeviceUdid());
			ThreadManager.setDriver( new AndroidDriver<>(ThreadManager.getAppiumLocalService().getUrl(), capabilities));
		}else
		{
			capabilities.setCapability("platformName", Platform.IOS);
			capabilities.setCapability("platformVersion", ThreadManager.getPlatformVersion());
			capabilities.setCapability("deviceName", ThreadManager.getDeviceName());
			capabilities.setCapability("wdaLocalPort", ThreadManager.getWdaLocalPort());
			capabilities.setCapability("autoAcceptAlerts", true);
			capabilities.setCapability("app", Constants.CARTER_APP_FULLPATH);
			capabilities.setCapability("locationServicesEnabled", "true");
			capabilities.setCapability("locationServicesAuthorized", "true");
			ThreadManager.setDriver( new IOSDriver<>(ThreadManager.getAppiumLocalService().getUrl(), capabilities));
		}

		ThreadManager.getDriver().manage().timeouts().implicitlyWait(Constants.MINIMUM_WEBDRIVER_WAIT_DURATION, TimeUnit.SECONDS);
	}
	
	/**
	 * Quit driver instance
	 */
	public void tearDown()
	{
		if(ThreadManager.getDriver()!=null)
		{
			ThreadManager.getDriver().quit();
		}
	}


}

package utilities;

import org.openqa.selenium.Platform;

import java.io.File;

public class Constants
{
	static JavaHelpers JavaHelpers;

	public static final String PROPERTYFILE="src/main/resources/Constants.properties";

	public static final String ENV = JavaHelpers.setSystemVariable(PROPERTYFILE, "MobileOperatingSystem");


	//Test Run Platform
	public static final String PLATFORM_VERSION = JavaHelpers.setSystemVariable(PROPERTYFILE, "PlatformVersion");
	public static final String PLATFORM = JavaHelpers.setSystemVariable(PROPERTYFILE ,"Platform");
	
	//Device & Application details
	public static final String DEVICE_NAME= JavaHelpers.getPropertyValue(PROPERTYFILE,"DeviceName");
	public static final String CARTER_APK_FULLNAME = JavaHelpers.getPropertyValue(PROPERTYFILE, "carterApkFullName");

	public static final String CARTER_APP_FULLNAME = JavaHelpers.getPropertyValue(PROPERTYFILE, "carterAppFullName");
	public static final String CARTER_APK_FULLPATH =new File("src/main/resources/apk", CARTER_APK_FULLNAME).getAbsolutePath();

	public static final String CARTER_APP_FULLPATH =new File("src/main/resources/app", CARTER_APP_FULLNAME).getAbsolutePath();

	public static final String CARTER_APP_WAIT_ACTIVITY = JavaHelpers.getPropertyValue(PROPERTYFILE,"App_Activity");

	public static final String CARTER_APK_PACKAGE = JavaHelpers.getPropertyValue(PROPERTYFILE,"App_Package");

	//Appium constants
	//Appium constants
	public static final int WEBDRIVER_WAIT_DURATION = Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTYFILE, "WebDriverWaitDuration"));
	public static final int MINIMUM_WEBDRIVER_WAIT_DURATION= Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTYFILE,"MinimumWebDriverWaitDuration"));
	public static final int NEW_COMMAND_TIMEOUT= Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTYFILE,"NewCommandTimeout"));
	public static final int SERVER_LUANCH_TIMEOUT= Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTYFILE,"ServerLaunchTimeout"));
	public static final String APPIUM_SERVER1= JavaHelpers.getPropertyValue(PROPERTYFILE,"AppiumServer1");
	
	//Other
	public static final String SCREENSHOT_LOCATION= JavaHelpers.getPropertyValue(PROPERTYFILE,"ScreenshotLocation");
	public static final Platform MOBILE_OS = setMobileOS();
	//public static final String SERVER_KEY = ;

	//ChromeDriver path
	//public static final String CHROMEDRIVER_FULLPATH=new File("src/main/resources/drivers","chromedriver.exe").getAbsolutePath();


	//Set Mobile OS
	private static Platform setMobileOS() {
		String os = JavaHelpers.setSystemVariable(PROPERTYFILE, "MobileOperatingSystem");
		if (os.equalsIgnoreCase("android")) {
			return Platform.ANDROID;
		}
		else if (os.equalsIgnoreCase("iOS"))
		{
			return Platform.IOS;
		}
		return null;
	}
	private Constants() 
	{
	    throw new IllegalStateException("Constants class");
	}

}



package utilities;

import com.google.common.io.Files;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.appmanagement.ApplicationState;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static utilities.Constants.JavaHelpers;

public class AppiumHelpers {

    AppiumDriver<	WebElement> driver;
    JavaHelpers helper;

	public AppiumHelpers(AppiumDriver<WebElement> driver)
	{
		this.driver = driver;
		helper = new JavaHelpers();
	}

	// Take screenshot
	/**
	 * Take screenshot of the web page
	 *
	 * @param fileName screenshot file name
	 * @throws IOException
	 */
	public void takeScreenshot(String fileName) throws IOException
	{
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(scrFile,
				new File(Constants.SCREENSHOT_LOCATION + fileName + helper.getTimeStamp("_yyyyMMdd_HHmmss") + ".png"));
	}


	//Elements 
		/**
	 * Enter text to input field
	 * @param e WebElement object
	 * @param text input text
	 * @param clear set true if want to clear field else set false
	 */
	public void enterText(WebElement e, String text, boolean clear)
	{
		e = waitTillElementIsClickable(e);
		if(clear)
		{
			e.clear();
		}
		e.click();
		e.sendKeys(text);
	}

	/**
	 * Enter text to input field
	 * @param e WebElement object
	 * @param text input text
	 * @param clear set true if want to clear field else set false
	 * @throws InterruptedException
	 */
	public void enterTextCharacterByCharacter(WebElement e, String text, boolean clear) throws InterruptedException
	{
		e = waitTillElementIsClickable(e);
		if(clear)
		{
			e.clear();
		}

		for (int i = 0; i < text.length(); i++)
		{
	        char c = text.charAt(i);
	        String s = new StringBuilder().append(c).toString();
	        e.sendKeys(s);
	        Thread.sleep(500); // Waiting for 0.5 second
	    }
	}

	/**
	 * Get Text from field
	 * @param e WebElement object
	 * @return text from field
	 */
	public String getText(WebElement e)
	{
		return waitTillElementIsVisible(e).getText().trim();
	}

	public String getText(By object)
	{
		return driver.findElement(object).getText();
	}

	/**
	 * Click on Element
	 * @param e WebElement object
	 */
	public void clickOn(WebElement e)
	{
		waitTillElementIsClickable(e).click();
	}
	
	/**
	 * Click on Element
	 * @param e WebElement object
	 */
	public void click(WebElement e)
	{
		e.click();
	}

	/**
	 * Click on Element
	 * @param by By object
	 */
	public void clickOn(By by)
	{
		waitTillElementIsClickable(by).click();
	}

	/**
	 * To determine whether WebElement has given Attribute or not
	 * @param e WebElement object
	 * @param attributeName attribute name e.g. style
	 * @return boolean
	 */
	public boolean isElementAtrributePresent(WebElement e, String attributeName)
	{
		return e.getAttribute(attributeName) != null;
	}

	/**
	 * To get Element attribute value
	 * @param e WebElement object
	 * @param attributeName attribute name e.g. style
	 * @return attribute value
	 */
	public String getElementAttributeValue(WebElement e, String attributeName)
	{
		if(isElementAtrributePresent(e,attributeName))
		{
			return e.getAttribute(attributeName);
		}
		return "Attribute" + attributeName +" not found";
	}

	/**
	 * method verify whether element is present on screen
	 *
	 * @param targetElement element to be present
	 * @return true if element is present else throws exception
	 * @throws InterruptedException Thrown when a thread is waiting, sleeping, or
	 *                              otherwise occupied, and the thread is
	 *                              interrupted, either before or during the
	 *                              activity.
	 */
	public Boolean isElementPresent(By targetElement) throws InterruptedException
	{
		return driver.findElements(targetElement).size() > 0;
	}


	//Waits
	/**
	 * To wait until element is clickable
	 * @param e WebElement object
	 * @return WebElement object
	 */
	public WebElement waitTillElementIsClickable(WebElement e)
	{
		 WebDriverWait wait = new WebDriverWait(driver, 30);
		 wait.until(ExpectedConditions.elementToBeClickable(e));
		 return e;
	}

	/**
	 * To wait until element is clickable
	 * @param by By object
	 * @return WebElement object
	 */
	public WebElement waitTillElementIsClickable(By by)
	{
		 WebDriverWait wait = new WebDriverWait(driver, 30);
		 return wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	/**
	 * o wait until element is visible
	 * @param e WebElement object
	 * @return WebElement object
	 */
	public WebElement waitTillElementIsVisible(WebElement e)
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(e));
		return e;
	}

	public Boolean VerifyElementIsPresent(WebElement el)
	{
		try {
			el.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * To verify whether element is enabled or not
	 * @param el WebElement object
	 * @return true or false
	 */
	public Boolean isElementEnabled(WebElement el)
	{
		try {
			el.isEnabled();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * o wait until element is visible
	 * @param e WebElement object
	 * @param waitDurationInSeconds wait duration in seconds
	 * @return WebElement object
	 */
	public WebElement waitTillElementIsVisible(WebElement e, int waitDurationInSeconds)
	{
		 WebDriverWait wait = new WebDriverWait(driver, waitDurationInSeconds);
		 wait.until(ExpectedConditions.visibilityOf(e));
		 return e;
	}

	/**
	 * o wait until element is visible
	 * @param by By object
	 * @param waitDurationInSeconds wait duration in seconds
	 * @return WebElement object
	 */
	public void waitTillElementIsVisible(By by, int waitDurationInSeconds)
	{
		 WebDriverWait wait = new WebDriverWait(driver, waitDurationInSeconds);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/**
	 * Wait until element is invisible
	 * @param e WebElement object
	 */
	public void waitTillElementIsNotVisible(WebElement e)
	{
		 WebDriverWait wait = new WebDriverWait(driver, 30);
		 wait.until(ExpectedConditions.invisibilityOfElementLocated((By) e));
	}

	/**
	 * Wait until element is invisible
	 * @param by By object
	 */
	public void waitTillElementIsNotVisible(By by)
	{
		 WebDriverWait wait = new WebDriverWait(driver, 30);
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	/**
	 * Wait until element is invisible
	 * @param e WebElement object
	 * @param waitDurationInSeconds wait duration in seconds
	 */
	public void waitTillElementIsNotVisible(WebElement e,int waitDurationInSeconds)
	{
		 WebDriverWait wait = new WebDriverWait(driver, waitDurationInSeconds);
		 wait.until(ExpectedConditions.invisibilityOfElementLocated((By) e));
	}

	/**
	 * Wait until element is invisible
	 * @param waitDurationInSeconds wait duration in seconds
	 * @param by By object
	 */
	public void waitTillElementIsNotVisible(By by, int waitDurationInSeconds)
	{
		 WebDriverWait wait = new WebDriverWait(driver,  waitDurationInSeconds);
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	/**
	 * Wait for specified duration and check if element is visible or not
	 * @param e WebElement object
	 * @param duration wait duration in seconds
	 * @return WebElement if visible or null if not visible
	 */
	public WebElement waitInCaseElementVisible(WebElement e, int duration)
	{
		 WebDriverWait wait = new WebDriverWait(driver, duration);
		 try
		 {
			 return wait.until(ExpectedConditions.visibilityOf(e));
		 }
		 catch (Exception ex)
		 {
			 return null;
		 }
	}

	/**
	 * Wait for specified duration and check if element is visible or not
	 * @param by By object
	 * @param duration wait duration in seconds
	 * @return WebElement if visible or null if not visible
	 */
	public WebElement waitInCaseElementVisible(By by, int duration)
	{
		 WebDriverWait wait = new WebDriverWait(driver, duration);
		 try
		 {
			 return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		 }
		 catch (Exception ex)
		 {
			 return null;
		 }
	}

	/**
	 * Wait for specified duration and check if element is clickable or not
	 * @param e WebElement object
	 * @param duration wait duration in seconds
	 * @return WebElement if clickable or null if not visible
	 */
	public WebElement waitInCaseElementClickable(WebElement e, int duration)
	{
		 WebDriverWait wait = new WebDriverWait(driver, duration);
		 try
		 {
			 return wait.until(ExpectedConditions.elementToBeClickable(e));
		 }
		 catch (Exception ex)
		 {
			 return null;
		 }
	}


	/**
	 * Wait till Element count is less than provided number
	 * @param by By object
	 * @param count provide number
	 */
	public void waitTillElementsCountIsLessThan(By by, int count)
	{
		 WebDriverWait wait = new WebDriverWait(driver, 30);
		 wait.until(ExpectedConditions.numberOfElementsToBeLessThan(by, count));
	}

	/**
	 * Wait till Element count is equal to provided number
	 * @param by By object
	 * @param count provide number
	 */
	public void waitTillElementsCountIsEqualTo(By by, int count)
	{
		 WebDriverWait wait = new WebDriverWait(driver, 30);
		 wait.until(ExpectedConditions.numberOfElementsToBe(by, count));
	}

	/**
	 * Wait till frame is available for switching
	 * @param e WebElement object
	 */
	public void waitTillframeToBeAvailableAndSwitchToIt(WebElement e)
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(e));
	}

	/**
	 * Wait till element not attached to DOM
	 * @param e WebElement object
	 */
	public void waitTillElementNotAttachedToDOM(WebElement e)
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.stalenessOf(e));
	}

	/**
	 * Waiting before performing next action
	 * @param seconds provide duration e.g. 1,2 etc
	 * @throws InterruptedException
	 */
	public void hardWait(int seconds) throws InterruptedException
	{
		Thread.sleep(seconds * 1000);
	}

	/**
	 * Wait till all elements are located
	 * @param by By object
	 * @return List of WebElement
	 */
	public List<WebElement> waitTillAllElementsAreLocated(By by)
	{
		 WebDriverWait wait = new WebDriverWait(driver, 30);
		 return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
	}


	//Mobile Specific Events

	/**
	 * Hide keyboard
	 */
	public void hideKeyboard()
	{
		driver.hideKeyboard();
	}

	public void closeKeyboardIos(){
		driver.getKeyboard().sendKeys(Keys.RETURN);
	}
	/**
	 * Go back by Android Native back click
	 *
	 */
	public void back()
	{
		((PressesKey) driver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
	}

	/**
	 * Close application by Android Home button
	 */
	public void home()
	{
		((PressesKey) driver).pressKey(new KeyEvent().withKey(AndroidKey.HOME));
	}


	/**
	 * Tap or Click on Element
	 * @param e WebElement object
	 */
	public void tapOrClick(WebElement e)
	{
		if(waitInCaseElementClickable(e, 30)!=null)
		{
			try
			{
				tap(e);
			}
			catch (Exception e1)
			{
				e.click();
			}
		}
		else
		{
			//Trying in-case it works 
			e.click();
		}
	}

	/**
	 * Tap on Element
	 * @param e WebElement object
	 */
	public void tap(WebElement e)
	{
		TouchAction<?> t = new TouchAction<>(driver);
			t.tap(tapOptions().withElement(element(e))).perform();
	}

	/**
	 * Tap on Element by coordinates
	 * @param x value of x coordinates
	 * @param y value of y coordinates
	 */
	public void tap(int x, int y)
	{
		 TouchAction<?> t = new TouchAction<>(driver);
	     t.tap(tapOptions().withPosition(point(x, y))).perform();
	}
	
	/**
	 * Tap on Element by location (using co-ordinates)
	 * @param e WebElement object
	 */
	public void tapByElementLocation(WebElement e)
	{
		Point p = e.getLocation();
        int x = p.getX();
        int y = p.getY();
        tap(x,y);
	}

	/**
	 * Long press on specific element by passing locator
	 * @param locator element to be long pressed
	 */
	public void longPress(By locator)
	{
		WebElement element = driver.findElement(locator);
		TouchAction<?> touch = new TouchAction<>(driver);
		LongPressOptions longPressOptions = new LongPressOptions();
		longPressOptions.withElement(ElementOption.element(element));
		touch.longPress(longPressOptions).release().perform();
	}

	/**
	 * Long press on specific x,y coordinates
	 * @param x x offset
	 * @param y y offset
	 */
	public void longPress(int x, int y)
	{
		TouchAction<?> touch = new TouchAction<>(driver);
		PointOption<?> pointOption = new PointOption<>();
		pointOption.withCoordinates(x, y);
		touch.longPress(pointOption).release().perform();
	}

	/**
	 * Method to long press on element with absolute coordinates.
	 * @param locator element to be long pressed
	 * @param x       x offset
	 * @param y       y offset
	 */
	public void longPress(By locator, int x, int y)
	{
		WebElement element = driver.findElement(locator);
		TouchAction<?> touch = new TouchAction<>(driver);
		LongPressOptions longPressOptions = new LongPressOptions();
		longPressOptions.withPosition(new PointOption<>().withCoordinates(x, y))
				.withElement(ElementOption.element(element));
		touch.longPress(longPressOptions).release().perform();
	}

	/**
	 * Open notifications on Android
	 */
	public void openNotifications()
	{
		((AndroidDriver<?>) driver).openNotifications();
	}

	/**
	 * Launch Application (app-under-test (AUT) is closed, or backgrounded, it will launch it)
	 */
	public void launchApp()
	{
		driver.launchApp();
	}

    /**
     * Scroll down on screen
     * @param swipeTimes       number of times swipe operation should work
     * @param durationForSwipe time duration of a swipe operation in milliseconds
     */
    public void scrollDown(int swipeTimes, int durationForSwipe)
    {
        Dimension dimension = driver.manage().window().getSize();

        for (int i = 0; i <= swipeTimes; i++) {
            int start = (int) (dimension.getHeight() * 0.5);
            int end = (int) (dimension.getHeight() * 0.3);
            int x = (int) (dimension.getWidth() * .5);

            new TouchAction<>(driver).press(PointOption.point(x, start)).moveTo(PointOption.point(x, end))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationForSwipe))).release().perform();
        }
    }

    /**
     * Scroll up on screen
     * @param swipeTimes       number of times swipe operation should work
     * @param durationForSwipe time duration of a swipe operation in milliseconds
     */
    public void scrollUp(int swipeTimes, int durationForSwipe)
    {
        Dimension dimension = driver.manage().window().getSize();

        for (int i = 0; i <= swipeTimes; i++) {
            int start = (int) (dimension.getHeight() * 0.3);
            int end = (int) (dimension.getHeight() * 0.5);
            int x = (int) (dimension.getWidth() * .5);

            new TouchAction<>(driver).press(PointOption.point(x, start)).moveTo(PointOption.point(x, end))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationForSwipe))).release().perform();
        }
    }

    /**
     * Scroll to element by text and parent resource id
     * @param elementText element text
     * @param parentLocatorResourceId parent element resource id
     */
    public void scrollToElementByText(String elementText, String parentLocatorResourceId)
    {
        String locatorText = "new UiScrollable(new UiSelector().resourceId(\"" + parentLocatorResourceId + "\")).scrollIntoView(new UiSelector().text(\""+elementText + "\"));";
        driver.findElement(MobileBy.AndroidUIAutomator(locatorText));
    }

    /**
     * Scroll to element by text
     * @param elementText element text
     */
    public void scrollToElementByText(String elementText)
    {
        String locatorText = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\""+elementText + "\"));";
        driver.findElement(MobileBy.AndroidUIAutomator(locatorText));
    }

    /**
     * Scroll to element resource id
     * @param elementResourceId element resource Id
     */
    public void scrollToElementByResourceId(String elementResourceId)
    {
        String locatorText = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().resourceIdMatches(\".*:id/"+ elementResourceId + "\"));";
        driver.findElement(MobileBy.AndroidUIAutomator(locatorText));
    }

    /**
	 * Scroll the page based on screen percentage.
	 * @param posX is horizontal point, should be in 0 -1 range.
	 * @param startY is starting point to be set, should be in 0 -1 range.
	 * @param endY end point to be set, should be in 0 -1 range
	 * @param duration set scroll duration in miliseconds
	 * Example : Scroll down > scroll(0.5, 0.80, 0.20, 2000)
	 */
		public void scroll(double posX, double startY, double endY, int duration) {
		//The viewing size of the device
		Dimension size = driver.manage().window().getSize();
		//x position set to mid-screen horizontally
		int width = (int) (size.width * posX);
		//Starting y location set to 80% of the height (near bottom)
		int startPoint = (int) (size.getHeight() * startY);
		//Ending y location set to 20% of the height (near top)
		int endPoint = (int) (size.getHeight() * endY);
		new TouchAction<>(driver).press(point(width, startPoint))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
				.moveTo(point(width, endPoint)).release().perform();
	}

    /**
     * Scroll horizontal the page based on screen percentage.
     * @param posY is vertical point, should be in 0 -1 range.
     * @param startX is starting point to be set, should be in 0 -1 range.
     * @param endX end point to be set, should be in 0 -1 range
     * @param duration set scroll duration in milliseconds
     * Example : Scroll horizontal > scroll(0.5, 0.80, 0.20, 2000)
     */
    public void scrollHorizontal(double posY, double startX, double endX, int duration) {
        //The viewing size of the device
        Dimension size = driver.manage().window().getSize();
        //y position set to mid-screen vertically
        int height = (int) (size.height * posY);
        //Starting x location set to 80% of the width (near left side screen)
        int startPoint = (int) (size.getWidth() * startX);
        //Ending x location set to 20% of the width (near right side screen)
        int endPoint = (int) (size.getWidth() * endX);
        new TouchAction<>(driver).press(point(startPoint, height))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
                .moveTo(point(endPoint, height)).release().perform();
    }

    /**
     * Get all the context handles at particular screen
     * @return Set
     */
    public Set<String> getContext()
    {
        return driver.getContextHandles();
    }

    /**
     * Set the context to required view.
     * @param context view to be set
     * @throws InterruptedException
     */
    public void setContext(String context) throws InterruptedException
    {
        hardWait(5);
			Set<String> contextHandles = driver.getContextHandles();

        for (String name: contextHandles) 
        {
					if (name.equals(context))
            {
            	driver.context(name);
            	break;
            }
        }
    }

	/**
	 * toggle location services for access setting and switch on/off location services
	 */
	public void toggleLocationServices() 
	{
		((AndroidDriver<WebElement>) driver).openNotifications();
		((AndroidDriver<WebElement>) driver).toggleLocationServices();
		driver.findElement(By.className("android.widget.Switch")).click();
		driver.navigate().back();
	}

	/**
	 * Push file to mobile device
	 * @param mobileDeviceLocationPath e.g. for android "/mnt/sdcard/Pictures"
	 * @param filePath e.g. src/main/resources/images
	 * @param fileName e.g. image1.jpg
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public void filePushToMobileDevice(String mobileDeviceLocationPath, String filePath, String fileName) throws IOException, InterruptedException 
	{
    	File img = new File(new File(filePath),fileName);
    	((AndroidDriver<WebElement>) driver).pushFile(mobileDeviceLocationPath + "/" + img.getName(), img);
    	hardWait(5); //Waiting for file load to finish
	}

	/**
	 * Check Application status
	 * @return ApplicationState enum
	 */
//	public ApplicationState appStatus(){
//		return driver.queryAppState(Constants.MAMIKOS_APP_PACKAGE_ID);
//	}
//
	
//iOS Specif Methods

	/**
	 * Navigate back to main app from other apps for iOS
	 */
	public void iOSActivateApp(String bundleId){

		HashMap<String, Object> args = new HashMap<>();
		args.put("bundleId", bundleId);
		driver.executeScript("mobile: activateApp", args);
	}
	
}

package runner.iOSRunners;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.AfterStep;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import utilities.AppiumServerManager;
import utilities.Constants;
import utilities.ThreadManager;

import java.io.IOException;
import java.net.URISyntaxException;

public class BaseTestRunneriOS extends AbstractTestNGCucumberTests {

    @BeforeTest(alwaysRun = true)
    @Parameters({"DeviceName","PlatformVersion","WdaLocalPort","AppiumPort"})
    public void beforeClass(String deviceName, String platformVersion, String wdaLocalPort, String appiumPort) throws InterruptedException, URISyntaxException {
        if(Constants.PLATFORM.equalsIgnoreCase("local"))
        {
            ThreadManager.setAllForiOS(deviceName, platformVersion, wdaLocalPort,appiumPort);
            AppiumServerManager.startAppium();
        }


    }

    @AfterClass(alwaysRun=true)
    public void afterClass() throws  InterruptedException {
        if(Constants.PLATFORM.equalsIgnoreCase("local"))
        {
            AppiumServerManager.stopAppium();
        }
    }
}

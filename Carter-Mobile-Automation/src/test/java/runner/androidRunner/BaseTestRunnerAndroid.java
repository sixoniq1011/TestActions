package runner.androidRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.*;
import utilities.AppiumServerManager;
import utilities.Constants;
import utilities.ThreadManager;

import java.io.IOException;
import java.net.URISyntaxException;

public class BaseTestRunnerAndroid extends AbstractTestNGCucumberTests {
    @BeforeClass(alwaysRun=true)
    @Parameters({"DeviceUdid","AppiumPort","SystemPort"})
    public void beforeClass(String deviceUdid, String appiumPort, String systemPort) throws InterruptedException, URISyntaxException {
        if(Constants.PLATFORM.equalsIgnoreCase("local"))
        {
            ThreadManager.setAllForAndroid(deviceUdid,appiumPort,systemPort);
            AppiumServerManager.startAppium();
        }


    }

    @AfterClass(alwaysRun=true)
    public void afterClass() throws IOException, URISyntaxException, InterruptedException {
        if(Constants.PLATFORM.equalsIgnoreCase("local"))
        {
            AppiumServerManager.stopAppium();
        }
    }
}

package steps;

import com.google.common.collect.ImmutableMap;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeSuite;
import utilities.AppiumHelpers;
import utilities.Constants;
import utilities.DriverManager;
import utilities.ThreadManager;


import java.io.ByteArrayInputStream;
import java.io.IOException;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class Hooks {

    DriverManager driverManager = new DriverManager();

    @Before
    public void setUp() throws IOException, InterruptedException {
        driverManager.capabilities();
        new AppiumHelpers(ThreadManager.getDriver()).hardWait(5);//Waiting to get device in good state to avoid any issues
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            Allure.addAttachment("Failed scenario", new ByteArrayInputStream(((TakesScreenshot) ThreadManager.getDriver()).getScreenshotAs(OutputType.BYTES)));
        }

        driverManager.tearDown();
    }


    @BeforeSuite
    void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Platform", Constants.MOBILE_OS.toString())
                        .build());
    }


}

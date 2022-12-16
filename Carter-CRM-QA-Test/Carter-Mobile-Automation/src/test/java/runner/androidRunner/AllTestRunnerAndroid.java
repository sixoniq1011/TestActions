package runner.androidRunner;


import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.testng.CucumberOptions;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterTest;
import utilities.ThreadManager;

import java.io.ByteArrayInputStream;

@CucumberOptions(plugin = {"pretty", "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm"},
        features = "src/test/resources/technicianFeatures",
        glue = "steps",
        strict = true,
        tags = {"@Technician"})

public class AllTestRunnerAndroid extends BaseTestRunnerAndroid{
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
         Allure.addAttachment(scenario.toString(), new ByteArrayInputStream(((TakesScreenshot) ThreadManager.getDriver()).getScreenshotAs(OutputType.BYTES)));
        }

    }
}


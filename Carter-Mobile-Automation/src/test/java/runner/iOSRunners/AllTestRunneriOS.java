package runner.iOSRunners;


import io.appium.java_client.AppiumDriver;
import io.cucumber.java.AfterStep;
import io.cucumber.testng.CucumberOptions;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import utilities.ThreadManager;

@CucumberOptions(plugin = {"pretty", "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm"},
        features = "src/test/resources/technicianFeatures",
        glue = "steps",
        tags = {"@try1"})
public class AllTestRunneriOS extends BaseTestRunneriOS{

}

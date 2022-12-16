package steps;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.AfterStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.carter.pages.HamburgerMenu;
import com.carter.pages.Login;
import utilities.ThreadManager;

public class LoginSteps {

    @AfterStep
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] screenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
    private AppiumDriver<WebElement> driver = ThreadManager.getDriver();
    private Login login = new Login(driver);
    private HamburgerMenu hamburgerMenu = new HamburgerMenu(driver);



    @Given("Open the Application and click on Get Started button")
    public void openTheApplicationAndClickOnGetStartedButton() {
        login.clickOnLetsGetStartedButton();
    }

    @And("fill the login details and click on login button")
    public void fillTheLoginDetailsAndClickOnLoginButton() {
            login.enterUserNameAndPasswordClickOnLoginButton("sa11@yopmail.com", "Demo@123456");
    }

    @Then("Verify that user logged in successfully")
    public void verifyThatUserLoggedInSuccessfully() {
        Assert.assertTrue(hamburgerMenu.isHamburgerMenuPresent(), "Hamburger menu is not present");
    }

}

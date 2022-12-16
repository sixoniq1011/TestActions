package com.carter.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.AppiumHelpers;

public class Login {


    AppiumHelpers appium;
    AppiumDriver<WebElement> driver;

    public Login(AppiumDriver<WebElement> driver)
    {
        this.driver = driver;
        appium = new AppiumHelpers(driver);

        //This initElements method will create all WebElements
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "Let’s Get Started  ")
    @iOSXCUITFindBy(accessibility = "Let’s Get Started  ")
    WebElement letsGetStartedBtn;

    @AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"Login\"])[1]")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"Login\"`][1]")
    WebElement loginTitle;

    @FindBy(xpath = "//android.widget.EditText[@text='Enter Your Email']")
    @iOSXCUITFindBy(accessibility = "Enter Your Email")
    WebElement emailField;

    @FindBy(xpath = "//android.widget.EditText[@text='Enter Your Password']")
    @iOSXCUITFindBy(accessibility = "Enter Your Password")
    WebElement passwordField;

    @AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"Login\"])[2]")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"Login\"`][2]")
    WebElement loginBtn;




    public void clickOnLetsGetStartedButton(){
        appium.tapOrClick(letsGetStartedBtn);
    }

    public void enterUserNameAndPasswordClickOnLoginButton(String email, String password){
        appium.enterText(emailField, email, false);
        appium.enterText(passwordField, password, false);
        appium.tapOrClick(loginBtn);
    }
}

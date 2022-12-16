package com.carter.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utilities.AppiumHelpers;

public class HamburgerMenu {


    AppiumHelpers appium;
    AppiumDriver<WebElement> driver;

    public HamburgerMenu(AppiumDriver<WebElement> driver)
    {
        this.driver = driver;
        appium = new AppiumHelpers(driver);

        //This initElements method will create all WebElements
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"My Order\")]//preceding-sibling::*")
    WebElement hamburgerMenu;

    @AndroidFindBy(accessibility = "Sign Out")
    WebElement signOutButton;

    public void clickOnHamBurgerMenu(){
        appium.waitTillElementIsClickable(hamburgerMenu);
        appium.tapOrClick(hamburgerMenu);
    }

    public boolean isSignOutButtonPresent(){
        return hamburgerMenu.isDisplayed();
    }

    public boolean isHamburgerMenuPresent(){
        return hamburgerMenu.isDisplayed();
    }

}

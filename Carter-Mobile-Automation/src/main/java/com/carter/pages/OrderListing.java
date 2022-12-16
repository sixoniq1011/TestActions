package com.carter.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utilities.AppiumHelpers;

import java.util.List;

public class OrderListing {

    AppiumHelpers appium;
    AppiumDriver<WebElement> driver;

    public OrderListing(AppiumDriver<WebElement> driver)
    {
        this.driver = driver;
        appium = new AppiumHelpers(driver);

        //This initElements method will create all WebElements
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Order #5007\")]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\"Order #5007\")]")
    private WebElement firstOrder;


    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Order\")]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\"Order\")]")
    private List<WebElement> orderList;


    public void clickOnFirstOrderFromTheList(){
        appium.tapOrClick(firstOrder);
    }

    public int getOrderList() throws InterruptedException {
        appium.waitTillElementIsClickable(firstOrder);
        return orderList.size();
    }

}

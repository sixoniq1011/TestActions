package com.carter.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utilities.AppiumHelpers;
import utilities.Constants;

import java.util.ArrayList;
import java.util.List;

public class OrderDetails {

    AppiumHelpers appium;
    AppiumDriver<WebElement> driver;

    public OrderDetails(AppiumDriver<WebElement> driver)
    {
        this.driver = driver;
        appium = new AppiumHelpers(driver);

        //This initElements method will create all WebElements
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @AndroidFindBy(xpath = "//android.widget.HorizontalScrollView//android.view.View[2]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView//following-sibling::XCUIElementTypeStaticText[contains(@value,\"Items\")]")
    private WebElement itemsTab;

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Order Items\")]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\"Order Items\")]")
    private WebElement orderItemsHeader;

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Order Items\")]//following-sibling::android.view.View[1]/android.view.View//*")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\"Order Items\")]//following-sibling::XCUIElementTypeOther[1]//XCUIElementTypeStaticText")
    private List<WebElement> orderItemsList;

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Order Items\")]/following-sibling::android.widget.ImageView[1]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\"Order Items\")]//following-sibling::XCUIElementTypeImage[1]")
    private WebElement addButtonOfOrderItems;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"SKU\"]/following-sibling::*[1]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name=\"Search SKU\"]")
    private WebElement skuTextBox;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name=\"Search SKU\"]//following-sibling::XCUIElementTypeImage[2]")
    private WebElement qrScanner;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Cancel\"]")
    private WebElement cancelButton;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"66642507\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Carter\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]")
    private WebElement selectProduct;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Quantity\"]/following-sibling::android.widget.EditText")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name=\"Enter Quantity\"]")
    private WebElement quantityTextBox;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Add\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Add\"]")
    private WebElement addButton;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Special Requests\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Special Requests\"]")
    private  WebElement specialRequestItemHeader;

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Order Items\")]/following-sibling::android.widget.ImageView[2]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Special Requests\"]//following-sibling::XCUIElementTypeImage")
    private WebElement addButtonOfSpecialRequestItems;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Special Request\"]//following-sibling::android.widget.ImageView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name=\"Search Special Request\"]")
    private WebElement specialRequestTextBox;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"+ Add Special Request\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"+ Add Special Request\"]")
    private WebElement specialRequestOption;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Title\"]/following-sibling::*[1]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name=\"Enter Title\"]")
    private WebElement titleTextBox;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Description\"]/following-sibling::*[1]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name=\"Enter Description\"]")
    private WebElement descriptionTextBox;



    @AndroidFindBy(xpath ="//android.view.View[@content-desc=\"Special Requests\"]//following-sibling::android.view.View[1]/android.view.View//*")
    @iOSXCUITFindBy(xpath ="//XCUIElementTypeStaticText[@name=\"Special Requests\"]//following-sibling::XCUIElementTypeOther//XCUIElementTypeStaticText")
    private List<WebElement> specialRequestItemsList;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Special Request\"]/ancestor::*[3]/following-sibling::*[2]/android.view.View/android.view.View")
    private List<WebElement>  specialRequestList;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Update\"]")
    private WebElement UpdateButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Remove\"]")
    private WebElement RemoveButton;

    @AndroidFindBy(xpath = "//android.widget.Toast")
    private WebElement toastMessageLocator;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Special Requests\"]//following-sibling::*[2]/android.view.View/android.view.View[1]")
    private  WebElement specialRequest;

    @AndroidFindBy(xpath = "//android.widget.EditText")
    private WebElement descriptionBox;


    public void clickOnItemTab(){
        appium.waitTillElementIsClickable(itemsTab);
        appium.tapOrClick(itemsTab);
    }

    public boolean isOrderItemHeaderPresent(){
        return orderItemsHeader.isDisplayed();
    }
    public boolean isSpecialItemRequestPresent() {
        appium.waitTillElementIsVisible(specialRequestItemHeader);
        return  specialRequestItemHeader.isDisplayed();
    }

    public String getOrderItemCount(){
        return appium.getElementAttributeValue(orderItemsHeader, "content-desc").replace("Order Items", "").replace("(", "").replace(")", "");
    }

    public int getOrderItemListSize(){
        List<Integer> listUpdate = new ArrayList<>();
        for(WebElement e: orderItemsList){
            listUpdate.add(orderItemsList.size());
        }

        return listUpdate.size();
    }

    public int getSpecialItemListSize(){
        List<Integer> listUpdate = new ArrayList<>();
        for(WebElement e: specialRequestItemsList){
            listUpdate.add(specialRequestItemsList.size());
        }

        return listUpdate.size();
    }

    public void tapOnAddButtonOfOrderItems() {
        appium.waitTillElementIsClickable(addButtonOfOrderItems);
        appium.tapOrClick(addButtonOfOrderItems);
    }

    public void enterProductDetailsAndTapOnAddButton() {
        if (Platform.ANDROID== Constants.MOBILE_OS) {
            appium.waitTillElementIsClickable(skuTextBox);
            appium.tapOrClick(skuTextBox);
            appium.enterText(skuTextBox, "666", true);
//        appium.hideKeyboard();
            appium.tapOrClick(selectProduct);
            appium.enterText(quantityTextBox, "25", true);
//        appium.tap(925,2118);

            appium.back();
            appium.tapOrClick(addButton);
        }else {
            appium.waitTillElementIsClickable(skuTextBox);
            appium.tapOrClick(qrScanner);
            appium.tapOrClick(cancelButton);
            appium.enterText(skuTextBox, "666", true);
            appium.tapOrClick(selectProduct);
            appium.enterText(quantityTextBox, "100", true);
            appium.closeKeyboardIos();
            quantityTextBox.sendKeys(Keys.TAB);
            appium.tapOrClick(addButton);
        }
    }

    public String enterSpecialRequestDetailsAndTapOnAddButton() throws InterruptedException{
        appium.waitTillElementIsClickable(specialRequestTextBox);
        Thread.sleep(5000);
        if (Constants.MOBILE_OS==Platform.IOS){
            appium.clickOn(specialRequestTextBox);
            appium.enterTextCharacterByCharacter(specialRequestTextBox, "t", true);
            specialRequestTextBox.sendKeys(Keys.BACK_SPACE);
            appium.enterTextCharacterByCharacter(specialRequestTextBox, "t", true);
        }else {
            appium.clickOn(specialRequestTextBox);
            appium.enterTextCharacterByCharacter(specialRequestTextBox, "t", true);
            appium.hideKeyboard();
        }

        String category = "Test" + System.nanoTime();
        addSpecialCategory(category);
        return category;
    }

    public void addSpecialCategory(String category) {
        appium.waitTillElementIsClickable(specialRequestOption);
        appium.tapOrClick(specialRequestOption);
        appium.enterText(titleTextBox,category,true);
        appium.enterText(descriptionTextBox,"Test",true);
        if(Constants.MOBILE_OS==Platform.ANDROID) {
            appium.hideKeyboard();
        }else {
            appium.tapOrClick(titleTextBox);
            appium.hideKeyboard();
        }
        appium.tapOrClick(addButton);
    }


    public void tapOnAddButtonOfSpecialRequestItems() throws InterruptedException {
        appium.waitTillElementIsClickable(addButtonOfSpecialRequestItems);
        appium.tapOrClick(addButtonOfSpecialRequestItems);
    }

    public void clickOnOrderItemByName(String orderName){
        String xpath;
        if (Constants.MOBILE_OS==Platform.ANDROID){
            xpath = "//android.view.View[contains(@content-desc,'"+orderName+"')]";
        }else {
            xpath = "//XCUIElementTypeStaticText[contains(@name,'"+orderName+"')]";
        }
        appium.clickOn(By.xpath(xpath));
    }

    public void UpdateOrderQuantity(String quantity){
        quantityTextBox.clear();
        appium.enterText(quantityTextBox, quantity, true);
    }

    public void tapOnUpdateButtonOfOrderItems() {
        if (Constants.MOBILE_OS==Platform.ANDROID){
            appium.back();
        }
        appium.waitTillElementIsClickable(UpdateButton);
        appium.tapOrClick(UpdateButton);
    }

    public void tapOnRemoveButtonOfOrderItems() {
        appium.waitTillElementIsClickable(RemoveButton);
        appium.tapOrClick(RemoveButton);
    }

    public String getToastMessage(){
        appium.waitTillElementIsVisible(toastMessageLocator);
       return appium.getText(toastMessageLocator);
    }

    public String  getUpdatedQuantity(String orderName){
        String xpath = "//android.view.View[contains(@content-desc,'"+orderName+"')]";
        WebElement quantity = driver.findElement(By.xpath(xpath));
        return appium.getElementAttributeValue(quantity, "content-desc");
    }

    public boolean isOrderItemPresent(String orderName) throws InterruptedException {
        String xpath = "//android.view.View[contains(@content-desc,'"+orderName+"')]";
        return appium.isElementPresent(By.xpath(xpath));
    }
    public String getSpecialRequestId() {
         return appium.getElementAttributeValue(specialRequest,"content-desc");
    }

    public void tapOnSpecialRequest() {
        appium.waitTillElementIsClickable(specialRequest);
        appium.tapOrClick(specialRequest);
    }

    public void updateDescription(String description) {
        appium.enterText(descriptionBox,description,true);
    }
    public String getDescriptionText() {
        return  appium.getText(descriptionBox).split(",")[0];
    }
    public boolean isSpecialRequestPresent(String specialRequest) throws InterruptedException {
//        appium.waitTillElementIsVisible(By.xpath("//android.view.View[contains(@content-desc,'"+specialRequest+"')]"),10);
        appium.hardWait(7);
        String xpath = "//android.view.View[contains(@content-desc,'"+specialRequest+"')]";
        return appium.isElementPresent(By.xpath(xpath));
    }


}

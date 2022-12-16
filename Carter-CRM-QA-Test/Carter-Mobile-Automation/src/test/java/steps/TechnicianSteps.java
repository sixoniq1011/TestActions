package steps;

import com.carter.pages.OrderDetails;
import com.carter.pages.OrderListing;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.AfterStep;
import io.cucumber.java.bs.I;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import utilities.ThreadManager;

public class TechnicianSteps {

    private AppiumDriver<WebElement> driver = ThreadManager.getDriver();
    OrderListing orderListing = new OrderListing(driver);
    OrderDetails orderDetails = new OrderDetails(driver);

    @AfterStep
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] screenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


    @Then("Verify that Order listing displayed in the order summery tab")
    public void verifyThatOrderListingDisplayedInTheOrderSummeryTab() throws InterruptedException {

        Assert.assertTrue(orderListing.getOrderList()>2, "Order List is not present");
    }

    @And("Open Order from order listing")
    public void openOrderFromOrderListing() {
        orderListing.clickOnFirstOrderFromTheList();
    }

    @When("Navigate to items page")
    public void navigateToItemsPage() {
        orderDetails.clickOnItemTab();
    }

    @Then("Verify that Order Items are displayed")
    public void verifyThatOrderItemsAreDisplayed() {
        Assert.assertTrue(orderDetails.isOrderItemHeaderPresent(), "Order Items section is not displayed");
        Assert.assertTrue(orderDetails.getOrderItemListSize() > 0,"Count is not matched");
    }

    @Then("Verify that special request Items are displayed")
    public void verifyThatSpecialRequestItemsAreDisplayed() {

        Assert.assertTrue(orderDetails.isSpecialItemRequestPresent(), "Special Request Items section is not displayed");
        Assert.assertTrue(orderDetails.getSpecialItemListSize() > 0,"Count is not matched");
    }
    @And("Tap on add button of special request")
    public void tapOnAddButtonOfSpecialRequest() throws InterruptedException {
        orderDetails.tapOnAddButtonOfSpecialRequestItems();
    }

    @And("Tap on add icon of order items")
    public void tapOnAddIconOfOrderItems() {
        orderDetails.tapOnAddButtonOfOrderItems();
    }

    @And("Enter special request details and tap on add button")
    public void enterSpecialRequestDetailsAndTapOnAddButton() throws InterruptedException {
        orderDetails.enterSpecialRequestDetailsAndTapOnAddButton();

    }
    @And("Enter product details and tap on add button")
    public void enterProductDetailsAndTapOnAddButton() throws InterruptedException{
        orderDetails.enterProductDetailsAndTapOnAddButton();
    }

    @Then("Verify special request is displayed in special request list")
    public void verifySpecialRequestIsDisplayedInSpecialRequestList() {
        Assert.assertTrue(orderDetails.isSpecialItemRequestPresent(),"special request is not displayed");
    }

    @Then("Verify the order is displayed in order item list")
    public void verifyTheOrderIsDisplayedInOrderItemList() {
    }

    @And("Open {string} order item")
    public void openOrderItem(String orderItem) {
        orderDetails.clickOnOrderItemByName(orderItem);
    }

    @When("Change the quantity to {string} for that order item")
    public void changeTheQuantityToForThatOrderItem(String quantity) {
        orderDetails.UpdateOrderQuantity(quantity);
    }

    @And("Tap on Update Button")
    public void tapOnUpdateButton() {
        orderDetails.tapOnUpdateButtonOfOrderItems();
    }

    @Then("Verify {string} toast message is displayed and {string} Order Item quantity updated to {string}")
    public void verifyToastMessageIsDisplayedAndItemQuantityUpdatedTo(String toastMessage, String orderItem, String quantity) {
//        String actualToastMessage = orderDetails.getToastMessage();
//        Assert.assertEquals(orderDetails.getToastMessage(), toastMessage, "Toast Message does not match");
        Assert.assertTrue(orderDetails.getUpdatedQuantity(orderItem).endsWith(quantity),"Quantity doesn't match");
    }

    @When("Tap on Remove Button")
    public void tapOnRemoveButton() {
        orderDetails.tapOnRemoveButtonOfOrderItems();
    }

    @And("Tap on Remove Button from Confirmation popUp")
    public void tapOnRemoveButtonFromConfirmationPopUp() {
        orderDetails.tapOnRemoveButtonOfOrderItems();
    }

    @Then("Verify that {string} order item is not present")
    public void verifyThatOrderItemIsNotPresent(String orderItem) throws InterruptedException {
        Assert.assertFalse(orderDetails.isOrderItemPresent(orderItem), "Order Item still present");
    }
    String requestId;
    @When("Open special request item")
    public void openSpecialRequest() {
        requestId= orderDetails.getSpecialRequestId();
        orderDetails.tapOnSpecialRequest();
    }
    @When("Change the Description to {string} for that special request")
    public void updateDescriptionOfTheSpecialRequest(String description) {
        orderDetails.updateDescription(description);
    }
    @Then("Verify {string} toast message is displayed and special request updated to {string}")
    public void verifySpecialRequestUpdated(String toastMessage,String description) {
        orderDetails.tapOnSpecialRequest();
        Assert.assertEquals(orderDetails.getDescriptionText(),description,"Description is not matched");
    }

    @Then("Verify {string} toast message is displayed and special request is not present")
    public void verifySpecialRequestIsNotPresent(String toastMessage) throws InterruptedException {
        System.out.println("Request id: "+requestId);
        Assert.assertFalse(orderDetails.isSpecialRequestPresent(requestId),"special request id still present");
    }
}

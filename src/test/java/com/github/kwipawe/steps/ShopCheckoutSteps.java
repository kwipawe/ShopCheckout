package com.github.kwipawe.steps;

import com.github.kwipawe.page.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ShopCheckoutSteps {

    private static String addressID;
    private static String orderID;
    private static String price;
    private WebDriver driver;

    public static String getAddressID() {
        return addressID;
    }

    public static void setAddressID(String addressID) {
        ShopCheckoutSteps.addressID = addressID;
    }

    public static String getOrderID() {
        return orderID;
    }

    public static void setOrderID(String orderID) {
        ShopCheckoutSteps.orderID = orderID;
    }

    public static String getPrice() {
        return price;
    }

    public static void setPrice(String price) {
        ShopCheckoutSteps.price = price;
    }


    @Given("I am on shop main page")
    public void iAmOnShopMainPage() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://mystore-testlab.coderslab.pl");
    }

    @When("I go to sign in page")
    public void iGoToSignInPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.signIn();
    }

    @And("I log in as an user using {string} and {string}")
    public void iLogInAsAnUserUsingEmailAndPassword(String login, String password) {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.loginAs(login, password);
    }

    @And("I go back to main page")
    public void iGoBackToMainPage() {
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        setAddressID(myAccountPage.getAddressId());
        myAccountPage.goToMainPage();
    }

    @And("I go to Hummingbird Printed Sweater product page")
    public void iGoToSweaterProductPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.goToProductPage();
    }


    @And("I choose {string} size")
    public void iChooseSize(String size) {
        ProductPage productPage = new ProductPage(driver);
        productPage.chooseSize(size);
    }


    @And("I choose  quantity {string}")
    public void iChooseQuantity(String quantity) {
        ProductPage productPage = new ProductPage(driver);
        productPage.setQuantity(quantity);
    }

    @And("I add product to shopping cart and proceed")
    public void iAddProductToShoppingCart() {
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCartAndProceed();
    }

    @And("I go to checkout page")
    public void iGoToCheckoutPage() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.proceedToCheckout();
    }

    @Then("I check if discount is right")
    public void iCheckIfDiscountIsRight() {
        MainPage mainPage = new MainPage(driver);
        float discountedPriceOnPage = mainPage.getDiscountedPriceOnPage();
        float calculatedDiscountedPrice = mainPage.calculateDiscountedPrice();
        Assert.assertEquals(discountedPriceOnPage, calculatedDiscountedPrice, 0.001);
    }

    @And("I select proper address")
    public void iSelectProperAddress() {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.selectAddress();
    }

    @And("I select pick up in-store delivery option")
    public void iSelectDeliveryOption() {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.chooseShippingMethod();
    }

    @And("I choose payment option and finalize the order")
    public void iChoosePaymentOptionAndFinalizeTheOrder() {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.choosePaymentAndOrder();
    }

    @Then("I capture a screenshot")
    public void iCaptureAScreenshot() {
        String path = System.getProperty("user.dir") + "\\screenshots\\CheckoutScreenShot" + System.currentTimeMillis() + ".png";
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(path));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //and sets static vars id & price
        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        setOrderID(orderConfirmationPage.getOrderId());
        setPrice(orderConfirmationPage.getPrice());
    }

    @And("I go to order history page")
    public void iGoToOrderHistoryPage() {
        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        orderConfirmationPage.goToOrderHistory();
    }

    @And("I check if my order has {string} status")
    public void iCheckIfMyOrderHasStatus(String status) {
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
        String statusFromOrderHistory = orderHistoryPage.getStatus();
        Assert.assertEquals(status, statusFromOrderHistory);
    }


    @And("I check if price is correct")
    public void iCheckIfPriceIsCorrect() {
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
        String priceFromOrderHistory = orderHistoryPage.getPriceFromCurrentOrder();
        Assert.assertEquals(getPrice(), priceFromOrderHistory);
    }
}

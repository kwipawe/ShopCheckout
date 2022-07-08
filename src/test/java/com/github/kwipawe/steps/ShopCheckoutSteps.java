package com.github.kwipawe.steps;

import com.github.kwipawe.page.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ShopCheckoutSteps {
    private WebDriver driver;

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
        mainPage.signInWithObject();
    }

    @And("I log in as an user using {string} and {string}")
    public void iLogInAsAnUserUsingEmailAndPassword(String login, String password) {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.loginAs(login, password);
    }

    @And("I go back to main page")
    public void iGoBackToMainPage() {
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.goToMainPageWithObject();
    }

    @And("I go to Hummingbird Printed Sweater product page")
    public void iGoToSweaterProductPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.goToProductPageWithObject();
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
        shoppingCartPage.proceedToCheckoutWithObject();
    }

    @Then("I check if discount is right")
    public void iCheckIfDiscountIsRight() {
        MainPage mainPage = new MainPage(driver);
        float discountedPriceOnPage = mainPage.getDiscountedPriceOnPage();
        float calculatedDiscountedPrice = mainPage.calculateDiscountedPrice();
        Assert.assertEquals(discountedPriceOnPage, calculatedDiscountedPrice, 0.001);
    }
}
package com.github.kwipawe.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void signIn() {
        driver.findElement(By.cssSelector("a[title='Log in to your customer account']")).click();
    }

    public SignInPage signInWithObject() {
        signIn();
        return new SignInPage(driver);
    }


    public void goToProductPage() {
        driver.findElement(By.xpath("//*[text()='Hummingbird printed sweater']")).click();
    }

    public ProductPage goToProductPageWithObject() {
        goToProductPage();
        return new ProductPage(driver);
    }

    public float getDiscountedPriceOnPage() {
        String discountPrice = driver.findElement(By.xpath("//*[@data-id-product='2']//span[@class='price']")).getText();
        String temp = discountPrice.replace("€","");
        float discountPriceFloatOnPage = Float.parseFloat(temp);
        return discountPriceFloatOnPage;
    }

    public float calculateDiscountedPrice() {
        String regularPrice = driver.findElement(By.xpath("//*[@data-id-product='2']//span[@class='regular-price']")).getText();
        String discount = driver.findElement(By.xpath("//*[@data-id-product='2']//span[@class='discount-percentage discount-product']")).getText();

        String temp = regularPrice.replace("€","");
        float regularPriceFloat = Float.parseFloat(temp);

        String temp2 = discount.replace("-", "");
        temp2 = temp2.replace("%","");
        float discountFloat = Float.parseFloat(temp2) / 100;

        float DiscountedPriceFloat = regularPriceFloat * (1 - discountFloat);
        return DiscountedPriceFloat;
    }

}
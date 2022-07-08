package com.github.kwipawe.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage {
    private WebDriver driver;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void proceedToCheckout() {
        driver.findElement(By.xpath("//a[text()='Proceed to checkout']")).click();
    }

    public OrderPage proceedToCheckoutWithObject() {
        proceedToCheckout();
        return new OrderPage(driver);
    }
}
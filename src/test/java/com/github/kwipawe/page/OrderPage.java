package com.github.kwipawe.page;

import com.github.kwipawe.steps.ShopCheckoutSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectAddress() {
        String addressId = new ShopCheckoutSteps().getAddressID(); //check lint warning again!
        boolean radioCheck = driver.findElement(By.xpath("//input[@value=" + addressId + "]")).isSelected();
        if (!radioCheck) {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value=" + addressId + "]"))).click();
        }
    }

    public void chooseShippingMethod() {
        driver.findElement(By.cssSelector("button[value='1'][name='confirm-addresses']")).click();
        boolean radioCheck = driver.findElement(By.id("delivery_option_1")).isSelected();
        if (!radioCheck) {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.id("delivery_option_1"))).click();
        }
    }

    public void choosePaymentAndOrder() {
        driver.findElement(By.cssSelector("button[value='1'][name='confirmDeliveryOption']")).click();
        driver.findElement(By.id("payment-option-1")).click();
        driver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).click();
        driver.findElement(By.xpath("//*[@id='payment-confirmation']/div[1]/button")).click();
    }
}

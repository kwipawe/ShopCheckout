package com.github.kwipawe.page;

import com.github.kwipawe.steps.ShopCheckoutSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class OrderPage {
    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectAddress() {
        String addressId = new ShopCheckoutSteps().accessAddressId();
        boolean radioCheck = driver.findElement(By.xpath("//input[@value=" + addressId + "]")).isSelected();
        if (radioCheck == false) {
//        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='id-address-delivery-address-" + addressId + "']/header/label/span[1]/span")));
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value=" + addressId + "]"))).click();
//        driver.findElement(By.xpath("//input[@value=" + addressId + "]")).click();
        }
    }

    public void chooseShippingMethod() {
        driver.findElement(By.cssSelector("button[value='1'][name='confirm-addresses']")).click();
        boolean radioCheck = driver.findElement(By.id("delivery_option_1")).isSelected();
        if (radioCheck == false) {
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

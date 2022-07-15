package com.github.kwipawe.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    private WebDriver driver;


    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void chooseSize(String size) {
        Select sizeDropDown = new Select(driver.findElement(By.id("group_1")));
        sizeDropDown.selectByVisibleText(size);
    }

    public void setQuantity(String quantity) {
        WebElement quantityBox = driver.findElement(By.id("quantity_wanted"));
        WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(5));
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("quantity_wanted")));
        quantityBox.click();
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.id("quantity_wanted")));
        // to override default js(?) value
        while (!quantityBox.getAttribute("value").equals(quantity)) {
            quantityBox.clear();
            quantityBox.sendKeys(Keys.SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE);
            quantityBox.sendKeys(Keys.CONTROL + "a");
            quantityBox.sendKeys(Keys.DELETE);
            quantityBox.sendKeys(quantity);
        }
    }

    public void addToCartAndProceed() {
        try {
            driver.findElement(By.cssSelector(".btn.btn-primary.add-to-cart")).click();
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            driver.findElement(By.cssSelector(".btn.btn-primary.add-to-cart")).click();
        }
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=cart&action=show");
        driver.navigate().refresh();
    }
}
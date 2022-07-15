package com.github.kwipawe.page;

import com.github.kwipawe.steps.ShopCheckoutSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderHistoryPage {
    private WebDriver driver;

    public OrderHistoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPriceFromCurrentOrder() {
        String priceFromCurrentOrder = driver.findElement(By.xpath("//th[text()='" + ShopCheckoutSteps.getOrderID() + "']/following-sibling::td[@class='text-xs-right']")).getText();
        priceFromCurrentOrder = priceFromCurrentOrder.replace("€", "");
        return priceFromCurrentOrder;
    }

    public String getStatus() {
        // to check with default status text
        return driver.findElement(By.xpath("//th[text()='" + ShopCheckoutSteps.getOrderID() + "']/following-sibling::td/span")).getText();
    }
}


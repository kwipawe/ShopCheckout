package com.github.kwipawe.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmationPage {

    private WebDriver driver;

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getOrderId() {
        // gets order confirmation id to compare with order history id
        String OrderId = driver.findElement(By.xpath("//*[text()[contains(.,'Order reference')]]")).getText();
        OrderId = OrderId.replaceAll("Order reference: ", "");
        return OrderId;
    }

    public String getPrice() {
        // gets order confirmation price to compare with order history price
        String price = driver.findElement(By.xpath("//td/span[text()='Total']/parent::td/following-sibling::td")).getText();
        price = price.replace("€", "");
        return price;
    }

    public void goToOrderHistory() {
        driver.findElement(By.xpath("//*[@id='_desktop_user_info']/div/a[2]/span")).click();
        driver.findElement(By.xpath("//*[@id='history-link']/span")).click();
    }

}
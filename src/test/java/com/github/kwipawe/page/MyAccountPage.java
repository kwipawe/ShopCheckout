package com.github.kwipawe.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {
    private WebDriver driver;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToMainPage() {
        driver.get("https://mystore-testlab.coderslab.pl");
    }

    public MainPage goToMainPageWithObject() {
        goToMainPage();
        return new MainPage(driver);
    }

    public String getAddressId() {
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=addresses");
        String AddressId = driver.findElement(By.cssSelector("article.address")).getAttribute("data-id-address");
        driver.navigate().back();
        return AddressId;
    }
}
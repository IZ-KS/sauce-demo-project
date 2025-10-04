package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    private By usernameField = By.xpath("//*[@id=\"user-name\"]");
    private By passwordField = By.xpath("//*[@id=\"password\"]");
    private By loginButton = By.xpath("//*[@id=\"login-button\"]");
    private By errorMsg = By.xpath("//h3[@data-test='error']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // 2. Page Actions (Methods to interact with the elements)
    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        // You would return the next Page Object here, e.g., return new HomePage(driver);
    }

    public String getErrorMsg(){
        return driver.findElement(errorMsg).getText();
    }
}

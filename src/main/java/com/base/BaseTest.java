package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    public WebDriver driver;

    @BeforeMethod
    public void setup() {

        // Use WebDriverManager to set up the ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // You'll often add implicit/explicit waits here
        driver.get("https://www.saucedemo.com/"); // Your application URL
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println("Test successful. Pausing for 3 seconds before closing...");
            try {
                Thread.sleep(3000); // Pauses execution for 3000 milliseconds (3 seconds)
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            driver.quit();
        }

    }
}
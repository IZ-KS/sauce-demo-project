package com.tests;

import com.base.BaseTest;
import com.pages.InventoryPage;
import com.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;

public class LoginTest  extends BaseTest{

    @Test
    public void verifySuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Login was successful.");

        String img1Src = inventoryPage.getAllProductImages().get(0).getDomAttribute("src");
        String img2Src = inventoryPage.getAllProductImages().get(1).getDomAttribute("src");

        Assert.assertNotEquals(img1Src, img2Src,
                "Standard user should have unique product images");

    }

    @Test
    public void verifyUnsuccesfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user","secret_sauce");

        String expectedErrMsg = "Epic sadface: Sorry, this user has been locked out.";
        Assert.assertEquals(loginPage.getErrorMsg(), expectedErrMsg, "Login was unsuccessful.");
    }

    @Test
    public void verifyProblemLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("problem_user","secret_sauce");

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Login was successful.");

        InventoryPage inventoryPage = new InventoryPage(driver);

        String img1Src = inventoryPage.getAllProductImages().get(0).getDomAttribute("src");
        String img2Src = inventoryPage.getAllProductImages().get(1).getDomAttribute("src");

        Assert.assertEquals(img1Src, img2Src,
                "Problem user should have same product images");
    }

    @Test
    public void performanceGlitchUser_verifyLoginDelays(){
        LoginPage loginPage = new LoginPage(driver);

        Instant startTime = Instant.now();

        loginPage.login("performance_glitch_user","secret_sauce");

        //Record End Time
        Instant endTime = Instant.now();

        long durationSeconds = Duration.between(startTime, endTime).getSeconds();

        long minimumExpectedDelay = 5;

        Assert.assertTrue(
                durationSeconds >= minimumExpectedDelay,
                "Login time was too fast. Expected a minimum delay of " + minimumExpectedDelay +
                        " seconds for the performance glitch user, but actual time was " + durationSeconds + " seconds."
        );

        // You should also perform a standard assertion to ensure the login was successful:
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "User did not reach inventory page.");
    }

    @Test
    public void errorUser_verifyButtonStuckInRemoveState(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("error_user","secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);

        //First Click
        inventoryPage.clickBackPackProductButton();

        //Second Click
        inventoryPage.clickBackPackProductButton();

        boolean isAddToCarPresent = !driver.findElements(inventoryPage.backPackProductAddButton).isEmpty();

        Assert.assertFalse(isAddToCarPresent,
                "The button should NOT have reverted to 'Add to cart' state for the error user.");

        String buttonText = driver.findElement(inventoryPage.backPackProductRemoveButton).getText();

        Assert.assertEquals(buttonText, "Remove",
                "After attempting to remove, the button text should still be 'Remove'.");



    }



}

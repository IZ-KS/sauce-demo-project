package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;
import org.openqa.selenium.WebElement;

public class InventoryPage {

    private final WebDriver driver;

    // Locator for the first product image (for comparison)
    private final By allProductImages = By.cssSelector(".inventory_item_img img");
    private final By firstProductImage = By.xpath("(//img[@class='inventory_item_img'])[1]");

    // Locators for the button states using Arrays
    //Just adding here for future reference
    //private final By firstProductAddToCartButton = By.xpath("(//button[text()='Add to cart'])[1]");
    //private final By firstProductRemoveButton = By.xpath("(//button[text()='Remove'])[1]");

    //BackPackItem
    public final By backPackProductAddButton = By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']");
    public final By backPackProductRemoveButton = By.xpath("//button[@data-test='remove-sauce-labs-backpack']");

    //SweaterItem
    public final By sweaterProductAddButton = By.xpath("//button[@data-test='add-to-cart-test.allthethings()-t-shirt-(red)']");

    //JacketItem
    public final By jacketProductAddButton = By.xpath("//button[@data-test='add-to-cart-sauce-labs-fleece-jacket']");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Gets the source URL (src attribute) of the first product image.
     */
    public String getFirstProductImageUrl() {
        return driver.findElement(firstProductImage).getDomAttribute("src");
    }

    /**
     * Gets a list of all product image WebElements.
     */
    public List<WebElement> getAllProductImages() {
        return driver.findElements(allProductImages);
    }

    public void clickBackPackProductButton() {
        // Try to find the Remove button first, as that is the expected state after the first click
        try {
            driver.findElement(backPackProductRemoveButton).click();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // If Remove isn't found, click Add to Cart
            driver.findElement(backPackProductAddButton).click();
        }
    }

}

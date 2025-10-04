package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;
import org.openqa.selenium.WebElement;

public class InventoryPage {

    private WebDriver driver;

    private By allProductImages = By.cssSelector(".inventory_item_img img");

    // Locator for the first product image (for comparison)
    private By firstProductImage = By.xpath("(//img[@class='inventory_item_img'])[1]");

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
}

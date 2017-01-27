package ru.agrefj.seleniumtests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by armendavtan on 27.01.17.
 */
public class MainPage {
    private static WebDriverWait wait;


    public static void AddProductsToCart(WebDriver driver){
        WebElement firstProduct;

        for(int i =0; i < 3; i++) {
            firstProduct = driver.findElement(By.cssSelector("div.image-wrapper"));
            firstProduct.click();
            ProductPage.SelectSizeIfNeeded(driver,1);
            ProductPage.AddToCart(driver,i);
            ProductPage.ReturnToMainPage(driver);
        }
    }

    public static void OpenCartPage(WebDriver driver){
        WebElement openCartButton = driver.findElement(By.cssSelector("div#cart"));
        openCartButton.click();
    }
}

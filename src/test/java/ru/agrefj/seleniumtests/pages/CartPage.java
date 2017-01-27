package ru.agrefj.seleniumtests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by armendavtan on 27.01.17.
 */
public class CartPage {
    private static WebDriverWait wait;
    public static void RemoveCartItem(WebDriver driver){
        wait = new WebDriverWait(driver,10);
        for(int i=3;i>0;i--){
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("td.item"), i-1));
        }
    }
}

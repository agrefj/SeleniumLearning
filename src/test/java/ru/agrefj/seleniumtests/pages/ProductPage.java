package ru.agrefj.seleniumtests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

/**
 * Created by armendavtan on 27.01.17.
 */
public class ProductPage {
    private static WebDriverWait wait;

    public static boolean SelectSizeIfNeeded(WebDriver driver, Integer choose){
        List<WebElement> select= driver.findElements(By.cssSelector("select"));
        if(select.size() > 0) {
            Select menuItem = new Select(select.get(0));
            menuItem.selectByValue("Small");
            return true;
        }
        return false;
    }

    public static void AddToCart(WebDriver driver, Integer i){
        wait = new WebDriverWait(driver,10);wait = new WebDriverWait(driver,10);
        WebElement addToCartButton;
        addToCartButton = driver.findElement(By.cssSelector("[name='add_cart_product'"));
        addToCartButton.click();
        wait.until(textToBePresentInElement(driver.findElement(By.cssSelector("div#cart span.quantity")),Integer.toString(i+1)));
    }

    public static void ReturnToMainPage(WebDriver driver){
        WebElement homeButton;
        homeButton = driver.findElement(By.cssSelector(".general-0 a"));
        wait = new WebDriverWait(driver,10);
        homeButton.click();
    }

}

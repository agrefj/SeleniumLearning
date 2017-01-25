package ru.agrefj.seleniumtests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

/**
 * Created by armendavtan on 24.01.17.
 */
public class ShippingCartTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    public boolean selectSizeIfNeeded(int index){

        List<WebElement> select= driver.findElements(By.cssSelector("select"));
        if(select.size() > 0) {

            Select menuItem = new Select(select.get(0));
            menuItem.selectByValue("Small");

            return true;
        }
        return false;
    }

    @Test
    public void ShippingCartTest(){
        driver.get("http://localhost/litecart/en/");

        WebElement firstProduct;
        WebElement addToCartButton;
        WebElement homeButton;


        for(int i =0; i < 3; i++) {
            firstProduct = driver.findElement(By.cssSelector("div.image-wrapper"));
            firstProduct.click();
            selectSizeIfNeeded(1);
            addToCartButton = driver.findElement(By.cssSelector("[name='add_cart_product'"));
            addToCartButton.click();
            homeButton = driver.findElement(By.cssSelector(".general-0 a"));
            wait.until(textToBePresentInElement(driver.findElement(By.cssSelector("div#cart span.quantity")),Integer.toString(i+1)));
            homeButton.click();
        }

        WebElement openCartButton = driver.findElement(By.cssSelector("div#cart"));
        openCartButton.click();


        for(int i=3;i>0;i--){
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("td.item"), i-1));
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}

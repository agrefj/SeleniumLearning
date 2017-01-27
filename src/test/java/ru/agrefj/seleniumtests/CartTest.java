package ru.agrefj.seleniumtests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.agrefj.seleniumtests.base.BaseTest;
import ru.agrefj.seleniumtests.pages.CartPage;
import ru.agrefj.seleniumtests.pages.MainPage;

/**
 * Created by armendavtan on 27.01.17.
 */
public class CartTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void CartTest() {
        BaseTest.openMainPage(driver);
        MainPage.AddProductsToCart(driver);
        MainPage.OpenCartPage(driver);
        CartPage.RemoveCartItem(driver);
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}

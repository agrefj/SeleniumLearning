package ru.agrefj.seleniumtests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by armendavtan on 02.01.17.
 */
public class StickersCheckTest {


        private WebDriver webDriver;
        private WebDriverWait wait;


        @Before
        public void start() {
            webDriver = new ChromeDriver();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            wait = new WebDriverWait(webDriver,10);

        }


        @Test
        public void StickersCheckTest() {
            webDriver.get("http://localhost/litecart/");
            List<WebElement> products = new ArrayList<WebElement>();
            products = webDriver.findElements(By.cssSelector("li.product"));



            for(int i=0; i<products.size(); i++){
                WebElement product = products.get(i);
                List<WebElement> stickers = product.findElements(By.cssSelector(".sticker"));
                Assert.assertEquals(2,stickers.size());
            }

        }






        @After
        public void stop(){
            webDriver.quit();
            webDriver = null;
        }

    }

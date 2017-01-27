package ru.agrefj.seleniumtests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by armendavtan on 27.01.17.
 */
public class CheckLogsTest {
    private WebDriver webDriver;
    private WebDriverWait wait;

    @Before
    public void start() {
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver,10);
    }


    @Test
    public void CheckLogsTest(){
        webDriver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        webDriver.findElement(By.name("username")).sendKeys("admin");
        webDriver.findElement(By.name("password")).sendKeys("admin");
        webDriver.findElement(By.name("login")).click();
        webDriver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        List<WebElement> productsList = webDriver.findElements(By.cssSelector("td:nth-child(3)>a[href*=product_id]"));
        List<String> urls = new ArrayList<>();
        productsList.forEach((c)->urls.add(c.getAttribute("href")));
        for(String url:urls){
            webDriver.get(url);
            webDriver.manage().logs().get(LogType.BROWSER).getAll().forEach(l->System.out.println(l));
            assertEquals("result ok",0,webDriver.manage().logs().get(LogType.BROWSER).getAll().size());
        }
    }


    @After
    public void stop(){
        webDriver.quit();
        webDriver = null;
    }

}

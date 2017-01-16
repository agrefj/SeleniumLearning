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
import java.util.Collections;
import java.util.List;

import static ru.agrefj.seleniumtests.base.BaseTest.isSortedListString;

/**
 * Created by armendavtan on 17.01.17.
 */
public class GeoZonesSortTest  {

    private WebDriver webDriver;
    private WebDriverWait wait;

    @Before
    public void start() {
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver,10);
    }

    @Test
    public void  geoZonesSortForCountries(){
        webDriver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        webDriver.findElement(By.name("username")).sendKeys("admin");
        webDriver.findElement(By.name("password")).sendKeys("admin");
        webDriver.findElement(By.name("login")).click();


        List<WebElement> countriesNames = webDriver.findElements(By.cssSelector("[name=geo_zones_form] tr.row td:nth-child(3)"));

        for (int i = 0; i < countriesNames.size(); i++) {
            countriesNames = webDriver.findElements(By.cssSelector("[name=geo_zones_form] tr.row td:nth-child(3)"));
            countriesNames.get(i).findElement(By.cssSelector("a")).click();
            List<WebElement> geoZonesNames = webDriver.findElements(By.cssSelector("[name=form_geo_zone] td:nth-child(3) option[selected = 'selected']"));
            List <String> geoZonesNamesText = new ArrayList<>();

            for (WebElement geoZone : geoZonesNames) {
                geoZonesNamesText.add(geoZone.getAttribute("outerText"));
            }
            Assert.assertTrue("A list isn't sorted by alphabetical", isSortedListString(geoZonesNamesText));
            webDriver.findElement(By.cssSelector("button[name = 'cancel']")).click();
        }
    }

    @After
    public void stop(){
        webDriver.quit();
        webDriver = null;
    }

}
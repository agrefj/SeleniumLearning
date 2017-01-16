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
import ru.agrefj.seleniumtests.base.BaseTest;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by armendavtan on 14.01.17.
 */
public class CountriesSortTest {

    private WebDriver webDriver;
    private WebDriverWait wait;

    @Before
    public void start() {
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver,10);
    }

    @Test
    public void CountriesCheckTest() {
        webDriver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        webDriver.findElement(By.name("username")).sendKeys("admin");
        webDriver.findElement(By.name("password")).sendKeys("admin");
        webDriver.findElement(By.name("login")).click();


        List<WebElement> countriesNames = webDriver.findElements(By.cssSelector("[name=countries_form] tr.row td:nth-child(5)"));
        List <String> countryNamesText = countriesNames.stream().map(country -> country.getAttribute("outerText")).collect(Collectors.toList());

        Assert.assertTrue("A list is not sorted correctly", isSortedListString(countryNamesText));

        for (int i = 0; i < countriesNames.size(); i++) {
            List<WebElement> countriesRows = webDriver.findElements(By.cssSelector("[name=countries_form] tr.row"));
            WebElement countryZoneValue = countriesRows.get(i).findElement(By.cssSelector("td:nth-child(6)"));
            int countryZoneNumber = Integer.valueOf(countryZoneValue.getAttribute("innerText"));

            if (countryZoneNumber > 0) {
                countriesNames = webDriver.findElements(By.cssSelector("[name=countries_form] tr.row td:nth-child(5)"));
                countriesNames.get(i).findElement(By.cssSelector("a")).click();
                List<WebElement> zoneNamesForCountry = webDriver.findElements(By.cssSelector("table#table-zones tr > td:nth-child(3)"));
                zoneNamesForCountry.remove(zoneNamesForCountry.size()-1);
                List <String> zoneNamesForCountryText = zoneNamesForCountry.stream().map(zone -> zone.getAttribute("innerText")).collect(Collectors.toList());

                Assert.assertTrue("A list isn't sorted correctly", isSortedListString(zoneNamesForCountryText));
                webDriver.findElement(By.cssSelector("button[name = 'cancel']")).click();
            }
        }
    }


    public Boolean isSortedListString(List<String> list){
        List<String> newList = new ArrayList(list);
        Collections.sort(list);
        return list.equals(newList);
    }


    @After
    public void stop(){
        webDriver.quit();
        webDriver = null;
    }


}

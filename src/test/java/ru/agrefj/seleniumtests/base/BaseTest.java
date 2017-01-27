package ru.agrefj.seleniumtests.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by armendavtan on 02.01.17.
 */
public class BaseTest {


    public static boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }


    public static boolean isSortedListString(List<String> list){
        List<String> newList = new ArrayList(list);
        Collections.sort(list);
        return list.equals(newList);
    }

    public static void openMainPage (WebDriver driver) {
        driver.get("http://localhost/litecart/en/");
    }

    public static void authorize(WebDriver driver){
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

}

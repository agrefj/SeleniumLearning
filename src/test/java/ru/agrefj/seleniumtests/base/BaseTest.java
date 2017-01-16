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

    public WebDriver driver;
    public WebDriverWait wait;

    public static boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }


    public static boolean isSortedListString(List<String> list){
        List<String> newList = new ArrayList(list);
        Collections.sort(list);
        return list.equals(newList);
    }


}

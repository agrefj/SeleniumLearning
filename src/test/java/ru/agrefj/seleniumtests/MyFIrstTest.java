package ru.agrefj.seleniumtests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Created by armendavtan on 14.12.16.
 */
public class MyFIrstTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
            public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void MyFirstTest(){
        driver.get("https://www.google.ru/");

    }


    @After
    public void stop(){
        driver.quit();
        driver = null;
    }




}

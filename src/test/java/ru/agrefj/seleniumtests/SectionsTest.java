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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by armendavtan on 02.01.17.
 */
public class SectionsTest {

    private WebDriver webDriver;
    private WebDriverWait wait;

    @Before
    public void start() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(webDriver,10);

    }


    @Test
    public void AuthorizationTest() {
        //Authorization
        webDriver.get("http://localhost/litecart/admin/login.php");
        webDriver.findElement(By.name("username")).sendKeys("admin");
        webDriver.findElement(By.name("password")).sendKeys("admin");
        webDriver.findElement(By.name("login")).click();

        //Opening sections
        List<WebElement> menuItemsList = new ArrayList<WebElement>();
        List<WebElement> subMenuItemsList = new ArrayList<WebElement>();
        menuItemsList = webDriver.findElements(By.xpath("//*[@id='app-']"));
        int menuItemSize = menuItemsList.size();
        for(int i = 1; i < menuItemSize;) {
            webDriver.findElement(By.xpath("//*[@id='app-'][" + i + "]")).click();
            subMenuItemsList = webDriver.findElements(By.xpath("//*[@id='app-'][" + i + "]//li"));
            if (subMenuItemsList.size() > 1) {
                for (int j = 0;j < subMenuItemsList.size();){
                    subMenuItemsList.get(j).click();
                    subMenuItemsList = webDriver.findElements(By.xpath("//*[@id='app-'][" + i + "]//li"));
                    j++;
                }
            }
            Assert.assertTrue(BaseTest.areElementsPresent(webDriver, By.xpath("//td[@id='content']//h1")));
            i++;
        }
    }




    @After
    public void stop(){
        webDriver.quit();
        webDriver = null;
    }

}

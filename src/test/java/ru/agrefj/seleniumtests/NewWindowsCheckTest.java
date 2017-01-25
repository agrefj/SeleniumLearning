package ru.agrefj.seleniumtests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

/**
 * Created by armendavtan on 25.01.17.
 */
public class NewWindowsCheckTest {

    private WebDriver webDriver;
    private WebDriverWait wait;

    @Before
    public void start() {
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver,10);
    }

    public void checkNewWindowAndReturn(WebElement link) {

        String currentWindow = webDriver.getWindowHandle();
        Set<String> oldWindows = webDriver.getWindowHandles();
        link.click();
        String newWindow = wait.until(checkOtherWindow(oldWindows));
        webDriver.switchTo().window(newWindow);
        webDriver.close();
        webDriver.switchTo().window(currentWindow);

    }

    public ExpectedCondition<String> checkOtherWindow(Set<String> oldWindows){
        return new ExpectedCondition<String>() {
            public String apply(WebDriver webDriver) {
                Set<String> handles = webDriver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }


    @Test
    public void NewWindowsCheckTest(){
        webDriver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        webDriver.findElement(By.name("username")).sendKeys("admin");
        webDriver.findElement(By.name("password")).sendKeys("admin");
        webDriver.findElement(By.name("login")).click();
        webDriver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        WebElement addNewCountryButton = webDriver.findElement(By.cssSelector("div[style='float: right;'] a"));
        addNewCountryButton.click();

        List<WebElement> linksList = webDriver.findElements(By.cssSelector("a[target = '_blank'] i.fa.fa-external-link"));

        for (Integer i = 0; i < linksList.size(); i++){
            WebElement externalLink = linksList.get(i);
            checkNewWindowAndReturn(externalLink);
        }





    }


    @After
    public void stop(){
        webDriver.quit();
        webDriver = null;
    }

}

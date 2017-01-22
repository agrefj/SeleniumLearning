package ru.agrefj.seleniumtests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by armendavtan on 22.01.17.
 */
public class UserRegistrationTest {

    private WebDriver webDriver;
    private WebDriverWait wait;

    @Before
    public void start() {
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver,10);
    }

    public void insertText(String text, String locator){
        WebElement element = webDriver.findElement(By.cssSelector(locator));
        element.clear();
        element.sendKeys(text);
    }

    public void fillSelectForm(String code, String locatorSearch){
        Select menuItem = new Select(webDriver.findElement(By.cssSelector(locatorSearch)));
        menuItem.selectByValue(code);
    }


    @Test
    public void UserRegistrationTest() {
        webDriver.get("http://localhost/litecart/en/");
        WebElement registrationLink = webDriver.findElement(By.cssSelector("#box-account-login > div > form > table > tbody > tr:nth-child(5) > td > a"));
        registrationLink.click();

        String email = "seleniumtestingemail1230@mailforspam.com";

        insertText("Ivan","input[name='firstname']");
        insertText("Ivanov","input[name='lastname']");
        insertText("Address","input[name='address1']");
        insertText("123456","input[name='postcode']");
        insertText("Moscow","input[name='city']");
        fillSelectForm("RU","select[name='country_code']");
        insertText(email,"input[name='email']");
        insertText("+71234567890","input[name='phone']");
        insertText("1234Pass","input[name='password']");
        insertText("1234Pass","input[name='confirmed_password']");

        WebElement createAccount = webDriver.findElement(By.cssSelector("button[name='create_account']"));
        createAccount.click();

        WebElement logoutLink = webDriver.findElement(By.cssSelector("#box-account > div > ul > li:nth-child(4) > a"));
        logoutLink.click();

        insertText (email, "input[name='email']");
        insertText ("1234Pass", "input[name='password']");

        WebElement loginButton = webDriver.findElement(By.cssSelector("button[name = 'login']"));
        loginButton.click();

        logoutLink = webDriver.findElement(By.cssSelector("#box-account > div > ul > li:nth-child(4) > a"));
        logoutLink.click();
    }


    @After
    public void stop(){
        webDriver.quit();
        webDriver = null;
    }
}


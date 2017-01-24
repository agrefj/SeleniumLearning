package ru.agrefj.seleniumtests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

/**
 * Created by armendavtan on 22.01.17.
 */
public class AddNewProductTest {

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

    public Boolean checkProductIsInList(String name, String locator) {
        List<WebElement> Products = webDriver.findElements(By.cssSelector(locator));
        Boolean result = false;
        for (WebElement product : Products) {
            if (name.equals(product.getText())) {
                result = true;
            }
        }
        return result;
    }

    public void selectCheckBox(String name, String locator, String tagName){
        List<WebElement> elements = webDriver.findElements(By.cssSelector(locator));

        for (int i=0; i < elements.size(); i++) {
            if (name.equals(elements.get(i).getAttribute(tagName))) {
                if (elements.get(i).getTagName().equals("input")) {
                    elements.get(i).click();
                }
                else {
                    elements.get(i-1).findElement(By.tagName("input")).click();
                }
            }
        }
    }


        @Test
    public void AddNewProductTest(){
        webDriver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        webDriver.findElement(By.name("username")).sendKeys("admin");
        webDriver.findElement(By.name("password")).sendKeys("admin");
        webDriver.findElement(By.name("login")).click();

        WebElement addProductButton = webDriver.findElement(By.cssSelector("#content a.button:nth-child(2)"));
        addProductButton.click();
        insertText("new product","input[name = 'name[en]']");
        insertText("test1","input[name = 'code']");
        String data = "data-name";
        selectCheckBox("Rubber Ducks","input[name = 'categories[]']","data-name");
        selectCheckBox("Female", "#tab-general tr:nth-child(7) div.input-wrapper tr td", "outerText");
        insertText("10","input[name = 'quantity']");

        File imageFile = new File("src/testimage.png");
        WebElement imageLocator = webDriver.findElement(By.cssSelector("input[name = 'new_images[]']"));
        imageLocator.sendKeys(imageFile.getAbsolutePath());

        WebElement dateValidFrom = webDriver.findElement(By.cssSelector("input[name = 'date_valid_from']"));
        dateValidFrom.sendKeys("01.01.2017");
        WebElement dateValidTo = webDriver.findElement(By.cssSelector("input[name = 'date_valid_to']"));
        dateValidTo.sendKeys("01.01.2018");

        WebElement informationTabButton = webDriver.findElement(By.cssSelector("[href='#tab-information']"));
        informationTabButton.click();

        Select manufacturer = new Select(webDriver.findElement(By.cssSelector("[name='manufacturer_id']")));
        manufacturer.selectByVisibleText("ACME Corp.");

        insertText("test","[name='keywords']");
        insertText("short description","[name='short_description[en]']");
        insertText("description","[name='description[en]']");
        insertText("head titile","[name='head_title[en]']");
        insertText("meta description","[name='meta_description[en]']");

        WebElement pricesTabButton = webDriver.findElement(By.cssSelector("[href='#tab-prices']"));
        pricesTabButton.click();

        insertText("25","[name='purchase_price']");
        Select currency = new Select(webDriver.findElement(By.cssSelector("[name='purchase_price_currency_code']")));
        currency.selectByVisibleText("Euros");
        insertText("32","[name='prices[EUR]']");

        WebElement saveButton = webDriver.findElement(By.cssSelector("[name='save']"));
        saveButton.click();

        webDriver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        Assert.assertTrue("There is not such product",  checkProductIsInList("new product",".dataTable tr td a"));
    }

    @After
    public void stop(){
        webDriver.quit();
        webDriver = null;
    }
}

package ru.agrefj.seleniumtests;

import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by armendavtan on 22.01.17.
 */
public class CheckProductPageTest {

    private WebDriver webDriver;
    private WebDriverWait wait;

    @Before
    public void start() {
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver,10);
    }

    @Test
    public void CheckProductPageTest() {
        webDriver.get("http://localhost/litecart/en/");
        WebElement product = webDriver.findElement(By.cssSelector("div#box-campaigns a"));

        //check text
        String productText = product.findElement(By.cssSelector("div.name")).getAttribute("innerText");


        //check priceText
        String priceText = product.findElement(By.cssSelector("div.price-wrapper .regular-price")).getAttribute("innerText");

        //check priceColor
        String priceColor = product.findElement(By.cssSelector("div.price-wrapper .regular-price")).getCssValue("color");
        Assert.assertThat(priceColor, IsEqual.equalTo("rgba(119, 119, 119, 1)"));

        //check priceFontSize
        String priceFontSize = product.findElement(By.cssSelector("div.price-wrapper .regular-price")).getCssValue("font-size");
        Assert.assertThat(priceFontSize, IsEqual.equalTo("14.4px"));

        //check priceFontStyle
        String priceFontStyle = product.findElement(By.cssSelector("div.price-wrapper .regular-price")).getCssValue("text-decoration");
        Assert.assertThat(priceFontStyle, IsEqual.equalTo("line-through"));

        //check salePriceText
        String salePriceText = product.findElement(By.cssSelector("div.price-wrapper .campaign-price")).getAttribute("innerText");


        //check salePriceColor
        String salePriceColor = product.findElement(By.cssSelector("div.price-wrapper .campaign-price")).getCssValue("color");
        Assert.assertThat(salePriceColor, IsEqual.equalTo("rgba(204, 0, 0, 1)"));

       //check salePriceFontSize
        String salePriceFontSize = product.findElement(By.cssSelector("div.price-wrapper .campaign-price")).getCssValue("font-size");
        Assert.assertThat(salePriceFontSize, IsEqual.equalTo("18px"));

        //check salePriceFontStyle
        String salePriceFontStyle = product.findElement(By.cssSelector("div.price-wrapper .campaign-price")).getCssValue("font-weight");
        Assert.assertThat(salePriceFontStyle, IsEqual.equalTo("bold"));

        product.click();

        String detailsPageProductText = webDriver.findElement(By.cssSelector("div#box-product h1.title")).getAttribute("innerText");

        String detailsPageProductRegPriceText = webDriver.findElement(By.cssSelector("div.information div.price-wrapper .regular-price")).getAttribute("innerText");
        String detailsPageProductRegPriceColor = webDriver.findElement(By.cssSelector("div#box-product div.price-wrapper .regular-price")).getCssValue("color");
        String detailsPageProductRegPriceFontSize = webDriver.findElement(By.cssSelector("div.information div.price-wrapper .regular-price")).getCssValue("font-size");
        String detailsPageProductRegPriceFontStyle = webDriver.findElement(By.cssSelector("div.information div.price-wrapper .regular-price")).getCssValue("text-decoration");

        String detailsPageProductSalePriceText = webDriver.findElement(By.cssSelector("div.information div.price-wrapper .campaign-price")).getAttribute("innerText");
        String detailsPageProductSalePriceColor = webDriver.findElement(By.cssSelector("div.information div.price-wrapper .campaign-price")).getCssValue("color");
        String detailsPageProductSalePriceFontSize = webDriver.findElement(By.cssSelector("div.information div.price-wrapper .campaign-price")).getCssValue("font-size");
        String detailsPageProductSalePriceFontStyle = webDriver.findElement(By.cssSelector("div.information div.price-wrapper .campaign-price")).getCssValue("font-weight");

        Assert.assertThat(productText, IsEqual.equalTo(detailsPageProductText));
        Assert.assertThat(priceText, IsEqual.equalTo(detailsPageProductRegPriceText));
        Assert.assertThat(salePriceText, IsEqual.equalTo(detailsPageProductSalePriceText));

        Assert.assertThat(detailsPageProductRegPriceColor, IsEqual.equalTo("rgba(102, 102, 102, 1)"));
        Assert.assertThat(detailsPageProductRegPriceFontSize, IsEqual.equalTo("16px"));
        Assert.assertThat(detailsPageProductRegPriceFontStyle, IsEqual.equalTo("line-through"));

        Assert.assertThat(detailsPageProductSalePriceColor, IsEqual.equalTo("rgba(204, 0, 0, 1)"));
        Assert.assertThat(detailsPageProductSalePriceFontSize, IsEqual.equalTo("22px"));
        Assert.assertThat(detailsPageProductSalePriceFontStyle, IsEqual.equalTo("bold"));

    }



    @After
    public void stop(){
        webDriver.quit();
        webDriver = null;
    }


}

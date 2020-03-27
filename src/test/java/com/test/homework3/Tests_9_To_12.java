package com.test.homework3;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Tests_9_To_12 {

private WebDriver driver;
private By statusCodeBtn = By.linkText("Status Codes");

@DataProvider (name="testData")
public static Object[] testData(){


    return new Object[] {"200","301","404","500"};
}



@Test (dataProvider="testData")

public void Test9_12 (String value) throws InterruptedException {


    driver.findElement(By.linkText(value)).click();
    Thread.sleep(2000);
    Assert.assertTrue(driver.findElement(By.xpath("//p")).getText().trim().
            contains("This page returned a "+value+" status code."));


}

@BeforeMethod
    public void setup() throws InterruptedException {
    WebDriverManager.chromedriver().version("79").setup();
    driver =new ChromeDriver();
    driver.manage().window().maximize();
    Thread.sleep(2000);
    driver.get("https://practice-cybertekschool.herokuapp.com/");
    Thread.sleep(3000);
    driver.findElement(statusCodeBtn).click();
    Thread.sleep(2000);

}

@AfterMethod
    public void tearDown(){
    driver.quit();
}





}
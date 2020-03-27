package com.test.homework3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.http.impl.io.IdentityInputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Tests_6_To_8 {

    private WebDriver driver;
    private String webUrl = "https://practice-cybertekschool.herokuapp.com/";
    private String emailUrl = "https://www.tempmailaddress.com/";
    private By signUpEmailBy = By.xpath("//a[@href=\"/sign_up\" and contains (text(), Mailing)]");
    private By fullNameRegBy = By.name("full_name");
    private By emailRegBy = By.name("email");
    private By emailSignUpButtonBy = By.name("wooden_spoon");
    private Actions actions;
    private By fileUploadBtn = By.xpath("//a[text()='File Upload']");
    private By chooseFileBtn = By.id("file-upload");
    private By uploadBtnBy = By.id("file-submit");
    private By autoComplete = By.xpath("//a[text()='Autocomplete']");


    @Test(description = "Email registration")
    private void TestCase6() throws InterruptedException {
        driver.get(emailUrl);
        String tempEmail = driver.findElement(By.cssSelector("span[id='email']")).getText();
        Thread.sleep(3000);

        driver.get(webUrl);
        Thread.sleep(3000);
        driver.findElement(signUpEmailBy).click();

        driver.findElement(fullNameRegBy).sendKeys("Richard Ekebas");
        driver.findElement(emailRegBy).sendKeys(tempEmail);
        Thread.sleep(3000);
        driver.findElement(emailSignUpButtonBy).click();
        String expected = "Thank you for signing up. Click the button below to return to the home page.";
        String actual = driver.findElement(By.cssSelector("h3[class='subheader']")).getText();
        Assert.assertEquals(actual, expected);
        Thread.sleep(3000);
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
        Thread.sleep(3000);
        WebElement receivedEmail=driver.findElement(By.cssSelector(".from"));
        String emailReceived =receivedEmail.getText().trim();
        // System.out.println("emailReceived = " + emailReceived);

        String expected2="do-not-reply@practice.cybertekschool.com";

        Assert.assertEquals(emailReceived,expected);

        receivedEmail.click();

        WebElement from=driver.findElement(By.cssSelector("[id='odesilatel']"));
        String actual2=from.getText().trim();

        Assert.assertEquals(actual2,expected2);

        WebElement subject=driver.findElement(By.cssSelector("[id='predmet']"));
        String subjectActual=subject.getText().trim();
        String expectedSubject="Thanks for subscribing to practice.cybertekschool.com!";

        Assert.assertEquals(subjectActual,expectedSubject);



    }

    @Test(description = "File Upload")
    public void TestCase7() throws InterruptedException {
        driver.get(webUrl);
        driver.findElement(fileUploadBtn).click();
        Thread.sleep(4000);
        String filePath = "C:\\Users\\Burati\\Desktop\\CYBERTEK DOCUMENTS\\softskills.txt";
        driver.findElement(chooseFileBtn).sendKeys(filePath);
        Thread.sleep(3000);
        driver.findElement(uploadBtnBy).click();
        Thread.sleep(5000);
        String expected = "softskills.txt";
        String actual = driver.findElement(By.id("uploaded-files")).getText();

        Assert.assertEquals(actual, expected);

    }

    @Test(description = "Autocomplete")
    public void TestCase8() throws InterruptedException {
        driver.get(webUrl);
        driver.findElement(autoComplete).click();
        driver.findElement(By.id("myCountry")).sendKeys("United States of America");
        Thread.sleep(4000);
        driver.findElement(By.xpath("//input[@type='hidden']/preceding-sibling::strong")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//input[@type='button']")).click();

        Thread.sleep(3000);
        String expected = "You selected: United States of America";

        String actual = driver.findElement(By.id("result")).getText();
        Thread.sleep(3000);
    }


    @BeforeMethod
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //Thread.sleep(4000);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();

    }

}




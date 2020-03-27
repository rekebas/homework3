package com.test.homework3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Tests_1_To_5 {


        private WebDriver driver;
        private Actions actions;
        private String url ="https://practice-cybertekschool.herokuapp.com/";
        private By registrationForm = By.xpath("//a[text()='Registration Form']");
        private By dateOfBirth = By.cssSelector("[name='birthday']");
        private By invalidBirthData = By.cssSelector("[data-bv-result ='INVALID']");
        private By prgLanguage1=By.xpath("//input[@id ='inlineCheckbox1']/following-sibling::label");
        private By prgLanguage2=By.xpath("//input[@id ='inlineCheckbox2']/following-sibling::label");
        private By prgLanguage3=By.xpath("//input[@id ='inlineCheckbox3']/following-sibling::label");
        private By firstNameBy =By.cssSelector("[name='firstname']");
        private By lastNameBy= By.cssSelector("input[name='lastname']");
        private By userNameBy =By.cssSelector("input[placeholder='username']");
        private By emailBy = By.xpath("//input[@placeholder='email@email.com']");
        private By passwordBy = By.xpath("//input[@class='form-control' and contains (@name,'password')]");
        private By telNoBy =By.cssSelector("input[type='tel']");
        private By genderBy = By.cssSelector("input[value='male']");
        private By birthDateBy = By.cssSelector("input[placeholder='MM/DD/YYYY']");
        private By planguageBy = By.cssSelector("input[value='java']");
        private By submitBy = By.id("wooden_spoon");






    @Test(description = " Verify that warning message is displayed:\n" +
            "“The date of birth is not valid”\n ")
    public void testCase1() throws InterruptedException {

   driver.findElement(dateOfBirth).sendKeys("wrong_dob");
   String expected = "The date of birth is not valid";
   String actual = driver.findElement(invalidBirthData).getText();
   Assert.assertEquals(expected, actual);
   Thread.sleep(5000);
    }

   @Test (description = " Verify that following options for\n" +
           "programming languages are displayed: c++, java,\n" +
           "JavaScript")
   public void testCase2(){
        String expectedC ="C++";
        String expectedJava="Java";
        String expectedJavaS="JavaScript";
        String actualC=driver.findElement(prgLanguage1).getText();
        String actualJava=driver.findElement(prgLanguage2).getText();
        String actualJavas=driver.findElement(prgLanguage3).getText();

        Assert.assertEquals(expectedC, actualC) ;
        Assert.assertEquals(expectedJava, actualJava) ;
        Assert.assertEquals(expectedJavaS, actualJavas) ;
   }

   @Test (description =  "Verify that warning message is displayed")
           public void testCase3() throws InterruptedException {
      driver.findElement(firstNameBy).sendKeys("a");
      Thread.sleep(2000);
      String expectedWarn ="first name must be more than 2 and less than 64 characters long";
      String actualWarn =driver.findElement(By.xpath("//small[contains(text(),'first name')][2]")).getText();
      Assert.assertEquals(expectedWarn, actualWarn);

   }

   @Test (description =" Verify that last name warning message is displayed ")
           public void testCase4() throws InterruptedException {

        driver.findElement(lastNameBy).sendKeys("a");
       Thread.sleep(2000);
        String expectedWarn ="The last name must be more than 2 and less than 64 characters long";
        String actualWarn = driver.findElement(By.xpath("//small[@class='help-block' and contains(text(),'last name')][2]")).getText();
        Assert.assertEquals(expectedWarn, actualWarn);
   }


   @Test

   public void testCase5() throws InterruptedException {

        driver.findElement(firstNameBy).sendKeys("Richard");
        driver.findElement(lastNameBy).sendKeys("Ekebas");
        driver.findElement(userNameBy).sendKeys("richard123");
        driver.findElement(emailBy).sendKeys("richard_ekebas@gmail.com");
        driver.findElement(passwordBy).sendKeys("richard123");
        driver.findElement(telNoBy).sendKeys("444-565-5678");
        driver.findElement(genderBy).click();
        driver.findElement(birthDateBy).sendKeys("01/01/2000");
        WebElement department = driver.findElement(By.cssSelector("select[name='department']"));
        Select selectDepartment =new Select( department);
        selectDepartment.selectByVisibleText("Department of Agriculture");
        WebElement jobTitle = driver.findElement(By.cssSelector("select[name='job_title']"));
        Select jobTitleSelect =new Select(jobTitle);
        jobTitleSelect.selectByVisibleText("Manager");
        driver.findElement(planguageBy).click();
        Thread.sleep(5000);
        driver.findElement(submitBy).click();
        String actual = driver.findElement(By.tagName("p")).getText();
        String expected ="You've successfully completed registration!";
        Assert.assertEquals(actual, expected);
        Thread.sleep(5000);

   }

   @BeforeMethod
   public void setup() throws InterruptedException {
   WebDriverManager.chromedriver().version("79").setup();
   driver=new ChromeDriver();
   driver.get(url);
   driver.manage().window().maximize();
   Thread.sleep(3000);
   driver.findElement(registrationForm).click();
   Thread.sleep(3000);


        }

    @AfterMethod
    public void tearDown(){
   driver.quit();
    }

    }


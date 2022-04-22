package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_08_TextBox_TextArea {
    WebDriver driver;
    String emailAddress, loginUrl, userID, password,
            name, genderValue, dateOfBirthInput, dateOfBirthOutput,
            addressInput, addressOutPut, city, state, pinNumber, phoneNumber;
    String projectPath = System.getProperty("user.dir");
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");

        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/v4/");

        loginUrl = driver.getCurrentUrl();
        emailAddress = "johndeep" + generateRandomNumber() + "@hotmail.com";
        name = "John Deep";
        genderValue = "male";
        dateOfBirthInput = "08/15/1994";
        dateOfBirthOutput = "1994-08-15";
        addressInput = "123 PO Box\nLos Angeles\nUnited States";
        addressOutPut = "123 PO Box Los Angeles United States";
        city = "Hawaii";
        state = "Los Angeles";
        pinNumber = "126589";
        phoneNumber = "0987566325";
    }

    @Test
    public void TC_01_Register() {
        driver.findElement(By.xpath("//a[text()='here']")).click();

        driver.findElement(By.name("emailid")).sendKeys(emailAddress);

        driver.findElement(By.name("btnLogin")).click();

        userID = driver.findElement(
                By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        password = driver.findElement(
                By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
    }

    @Test
    public void TC_02_Login() {
        driver.get(loginUrl);

        driver.findElement(By.name("uid")).sendKeys(userID);
        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(By.name("btnLogin")).click();

        // Verify home page is displayed
        Assert.assertEquals(
                driver.findElement(
                        By.cssSelector("tr.heading3>td")).getText(),
                "Manger Id : " + userID);
    }

    @Test
    public void TC_03_Create_New_Customer() {
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();

        //Input
        driver.findElement(By .name("name")).sendKeys(name);
        driver.findElement(By.xpath("//input[@value='m']")).click();

        WebElement dateOfBirthTextBox = driver.findElement(By.name("dob"));
        jsExecutor.executeScript("arguments[0].removeAttribute('type')", dateOfBirthTextBox);
        dateOfBirthTextBox.sendKeys(dateOfBirthInput);
        sleepInSecond(6);

        driver.findElement(By.name("addr")).sendKeys(addressInput);
        driver.findElement(By.name("city")).sendKeys(city);
        driver.findElement(By.name("state")).sendKeys(state);
        driver.findElement(By.name("pinno")).sendKeys(pinNumber);
        driver.findElement(By.name("telephoneno")).sendKeys(phoneNumber);
        driver.findElement(By.name("emailid")).sendKeys(emailAddress);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("sub")).click();
        sleepInSecond(4);
        //Output -> Verify
        Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(),
                "Customer Registered Successfully!!!");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),
                name);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
                genderValue);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
                dateOfBirthOutput);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
                addressOutPut);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),
                city);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),
                pinNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
                phoneNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
                emailAddress);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int generateRandomNumber() {
        Random rand = new Random();
        return  rand.nextInt(99);
    }
}

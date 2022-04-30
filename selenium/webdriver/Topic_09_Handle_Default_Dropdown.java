package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_09_Handle_Default_Dropdown {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    Select select;
    String firstName, lastName, day,
            month, year, companyName, password, email, address;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();

        firstName = "Automation";
        lastName = "FC";
        day = "23";
        month = "May";
        year = "1993";
        companyName = "Automation FC";
        password = "123123";
        email = "automation" + generateRandomNumber() + "@hotmail.com";
        address = "adc, zyx";

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_NopCommerce() {
        driver.get("https://demo.nopcommerce.com/register");

        //I - Action (Input Data)
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);

        // Dropdown DayOfBirth
        select = new Select(driver.findElement(By.name("DateOfBirthDay")));
        select.selectByVisibleText(day);

        Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
        Assert.assertFalse(select.isMultiple());

        sleepInSecond(3);

        // Dropdown Month
        select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        select.selectByVisibleText(month);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

        sleepInSecond(3);
        // Dropdown Year
        select = new Select(driver.findElement(By.name("DateOfBirthYear")));
        select.selectByVisibleText(year);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Company")).sendKeys(companyName);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
        sleepInSecond(5);
        driver.findElement(By.id("register-button")).click();
        sleepInSecond(5);
        //II - Verify (Output Data)
        Assert.assertEquals(driver.findElement(By.className("result")).getText(),
                "Your registration completed 1");
        driver.findElement(By.className("ico-account")).click();
        sleepInSecond(5);
        Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"),
                firstName);
        Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"),
                lastName);
        Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"),
                email);
        Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"),
                companyName);

        select = new Select(driver.findElement(By.name("DateOfBirthDay")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), day);

        select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

        select = new Select(driver.findElement(By.name("DateOfBirthYear")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
        sleepInSecond(5);

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

package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_05_Xpath_Login {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String firstName, fullName, lastName, email, password;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        firstName = "Automation";
        lastName = "FC";
        fullName = firstName + " " + lastName;
        email = "afc" + generateRandomNumber() + "@hotmail.vn";
        password = "123456";
    }

    @Test
    public void TC_01_Empty_Data() {
        driver.get("http://live.techpanda.org/index.php");

        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        driver.findElement(By.id("email")).clear();
        driver.findElement(By.name("login[password]")).clear();
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),
                "This is a required field.");
        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),
                "This is a required field.");
    }

    @Test
    public void TC_02_Invalid_Email() {
        driver.get("http://live.techpanda.org/index.php");

        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        driver.findElement(By.id("email")).sendKeys("1234@1234.123");
        driver.findElement(By.name("login[password]")).sendKeys("123456") ;
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),
                "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_03_Invalid_Password() {
        driver.get("http://live.techpanda.org/index.php");

        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
        driver.findElement(By.name("login[password]")).sendKeys("123") ;
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),
                "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_Incorrect_Email_Password() {
        driver.get("http://live.techpanda.org/index.php");

        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        driver.findElement(By.id("email")).sendKeys("automation1@gmail.com");
        driver.findElement(By.name("login[password]")).sendKeys("123333") ;
        driver.findElement(By.cssSelector("button[title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),
                "Invalid login or password.");
    }

    @Test
    public void TC_05_Create_New_Account() {
        driver.get("http://live.techpanda.org/index.php");

        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(By.cssSelector("a[title='Create an Account'] span span")).click();

        driver.findElement(By.id("firstname")).sendKeys(firstName);
        driver.findElement(By.id("lastname")).sendKeys(lastName);
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("confirmation")).sendKeys(password);

        driver.findElement(By.cssSelector("button[title='Register']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "Thank you for registering with Main Website Store.");

        String contactInfoText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p"))
                .getText();
        System.out.println(contactInfoText);
        System.out.println(fullName);
        System.out.println(email);
        Assert.assertTrue(contactInfoText.contains(fullName));
        Assert.assertTrue(contactInfoText.contains(email));

        driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }

    @Test
    public void TC_06_Valid_Email_Password() {
        driver.get("http://live.techpanda.org/index.php");

        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.name("login[password]")).sendKeys(password) ;
        driver.findElement(By.cssSelector("button[title='Login']")).click();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int generateRandomNumber() {
        Random rand = new Random();
        return  rand.nextInt(99);
    }
}

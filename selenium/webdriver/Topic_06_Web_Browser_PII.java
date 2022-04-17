package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_Web_Browser_PII {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_PageUrl() {
        driver.get("http://live.techpanda.org/index.php");

        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void TC_02_PageTitle() {
        driver.get("http://live.techpanda.org/index.php");

        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        Assert.assertEquals(driver.getTitle(), "Customer Login");

        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void TC_03_Navigation() {
        driver.get("http://live.techpanda.org/index.php");

        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

        driver.navigate().back();

        Assert.assertEquals(driver.getTitle(), "Customer Login");

        driver.navigate().forward();

        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void TC_04_Page_Source() {
        driver.get("http://live.techpanda.org/index.php");

        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        String loginPageSource = driver.getPageSource();

        Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));

        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

        String registerPageSource = driver.getPageSource();

        Assert.assertTrue(registerPageSource.contains("Create an Account"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

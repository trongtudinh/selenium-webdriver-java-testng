package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_() {
        //Selenium has 8 locator type
        // Id
        driver.findElement(By.id("email")).sendKeys("test@abc.com");

        // Class
        driver.findElement(By.className("fb_logo")).isDisplayed();

        // Name
        driver.findElement(By.name("email")).isDisplayed();

        // Tagname
        driver.findElement(By.tagName("a"));

        // Link Text
        driver.findElement(By.linkText("English (UK)"));

        // Partial Link Text
        driver.findElement(By.partialLinkText("English (UK)"));

        // XPath
        driver.findElement(By.xpath("//a"));
        driver.findElement(By.xpath("//a[text()='English (UK)']"));
        driver.findElement(By.xpath("//a[contains(text(), 'English (UK)')]"));
        driver.findElement(By.xpath("//input[@id='email']"));
        driver.findElement(By.xpath("//input[@name='email']"));
        driver.findElement(By.xpath("//img[@class='fb_logo _8ilh img']"));
        driver.findElement(By.xpath("//img[contains(@class, 'fb_logo')]"));
        driver.findElement(By.xpath("//img[start-with(@class, 'fb_logo')]"));

        // Css
        // Find by id
        driver.findElement(By.cssSelector("input[id='email']"));
        driver.findElement(By.cssSelector("input#email"));
        driver.findElement(By.cssSelector("#email"));

        // Find by class
        driver.findElement(By.cssSelector("img[class='fb_logo _8ilh img']"));
        driver.findElement(By.cssSelector("img.fb_logo"));
        driver.findElement(By.cssSelector(".fb_logo"));

        // Find by name
        driver.findElement(By.cssSelector("img[name='email']"));

        driver.findElement(By.cssSelector("a"));

        //Css don't work with text
        driver.findElement(By.cssSelector("a[title='Vietnamese']"));
        driver.findElement(By.cssSelector("a[title*='Vietnamese']"));
        driver.findElement(By.cssSelector("a[onClick*='vi_VN']"));
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
}
package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_03_Locator_Perfomance {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @Test
    public void TC_01_ID() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
            driver.findElement(By.id("email"));
        }

        driver.quit();
    }

    @Test
    public void TC_02_Css() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        for (int i = 0; i < 1000; i++) {
            System.out.println("CSS lan thu:  " + i);
            driver.findElement(By.cssSelector("input[title='Email Address']"));
        }

        driver.quit();
    }

    @Test
    public void TC_03_Xpath() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        for (int i = 0; i < 1000; i++) {
            System.out.println("XPath lan thu: " + i);
            driver.findElement(By.xpath("//input[@title='Email Address']"));
        }

        driver.quit();
    }
}

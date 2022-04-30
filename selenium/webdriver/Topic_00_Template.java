package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_00_Template {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    Alert alert;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
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

    public void clickByJS(By by) {
        WebElement element = driver.findElement(by);
        jsExecutor.executeScript("arguments[0].click()", element);
    }

    public void removeAttributeByJs(String attribute, By by) {
        WebElement element = driver.findElement(by);
        jsExecutor.executeScript("arguments[0].removeAttribute('"+attribute+"')", element);
    }
}
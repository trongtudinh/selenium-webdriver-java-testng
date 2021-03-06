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

public class Topic_11_Button_Default_Radio_Checkbox {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");

        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        WebElement loginBtn = driver.findElement(By.cssSelector(".fhs-btn-login"));
        Assert.assertFalse(loginBtn.isEnabled());

        driver.findElement(By.id("login_username")).sendKeys("tudt@manebi.co");
        driver.findElement(By.id("login_password")).sendKeys("12362227");
        sleepInSecond(1);
        Assert.assertTrue(loginBtn.isEnabled());

        String backgroundColor = loginBtn.getCssValue("background-color");
        System.out.println(backgroundColor);
        Assert.assertEquals(backgroundColor, "rgb(201, 33, 39)");

        driver.navigate().refresh();
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        loginBtn = driver.findElement(By.cssSelector(".fhs-btn-login"));
        jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", loginBtn);
        sleepInSecond(2);

        Assert.assertTrue(loginBtn.isEnabled());
        loginBtn.click();

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_username']/parent::div/following-sibling::div[@class='fhs-input-alert']")).getText(),
                "Th??ng tin n??y kh??ng th??? ????? tr???ng");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_password']/parent::div/following-sibling::div[@class='fhs-input-alert']")).getText(),
                "Th??ng tin n??y kh??ng th??? ????? tr???ng");
    }

    @Test
    public void TC_02_Default_Radio() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        WebElement checkBoxElement = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding::input[1]"));
        checkBoxElement.click();

        Assert.assertTrue(checkBoxElement.isSelected());

        checkBoxElement.click();

        Assert.assertFalse(checkBoxElement.isSelected());
    }

    @Test
    public void TC_03_Default_Checkbox() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        WebElement checkBoxElement = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
        checkBoxElement.click();

        Assert.assertTrue(checkBoxElement.isSelected());

        driver.findElement(By.xpath("//label[text()='1.6 Diesel, 77kW']/preceding-sibling::input")).click();

        Assert.assertFalse(checkBoxElement.isSelected());
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

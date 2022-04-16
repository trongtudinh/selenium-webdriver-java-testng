package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_XPath_Register {
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
    public void TC_01_Empty_Data() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),
                "Vui lòng nhập họ tên");

        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),
                "Vui lòng nhập email");

        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),
                "Vui lòng nhập lại địa chỉ email");

        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),
                "Vui lòng nhập mật khẩu");

        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),
                "Vui lòng nhập lại mật khẩu");

        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),
                "Vui lòng nhập số điện thoại.");
    }

    @Test
    public void TC_02_Invalid_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Hoang Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("123@456@666");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@456@666");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("0856727864");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),
                "Vui lòng nhập email");

        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),
                "Vui lòng nhập lại địa chỉ email");
    }

    @Test
    public void TC_03_Incorrect_Confirm_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Hoang Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("hoangnguyen@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("hoangnguyen@hotmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("0856727864");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),
                "Vui lòng nhập lại địa chỉ email");
    }

    @Test
    public void TC_04_Password_Less_Than_6_Characters() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Hoang Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("hoangnguyen@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("hoangnguyen@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("12345");
        driver.findElement(By.id("txtCPassword")).sendKeys("12345");
        driver.findElement(By.id("txtPhone")).sendKeys("0856727864");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),
                "Mật khẩu phải có ít nhất 6 ký tự");

        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),
                "Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC_05_Incorrect_ConfirmPassword() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Hoang Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("hoangnguyen@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("hoangnguyen@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123457");
        driver.findElement(By.id("txtPhone")).sendKeys("0856727864");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),
                "Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void TC_06_Invalid_Phone() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Hoang Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("hoangnguyen@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("hoangnguyen@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("095672786");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),
                "Số điện thoại phải từ 10-11 số.");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
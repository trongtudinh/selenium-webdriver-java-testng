package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_07_Web_Element {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

//    @Test
    public void TC_01_Displayed() {
        driver.get("https://automationfc.github.io/basic-form/");

        WebElement emailTextBox = driver.findElement(By.cssSelector("#mail"));

        if (emailTextBox.isDisplayed()) {
            emailTextBox.sendKeys("Automation Testing");
            System.out.println("Email Text Box is displayed");
        } else {
            System.out.println("Email Text Box is not displayed");
        }

        WebElement ageUnder18Radio = driver.findElement(By.cssSelector("#under_18"));

        if (ageUnder18Radio.isDisplayed()) {
            ageUnder18Radio.click();
            System.out.println("Age under 18 radio is displayed");
        } else {
            System.out.println("Age under 18 radio is not displayed");
        }

        WebElement educationTextArea = driver.findElement(By.cssSelector("#edu"));

        if (educationTextArea.isDisplayed()) {
            educationTextArea.sendKeys("Automation Testing");
            System.out.println("Education Text Area is displayed");
        } else {
            System.out.println("Education Text Area is not displayed");
        }

        WebElement name5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));

        if (name5Text.isDisplayed()) {
            System.out.println("Name 5 Text is displayed");
        } else {
            System.out.println("Name 5 Text is not displayed");
        }
    }

//    @Test
    public void TC_02_Enabled() {
        driver.get("https://automationfc.github.io/basic-form/");

        WebElement emailTextBox = driver.findElement(By.cssSelector("#mail"));

        if (emailTextBox.isEnabled()) {
            System.out.println("Email Text Box is enable");
        } else {
            System.out.println("Email Text Box is not disable");
        }

        WebElement educationTextArea = driver.findElement(By.cssSelector("#edu"));

        if (educationTextArea.isEnabled()) {
            System.out.println("Education Text Area is enable");
        } else {
            System.out.println("Education Text Area is disable");
        }

        WebElement job3DropDown = driver.findElement(By.cssSelector("#job3"));

        if (job3DropDown.isEnabled()) {
            System.out.println("Job 3 Drop Down is enable");
        } else {
            System.out.println("Job 3 Drop Down is disable");
        }
    }

//    @Test
    public void TC_03_Selected() {
        driver.get("https://automationfc.github.io/basic-form/");

        WebElement under18Radio = driver.findElement(By.cssSelector("#under_18"));
        WebElement javaCheckbox = driver.findElement(By.cssSelector("#java"));

        under18Radio.click();
        javaCheckbox.click();
        Assert.assertTrue(under18Radio.isSelected());
        Assert.assertTrue(javaCheckbox.isSelected());

        under18Radio.click();
        javaCheckbox.click();
        Assert.assertTrue(under18Radio.isSelected());
        Assert.assertFalse(javaCheckbox.isSelected());
    }

    @Test
    public void TC_03_MailChimp() {
        driver.get("https://login.mailchimp.com/signup/");

        driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
        driver.findElement(By.id("new_username")).sendKeys("automationfc");

        WebElement passwordTextBox = driver.findElement(By.cssSelector("input#new_password"));
        WebElement signupButton = driver.findElement(By.cssSelector("button#create-account"));

        // Lower case
        passwordTextBox.sendKeys("auto");
        sleepInSecond(1);

        Assert.assertTrue(driver.findElement(
                By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
        Assert.assertFalse(signupButton.isEnabled());

        // Upper case
        passwordTextBox.clear();
        sleepInSecond(1);
        passwordTextBox.sendKeys("AUTO");
        sleepInSecond(1);

        Assert.assertTrue(driver.findElement(
                By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']"))
                .isDisplayed());
        Assert.assertFalse(signupButton.isEnabled());

        // Number
        passwordTextBox.clear();
        passwordTextBox.sendKeys("12345");
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(
                By.xpath("//li[@class='number-char completed' and text()='One number']"))
                .isDisplayed());
        Assert.assertFalse(signupButton.isEnabled());

        // Special
        passwordTextBox.clear();
        passwordTextBox.sendKeys("@#$");
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(
                By.xpath("//li[@class='special-char completed' and text()='One special character']"))
                .isDisplayed());
        Assert.assertFalse(signupButton.isEnabled());

        // 8 character
        passwordTextBox.clear();
        passwordTextBox.sendKeys("12345678");
        sleepInSecond(1);

        Assert.assertTrue(driver.findElement(
                By.xpath("//li[@class='number-char completed' and text()='One number']"))
                .isDisplayed());

        Assert.assertTrue(driver.findElement(
                By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']"))
                .isDisplayed());
        Assert.assertFalse(signupButton.isEnabled());

        // Combine
        passwordTextBox.clear();
        passwordTextBox.sendKeys("Auto123!@#");
        sleepInSecond(1);

        Assert.assertTrue(driver.findElement(
                By.xpath("//h4[text()=\"Your password is secure and you're all set!\"]"))
                .isDisplayed());
        Assert.assertTrue(signupButton.isEnabled());
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
}

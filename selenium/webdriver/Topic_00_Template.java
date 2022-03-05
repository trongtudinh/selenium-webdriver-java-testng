package webdriver;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_Template {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {

    }

    @Test
    public void TC_01_ValidateCurrentUrl() {

    }

    @Test
    public void TC_02_ValidatePageTitle() {

    }

    @Test
    public void TC_03_LoginFormDisplayed() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
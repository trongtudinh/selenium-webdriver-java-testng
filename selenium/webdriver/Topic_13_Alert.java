package webdriver;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_13_Alert  extends  Topic_00_Template {

    @Test
    public void TC_01_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();

        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
    }

    @Test
    public void TC_02_ConfirmAlert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.dismiss();

        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
    }

    @Test
    public void TC_03_PromptAlert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS prompt");
        alert.sendKeys("daominhdam");

        alert.accept();

        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: daominhdam");
    }
}

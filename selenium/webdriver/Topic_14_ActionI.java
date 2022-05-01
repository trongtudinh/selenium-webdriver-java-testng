package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Topic_14_ActionI extends  Topic_00_Template {

    @Test
    public void TC_01_Hover() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        WebElement inputElement = driver.findElement(By.id("age"));
        action = new Actions(driver);
        action.moveToElement(inputElement).perform();
        sleepInSecond(4);
        Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover() {
        driver.get("https://www.myntra.com/");

        WebElement menuElement = driver.findElement(By.xpath("//header//a[text()='Kids']"));
        action = new Actions(driver);
        action.moveToElement(menuElement).perform();
        sleepInSecond(4);

        action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
        sleepInSecond(3);

        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).isDisplayed());
    }

    @Test
    public void TC_03_Hover() {
        driver.get("https://www.fahasa.com/");

        WebElement menuElement = driver.findElement(By.xpath("//span[@class='icon_menu']"));
        action = new Actions(driver);
        action.moveToElement(menuElement).perform();
        sleepInSecond(4);

        action.moveToElement(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
        sleepInSecond(3);

        action.click(driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Tiểu Thuyết']"))).perform();
        sleepInSecond(3);

        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//child::strong[text()='Tiểu thuyết']")).isDisplayed());

    }

    @Test
    public  void TC_04_ClickAndHold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> elements = driver.findElements(By.cssSelector("ol#selectable li"));
        action = new Actions(driver);
        action.clickAndHold(elements.get(0));
        action.clickAndHold(elements.get(1));
        action.clickAndHold(elements.get(2));
        action.clickAndHold(elements.get(3));
        action.release();
        action.perform();

        Assert.assertEquals(elements.get(0).getAttribute("class"),"ui-state-default ui-selectee ui-selected");
        Assert.assertEquals(elements.get(1).getAttribute("class"),"ui-state-default ui-selectee ui-selected");
        Assert.assertEquals(elements.get(2).getAttribute("class"),"ui-state-default ui-selectee ui-selected");
        Assert.assertEquals(elements.get(3).getAttribute("class"),"ui-state-default ui-selectee ui-selected");
    }

    @Test
    public  void TC_05_ClickRadom() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> elements = driver.findElements(By.cssSelector("ol#selectable li"));
        action = new Actions(driver);
        action.keyDown(Keys.COMMAND);

        action.click(elements.get(1));
        action.click(elements.get(3));
        action.click(elements.get(6));
        action.click(elements.get(11));

        action.keyUp(Keys.COMMAND);

        action.perform();

        Assert.assertEquals(elements.get(1).getAttribute("class"),"ui-state-default ui-selectee ui-selected");
        Assert.assertEquals(elements.get(3).getAttribute("class"),"ui-state-default ui-selectee ui-selected");
        Assert.assertEquals(elements.get(6).getAttribute("class"),"ui-state-default ui-selectee ui-selected");
        Assert.assertEquals(elements.get(11).getAttribute("class"),"ui-state-default ui-selectee ui-selected");
    }
}

package webdriver;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_12_Handle_Custom_Radio_Checkbox extends Topic_00_Template {

    @Test
    public void TC_01_Custom_Radio() {
        driver.get("https://material.angular.io/components/radio/examples");

        By byCustomRadio = By.xpath("//span[text()=' Winter ']/preceding-sibling::span/input");
        clickByJS(byCustomRadio);
        sleepInSecond(5);

        Assert.assertTrue(driver.findElement(byCustomRadio).isSelected());
    }

    @Test
    public void TC_02_Custom_CheckBox() {
        driver.get("https://material.angular.io/components/checkbox/examples");

        By byIndeterminateCustomCheckBox = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");
        By byCheckedCustomCheckBox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
        clickByJS(byIndeterminateCustomCheckBox);
        sleepInSecond(1);
        clickByJS(byCheckedCustomCheckBox);
        sleepInSecond(1);

        Assert.assertTrue(driver.findElement(byIndeterminateCustomCheckBox).isSelected());
        Assert.assertTrue(driver.findElement(byCheckedCustomCheckBox).isSelected());

        clickByJS(byIndeterminateCustomCheckBox);
        sleepInSecond(1);
        clickByJS(byCheckedCustomCheckBox);
        sleepInSecond(1);

        Assert.assertFalse(driver.findElement(byIndeterminateCustomCheckBox).isSelected());
        Assert.assertFalse(driver.findElement(byCheckedCustomCheckBox).isSelected());
    }
}

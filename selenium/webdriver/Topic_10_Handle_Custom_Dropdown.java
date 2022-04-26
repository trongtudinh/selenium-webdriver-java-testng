package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_10_Handle_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, 30);
        jsExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_JQuery() {
        driver.get(
            "https://jqueryui.com/resources/demos/selectmenu/default.html"
        );

        selectItemInCustomDropdown("span#number-button>span.ui-selectmenu-icon",
                "ul#number-menu div",
                "19"
                );
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),
                "19");

        selectItemInCustomDropdown("span#number-button>span.ui-selectmenu-icon",
                "ul#number-menu div",
                "4"
        );
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),
                "4");

        selectItemInCustomDropdown("span#number-button>span.ui-selectmenu-icon",
                "ul#number-menu div",
                "10"
        );
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),
                "10");
    }

    @Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemInCustomDropdown("i.dropdown", "div.item>span.text", "Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),
                "Stevie Feliciano");

        selectItemInCustomDropdown("i.dropdown", "div.item>span.text", "Elliot Fu");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),
                "Elliot Fu");

        selectItemInCustomDropdown("i.dropdown", "div.item>span.text", "Justen Kitsune");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),
                "Justen Kitsune");
    }

    @Test
    public void TC_03_Vue() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        selectItemInCustomDropdown("li.dropdown-toggle",
                "ul.dropdown-menu a",
                "Second Option");

        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),
                "Second Option");
    }

    @Test
    public void TC_04_Angular() {
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
        sleepInSecond(10);
        selectItemInCustomDropdown("ng-select[bindvalue='provinceCode']",
                "div[role='option']>span.ng-option-label",
                "Tỉnh Bắc Kạn");
        String actualText = (String) jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='provinceCode'] span.ng-value-label\").innerText");

        Assert.assertEquals(actualText, "Tỉnh Bắc Kạn");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void selectItemInCustomDropdown(String parentLocator, String childLocator, String expectTextItem) {
        // Step 1: Click vào 1 element cho nó xổ hết ra các item
        //
        driver.findElement(By.cssSelector(parentLocator)).click();
        sleepInSecond(2);

        // Step 2: Chờ cho các item được load hết ra thành công
        // Lưu ý 1: Locator chứa hết tất cả các item
        // Lưu ý 2: Locator phải đến node cuối cùng chứa text

        explicitWait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector(childLocator)));

        // Step 3: Tìm item cần chọn
        // B1: Nếu item cần chọn nằm trong vùng nhìn thấy thì không cần scroll tìm tiếp
        // B2: Nếu item cần chọn ở dưới thì scroll xuống đến item đó
        List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));

        // Lấy tất cả các element (item) ra sau đó duyệt qua từng item
        // Duyệt qua từng item getText của item ra
        for (WebElement item: allItems) {
            String actualText = item.getText();
            System.out.println("Actual Text: " + actualText);

            // Nếu text = text mình mong muốn (item cần click vào)
            if (actualText.equals(expectTextItem)) {
                jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
                sleepInSecond(2);
                // Step 4: Click vao item do'
                item.click();

                sleepInSecond(2);
                break;
            }
        }
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

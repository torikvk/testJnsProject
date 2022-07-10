package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.function.Function;

public class OpenPage {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver(); //Инициализация WebDriver
    }

    @Test
    public void loginTest() {
        driver.get("http://www.bbc.com/");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        By searchQ = By.id("orb-search-q");
        By searchBtn = By.id("orb-search-button");
        By searchLnk = By.cssSelector("main#main-content li:first-child");

        driver.findElement(searchQ).sendKeys("England");
        driver.findElement(searchBtn).click();
        WebElement searchLink1 = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                WebElement element = webDriver.findElement(searchLnk);
                if (element.isDisplayed()&&element.isEnabled())
                    return element;
                else return null;
            }
        });
        if (searchLink1!=null){
            searchLink1.click();
        }
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}

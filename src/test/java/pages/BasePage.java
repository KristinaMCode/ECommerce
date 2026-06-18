package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitForUrlToContain(String urlFragment) {
        wait.until(ExpectedConditions.urlContains(urlFragment));
    }

    protected boolean isElementAbsent(By locator) {
        return driver.findElements(locator).isEmpty();
    }

    protected List<String> getElementTexts(By locator) {
        waitForVisible(locator);
        List<WebElement> items = driver.findElements(locator);
        List<String> actualItemNames = new ArrayList<>();
        for (WebElement item : items) {
            actualItemNames.add(item.getText());
        }
        return actualItemNames;
    }

    protected void verifyItemsPresent(List<String> expectedItems, List<String> actualItems) {
        for (String expectedItem : expectedItems) {
            assertTrue(expectedItem + " should be present, but found " + actualItems, actualItems.contains(expectedItem));
        }
    }
}

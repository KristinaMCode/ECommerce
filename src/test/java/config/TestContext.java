package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }


    public TestContext() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        //Disable password manager to prevent pop-ups during tests
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--password-store=basic");
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
    }
}

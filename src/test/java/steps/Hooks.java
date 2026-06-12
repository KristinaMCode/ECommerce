package steps;

import config.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {
    private final TestContext ctx;
    public WebDriver driver;

    public Hooks(TestContext ctx) {
        this.ctx = ctx;
    }

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
        System.out.println("=== setup: driver created ===");
    }

    @After
    public void tearDown() {
        ctx.driver.quit();
        if (driver != null) {
            driver.quit();
        }
    }
}

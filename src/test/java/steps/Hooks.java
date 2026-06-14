package steps;

import config.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class Hooks {
    private final TestContext ctx;

    public Hooks(TestContext ctx) {
        this.ctx = ctx;
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) ctx.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(
                    "Screenshot on failure - " + scenario.getName(),
                    new ByteArrayInputStream(screenshot)
            );
        }
        ctx.getDriver().quit();
    }
}

package steps;
import config.TestContext;
import io.cucumber.java.After;

public class Hooks {
    private final TestContext ctx;

    public Hooks(TestContext ctx) {
        this.ctx = ctx;
    }

    @After
    public void tearDown() {
        ctx.getDriver().quit();

    }
}

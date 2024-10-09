package stepDefinitions;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import cucumber.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.BrowserUtils;

public class Hooks {
    private TestContext testContext;


    public Hooks(){
        this.testContext = TestContext.getInstance(); }

    @Before
    public void beforeScenario() {
        testContext.getPage();
    }


    @AfterStep()
    public void afterStep(Scenario scenario){

        BrowserUtils.getScreenShot(testContext,scenario);
    }
    @After()
    public void AfterAll(Scenario scenario) {

        if(scenario.isFailed()){
            BrowserUtils.getScreenShot(testContext,scenario);
        }

        System.out.println("-----> After annotation: Closing browser");
        System.out.println("scenario.getName() = " + scenario.getName());
        System.out.println("scenario.getSourceTagNames() = " + scenario.getSourceTagNames());
        System.out.println("scenario.isFailed() = " + scenario.isFailed());

        TestContext.reset();
    }
}

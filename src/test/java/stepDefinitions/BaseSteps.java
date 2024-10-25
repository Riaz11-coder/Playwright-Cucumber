package stepDefinitions;

import cucumber.ScenarioContext;
import cucumber.TestContext;
import managers.BrowserManager;
import pageObjects.GooglePage;
import utils.BrowserUtils;

public class BaseSteps {

    TestContext testContext;
    GooglePage googlePage;
    BrowserUtils browserUtils;
    ScenarioContext scenarioContext;

    public BaseSteps() {
        this.testContext = new TestContext();
        googlePage = testContext.getPageObjectManager().getGooglePage();
        browserUtils = testContext.getPageObjectManager().getBrowserUtils();
        scenarioContext = testContext.getScenarioContext();

    }

}
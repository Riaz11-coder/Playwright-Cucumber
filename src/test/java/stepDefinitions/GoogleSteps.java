package stepDefinitions;

import cucumber.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.GooglePage;
import utilities.BrowserUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleSteps {
     TestContext testContext;
     GooglePage googlePage;
     BrowserUtils browserUtils;

    public GoogleSteps(){
        testContext = TestContext.getInstance();
        googlePage = testContext.getPageObjectManager().getGooglePage();
        browserUtils = testContext.getPageObjectManager().getBrowserUtils();
    }

    @Given("I navigate to Google search page")
    public void i_navigate_to_google_search_page() {
        googlePage.navigateToGoogleHomePage();
    }

    @When("I check page title")
    public void i_check_page_title() {
        System.out.println(testContext.getPage().title());
    }

    @Then("I have page title assertion")
    public void i_have_page_title_assertion() {
        assertEquals("Google", googlePage.getPageTitle());
    }

}

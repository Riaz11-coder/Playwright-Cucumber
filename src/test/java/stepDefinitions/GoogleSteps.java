package stepDefinitions;

import cucumber.ScenarioContext;
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
     ScenarioContext scenarioContext;

    public GoogleSteps(){
        testContext = TestContext.getInstance();
        googlePage = testContext.getPageObjectManager().getGooglePage();
        browserUtils = testContext.getPageObjectManager().getBrowserUtils();
        scenarioContext = testContext.getScenarioContext();
    }

    @Given("I navigate to Google search page")
    public void i_navigate_to_google_search_page() {
        googlePage.navigateToGoogleHomePage();
    }

    @When("I check page title")
    public void i_check_page_title() {
        String pageTitle = testContext.getPage().title();
        scenarioContext.setContext("PageTitle",pageTitle);
        System.out.println(pageTitle);
    }

    @Then("I have page title assertion")
    public void i_have_page_title_assertion() {
        String expectedTitle = "Google";
        String actualTitle = scenarioContext.getContext("PageTitle",String.class);
        assertEquals(expectedTitle,actualTitle);
    }

    @When("I query the search bar for {string}")
    public void iQueryTheSearchBarFor(String query) {
        googlePage.querySearch(query);
        scenarioContext.setContext("SearchQuery", query);
    }

    @Then("I should be able to navigate to the Web page")
    public void iShouldBeAbleToNavigateToTheWebPage() {
        String query = scenarioContext.getContext("SearchQuery", String.class);
        googlePage.searchPageTitleValidation(query);
    }



}

package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;


import static org.junit.jupiter.api.Assertions.assertEquals;




public class GoogleSteps extends BaseSteps{


    @Given("I navigate to Google search page")
    public void i_navigate_to_google_search_page() throws InterruptedException {
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

    @Given("I open the Google homepage")
    public void openGoogleHomepage() {
        googlePage.navigateToGoogleHomePage();
    }

    @When("I search for {string}")
    public void searchFor(String keyword) {
        googlePage.googlePageSearch(keyword);
        scenarioContext.setContext("SearchQuery", keyword);

    }

    @Then("I should see search results for {string}")
    public void verifySearchResults(String keyword) {
       String SearchQuery = scenarioContext.getContext("SearchQuery",String.class);
       Assertions.assertEquals(keyword,SearchQuery);
       googlePage.assertPageContent(keyword);


    }

    @Then("the page title should contain {string}")
    public void verifyTitleContains(String keyword) {
        String SearchQuery = scenarioContext.getContext("SearchQuery",String.class);
        Assertions.assertEquals(keyword,SearchQuery);
        googlePage.assertPageTitle(keyword);
    }

    @When("I navigate to image search")
    public void navigateToImageSearch() {
        googlePage.imageSearch();
    }

    @Then("I should see image results for {string}")
    public void verifyImageResults(String keyword) {
        String SearchQuery = scenarioContext.getContext("SearchQuery",String.class);
        Assertions.assertEquals(keyword,SearchQuery);
        googlePage.assertPageContent(keyword);
    }

}





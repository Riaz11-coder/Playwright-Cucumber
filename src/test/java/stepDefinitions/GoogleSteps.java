package stepDefinitions;

import com.microsoft.playwright.Page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObjects.GooglePage;
import utilities.BrowserManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleSteps {
    private static BrowserManager browserManager;
    private Page page;
    private GooglePage googlePage;

    @BeforeAll
    static void setUp() {
        browserManager = BrowserManager.getInstance();
    }

    @BeforeEach
    void createContext() {
        page = browserManager.createPage();
        googlePage = new GooglePage(page);
    }

    @Given("I navigate to Google search page")
    public void i_navigate_to_google_search_page() {
        googlePage.navigateToGoogleHomePage();


    }

    @When("I check page title")
    public void i_check_page_title() {
        System.out.println(page.title());
    }

    @Then("I have page title assertion")
    public void i_have_page_title_assertion() {
        assertEquals("Google", googlePage.getPageTitle());
    }

    @AfterAll
    static void tearDown() {
        browserManager.closeBrowser();
    }
}

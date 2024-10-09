package pageObjects;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Assertions;

public class GooglePage extends BasePage{

    public GooglePage(Page page) {
        super(page);
    }

    public void navigateToGoogleHomePage() {
        page.navigate("https://www.google.com");
    }

    public String getPageTitle() {
        return page.title();
    }



    public void searchGoogle(String query) {
        // Wait for the page to load completely
        page.waitForLoadState();

        // Use JavaScript to access the shadow DOM and find the search input
        String js = "document.querySelector('input[name=\"q\"]') || " +
                "document.querySelector('input[title=\"Search\"]') || " +
                "document.querySelector('textarea[name=\"q\"]')";

        // Evaluate the JavaScript and get the element
        ElementHandle searchInput = page.evaluateHandle(js).asElement();

        if (searchInput == null) {
            throw new RuntimeException("Could not find the search input element");
        }

        // Type the query and press Enter
        searchInput.fill(query);
        searchInput.press("Enter");

        // Wait for search results to load
        page.waitForSelector("div#search");

        // Perform assertions
        if (query.equals("Porsche")) {
            Assertions.assertTrue(page.isVisible("text=Porsche"), "Porsche text should be visible");
        }
    }

    public void clickFirstResult() {
        page.click("h3[class='LC20lb MBeuO DKV0Md']");
    }
}

package pageObjects;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import managers.FileReaderManager;
import managers.PageObjectManager;
import org.junit.jupiter.api.Assertions;

public class GooglePage extends PageObjectManager {
    private final Locator searchInput;
    private final Locator audiPageBtn;
    private final Locator porschePageBtn;

    public GooglePage(Page page) {

        super(page);
        this.searchInput = page.locator("textarea[name='q']");
        this.audiPageBtn = page.locator("span.VuuXrf:has-text('Audi')");
        this.porschePageBtn = page.locator("span.VuuXrf:has-text('Porsche')");

    }

    public void navigateToGoogleHomePage() {
        page.navigate(FileReaderManager.getInstance().getConfigReader().getGoogleUrl());
    }

    public String getPageTitle() {
        return page.title();
    }

    public void querySearch(String query){
        page.waitForLoadState();
        searchInput.fill(query);
        searchInput.press("Enter");
        page.waitForLoadState();
        if(query.equals("Porsche")) {
            Assertions.assertTrue(page.isVisible("text=Porsche"), "Porsche text should be visible");
        } else if (query.equals("audi")) {
            Assertions.assertTrue(page.isVisible("text=Audi USA"), "Audi USA text should be visible");
        }
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



    public void searchPageTitleValidation(String query) {
        page.click("h3[class='LC20lb MBeuO DKV0Md']");

        page.waitForLoadState();
        String expectedPageTitle = query;
        String actualPageTitle = page.title();
        Assertions.assertEquals(expectedPageTitle,actualPageTitle);

    }
}

package pageObjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import managers.FileReaderManager;
import managers.PageObjectManager;
import org.junit.jupiter.api.Assertions;

public class GooglePage extends PageObjectManager {
    private final Locator GoogleSearchInput;
    private final Locator firstSearchableResult;
    private final Locator firstClickableResult;


    public GooglePage(Page page) {
        super(page);
        this.GoogleSearchInput = page.locator("textarea[name='q']");
        this.firstSearchableResult = page.locator("span.VuuXrf").first();
        this.firstClickableResult = page.locator("h3[class='LC20lb MBeuO DKV0Md']").first();
    }

    public void navigateToGoogleHomePage() {
        page.navigate(FileReaderManager.getInstance().getConfigReader().getGoogleUrl());
    }

    public void querySearch(String query){
        page.waitForLoadState();
        GoogleSearchInput.fill(query);
        GoogleSearchInput.press("Enter");
        page.waitForLoadState();

        // Wait for the first search result to be visible
        firstSearchableResult.waitFor();

        // Get the text of the first result
        String actualText = firstSearchableResult.innerText().trim(); // Remove extra spaces

        switch (query) {
            case "Porsche":
                Assertions.assertEquals("Porsche", actualText, "Expected Porsche result but got: " + actualText);
                break;
            case "Audi":
                Assertions.assertEquals("Audi", actualText, "Expected Audi result but got: " + actualText);
                break;
            case "Tesla":
                Assertions.assertEquals("Tesla", actualText, "Expected Tesla result but got: " + actualText);
                break;
            case "Ferrari":
                Assertions.assertEquals("Ferrari", actualText, "Expected Ferrari result but got: " + actualText);
                break;
            default:
                Assertions.fail("Unexpected query: " + query);
        }
    }


    public void searchPageTitleValidation(String query) {
        firstClickableResult.click();
        page.waitForLoadState();
        String expectedPageTitle = query;
        String actualPageTitle = page.title();
        Assertions.assertTrue(actualPageTitle.contains(expectedPageTitle));

    }
}

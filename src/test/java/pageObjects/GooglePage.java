package pageObjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import managers.FileReaderManager;
import managers.PageObjectManager;
import org.junit.jupiter.api.Assertions;


public class GooglePage extends PageObjectManager {
    private final Locator GoogleSearchInput;
    private final Locator firstSearchableResult;
    private final Locator firstClickableResult;
    private final Locator Images;


    public GooglePage(Page page) {
        super(page);
        this.GoogleSearchInput = page.locator("textarea[name='q']");
        this.firstSearchableResult = page.locator("span.VuuXrf").first();
        this.firstClickableResult = page.locator("h3[class='LC20lb MBeuO DKV0Md']").first();
        this.Images = page.locator("a:has-text('Images')");
    }

    public void navigateToGoogleHomePage() {
        page.navigate(FileReaderManager.getInstance().getConfigReader().getGoogleUrl());
    }

    public void querySearch(String query){
        page.waitForLoadState();
        //page.waitForSelector("textarea[name='q']",new Page.WaitForSelectorOptions().setTimeout(5000));
        GoogleSearchInput.fill(query);
        GoogleSearchInput.press("Enter");

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
        //page.waitForSelector("h3[class='LC20lb MBeuO DKV0Md']", new Page.WaitForSelectorOptions().setTimeout(5000));
        firstClickableResult.click();
        page.waitForLoadState();
        String expectedPageTitle = query;
        String actualPageTitle = page.title();
        Assertions.assertTrue(actualPageTitle.contains(expectedPageTitle));

    }

    public void googlePageSearch(String keyword){
        //page.waitForSelector("textarea[name='q']", new Page.WaitForSelectorOptions().setTimeout(5000));
        GoogleSearchInput.fill(keyword);
        GoogleSearchInput.press("Enter");
        page.waitForSelector("#search");
    }

    public void assertPageContent(String keyword){
        String pageContent = page.content();
        Assertions.assertTrue(pageContent.contains(keyword), "Search results should contain " + keyword);
    }

    public void assertPageTitle(String keyword){
        String pageTitle = page.title();
        Assertions.assertTrue(pageTitle.contains(keyword), "Page title should contain " + keyword);
    }

    public void imageSearch(){
        //page.waitForSelector("a:has-text('Images')", new Page.WaitForSelectorOptions().setTimeout(5000));
        Images.click();
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        page.waitForSelector("#hdtb-sc");
    }
}

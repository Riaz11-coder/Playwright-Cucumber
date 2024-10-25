package cucumber;

import managers.BrowserManager;
import com.microsoft.playwright.Page;
import managers.FileReaderManager;
import managers.PageObjectManager;

public class TestContext {
    private BrowserManager browserManager;
    private Page page;
    private PageObjectManager pageObjectManager;
    private ScenarioContext scenarioContext;


    // Constructor initializing the BrowserManager and ScenarioContext
    public TestContext() {
        browserManager = BrowserManager.getInstance();// Use the singleton instance
        scenarioContext = new ScenarioContext();
    }

    // Lazy initialization for PageObjectManager
    public PageObjectManager getPageObjectManager() {
        if (pageObjectManager == null) {
            pageObjectManager = new PageObjectManager(getPage());
        }
        return pageObjectManager;
    }

    // Lazy initialization for Page
    public Page getPage() {
        if (page == null || page.isClosed()) {
            page = browserManager.createPage(
                    FileReaderManager.getInstance().getConfigReader().getBrowser(),
                    FileReaderManager.getInstance().getConfigReader().getEnvironment()
            );
        }
        return page;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

    public BrowserManager getBrowserManager() {
        return browserManager;
    }


    // Clean-up logic for closing page and browser after the test finishes
    public void cleanUp() {
        if (page != null) {
            page.close();
        }
        browserManager.closeBrowser();  // Close the browser after all tests
    }
}

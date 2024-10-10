package cucumber;

import managers.BrowserManager;
import com.microsoft.playwright.Page;
import managers.FileReaderManager;
import managers.PageObjectManager;

public class TestContext {
    private static volatile TestContext instance;
    private BrowserManager browserManager;
    private Page page;
    private PageObjectManager pageObjectManager;
    private ScenarioContext scenarioContext;

    public TestContext() {
        browserManager = BrowserManager.getInstance();
        page = browserManager.createPage(FileReaderManager.getInstance().getConfigReader().getBrowser(), FileReaderManager.getInstance().getConfigReader().getEnvironment());
        pageObjectManager = new PageObjectManager(page);
        scenarioContext = new ScenarioContext();
    }

    public static TestContext getInstance() {
        if (instance == null) {
            synchronized (TestContext.class) {
                if (instance == null) {
                    instance = new TestContext();
                }
            }
        }
        return instance;
    }
    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

    public BrowserManager getBrowserManager() {return browserManager;
    }

    public Page getPage() {
        if(page == null || page.isClosed()) {
            page = browserManager.createPage(FileReaderManager.getInstance().getConfigReader().getBrowser(), FileReaderManager.getInstance().getConfigReader().getEnvironment());
        }
        return page;
    }

    public static void reset() {
        synchronized (TestContext.class) {
            if (instance != null) {
                instance.cleanUp();
                instance.getScenarioContext().clearContext(); // Clear context on reset
                instance = null;
            }
        }
    }
    public void cleanUp() {
        if (page != null) {
            page.close();
        }
        browserManager.closeBrowser();

    }


}
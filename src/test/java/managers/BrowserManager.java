package managers;

import com.microsoft.playwright.*;
import enums.BrowserType;
import enums.EnvironmentType;

public class BrowserManager {
    private static BrowserManager instance;
    private Playwright playwright;
    private Browser browser;

    private BrowserManager() {
        // Private constructor for singleton pattern
    }

    public static BrowserManager getInstance() {
        if (instance == null) {
            instance = new BrowserManager();
        }
        return instance;
    }

    public Page createPage(BrowserType browserType, EnvironmentType environmentType) {
        if (playwright == null) {
            playwright = Playwright.create();
        }

        if (browser == null) {
            browser = createBrowser(browserType, environmentType);
        }

        return browser.newPage();
    }

    private Browser createBrowser(BrowserType browserType, EnvironmentType environmentType) {
        switch (environmentType) {
            case LOCAL:
                return createLocalBrowser(browserType);
            case REMOTE:
                return createRemoteBrowser(browserType);
            case BROWSERSTACK:
                return createBrowserStackBrowser(browserType);
            default:
                throw new IllegalArgumentException("Unsupported environment type: " + environmentType);
        }
    }

    private Browser createLocalBrowser(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                return playwright.chromium().launch(new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false));
            case FIREFOX:
                return playwright.firefox().launch(new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false));
            case SAFARI:
                return playwright.webkit().launch(new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false));
            case EDGE:
                try {
                    return playwright.chromium().launch(new com.microsoft.playwright.BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false));
                }catch(PlaywrightException e){
                    System.out.println("if Edge not available falling back to Chrome");
                }
            default:
                throw new IllegalArgumentException("Unsupported browser type for local environment: " + browserType);
        }

    }

    private Browser createRemoteBrowser(BrowserType browserType) {
        // Implement remote browser creation logic here
        // This might involve connecting to a remote Selenium grid or similar setup
        throw new UnsupportedOperationException("Remote browser creation not implemented yet");
    }

    private Browser createBrowserStackBrowser(BrowserType browserType) {
        // Implement BrowserStack browser creation logic here
        // This might involve setting up BrowserStack capabilities and connecting to their service
        throw new UnsupportedOperationException("BrowserStack browser creation not implemented yet");
    }

    public void closeBrowser() {
        if (browser != null) {
            browser.close();
            browser = null;
        }
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}
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
        Browser browser = null;

        // Common launch options
        com.microsoft.playwright.BrowserType.LaunchOptions launchOptions = new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(getHeadlessMode());

        try {
            switch (browserType) {
                case CHROME:
                case EDGE:
                    browser = launchChromiumBrowser(browserType, launchOptions);
                    break;
                case FIREFOX:
                    browser = playwright.firefox().launch(launchOptions);
                    break;
                case SAFARI:
                    browser = playwright.webkit().launch(launchOptions);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser type: " + browserType);
            }
        } catch (PlaywrightException e) {
            System.err.println("Error launching browser: " + e.getMessage());
            throw new RuntimeException("Failed to launch browser: " + browserType, e);
        }

        return browser;
    }

    private Browser launchChromiumBrowser(BrowserType browserType, com.microsoft.playwright.BrowserType.LaunchOptions launchOptions) {
        // If the browser is EDGE, set the channel to "msedge"
        if (browserType == BrowserType.EDGE) {
            try {
                launchOptions.setChannel("msedge");
                return playwright.chromium().launch(launchOptions);
            } catch (PlaywrightException e) {
                System.out.println("Edge not available, falling back to Chrome.");
            }
        }

        // Fallback to Chrome (for both Chrome and Edge fallback case)
        return playwright.chromium().launch(launchOptions);
    }

    private boolean getHeadlessMode() {
        // Dynamically configure headless mode, could be fetched from config or environment variables
        return FileReaderManager.getInstance().getConfigReader().isHeadlessMode();
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
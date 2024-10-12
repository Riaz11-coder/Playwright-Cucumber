package managers;

import com.microsoft.playwright.*;
import enums.BrowserType;
import enums.EnvironmentType;

import java.nio.file.Paths;

public class BrowserManager {
    private static BrowserManager instance;
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;

    private BrowserManager() {
        // Private constructor for singleton pattern
    }

    public static BrowserManager getInstance() {
        if (instance == null) {
            instance = new BrowserManager();
        }
        return instance;
    }

    // Create a new page with an isolated context, reusing the same browser instance
    public Page createPage(BrowserType browserType, EnvironmentType environmentType) {
        if (playwright == null) {
            playwright = Playwright.create();
        }

        if (browser == null) {
            browser = createBrowser(browserType, environmentType);
        }

        if (context == null || context.pages().isEmpty()) {
            context = browser.newContext();  // Create a new context for each test


        }

        return context.newPage();
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
        com.microsoft.playwright.BrowserType.LaunchOptions launchOptions =
                new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(getHeadlessMode());

        try {
            switch (browserType) {
                case CHROME:
                case EDGE:
                    return launchChromiumBrowser(browserType, launchOptions);
                case FIREFOX:
                    return playwright.firefox().launch(launchOptions);
                case SAFARI:
                    return playwright.webkit().launch(launchOptions);
                default:
                    throw new IllegalArgumentException("Unsupported browser type: " + browserType);
            }
        } catch (PlaywrightException e) {
            System.err.println("Error launching browser: " + e.getMessage());
            throw new RuntimeException("Failed to launch browser: " + browserType, e);
        }
    }

    private Browser launchChromiumBrowser(BrowserType browserType, com.microsoft.playwright.BrowserType.LaunchOptions launchOptions) {
        if (browserType == BrowserType.EDGE) {
            try {
                launchOptions.setChannel("msedge");
                return playwright.chromium().launch(launchOptions);
            } catch (PlaywrightException e) {
                System.out.println("Edge not available, falling back to Chrome.");
            }
        }

        return playwright.chromium().launch(launchOptions);
    }

    private boolean getHeadlessMode() {
        return FileReaderManager.getInstance().getConfigReader().isHeadlessMode();
    }

    private Browser createRemoteBrowser(BrowserType browserType) {
        throw new UnsupportedOperationException("Remote browser creation not implemented yet");
    }

    private Browser createBrowserStackBrowser(BrowserType browserType) {
        throw new UnsupportedOperationException("BrowserStack browser creation not implemented yet");
    }

    // Close the browser only after all tests are finished
    public void closeBrowser() {
        if (context != null) {
            context.close();
            context = null;
        }
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

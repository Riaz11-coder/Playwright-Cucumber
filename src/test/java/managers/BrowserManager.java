package managers;

import com.microsoft.playwright.*;
import enums.BrowserType;
import enums.EnvironmentType;


public class BrowserManager {
    private static BrowserManager instance;
    private Playwright playwright;
    private Browser browser;

    // ThreadLocal variables for isolated BrowserContext and Page per thread
    private ThreadLocal<BrowserContext> threadLocalContext = new ThreadLocal<>();
    private ThreadLocal<Page> threadLocalPage = new ThreadLocal<>();

    private BrowserManager() {
        this.playwright = Playwright.create();
    }

    public static synchronized BrowserManager getInstance() {
        if (instance == null) {
            instance = new BrowserManager();
        }
        return instance;
    }

    private synchronized Browser getBrowser(BrowserType browserType, EnvironmentType environmentType) {
        if (browser == null) {
            browser = createBrowser(browserType, environmentType);
        }
        return browser;
    }

    public Page createPage(BrowserType browserType, EnvironmentType environmentType) {
        // Initialize a new BrowserContext for each thread
        if (threadLocalContext.get() == null) {
            BrowserContext context = getBrowser(browserType, environmentType).newContext();
            threadLocalContext.set(context);
        }

        // Initialize a new Page for each thread
        if (threadLocalPage.get() == null || threadLocalPage.get().isClosed()) {
            Page page = threadLocalContext.get().newPage();
            threadLocalPage.set(page);
        }
        return threadLocalPage.get();
    }

    private Browser createBrowser(BrowserType browserType, EnvironmentType environmentType) {
        switch (environmentType) {
            case LOCAL:
                return createLocalBrowser(browserType);
            case REMOTE:
            case BROWSERSTACK:
                throw new UnsupportedOperationException("Remote browsers are not supported yet.");
            default:
                throw new IllegalArgumentException("Unsupported environment type: " + environmentType);
        }
    }

    private Browser createLocalBrowser(BrowserType browserType) {
        com.microsoft.playwright.BrowserType.LaunchOptions launchOptions =
                new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(getHeadlessMode());

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
    }

    private Browser launchChromiumBrowser(BrowserType browserType, com.microsoft.playwright.BrowserType.LaunchOptions launchOptions) {
        if (browserType == BrowserType.EDGE) {
            launchOptions.setChannel("msedge");
        }
        return playwright.chromium().launch(launchOptions);
    }

    private boolean getHeadlessMode() {
        return FileReaderManager.getInstance().getConfigReader().isHeadlessMode();
    }

    public void closeBrowser() {
        if (threadLocalPage.get() != null) {
            threadLocalPage.get().close();
            threadLocalPage.remove();
        }
        if (threadLocalContext.get() != null) {
            threadLocalContext.get().close();
            threadLocalContext.remove();
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

package utilities;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BrowserManager {

    private static BrowserManager instance;
    private Playwright playwright;
    private Browser browser;

    private BrowserManager() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    public static BrowserManager getInstance() {
        if (instance == null) {
            instance = new BrowserManager();
        }
        return instance;
    }

    public Page createPage() {
        return browser.newPage();
    }

    public void closeBrowser() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}

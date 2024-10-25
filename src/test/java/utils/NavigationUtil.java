package utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitUntilState;

public class NavigationUtil extends BasePlaywrightUtil {

    public NavigationUtil(Page page) {
        super(page);
    }

    public void navigateTo(String url) {
        page.navigate(url);
    }

    public void refresh() {
        page.reload();
    }

    public void goBack() {
        page.goBack();
    }

    public void goForward() {
        page.goForward();
    }

    public void waitForLoadState(LoadState state) {
        page.waitForLoadState(state);
    }

    public void waitForNavigation(Page page, WaitUntilState waitUntilState) {
        page.waitForNavigation((Runnable) new Page.WaitForNavigationOptions().setWaitUntil(waitUntilState));
    }

    public void waitForPageLoad(WaitUntilState waitUntilState) {
        page.waitForLoadState();
    }

    public void waitForPageStability() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
}

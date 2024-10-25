package utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Path;

public class ScreenshotUtil extends BasePlaywrightUtil{
    public ScreenshotUtil(Page page) {
        super(page);
    }

    public byte[] takeScreenshot() {
        return page.screenshot();
    }

    public void takeScreenshot(Path path) {
        page.screenshot(new Page.ScreenshotOptions().setPath(path));
    }

    public byte[] takeFullPageScreenshot() {
        return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
    }

    public byte[] takeElementScreenshot(String selector) {
        return page.locator(selector).screenshot();
    }

    public void takeElementScreenshot(String selector, Path path) {
        page.locator(selector).screenshot(new Locator.ScreenshotOptions().setPath(path));
    }

    public byte[] takePdfScreenshot() {
        return page.pdf();
    }
}
package utils;

import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.FrameLocator;


public class FrameUtil extends BasePlaywrightUtil {

    public FrameUtil(Page page) {
        super(page);
    }

    public Frame getFrameByName(String name) {
        return page.frame(name);
    }

    public Frame getFrameByUrl(String url) {
        return page.frameByUrl(url);
    }

    public FrameLocator getFrameLocator(String selector) {
        return page.frameLocator(selector);
    }

    public void switchToFrame(String selector) {
        getFrameLocator(selector).first();
    }

    public String getFrameContent(String selector) {
        return getFrameLocator(selector).first().locator("body").innerHTML();
    }
}

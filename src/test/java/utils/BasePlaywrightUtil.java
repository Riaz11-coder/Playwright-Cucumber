package utils;

import com.microsoft.playwright.Page;

public class BasePlaywrightUtil {
    protected Page page;

    public BasePlaywrightUtil(Page page) {
        this.page = page;
    }
}
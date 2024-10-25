package utils;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Page;

import java.nio.file.Path;
import java.util.function.Consumer;

public class DownloadUtil extends BasePlaywrightUtil {

    public DownloadUtil(Page page) {
        super(page);
    }

    public void waitForDownload(Consumer<Download> action) {
        page.waitForDownload((Runnable) action);
    }

    public Path downloadFile(String selector, String saveFilePath) {
        Download download = page.waitForDownload(() -> page.click(selector));
        return download.path();
    }

    public String getDownloadedFileName(String selector) {
        Download download = page.waitForDownload(() -> page.click(selector));
        return download.suggestedFilename();
    }
}
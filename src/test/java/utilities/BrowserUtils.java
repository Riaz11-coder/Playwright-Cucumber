package utilities;

import com.microsoft.playwright.Page;
import cucumber.TestContext;
import io.cucumber.java.Scenario;

import java.nio.file.Paths;

public class BrowserUtils {

    Page page;

    public BrowserUtils(Page page){
        this.page = page;
    }

    public void takeScreenshot(Page page, String filePath) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filePath)));
    }

    public static void getScreenShot(TestContext testContext, Scenario scenario) {
        Page page = testContext.getPage();
        if(page != null && !page.isClosed()){
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            scenario.attach(screenshot, "image/png", "Step Screenshot");
        } else {
            System.out.println("Unable to take screenshot: Page is null or closed");
        }

    }
}
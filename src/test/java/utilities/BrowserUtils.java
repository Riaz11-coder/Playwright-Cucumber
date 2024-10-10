package utilities;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import cucumber.TestContext;
import io.cucumber.java.Scenario;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BrowserUtils {

    private Page page;

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
            scenario.attach(screenshot, "image/png", page.title());
        } else {
            System.out.println("Unable to take screenshot: Page is null or closed");
        }

    }


        public String getScreenshot(String name) throws IOException {
            String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            Files.write(Paths.get(target), screenshot);
            return target;
        }

        public void switchToWindow(String targetTitle) {
            for (Page newPage : page.context().pages()) {
                if (newPage.title().equals(targetTitle)) {
                    newPage.bringToFront();
                    return;
                }
            }
        }

        public void hover(String selector) {
            page.hover(selector);
        }

        public List<String> getElementsText(String selector) {
            return page.querySelectorAll(selector).stream()
                    .map(element -> element.innerText())
                    .collect(Collectors.toList());
        }

        public ElementHandle getShadowElement(String shadowHostSelector, String shadowElementSelector) {
            ElementHandle shadowHost = page.querySelector(shadowHostSelector);
            return shadowHost.evaluateHandle("element => element.shadowRoot.querySelector('" + shadowElementSelector + "')").asElement();
        }

        public static void waitFor(int seconds) {
            try {
                Thread.sleep(seconds * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void waitForVisibility(String selector, int timeoutInSeconds) {
            page.waitForSelector(selector, new Page.WaitForSelectorOptions().setTimeout(timeoutInSeconds * 1000));
        }

        public void waitForClickability(String selector, int timeoutInSeconds) {
            page.waitForSelector(selector, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.ATTACHED).setTimeout(timeoutInSeconds * 1000));
        }

        public void waitForPageToLoad() {
            page.waitForLoadState(LoadState.NETWORKIDLE);
        }

        public void verifyElementDisplayed(String selector) {
            Assertions.assertTrue(page.isVisible(selector), "Element not visible: " + selector);
        }

        public void verifyElementNotDisplayed(String selector) {
            Assertions.assertFalse(page.isVisible(selector), "Element should not be visible: " + selector);
        }

        public void clickWithJS(String selector) {
            page.evaluate("selector => document.querySelector(selector).click()", selector);
        }

        public void scrollToElement(String selector) {
            page.evaluate("selector => document.querySelector(selector).scrollIntoView()", selector);
        }

        public void doubleClick(String selector) {
            page.dblclick(selector);
        }

        public void setAttribute(String selector, String attributeName, String attributeValue) {
            page.evaluate("(selector, name, value) => document.querySelector(selector).setAttribute(name, value)");
        }

        public void highlight(String selector) {
            page.evaluate("selector => { const element = document.querySelector(selector); element.style.backgroundColor = 'yellow'; element.style.border = '2px solid red'; }", selector);
            waitFor(1);
        }

        public void selectCheckBox(String selector, boolean check) {
            ElementHandle checkbox = page.querySelector(selector);
            if (check != checkbox.isChecked()) {
                checkbox.click();
            }
        }

        public void clickWithTimeOut(String selector, int timeout) {
            page.click(selector, new Page.ClickOptions().setTimeout(timeout * 1000));
        }

        public void executeJSCommand(String command) {
            page.evaluate(command);
        }

        public void clickWithWait(String selector, int attempts) {
            for (int i = 0; i < attempts; i++) {
                try {
                    page.click(selector);
                    break;
                } catch (PlaywrightException e) {
                    e.printStackTrace();
                    waitFor(1);
                }
            }
        }

        public void waitForPresenceOfElement(String selector, int timeoutInSeconds) {
            page.waitForSelector(selector, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.ATTACHED).setTimeout(timeoutInSeconds * 1000));
        }

        public static boolean isFileDownloaded(String downloadPath, String fileName) {
            File dir = new File(downloadPath);
            File[] dirContents = dir.listFiles();

            for (File file : dirContents) {
                if (file.getName().equals(fileName)) {
                    file.delete();
                    return true;
                }
            }
            return false;
        }
    }

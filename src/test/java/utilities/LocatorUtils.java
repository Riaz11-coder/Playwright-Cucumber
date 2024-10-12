package utilities;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

public class LocatorUtils {

    private Page page;


    public LocatorUtils(Page page) {
        this.page = page;
    }




    public static String getInnerText(Page page, String selector) {
        return page.locator(selector).innerText();
    }

    public static String getTextContent(Page page, String selector) {
        return page.locator(selector).textContent();
    }

    public static void clickElement(Page page, String selector) {
        page.locator(selector).click();
    }

    public static void fillInput(Page page, String selector, String value) {
        page.locator(selector).fill(value);
    }

    public static void checkCheckbox(Page page, String selector) {
        page.locator(selector).check();
    }

    public static void uncheckCheckbox(Page page, String selector) {
        page.locator(selector).uncheck();
    }

    public static void hoverOverElement(Page page, String selector) {
        page.locator(selector).hover();
    }

    public static boolean isElementVisible(Page page, String selector) {
        return page.locator(selector).isVisible();
    }

    public static boolean isElementChecked(Page page, String selector) {
        return page.locator(selector).isChecked();
    }

    public static void setInputFiles(Page page, String selector, String filePath) {
        page.locator(selector).setInputFiles(Paths.get(filePath));
    }

    public static void tapElement(Page page, String selector) {
        page.locator(selector).tap();
    }

    public static void selectOption(Page page, String selector, String value) {
        page.locator(selector).selectOption(value);
    }

    public static void pressKey(Page page, String selector, String key) {
        page.locator(selector).press(key);
    }

    public static void scrollToElement(Page page, String selector) {
        page.locator(selector).scrollIntoViewIfNeeded();
    }

    public static void takeScreenshot(Page page, String selector, String filePath) {
        page.locator(selector).screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(filePath)));
    }

    // Utility methods for specific Locator functions

    public static int getElementCount(Page page, String selector) {
        return page.locator(selector).count();
    }

    public static ElementHandle getBoundingBox(Page page, String selector) {
        return (ElementHandle) page.locator(selector).boundingBox();
    }

    public static void dispatchCustomEvent(Page page, String selector, String eventType) {
        page.locator(selector).dispatchEvent(eventType);
    }

    public static Locator getFirstElement(Page page, String selector) {
        return page.locator(selector).first();
    }

    public static Locator getLastElement(Page page, String selector) {
        return page.locator(selector).last();
    }

    public static Locator getNthElement(Page page, String selector, int index) {
        return page.locator(selector).nth(index);
    }

    // Methods for advanced locator strategies (by label, role, testId, etc.)

    public static Locator getByAltText(Page page, String altText) {
        return page.getByAltText(altText);
    }

    public static Locator getByLabel(Page page, String label) {
        return page.getByLabel(label);
    }

    public static Locator getByPlaceholder(Page page, String placeholder) {
        return page.getByPlaceholder(placeholder);
    }

    public static Locator getByRole(Page page, String role) {
        return page.getByRole(AriaRole.valueOf(role));
    }

    public static Locator getByTestId(Page page, String testId) {
        return page.getByTestId(testId);
    }

    public static Locator getByText(Page page, String text) {
        return page.getByText(text);
    }

    public static Locator getByTitle(Page page, String title) {
        return page.getByTitle(title);
    }

    // Drag and drop actions

    public static void dragTo(Page page, String sourceSelector, String targetSelector) {
        Locator source = page.locator(sourceSelector);
        Locator target = page.locator(targetSelector);
        source.dragTo(target);
    }
}

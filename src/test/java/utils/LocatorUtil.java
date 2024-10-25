package utils;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

public class LocatorUtil extends BasePlaywrightUtil {

    public LocatorUtil(Page page) {
        super(page);
    }

    // Locator-related methods
    public String getTextContent(String selector) {
        return page.locator(selector).textContent();
    }

    public void clickElement(String selector) {
        page.locator(selector).click();
    }

    public void fillInput(String selector, String value) {
        page.locator(selector).fill(value);
    }

    public void checkCheckbox(String selector) {
        page.locator(selector).check();
    }

    public void uncheckCheckbox(String selector) {
        page.locator(selector).uncheck();
    }

    public void hoverOverElement(String selector) {
        page.locator(selector).hover();
    }

    public boolean isElementVisible(String selector) {
        return page.locator(selector).isVisible();
    }

    public boolean isElementChecked(String selector) {
        return page.locator(selector).isChecked();
    }

    public void setInputFiles(String selector, String filePath) {
        page.locator(selector).setInputFiles(Paths.get(filePath));
    }

    public void tapElement(String selector) {
        page.locator(selector).tap();
    }

    public void selectOption(String selector, String value) {
        page.locator(selector).selectOption(value);
    }

    public void pressKey(String selector, String key) {
        page.locator(selector).press(key);
    }

    public void scrollToElement(String selector) {
        page.locator(selector).scrollIntoViewIfNeeded();
    }

    public void takeScreenshot(String selector, String filePath) {
        page.locator(selector).screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(filePath)));
    }

    // Utility methods for specific Locator functions
    public int getElementCount(String selector) {
        return page.locator(selector).count();
    }

    public ElementHandle getBoundingBox(String selector) {
        return (ElementHandle) page.locator(selector).boundingBox();
    }

    public void dispatchCustomEvent(String selector, String eventType) {
        page.locator(selector).dispatchEvent(eventType);
    }

    public Locator getFirstElement(String selector) {
        return page.locator(selector).first();
    }

    public Locator getLastElement(String selector) {
        return page.locator(selector).last();
    }

    public Locator getNthElement(String selector, int index) {
        return page.locator(selector).nth(index);
    }

    // Methods for advanced locator strategies (by label, role, testId, etc.)
    public Locator getByAltText(String altText) {
        return page.getByAltText(altText);
    }

    public Locator getByLabel(String label) {
        return page.getByLabel(label);
    }

    public Locator getByPlaceholder(String placeholder) {
        return page.getByPlaceholder(placeholder);
    }

    public Locator getByRole(String role) {
        return page.getByRole(AriaRole.valueOf(role));
    }

    public Locator getByTestId(String testId) {
        return page.getByTestId(testId);
    }

    public Locator getByText(String text) {
        return page.getByText(text);
    }

    public Locator getByTitle(String title) {
        return page.getByTitle(title);
    }

    // Drag and drop actions
    public void dragTo(String sourceSelector, String targetSelector) {
        Locator source = page.locator(sourceSelector);
        Locator target = page.locator(targetSelector);
        source.dragTo(target);
    }
}

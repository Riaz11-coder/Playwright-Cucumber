package utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionUtil extends BasePlaywrightUtil{



    public AssertionUtil(Page page){
        super(page);
    }

    public void assertTitle(String expectedTitle) {
        assertEquals(expectedTitle, page.title(), "The page title does not match the expected title.");
    }

    public void assertUrl(String expectedUrl) {
        assertEquals(expectedUrl, page.url(), "The page URL does not match the expected URL.");
    }

    public void assertVisible(String selector) {
        assertTrue(page.locator(selector).isVisible(), "The element with selector '" + selector + "' is not visible.");
    }

    public void assertHidden(String selector) {
        assertFalse(page.locator(selector).isVisible(), "The element with selector '" + selector + "' is not hidden.");
    }

    public void assertText(String selector, String expectedText) {
        assertEquals(expectedText, page.locator(selector).textContent(), "The text content does not match the expected text.");
    }

    public void assertEnabled(String selector) {
        assertFalse(page.locator(selector).isDisabled(), "The element with selector '" + selector + "' is disabled but expected to be enabled.");
    }

    public void assertDisabled(String selector) {
        assertTrue(page.locator(selector).isDisabled(), "The element with selector '" + selector + "' is enabled but expected to be disabled.");
    }

    public void assertRole(String selector, AriaRole expectedRole) {
        assertEquals(expectedRole.toString().toLowerCase(),
                page.locator(selector).getAttribute("role"),
                "The role does not match the expected ARIA role.");
    }
}


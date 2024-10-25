package stepDefinitions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertTrue;
@Execution(ExecutionMode.CONCURRENT)
//@UsePlaywright
public class GoogleSearchTest {
     Playwright playwright;
     Browser browser;
     Page page;

    @BeforeEach
    public void setUp() {
        playwright = Playwright.create(); // Initialize Playwright
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); // Launch browser
        page = browser.newPage(); // Create a new page (browser tab)
    }

    @AfterEach
    public void tearDown() {
        page.close(); // Close the page
        browser.close(); // Close the browser
        playwright.close(); // Clean up Playwright
    }

    @Test
    public void testGoogleSearch() {
        // Step 1: Navigate to Google Homepage
        page.navigate("https://www.google.com");

        // Step 2: Check if page contains "Google" in the title
        String title = page.title();
        assertTrue(title.contains("Google"), "The page title should contain 'Google'");

        // Step 3: Type "Playwright" into the Google search bar
        page.fill("[name='q']", "Playwright");

        // Step 4: Submit the search form by pressing Enter
        page.press("[name='q']", "Enter");

        // Step 5: Wait for results page to load and check the title contains "Playwright"
        page.waitForSelector("#search");
        String newTitle = page.title();
        assertTrue(newTitle.contains("Playwright"), "The search results title should contain 'Playwright'");
    }

    @Test
    public void testGoogleSearch2() {
        // Step 1: Navigate to Google Homepage
        page.navigate("https://www.google.com");

        // Step 2: Check if page contains "Google" in the title
        String title = page.title();
        assertTrue(title.contains("Google"), "The page title should contain 'Google'");

        // Step 3: Type "Playwright" into the Google search bar
        page.fill("[name='q']", "Playwright");

        // Step 4: Submit the search form by pressing Enter
        page.press("[name='q']", "Enter");

        // Step 5: Wait for results page to load and check the title contains "Playwright"
        page.waitForSelector("#search");
        String newTitle = page.title();
        assertTrue(newTitle.contains("Playwright"), "The search results title should contain 'Playwright'");
    }

    @Test
    public void testGoogleSearch3() {
        // Step 1: Navigate to Google Homepage
        page.navigate("https://www.google.com");

        // Step 2: Check if page contains "Google" in the title
        String title = page.title();
        assertTrue(title.contains("Google"), "The page title should contain 'Google'");

        // Step 3: Type "Playwright" into the Google search bar
        page.fill("[name='q']", "Playwright");

        // Step 4: Submit the search form by pressing Enter
        page.press("[name='q']", "Enter");

        // Step 5: Wait for results page to load and check the title contains "Playwright"
        page.waitForSelector("#search");
        String newTitle = page.title();
        assertTrue(newTitle.contains("Playwright"), "The search results title should contain 'Playwright'");
    }
}



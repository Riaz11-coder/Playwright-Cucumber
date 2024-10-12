package hooks;

import io.cucumber.java.BeforeAll;

public class GlobalHooks {

    @BeforeAll
    public static void beforeAllTests() {
        System.out.println("Cleaning up old trace files...");
        TraceCleaner.clearOldTraces();  // Clean traces before running all tests
    }
}

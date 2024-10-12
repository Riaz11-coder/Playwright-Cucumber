package hooks;

import com.microsoft.playwright.Tracing;
import cucumber.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.BrowserUtils;

import java.nio.file.Path;

public class Hooks {
    private TestContext testContext;


    public Hooks(){
        this.testContext = TestContext.getInstance();
    }

    @Before
    public void beforeScenario() {
        testContext.getPage();
    }

    @Before
    public void startTracing() {
        // If tracing is already started, stop it first to avoid overlap
        try {
            testContext.getPage().context().tracing().stop();
        } catch (Exception e) {
            // Ignore any error if there's no tracing to stop
        }

        // Start fresh tracing before each scenario
        testContext.getPage().context().tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }



    @AfterStep()
    public void afterStep(Scenario scenario){
        if(scenario.isFailed()){
            BrowserUtils.getScreenShot(testContext,scenario);
        }

    }


    @After()
    public void AfterAll(Scenario scenario) {
        //zsh terminal command to open Playwright Trace Viewer to view trace.zip
        //npx playwright show-trace + trace file name

        String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");

        // Create the trace folder in 'target/traceFiles'
        Path traceFolder = TraceFileUtils.createTraceFolder();

        // Generate unique file name with timestamp
        String traceFileName = "trace_" + scenarioName + "_" + System.currentTimeMillis() + ".zip";
        Path traceFilePath = traceFolder.resolve(traceFileName);

        // Save trace for this specific iteration in the trace folder
        testContext.getPage().context().tracing().stop(new Tracing.StopOptions()
                .setPath(traceFilePath));

        System.out.println("Saved trace for scenario: " + scenario.getName() + " to " + traceFilePath.toString());

        // Cleanup after scenario
        TestContext.reset();
    }
}

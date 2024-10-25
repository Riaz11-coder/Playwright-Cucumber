package hooks;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import cucumber.TestContext;
import io.cucumber.java.*;
import managers.BrowserManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import utils.BrowserUtils;

import java.nio.file.Path;

public class Hooks {
    private TestContext testContext;


    public Hooks() {
        this.testContext = new TestContext();
    }

    @Before
    public void beforeScenario() {
        testContext.getPageObjectManager();
    }

    @Before
    public void startTracing() {
        Page page = testContext.getPage();
        page.context().tracing().start(new Tracing.StartOptions()
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
        //npx playwright show-trace path/to/trace.zip

        String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");

        // Create the trace folder in 'target/traceFiles'
        Path traceFolder = TraceFileUtils.createTraceFolder();

        // Generate unique file name with timestamp
        String traceFileName = "trace_" + scenarioName + "_" + System.currentTimeMillis() + ".zip";
        Path traceFilePath = traceFolder.resolve(traceFileName);
        Page page = testContext.getPage();
        // Save trace for this specific iteration in the trace folder
        page.context().tracing().stop(new Tracing.StopOptions()
                .setPath(traceFilePath));

        System.out.println("Saved trace for scenario: " + scenario.getName() + " to " + traceFilePath.toString());

        // Cleanup after scenario
        //TestContext.reset();
    }

    @AfterEach
    public void cleanUp(){
        testContext.cleanUp();
    }








}

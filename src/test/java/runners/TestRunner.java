package runners;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.*;





@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME,value = "stepDefinitions,hooks")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME,value = "@GoogleSearch")
@ConfigurationParameter(key = Constants.EXECUTION_DRY_RUN_PROPERTY_NAME,value = "false")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME,value = "pretty,json:target/cucumber-reports/Cucumber.json,junit:target/cucumber-reports/Cucumber.xml,html:target/cucumber-reports/cucumber-report.html")
public class TestRunner {
}




package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags="${cucumber.filter.tags}",
        features = "src/test/resources/features", glue = {"stepdefinitions"},
        plugin = { "pretty", "json:target/cucumber-reports/cucumber.json",	"html:target/cucumber-reports/cucumberreport.html" }, monochrome = true)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests{
}

package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/hunter_api_leads.feature",
	glue = "stepsDefinition",
	tags = "@Tc03",
	plugin = {
		"pretty", "html:target/report-html.html", "json:target/report.json" }, monochrome = true)
public class RunnerAPI {

}
package apiautomation.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue= "apiautomation.steps",
    tags = "@ListUser",
    plugin = {
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "json:target/cucumber-reports.json"},
    monochrome = true

)
public class Runner {
    public Runner() {
    }
}

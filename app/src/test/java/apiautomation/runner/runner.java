package apiautomation.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = { "src/test/resources/features"},
    glue = { "apiautomation.steps" },
    plugin = {
"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "json:target/cucumber-reports.json" },
         monochrome = true,
         tags = "@Regresion"
)

public class runner {
    public runner() {

    }


}

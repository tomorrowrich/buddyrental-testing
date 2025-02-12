package buddyrental_testing;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",  // Path to the feature files
        glue = "hellocucumber",           // Package where the step definitions are
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class cucumberTestRunner {
}

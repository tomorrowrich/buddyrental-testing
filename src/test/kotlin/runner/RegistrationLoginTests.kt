package runner

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["src/test/resources/registrationLogin"],
    glue = ["buddyrental/registrationLogin"],
    plugin = ["pretty", "html:target/RegistrationLogin.html"],
    tags = "@RegistrationLogin"
)
class RegistrationLoginTests 

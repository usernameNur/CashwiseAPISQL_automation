package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = "json:target/reports/cucumber.json",
        features = "src/test/resources",
        glue = "steps",
        tags = "@regression",
        dryRun = true

)
public class CucumberRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }


}
//mvn test -Dcucumber.filter.tags="@createInvoice"      To run through terminal maven
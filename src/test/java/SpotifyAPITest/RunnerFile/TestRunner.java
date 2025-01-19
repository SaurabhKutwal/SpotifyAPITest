package SpotifyAPITest.RunnerFile;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(features = "src/test/java/SpotifyAPITest/FeatureFile",monochrome = true,
plugin = {"pretty","html:output/reports/report.html"})
public class TestRunner extends AbstractTestNGCucumberTests {
//    @DataProvider(parallel = true)
//    @Override
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }
}

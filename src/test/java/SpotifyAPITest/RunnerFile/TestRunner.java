package SpotifyAPITest.RunnerFile;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(features = "src/test/java/SpotifyAPITest/FeatureFile",monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

}

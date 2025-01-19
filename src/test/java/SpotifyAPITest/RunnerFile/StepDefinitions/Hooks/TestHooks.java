package SpotifyAPITest.RunnerFile.StepDefinitions.Hooks;


import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TestHooks{
    private static final Logger logger = LogManager.getLogger(TestHooks.class);

    @BeforeAll
    public static void before_all(){
        logger.info("Execution started.......");
    }

    @AfterAll
    public static void after_all(){
        logger.info("Execution finished.......");
    }

    @Before
    public void beforeScenario(Scenario scenario){
        logger.info("Execution started for scenario : {}", scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario){
        logger.info("Execution finished for scenario : {}", scenario.getName());
        logger.info("Status of scenario :{}", scenario.getStatus());
    }

}

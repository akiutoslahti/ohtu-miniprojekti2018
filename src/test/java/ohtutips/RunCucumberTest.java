package ohtutips;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import java.util.logging.Level;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"})
public class RunCucumberTest {
}

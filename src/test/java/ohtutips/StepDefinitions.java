package ohtutips;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration()
@SpringBootTest( properties = "server.port=8080", classes = OhtuTipsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StepDefinitions {
    
    private WebDriver driver = new HtmlUnitDriver();
    private String baseUrl = "http://localhost:8080";
    
    @Given("website is loaded")
    public void website_is_loaded() throws Throwable {
        driver.get(baseUrl);
    }
    
    @Then("frontpage loads")
    public void frontpage_loads() throws Throwable {
        pageHasContent("Reading Tips Archive");
    }

    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
}

package ohtutips;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs extends SpringIntegrationTest {
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:8080";
   
    
    @Given("website is loaded")
    public void website_is_loaded() throws Throwable {
        driver.get(baseUrl);
    }
    
    @Then("frontpage loads")
    public void frontpage_loads() throws Throwable {
        pageHasContent("Reading Tips Archive");
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }

    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
}

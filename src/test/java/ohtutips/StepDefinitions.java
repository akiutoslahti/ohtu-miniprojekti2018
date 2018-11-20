package ohtutips;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration()
@SpringBootTest(properties = "server.port=8080", classes = OhtuTipsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StepDefinitions {

    private WebDriver driver = new HtmlUnitDriver();
    private String baseUrl = "http://localhost:8080";

    @Given("user has opened the application and link {string} has been clicked")
    public void user_has_opened_the_application_and_link_has_been_clicked(String linkText) {
    }

    @When("all necessary book tip field have been filled")
    public void all_necessary_book_tip_field_have_been_filled() {
    }

    @When("book tip has been submitted")
    public void book_tip_has_been_submitted() {
    }

    @Then("list of book tips has {int} entries")
    public void list_of_book_tips_has_entries(int amount) {
        WebElement element = driver.findElement(By.id("book-tips"));
        List<WebElement> list = element.findElements(By.xpath(".//li"));
        assertEquals(amount, list.size());
    }

    @Then("one of them is the newly created one")
    public void one_of_them_is_the_newly_created_one() {
    }

    @When("all necessary book tip field have not been filled")
    public void all_necessary_book_tip_field_have_not_been_filled() {
    }

    @Then("error message {string} is shown")
    public void error_message_is_shown(String errorMsg) {
    }

    @Given("user has opened the application")
    public void user_has_opened_the_application() {
        driver.get(baseUrl);
    }

    @Then("list of book tips is shown")
    public void list_of_book_tips_is_shown() {
        pageHasContent("Reading Tips Archive");
        pageHasContent("Books");
    }

    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

}

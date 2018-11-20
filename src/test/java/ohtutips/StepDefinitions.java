package ohtutips;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import ohtutips.domain.BookTip;
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
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText(linkText));
        element.click();
    }

    @When("all necessary book tip fields have been filled")
    public void all_necessary_book_tip_fields_have_been_filled() {
        BookTip bookTip = oneBookTest();
        WebElement element = driver.findElement(By.name("author"));
        element.sendKeys(bookTip.getAuthor());
        element = driver.findElement(By.name("title"));
        element.sendKeys(bookTip.getTitle());
        element = driver.findElement(By.name("type"));
        element.sendKeys(bookTip.getType());
        element = driver.findElement(By.name("isbn"));
        element.sendKeys(bookTip.getIsbn());
        element = driver.findElement(By.name("tags"));
        element.sendKeys(bookTip.getTags());
    }

    @When("book tip has been submitted")
    public void book_tip_has_been_submitted() {
        driver.findElement(By.tagName("form")).submit();
    }

    @Then("list of book tips has {int} entries")
    public void list_of_book_tips_has_entries(int amount) {
        WebElement element = driver.findElement(By.id("book-tips"));
        List<WebElement> list = element.findElements(By.xpath(".//li"));
        assertEquals(amount, list.size());
    }

    @Then("one of them is the newly created one")
    public void one_of_them_is_the_newly_created_one() {
        BookTip bookTip = oneBookTest();
        boolean contains = false;
        WebElement element = driver.findElement(By.id("book-tips"));
        List<WebElement> list = element.findElements(By.xpath(".//li"));
        for (WebElement tip : list) {
            if (tip.getText().contains(bookTip.getIsbn())) {
                contains = true;
                break;
            }
        }
        assertTrue(contains);
    }

    @When("all necessary book tip fields have not been filled")
    public void all_necessary_book_tip_fields_have_not_been_filled() {
        BookTip bookTip = oneBookTest();
        WebElement element = driver.findElement(By.name("author"));
        element.sendKeys(bookTip.getAuthor());
        element = driver.findElement(By.name("title"));
        element.sendKeys(bookTip.getTitle());
        element = driver.findElement(By.name("type"));
        element.sendKeys(bookTip.getType());
        element.sendKeys(bookTip.getTags());
    }

    @Then("error message {string} is shown")
    public void error_message_is_shown(String errorMsg) {
        pageHasContent(errorMsg);
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
    
    private BookTip oneBookTest() {
        BookTip bookTip = new BookTip();
        bookTip.setTitle("The Martian");
        bookTip.setAuthor("Andy Weir");
        bookTip.setType("Book");
        bookTip.setIsbn("978-0091956134");
        bookTip.setTags("Science Fiction");
        bookTip.setPrerequisiteCourses("");
        bookTip.setRelatedCourses("");
        return bookTip;
    }

}

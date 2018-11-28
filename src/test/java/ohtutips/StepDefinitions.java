package ohtutips;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import ohtutips.domain.BookTip;
import static org.junit.Assert.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration()
@SpringBootTest(properties = "server.port=8080",
        classes = OhtuTipsApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StepDefinitions {

    private WebDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
    private String baseUrl = "http://localhost:8080";

    @Given("user has opened the application")
    public void user_has_opened_the_application() {
        driver.get(baseUrl);
    }

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
        element = driver.findElement(By.name("isbn"));
        element.sendKeys(bookTip.getIsbn());
        element = driver.findElement(By.name("tags"));
        element.sendKeys(bookTip.getTags());
    }

    @When("book tip has been submitted")
    public void book_tip_has_been_submitted() {
        driver.findElement(By.tagName("form")).submit();
    }

    @When("user navigates to book tip details")
    public void user_navigates_to_book_tip_details() {
        BookTip bookTip = oneBookTest();
        WebElement element = driver.findElement(
                By.linkText(bookTip.getTitle() + " by " + bookTip.getAuthor()));
        element.click();
        pageHasContent("Book tip details");
        pageHasContent("Author: " + bookTip.getAuthor());
        pageHasContent("Title: " + bookTip.getTitle());
        pageHasContent("ISBN: " + bookTip.getIsbn());
    }

    @When("user navigates to any book tip details")
    public void user_navigates_to_any_book_tip_details() {
        WebElement element = driver.findElement(By.id("book-tips"));
        element = element.findElement(By.xpath(".//a[1]"));
        element.click();
    }

    @When("clicks delete button")
    public void clicks_delete() throws Throwable {
        driver.findElement(By.id("delete-button")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(500);
    }

    @When("'sort by title' is clicked")
    public void clicks_sort_by_title() throws Throwable {
        driver.findElement(By.id("titleSort")).click();
        Thread.sleep(500);
    }

    @When("'sort by author' is clicked")
    public void clicks_sort_by_author() throws Throwable {
        driver.findElement(By.id("authorSort")).click();
        Thread.sleep(500);
    }

    @When("'sort by id' is clicked")
    public void clicks_sort_by_id() throws Throwable {
        driver.findElement(By.id("idSort")).click();
    }

    @When("clicks Edit button")
    public void clicks_edit_button() throws Throwable {
        driver.findElement(By.id("edit-button")).click();
        Thread.sleep(500);
    }

    @When("clicks Save button")
    public void clicks_save_button() throws Throwable {
        driver.findElement(By.id("save-button")).click();
        Thread.sleep(500);
    }

    @When("all necessary book tip fields have not been filled")
    public void all_necessary_book_tip_fields_have_not_been_filled() {
        BookTip bookTip = oneBookTest();
        WebElement element = driver.findElement(By.name("author"));
        element.sendKeys(bookTip.getAuthor());
        element = driver.findElement(By.name("title"));
        element.sendKeys(bookTip.getTitle());
        element = driver.findElement(By.name("isbn"));
        element.sendKeys(bookTip.getTags());
    }

    @Then("list of book tips is shown")
    public void list_of_book_tips_is_shown() {
        pageHasContent("Reading Tips Archive");
        pageHasContent("Books");
    }

    @Then("list of book tips has {int} entries")
    public void list_of_book_tips_has_entries(int amount) {
        WebElement element = driver.findElement(By.id("book-tips"));
        List<WebElement> list = element.findElements(By.xpath(".//li"));
        assertEquals(amount, list.size());
    }

    @Then("one of them is the newly created one")
    public void one_of_them_is_the_newly_created_one() {
        assertTrue(bookTipListContains(oneBookTest()));
    }

    @Then("error message {string} is shown")
    public void error_message_is_shown(String errorMsg) {
        pageHasContent(errorMsg);
    }

    @Then("deleted one is not listed")
    public void deleted_one_is_not_listed() {
        assertFalse(bookTipListContains(oneBookTest()));
    }

    @When("enters valid book title")
    public void enters_valid_book_title() {
        WebElement element = driver.findElement(By.name("title"));
        element.clear();
        element.sendKeys("The Hitchhiker's Guide to the Galaxy");
    }

    @Then("changed book title is shown")
    public void changed_book_title_is_shown() {
        driver.findElement(By.linkText("back")).click();
        pageHasContent("The Hitchhiker's Guide to the Galaxy");
    }

    @When("empties title field")
    public void empties_title_field() {
        WebElement element = driver.findElement(By.name("title"));
        element.clear();
    }

    @Then("book tips are sorted by id")
    public void book_tips_are_sorted_by_id() {
        WebElement tipsElement = driver.findElement(By.id("book-tips"));
        List<WebElement> allTips = tipsElement.findElements(By.xpath(".//a"));
        int previousId = -1;
        for (int i = 0; i < allTips.size(); i++) {
            int currentId = Integer.parseInt(allTips.get(i).getAttribute("id"));
            assertTrue(currentId > previousId);
            previousId = currentId;
        }
    }

    @Then("book tips are sorted by author")
    public void book_tips_are_sorted_by_author() {
        WebElement tipsElement = driver.findElement(By.id("book-tips"));
        List<WebElement> allTips = tipsElement.findElements(By.xpath(".//a"));
        assertEquals("Structure and Interpretation of Computer Programs by Abelson, Harold", allTips.get(0).getText());
        assertEquals("The C programming language by Kernighan, Brian W.", allTips.get(1).getText());
        assertEquals("Introduction to the Theory of Computation by Sipser, Michael", allTips.get(2).getText());
    }

    @Then("book tips are sorted by title")
    public void book_tips_are_sorted_by_title() {
        WebElement tipsElement = driver.findElement(By.id("book-tips"));
        List<WebElement> allTips = tipsElement.findElements(By.xpath(".//a"));
        assertEquals("Introduction to the Theory of Computation by Sipser, Michael", allTips.get(0).getText());
        assertEquals("Structure and Interpretation of Computer Programs by Abelson, Harold", allTips.get(1).getText());
        assertEquals("The C programming language by Kernighan, Brian W.", allTips.get(2).getText());

    }

    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private boolean bookTipListContains(BookTip bookTip) {
        WebElement element = driver.findElement(By.id("book-tips"));
        List<WebElement> list = element.findElements(By.xpath(".//li"));
        for (WebElement tip : list) {
            if (tip.getText().contains(bookTip.getTitle())
                    && tip.getText().contains(bookTip.getAuthor())) {
                return true;
            }
        }
        return false;
    }

    private BookTip oneBookTest() {

        BookTip bookTip = new BookTip();
        bookTip.setTitle("Introduction to algorithms");
        bookTip.setAuthor("Cormen, Thomas H.");
        bookTip.setIsbn("978-0-262-03384-8");
        bookTip.setTags("Algorithms");
        bookTip.setPrerequisiteCourses("");
        bookTip.setRelatedCourses("");

        return bookTip;
    }

}

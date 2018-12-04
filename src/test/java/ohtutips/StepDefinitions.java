package ohtutips;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import ohtutips.domain.BlogTip;
import ohtutips.domain.BookTip;
import ohtutips.domain.Tip;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration()
@SpringBootTest(properties = "server.port=8080",
        classes = OhtuTipsApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StepDefinitions {

    private final WebDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
    //private final WebDriver driver = new ChromeDriver();
    private final String baseUrl = "http://localhost:8080";

    @Given("application has been opened")
    public void user_has_opened_the_application() {
        driver.get(baseUrl);
    }

    @Given("button add new reading tip has been clicked")
    public void button_add_new_reading_tip_has_been_clicked() {
        driver.findElement(By.id("add-new-reading-tip")).click();
    }

    @When("valid filter {string} is applied")
    public void valid_filter_string_is_typed_into_filter_field(String filter) {
        enter_filter_string(filter);
    }

    @When("invalid filter {string} is applied")
    public void invalid_filter_string_is_typed_into_filter_field(String filter) {
        enter_filter_string(filter);
    }

    @When("filter is removed")
    public void filter_is_removed() {
        WebElement element = driver.findElement(By.id("filterInput"));
        // Sending backspaces instead of .clear to simulate typing and trigger JS
        element.sendKeys(Keys.BACK_SPACE);
        element.sendKeys(Keys.BACK_SPACE);
    }

    @When("all necessary {string} tip fields have been filled")
    public void all_necessary_tip_fields_have_been_filled(String tipType) throws Throwable {
        Tip testTip = null;
        if (tipType.equals("book")) {
            testTip = oneBookTest();
        } else if (tipType.equals("blog")) {
            testTip = oneBlogTest();
        } else {
            fail();
        }

        WebElement element = driver.findElement(By.name("author"));
        element.sendKeys(testTip.getAuthor());
        element = driver.findElement(By.name("title"));
        element.sendKeys(testTip.getTitle());
        element = driver.findElement(By.name("tags"));
        element.sendKeys(testTip.getTags());

        if (tipType.equals("book")) {
            element = driver.findElement(By.name("isbn"));
            element.sendKeys(oneBookTest().getIsbn());
        } else {
            element = driver.findElement(By.name("url"));
            element.sendKeys(oneBlogTest().getUrl());
        }
    }

    @When("user navigates to {string} tip details")
    public void user_navigates_to_tip_details(String tipType) {
        Tip testTip = null;
        if (tipType.equals("book")) {
            testTip = oneBookTest();
        } else if (tipType.equals("blog")) {
            testTip = oneBlogTest();
        } else {
            fail();
        }

        WebElement element = driver.findElement(
                By.linkText(testTip.getTitle() + " by " + testTip.getAuthor()));
        element.click();
        pageHasContent(tipType.substring(0, 1).toUpperCase()
                + tipType.substring(1) + " tip details");
        pageHasContent("Author: " + testTip.getAuthor());
        pageHasContent("Title: " + testTip.getTitle());
        pageHasContent("Tags: " + testTip.getTags());
    }

    @When("any {string} tip is navigated to")
    public void any_tip_is_navigated_to(String tipType) {
        WebElement element = driver.findElement(By.id(tipType + "-tips"));
        element = element.findElement(By.xpath(".//a[1]"));
        element.click();
    }

    @When("{string} tip number {int} is navigated to")
    public void certain_tip_is_navigated_to(String tipType, int id) {
        WebElement element = driver.findElement(By.id(tipType + "-tips"));
        List<WebElement> list = element.findElements(By.xpath(".//a[1]"));
        WebElement certainElement = list.get(id);
        certainElement.click();
        pageHasContent(tipType + "-tip-details");
    }

    @When("sort by {string} is clicked")
    public void sort_by_is_clicked(String sortId) {
        driver.findElement(By.id(sortId + "Sort")).click();
    }

    @When("{string} button has been clicked")
    public void button_has_been_clicked(String buttonId) throws Throwable {
        driver.findElement(By.id(buttonId.toLowerCase() + "-button")).click();
        if (buttonId.toLowerCase().equals("delete")) {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            Thread.sleep(500);
        }
    }

    @When("all necessary {string} tip fields have not been filled")
    public void all_necessary_tip_fields_have_not_been_filled(String tipType) throws Throwable {
        Tip testTip = null;
        if (tipType.equals("book")) {
            testTip = oneBookTest();
        } else if (tipType.equals("blog")) {
            testTip = oneBlogTest();
        } else {
            fail();
        }

        WebElement element = driver.findElement(By.name("author"));
        element.sendKeys(testTip.getAuthor());
        element = driver.findElement(By.name("title"));
        element.sendKeys(testTip.getTitle());
        element = driver.findElement(By.name("tags"));
        element.sendKeys(testTip.getTags());
    }

    @When("valid {string} title has been entered")
    public void enters_valid_title(String tipType) {
        WebElement element = driver.findElement(By.name("title"));
        element.clear();
        element.sendKeys("This is a valid" + tipType + "title");
    }

    @When("title field has been emptied")
    public void empties_title_field() {
        WebElement element = driver.findElement(By.name("title"));
        element.clear();
    }

    @When("{string} tip number {int} is {string} studied")
    public void tip_studied_status(String tipType, int id, String status) throws Throwable {
        WebElement element = driver.findElement(By.id(tipType + "-tips"));
        List<WebElement> list = element.findElements(By.xpath(".//li"));
        WebElement checkElement = list.get(id).findElement(By.id("studiedcheck"));
        if (status.equals("not")) {
            assertFalse(checkElement.isSelected());
        } else {
            assertTrue(checkElement.isSelected());
        }
    }

    @When("{string} tip is {string} studied")
    public void tip_studied_status(String tipType, String status) throws Throwable {
        WebElement checkElement = driver.findElement(By.id("studiedcheck"));
        if (status.equals("not")) {
            assertFalse(checkElement.isSelected());
        } else {
            assertTrue(checkElement.isSelected());
        }
    }

    @When("{string} tip number {int} studied is clicked")
    public void tip_studied_status_changed(String tipType, int id) throws Throwable {
        WebElement element = driver.findElement(By.id(tipType + "-tips"));
        List<WebElement> list = element.findElements(By.xpath(".//li"));
        WebElement checkElement = list.get(id).findElement(By.id("studiedcheck"));
        checkElement.click();
        Thread.sleep(500);
    }

    @When("{string} tip studied is clicked")
    public void tip_studied_status_changed(String tipType) throws Throwable {
        WebElement checkElement = driver.findElement(By.id("studiedcheck"));
        checkElement.click();
        Thread.sleep(500);
    }

    @Then("all tips contain {string}")
    public void all_tips_contain_string(String filter) {
        WebElement element = driver.findElement(By.id("book-tips"));
        List<WebElement> list = element.findElements(By.xpath(".//a"));
        for (WebElement el : list) {
            assertTrue(el.getText().replace(" by ", "").toLowerCase().contains(filter));
        }

        element = driver.findElement(By.id("blog-tips"));
        list = element.findElements(By.xpath(".//a"));
        for (WebElement el : list) {
            assertTrue(el.getText().replace(" by ", "").toLowerCase().contains(filter));
        }

    }

    @Then("list of {string} tips is shown")
    public void list_of_tips_is_shown(String tipType) {
        pageHasContent("Reading Tips Archive");
        pageHasContent(tipType.substring(0, 1).toUpperCase() + tipType.substring(1));
    }

    @Then("list of {string} tips has {int} entries")
    public void list_of_tips_has_entries(String tipType, int amount) {
        WebElement element = driver.findElement(By.id(tipType + "-tips"));
        List<WebElement> list = element.findElements(By.xpath(".//li"));
        assertEquals(amount, list.size());
    }

    @Then("one of {string} is the newly created one")
    public void one_of_is_the_newly_created_one(String tipType) {
        if (tipType.equals("books")) {
            assertTrue(tipListContains(oneBookTest()));
        } else if (tipType.equals("blogs")) {
            assertTrue(tipListContains(oneBlogTest()));
        }
    }

    @Then("error message {string} is shown")
    public void error_message_is_shown(String errorMsg) {
        pageHasContent(errorMsg);
    }

    @Then("deleted {string} is not listed")
    public void deleted_is_not_listed(String tipType) {
        if (tipType.equals("book")) {
            assertFalse(tipListContains(oneBookTest()));
        } else if (tipType.equals("blog")) {
            assertFalse(tipListContains(oneBlogTest()));
        } else {
            fail();
        }
    }

    @Then("changed {string} title is shown")
    public void changed_title_is_shown(String tipType) {
        driver.findElement(By.id("back-button")).click();
        pageHasContent("This is a valid" + tipType + "title");
    }

    @Then("{string} tips are sorted by id")
    public void tips_are_sorted_by_id(String tipType) {
        WebElement tipsElement = driver.findElement(By.id(tipType + "-tips"));
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
        assertEquals("Structure and Interpretation of Computer Programs by Abelson, Harold",
                allTips.get(0).getText());
        assertEquals("The C programming language by Kernighan, Brian W.",
                allTips.get(1).getText());
        assertEquals("Introduction to the Theory of Computation by Sipser, Michael",
                allTips.get(2).getText());
    }

    @Then("book tips are sorted by title")
    public void book_tips_are_sorted_by_title() {
        WebElement tipsElement = driver.findElement(By.id("book-tips"));
        List<WebElement> allTips = tipsElement.findElements(By.xpath(".//a"));
        assertEquals("Introduction to the Theory of Computation by Sipser, Michael",
                allTips.get(0).getText());
        assertEquals("Structure and Interpretation of Computer Programs by Abelson, Harold",
                allTips.get(1).getText());
        assertEquals("The C programming language by Kernighan, Brian W.",
                allTips.get(2).getText());

    }

    @Then("blog tips are sorted by author")
    public void blog_tips_are_sorted_by_author() {
        WebElement tipsElement = driver.findElement(By.id("blog-tips"));
        List<WebElement> allTips = tipsElement.findElements(By.xpath(".//a"));
        assertEquals("The New Methodology by Fowler, Martin",
                allTips.get(0).getText());
        assertEquals("Make The Product Backlog DEEP by Pichler, Roman",
                allTips.get(1).getText());
        assertEquals("Dependency Injection Demystified by Shore, James",
                allTips.get(2).getText());
    }

    @Then("blog tips are sorted by title")
    public void blog_tips_are_sorted_by_title() {
        WebElement tipsElement = driver.findElement(By.id("blog-tips"));
        List<WebElement> allTips = tipsElement.findElements(By.xpath(".//a"));
        assertEquals("Dependency Injection Demystified by Shore, James",
                allTips.get(0).getText());
        assertEquals("Make The Product Backlog DEEP by Pichler, Roman",
                allTips.get(1).getText());
        assertEquals("The New Methodology by Fowler, Martin",
                allTips.get(2).getText());
    }

    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private boolean tipListContains(Tip testTip) {
        WebElement element;
        if (testTip instanceof BookTip) {
            element = driver.findElement(By.id("book-tips"));
        } else if (testTip instanceof BlogTip) {
            element = driver.findElement(By.id("blog-tips"));
        } else {
            return false;
        }

        List<WebElement> list = element.findElements(By.xpath(".//li"));
        for (WebElement tipElement : list) {
            if (tipElement.getText().contains(testTip.getTitle())
                    && tipElement.getText().contains(testTip.getAuthor())) {
                return true;
            }
        }
        return false;
    }

    private void enter_filter_string(String filter) {
        WebElement element = driver.findElement(By.id("filterInput"));
        element.clear();
        element.sendKeys(filter);
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

    private BlogTip oneBlogTest() {

        BlogTip blogTip = new BlogTip();
        blogTip.setTitle("The Three Rules of TDD");
        blogTip.setAuthor("Uncle Bob");
        blogTip.setUrl("http://butunclebob.com/ArticleS.UncleBob.TheThreeRulesOfTdd");
        blogTip.setTags("TDD");
        blogTip.setPrerequisiteCourses("");
        blogTip.setRelatedCourses("");

        return blogTip;
    }

}

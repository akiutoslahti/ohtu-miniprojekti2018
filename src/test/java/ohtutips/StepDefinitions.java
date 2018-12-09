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
import ohtutips.domain.BookTip;
import ohtutips.domain.LinkTip;
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

    private static final String BOOK = "book";
    private static final String BLOG = "blog";
    private static final String TUBE = "tube";
    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String TAGS = "tags";
    private static final String ISBN = "isbn";
    private static final String URL = "url";
    private static final String TIPS = "-tips";

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

    @When("filter {string} is applied")
    public void filter_string_is_typed_into_filter_field(String filter) {
        enter_filter_string(filter);
    }

    @When("{int} last characters from filter are removed")
    public void last_chars_from_filter_are_removed(int chars) {
        WebElement element = driver.findElement(By.id("filterInput"));
        // Sending backspaces instead of .clear to simulate typing and trigger JS
        for (int i = 0; i < chars; i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    @When("all necessary {string} tip fields have been filled")
    public void all_necessary_tip_fields_have_been_filled(String tipType) throws Throwable {
        Tip testTip = null;
        if (tipType.equals(BOOK)) {
            testTip = oneBookTest();
        } else if (tipType.equals(BLOG)) {
            testTip = oneBlogTest();
        } else if (tipType.equals(TUBE)) {
            testTip = oneTubeTest();
        } else {
            fail();
        }

        WebElement element = driver.findElement(By.name(AUTHOR));
        element.sendKeys(testTip.getAuthor());
        element = driver.findElement(By.name(TITLE));
        element.sendKeys(testTip.getTitle());
        element = driver.findElement(By.name(TAGS));
        element.sendKeys(testTip.getTags());

        if (tipType.equals(BOOK)) {
            element = driver.findElement(By.name(ISBN));
            element.sendKeys(oneBookTest().getIsbn());
        } else if (tipType.equals(BLOG)) {
            element = driver.findElement(By.name(URL));
            element.sendKeys(oneBlogTest().getUrl());
        } else if (tipType.equals(TUBE)) {
            element = driver.findElement(By.name(URL));
            element.sendKeys(oneTubeTest().getUrl());
        } else {
            fail();
        }
    }

    @When("user navigates to {string} tip details")
    public void user_navigates_to_tip_details(String tipType) {
        Tip testTip = null;
        if (tipType.equals(BOOK)) {
            testTip = oneBookTest();
        } else if (tipType.equals(BLOG)) {
            testTip = oneBlogTest();
        } else if (tipType.equals(TUBE)) {
            testTip = oneTubeTest();
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
        WebElement element = driver.findElement(By.id(tipType + TIPS));
        element = element.findElement(By.xpath(".//a[1]"));
        element.click();
    }

    //
    // Depends on the number of tips
    //
    @When("{string} tip number {int} is navigated to")
    public void certain_tip_is_navigated_to(String tipType, int id) {
        WebElement element = driver.findElement(By.id(tipType + TIPS));
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
        if (tipType.equals(BOOK)) {
            testTip = oneBookTest();
        } else if (tipType.equals(BLOG)) {
            testTip = oneBlogTest();
        } else if (tipType.equals(TUBE)) {
            testTip = oneTubeTest();
        } else {
            fail();
        }

        WebElement element = driver.findElement(By.name(AUTHOR));
        element.sendKeys(testTip.getAuthor());
        element = driver.findElement(By.name(TITLE));
        element.sendKeys(testTip.getTitle());
        element = driver.findElement(By.name(TAGS));
        element.sendKeys(testTip.getTags());
    }

    @When("valid {string} title has been entered")
    public void enters_valid_title(String tipType) {
        WebElement element = driver.findElement(By.name(TITLE));
        element.clear();
        element.sendKeys("This is a valid" + tipType + TITLE);
    }

    @When("title field has been emptied")
    public void empties_title_field() {
        WebElement element = driver.findElement(By.name(TITLE));
        element.clear();
    }

    //
    // Depends on the number of tips
    //
    @When("{string} tip number {int} is {string} studied")
    public void tip_studied_status(String tipType, int id, String status) throws Throwable {
        WebElement element = driver.findElement(By.id(tipType + TIPS));
        List<WebElement> list = element.findElements(By.xpath(".//li"));
        List<WebElement> delTags = list.get(id).findElements(By.tagName("del"));
        if (status.equals("not")) {
            assertTrue(delTags.isEmpty());
        } else {
            assertFalse(delTags.isEmpty());
        }
    }

    //
    // Depends on the number of tips
    //
    @When("{string} tip is {string} studied")
    public void tip_studied_status(String tipType, String status) throws Throwable {
        WebElement checkElement = driver.findElement(By.id("studiedcheck"));
        if (status.equals("not")) {
            assertFalse(checkElement.isSelected());
        } else {
            assertTrue(checkElement.isSelected());
        }
    }

    //
    // Depends on the number of tips
    //
    @When("{string} tip number {int} studied is clicked")
    public void tip_studied_status_changed(String tipType, int id) throws Throwable {
        WebElement element = driver.findElement(By.id(tipType + TIPS));
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
    
    @When("show studied is clicked")
    public void studied_filtering_changed() throws Throwable {
        WebElement checkElement = driver.findElement(By.id("includeStudied"));
        checkElement.click();
    }

    @Then("all tips contain {string}")
    public void all_tips_contain_string(String filter) {
        WebElement element = driver.findElement(By.id(BOOK + TIPS));
        List<WebElement> list = element.findElements(By.xpath(".//a"));
        for (WebElement el : list) {
            assertTrue(el.getText().replace(" by ", "").toLowerCase().contains(filter));
        }

        element = driver.findElement(By.id(BLOG + TIPS));
        list = element.findElements(By.xpath(".//a"));
        for (WebElement el : list) {
            assertTrue(el.getText().replace(" by ", "").toLowerCase().contains(filter));
        }

        element = driver.findElement(By.id(TUBE + TIPS));
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

    //
    // Depends on how many rows in database + possible filters
    //
    @Then("list of {string} tips has {int} entries")
    public void list_of_tips_has_entries(String tipType, int amount) {
        WebElement element = driver.findElement(By.id(tipType + TIPS));
        List<WebElement> list = element.findElements(By.xpath(".//li"));
        assertEquals(amount, list.size());
    }

    @Then("one tip of type {string} is the newly created one")
    public void one_tip_of_type_is_the_newly_created_one(String tipType) {
        if (tipType.equals(BOOK)) {
            assertTrue(tipListContains(oneBookTest(), tipType));
        } else if (tipType.equals(BLOG)) {
            assertTrue(tipListContains(oneBlogTest(), tipType));
        } else if (tipType.equals(TUBE)) {
            assertTrue(tipListContains(oneTubeTest(), tipType));
        } else {
            fail();
        }
    }

    @Then("error message {string} is shown")
    public void error_message_is_shown(String errorMsg) {
        pageHasContent(errorMsg);
    }

    @Then("deleted {string} is not listed")
    public void deleted_is_not_listed(String tipType) {
        if (tipType.equals(BOOK)) {
            assertFalse(tipListContains(oneBookTest(), tipType));
        } else if (tipType.equals(BLOG)) {
            assertFalse(tipListContains(oneBlogTest(), tipType));
        } else if (tipType.equals(TUBE)) {
            assertFalse(tipListContains(oneTubeTest(), tipType));
        } else {
            fail();
        }
    }

    @Then("changed {string} title is shown")
    public void changed_title_is_shown(String tipType) {
        driver.findElement(By.id("back-button")).click();
        pageHasContent("This is a valid" + tipType + TITLE);
    }

    @Then("{string} tips are sorted by id")
    public void tips_are_sorted_by_id(String tipType) {
        WebElement tipsElement = driver.findElement(By.id(tipType + TIPS));
        List<WebElement> allTips = tipsElement.findElements(By.xpath(".//a"));
        int previousId = -1;

        for (int i = 0; i < allTips.size(); i++) {
            int currentId = Integer.parseInt(allTips.get(i).getAttribute("id"));
            assertTrue(currentId > previousId);
            previousId = currentId;
        }
    }

    @Then("{string} tips are sorted by {string}")
    public void tips_are_sorted_by(String tipType, String sortedBy) {
        WebElement tipsElement = driver.findElement(By.id(tipType + TIPS));
        List<WebElement> tips = tipsElement.findElements(By.xpath(".//a"));
        String previous = "";
        for (WebElement tip : tips) {
            String fullText = tip.getText();
            if (sortedBy.equals(TITLE)) {
                String title = fullText.substring(0, fullText.indexOf(" by "));
                assertTrue(previous.compareToIgnoreCase(title) <= 0);
                previous = title;
            } else {
                String author = fullText.substring(fullText.indexOf(" by ") + 4, fullText.length());
                assertTrue(previous.compareToIgnoreCase(author) <= 0);
                previous = author;
            }
        }
    }

    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private boolean tipListContains(Tip testTip, String tipType) {
        WebElement element;
        if (testTip instanceof BookTip && tipType.equals(BOOK)) {
            element = driver.findElement(By.id(BOOK + TIPS));
        } else if (testTip instanceof LinkTip && tipType.equals(BLOG)) {
            element = driver.findElement(By.id(BLOG + TIPS));
        } else if (testTip instanceof LinkTip && tipType.equals(TUBE)) {
            element = driver.findElement(By.id(TUBE + TIPS));
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
        bookTip.setIsbn("978-0262033848");
        bookTip.setTags("Algorithms");
        bookTip.setDescription("");

        return bookTip;
    }

    private LinkTip oneBlogTest() {

        LinkTip blogTip = new LinkTip();
        blogTip.setTitle("The Three Rules of TDD");
        blogTip.setAuthor("Uncle Bob");
        blogTip.setUrl("http://butunclebob.com/ArticleS.UncleBob.TheThreeRulesOfTdd");
        blogTip.setTags("TDD");
        blogTip.setType(BLOG);
        blogTip.setDescription("");

        return blogTip;
    }

    private LinkTip oneTubeTest() {

        LinkTip tubeTip = new LinkTip();
        tubeTip.setTitle("Web Development 2018 - The Must-Know Tech");
        tubeTip.setAuthor("LearnCode.academy");
        tubeTip.setUrl("https://www.youtube.com/watch?v=gVXcqO9A1vo");
        tubeTip.setTags("Web Development");
        tubeTip.setType(TUBE);
        tubeTip.setDescription("A complete roadmap to being a successful web dev in 2018!");

        return tubeTip;
    }

}

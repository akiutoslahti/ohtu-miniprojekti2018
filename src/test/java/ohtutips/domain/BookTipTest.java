package ohtutips.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class BookTipTest {

    private BookTip testBook;

    @Before
    public void setUp() {
        this.testBook = new BookTip();
    }

    @Test
    public void setGetTitleTest() {
        assertNull(this.testBook.getTitle());
        this.testBook.setTitle("test title");
        assertEquals("test title", this.testBook.getTitle());
    }

    @Test
    public void setGetAuthorTest() {
        assertNull(this.testBook.getAuthor());
        this.testBook.setAuthor("test author");
        assertEquals("test author", this.testBook.getAuthor());
    }

    @Test
    public void setGetIsbnTest() {
        assertNull(this.testBook.getIsbn());
        this.testBook.setIsbn("test isbn");
        assertEquals("test isbn", this.testBook.getIsbn());
    }
    
    @Test
    public void setGetTagsTest() {
        assertNull(this.testBook.getTags());
        this.testBook.setTags("test tags");
        assertEquals("test tags", this.testBook.getTags());
    }
    
    @Test
    public void setGetPrerequisiteCoursesTest() {
        assertNull(this.testBook.getPrerequisiteCourses());
        this.testBook.setPrerequisiteCourses("test prerequisite courses");
        assertEquals("test prerequisite courses", this.testBook.getPrerequisiteCourses());
    }
    
    @Test
    public void setGetRelatedCoursesTest() {
        assertNull(this.testBook.getRelatedCourses());
        this.testBook.setRelatedCourses("test related courses");
        assertEquals("test related courses", this.testBook.getRelatedCourses());
    }
    
}

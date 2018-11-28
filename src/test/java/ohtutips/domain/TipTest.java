package ohtutips.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TipTest {

    private Tip testTip;

    @Before
    public void setUp() {
        testTip = new Tip() {
        };
    }

    @Test
    public void setGetTitleTest() {
        assertNull(this.testTip.getTitle());
        this.testTip.setTitle("test title");
        assertEquals("test title", this.testTip.getTitle());
    }

    @Test
    public void setGetAuthorTest() {
        assertNull(this.testTip.getAuthor());
        this.testTip.setAuthor("test author");
        assertEquals("test author", this.testTip.getAuthor());
    }

    @Test
    public void setGetTagsTest() {
        assertNull(this.testTip.getTags());
        this.testTip.setTags("test tags");
        assertEquals("test tags", this.testTip.getTags());
    }

    @Test
    public void setGetPrerequisiteCoursesTest() {
        assertNull(this.testTip.getPrerequisiteCourses());
        this.testTip.setPrerequisiteCourses("test prerequisite courses");
        assertEquals("test prerequisite courses", this.testTip.getPrerequisiteCourses());
    }

    @Test
    public void setGetRelatedCoursesTest() {
        assertNull(this.testTip.getRelatedCourses());
        this.testTip.setRelatedCourses("test related courses");
        assertEquals("test related courses", this.testTip.getRelatedCourses());
    }

    @Test
    public void setGetStudiedTest() {
        assertFalse(testTip.getStudied());
        testTip.setStudied(true);
        assertTrue(testTip.getStudied());
    }
}

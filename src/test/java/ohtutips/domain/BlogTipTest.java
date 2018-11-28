package ohtutips.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BlogTipTest {

    private BlogTip testBlog;

    @Before
    public void setUp() {
        testBlog = new BlogTip();
    }

    @Test
    public void setGetUrlTest() {
        testBlog.setUrl("test url");
        assertEquals("test url", testBlog.getUrl());
    }

}

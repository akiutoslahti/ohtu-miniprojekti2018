package ohtutips.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BlogTipTest {

    private LinkTip testBlog;

    @Before
    public void setUp() {
        testBlog = new LinkTip();
    }

    @Test
    public void setGetUrlTest() {
        testBlog.setUrl("test url");
        assertEquals("test url", testBlog.getUrl());
    }

}

package ohtutips.controller;

import com.zaxxer.hikari.HikariDataSource;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import ohtutips.domain.BlogTip;
import ohtutips.domain.BookTip;
import ohtutips.repository.BlogTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ohtutips.repository.BookTipRepository;
import org.springframework.data.domain.Sort;

@Controller
public class DefaultController {

    @Autowired
    private BookTipRepository bookTipRepository;

    @Autowired
    private BlogTipRepository blogTipRepository;

    @Autowired
    private HikariDataSource ds;

    @PostConstruct
    public void init() {
        if (ds.getJdbcUrl().contains("jdbc:h2:mem:testdb")) {
            ArrayList<BookTip> bookTips = initialBooks();
            for (BookTip tip : bookTips) {
                bookTipRepository.save(tip);
            }
            ArrayList<BlogTip> blogTips = initialBlogs();
            for (BlogTip tip : blogTips) {
                blogTipRepository.save(tip);
            }
        }
    }

    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("books", bookTipRepository.findAll(
                new Sort(Sort.Direction.ASC, "id")));
        return "index";
    }

    private ArrayList<BookTip> initialBooks() {
        ArrayList<BookTip> tips = new ArrayList<>();

        BookTip bookTip1 = new BookTip();
        bookTip1.setTitle("Lord Of The Rings");
        bookTip1.setAuthor("Tolkien, J. R. R.");
        bookTip1.setIsbn("978-0544003415");
        bookTip1.setTags("Fantasy");
        bookTip1.setPrerequisiteCourses("");
        bookTip1.setRelatedCourses("");

        tips.add(bookTip1);

        BookTip bookTip2 = new BookTip();
        bookTip2.setTitle("Ready Player One");
        bookTip2.setAuthor("Cline, Ernest");
        bookTip2.setIsbn("978-0099560432");
        bookTip2.setTags("Science Fiction");
        bookTip2.setPrerequisiteCourses("");
        bookTip2.setRelatedCourses("");

        tips.add(bookTip2);

        BookTip bookTip3 = new BookTip();
        bookTip3.setTitle("Introduction to the Theory of Computation");
        bookTip3.setAuthor("Sipser, Michael");
        bookTip3.setIsbn("978-1133187790");
        bookTip3.setTags("Computer Science");
        bookTip3.setPrerequisiteCourses("");
        bookTip3.setRelatedCourses("");

        tips.add(bookTip3);

        return tips;
    }

    private ArrayList<BlogTip> initialBlogs() {
        ArrayList<BlogTip> tips = new ArrayList<>();

        BlogTip blogTip1 = new BlogTip();
        blogTip1.setTitle("The New Methodology");
        blogTip1.setAuthor("Fowler, Martin");
        blogTip1.setURL("https://martinfowler.com/articles/newMethodology.html");
        blogTip1.setTags("Agile Development");
        blogTip1.setPrerequisiteCourses("");
        blogTip1.setRelatedCourses("");

        tips.add(blogTip1);

        return tips;
    }

}

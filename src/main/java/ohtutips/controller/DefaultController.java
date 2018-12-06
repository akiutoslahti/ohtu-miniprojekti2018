package ohtutips.controller;

import com.zaxxer.hikari.HikariDataSource;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import ohtutips.domain.BookTip;
import ohtutips.domain.LinkTip;
import ohtutips.repository.BookTipRepository;
import ohtutips.repository.LinkTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultController {

    @Autowired
    private BookTipRepository bookTipRepository;

    @Autowired
    private LinkTipRepository linkTipRepository;

    @Autowired
    private HikariDataSource ds;

    @PostConstruct
    public void init() {
        if (ds.getJdbcUrl().contains("jdbc:h2:mem:testdb")) {
            ArrayList<BookTip> bookTips = initialBooks();
            for (BookTip tip : bookTips) {
                bookTipRepository.save(tip);
            }
            ArrayList<LinkTip> blogTips = initialBlogs();
            for (LinkTip tip : blogTips) {
                linkTipRepository.save(tip);
            }
            ArrayList<LinkTip> tubeTips = initialTubes();
            for (LinkTip tip : tubeTips) {
                linkTipRepository.save(tip);
            }
        }
    }

    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("books", bookTipRepository.findAll(
                new Sort(Sort.Direction.ASC, "id")));
        model.addAttribute("blogs", linkTipRepository.findByType(
                "blog", new Sort(Sort.Direction.ASC, "id")));
        model.addAttribute("tubes", linkTipRepository.findByType(
                "tube", new Sort(Sort.Direction.ASC, "id")));
        return "index";
    }
    
    @RequestMapping(value = "/new_tip", method = RequestMethod.GET)
    public String new_tip(Model model) {
        return "addTip";
    }

    private ArrayList<BookTip> initialBooks() {
        ArrayList<BookTip> tips = new ArrayList<>();

        BookTip bookTip1 = new BookTip();
        BookTip bookTip2 = new BookTip();
        BookTip bookTip3 = new BookTip();

        tips.add(bookTip1);
        tips.add(bookTip2);
        tips.add(bookTip3);

        bookTip1.setTitle("Structure and Interpretation of Computer Programs");
        bookTip1.setAuthor("Abelson, Harold");
        bookTip1.setIsbn("978-0262510875");
        bookTip1.setTags("Programming");
        bookTip1.setDescription("Long time Programming101 coursebook from MIT.");

        bookTip2.setTitle("The C programming language");
        bookTip2.setAuthor("Kernighan, Brian W.");
        bookTip2.setIsbn("0-13-110370-9 ");
        bookTip2.setTags("Programming");
        bookTip2.setDescription("The one and only legendary C book.");

        bookTip3.setTitle("Introduction to the Theory of Computation");
        bookTip3.setAuthor("Sipser, Michael");
        bookTip3.setIsbn("978-1133187790");
        bookTip3.setTags("Computer Science");
        bookTip3.setDescription("Enjoyable read about models of computation.");

        return tips;
    }

    private ArrayList<LinkTip> initialBlogs() {
        ArrayList<LinkTip> tips = new ArrayList<>();

        LinkTip blogTip1 = new LinkTip();
        LinkTip blogTip2 = new LinkTip();
        LinkTip blogTip3 = new LinkTip();

        tips.add(blogTip1);
        tips.add(blogTip2);
        tips.add(blogTip3);

        blogTip1.setTitle("The New Methodology");
        blogTip1.setAuthor("Fowler, Martin");
        blogTip1.setUrl("https://martinfowler.com/articles/newMethodology.html");
        blogTip1.setTags("Agile Development");
        blogTip1.setType("blog");
        blogTip1.setDescription("Good overview about agile development.");

        blogTip2.setTitle("Dependency Injection Demystified");
        blogTip2.setAuthor("Shore, James");
        blogTip2.setUrl("https://www.jamesshore.com/Blog/Dependency-Injection-Demystified.html");
        blogTip2.setTags("Programming");
        blogTip2.setType("blog");
        blogTip2.setDescription("Teaches you a nice design pattern for efficient testing.");

        blogTip3.setTitle("Make The Product Backlog DEEP");
        blogTip3.setAuthor("Pichler, Roman");
        blogTip3.setUrl("https://www.romanpichler.com/blog/make-the-product-backlog-deep/");
        blogTip3.setTags("Agile Development");
        blogTip3.setType("blog");
        blogTip3.setDescription("Good guidelines for backlog grooming!");

        return tips;
    }

    private ArrayList<LinkTip> initialTubes() {
        ArrayList<LinkTip> tips = new ArrayList<>();

        LinkTip tubeTip1 = new LinkTip();

        tips.add(tubeTip1);

        tubeTip1.setTitle("Turing & The Halting Problem");
        tubeTip1.setAuthor("Computerphile");
        tubeTip1.setUrl("https://www.youtube.com/watch?v=macM_MtS_w4");
        tubeTip1.setTags("Computer Science");
        tubeTip1.setType("tube");
        tubeTip1.setDescription("Alan Turing almost accidentally created the blueprint "
                + "for the modern day digital computer...");

        return tips;
    }
}

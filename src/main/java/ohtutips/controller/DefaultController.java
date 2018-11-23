package ohtutips.controller;

import javax.annotation.PostConstruct;
import ohtutips.domain.BookTip;
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

    @PostConstruct
    public void init() {
        // purge previous data and insert initial data
        bookTipRepository.deleteAll();
        
        BookTip bookTip1 = new BookTip();
        bookTip1.setTitle("Lord Of The Rings");
        bookTip1.setAuthor("J. R. R. Tolkien");
        bookTip1.setType("Book");
        bookTip1.setIsbn("978-0544003415");
        bookTip1.setTags("Fantasy");
        bookTip1.setPrerequisiteCourses("");
        bookTip1.setRelatedCourses("");
        
        bookTipRepository.save(bookTip1);
        
        BookTip bookTip2 = new BookTip();
        bookTip2.setTitle("Silmarillion");
        bookTip2.setAuthor("J. R. R. Tolkien");
        bookTip2.setType("Book");
        bookTip2.setIsbn("978-0345325815");
        bookTip2.setTags("Fantasy");
        bookTip2.setPrerequisiteCourses("");
        bookTip2.setRelatedCourses("");
        
        bookTipRepository.save(bookTip2);

        BookTip bookTip3 = new BookTip();
        bookTip3.setTitle("The Hobbit or There and Back Again");
        bookTip3.setAuthor("J. R. R. Tolkien");
        bookTip3.setType("Book");
        bookTip3.setIsbn("978-0618002214");
        bookTip3.setTags("Fantasy");
        bookTip3.setPrerequisiteCourses("");
        bookTip3.setRelatedCourses("");
        
        bookTipRepository.save(bookTip3);
    }

    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("books", bookTipRepository.findAll(
                new Sort(Sort.Direction.ASC, "id")));
        return "index";
    }
    
    
}

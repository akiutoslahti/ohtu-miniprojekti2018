package ohtutips.controller;

import javax.annotation.PostConstruct;
import ohtutips.domain.Book;
import ohtutips.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultController {

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void init() {
        // test data to the application
        Book book1 = new Book();
        book1.setTitle("Lord Of The Rings");
        book1.setAuthor("J. R. R. Tolkien");
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("Silmarillion");
        book2.setAuthor("J. R. R. Tolkien");
        bookRepository.save(book2);

        Book book3 = new Book();
        book3.setTitle("Hobbit");
        book3.setAuthor("J. R. R. Tolkien");
        bookRepository.save(book3);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }

}

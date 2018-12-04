package ohtutips.controller;

import java.util.ArrayList;
import java.util.List;
import ohtutips.domain.BookTip;
import ohtutips.repository.BookTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {

    @Autowired
    private BookTipRepository bookTipRepository;

    @RequestMapping(value = "/book_tip/{id}", method = RequestMethod.GET)
    public String bookTipDetails(Model model, @PathVariable long id) {
        model.addAttribute("book", bookTipRepository.findById(id).get());
        return "bookTipDetails";
    }

    @RequestMapping(value = "/book_tip", method = RequestMethod.POST)
    public String addBookTip(Model model, @RequestParam String author,
            @RequestParam String title,
            @RequestParam String isbn, @RequestParam String tags,
            @RequestParam String prerequisiteCourses,
            @RequestParam String relatedCourses) {

        List<String> errors = new ArrayList<>();
        if (author.trim().isEmpty() || title.trim().isEmpty()
                || isbn.trim().isEmpty()
                || tags.trim().isEmpty()) {
            errors.add("Please fill all fields marked with (*).");

            model.addAttribute("errors", errors);
            return "addTip";
        }

        BookTip bookTip = new BookTip();
        bookTip.setAuthor(author);
        bookTip.setTitle(title);
        bookTip.setIsbn(isbn);
        bookTip.setTags(tags);
        bookTip.setPrerequisiteCourses(prerequisiteCourses);
        bookTip.setRelatedCourses(relatedCourses);

        bookTipRepository.save(bookTip);
        return "redirect:/";
    }

    @RequestMapping(value = "/book_tip/{id}", method = RequestMethod.DELETE)
    public String deleteBookTip(@PathVariable long id) {
        bookTipRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/book_tip/{id}", method = RequestMethod.PUT)
    public String modifyBookTip(@PathVariable long id, Model model,
            @RequestParam String author, @RequestParam String title,
            @RequestParam String isbn,
            @RequestParam String tags, @RequestParam String prerequisiteCourses,
            @RequestParam String relatedCourses) {

        if (author.trim().isEmpty() || title.trim().isEmpty()
                || isbn.trim().isEmpty()
                || tags.trim().isEmpty()) {
            List<String> errors = new ArrayList<>();

            errors.add("Please do not empty fields marked with (*).");

            model.addAttribute("errors", errors);
            model.addAttribute("book", bookTipRepository.findById(id).get());
            return "bookTipDetails";
        }

        BookTip bookTip = bookTipRepository.findById(id).get();
        bookTip.setAuthor(author);
        bookTip.setTitle(title);
        bookTip.setIsbn(isbn);
        bookTip.setTags(tags);
        bookTip.setPrerequisiteCourses(prerequisiteCourses);
        bookTip.setRelatedCourses(relatedCourses);

        bookTipRepository.save(bookTip);

        return "redirect:/book_tip/" + id;
    }
    
    @RequestMapping(value = "/book_tip/{id}/study", method = RequestMethod.POST)
    @ResponseBody
    public void study(@PathVariable long id, @RequestParam Integer studied) {
        BookTip bt = bookTipRepository.findById(id).get();
        
        bt.setStudied(studied == 1);
        
        bookTipRepository.save(bt);
    }
}

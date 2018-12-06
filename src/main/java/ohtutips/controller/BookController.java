package ohtutips.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
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

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @RequestMapping(value = "/book_tip/{id}", method = RequestMethod.GET)
    public String bookTipDetails(Model model, @PathVariable long id) {
        model.addAttribute("book", bookTipRepository.findById(id).get());
        return "bookTipDetails";
    }

    @RequestMapping(value = "/book_tip", method = RequestMethod.POST)
    public String addBookTip(Model model, @RequestParam String author,
            @RequestParam String title, @RequestParam String isbn,
            @RequestParam String tags, @RequestParam String description) {

        List<String> errors = new ArrayList<>();

        BookTip bookTip = new BookTip();
        bookTip.setAuthor(author);
        bookTip.setTitle(title);
        bookTip.setIsbn(isbn);
        bookTip.setTags(tags);
        bookTip.setDescription(description);

        Set<ConstraintViolation<BookTip>> violations = validator.validate(bookTip);
        for (ConstraintViolation<BookTip> violation : violations) {
            errors.add(violation.getMessage());
        }

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "addTip";
        }

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
            @RequestParam String isbn, @RequestParam String tags,
            @RequestParam String description) {

        BookTip bookTip = bookTipRepository.findById(id).get();
        bookTip.setAuthor(author);
        bookTip.setTitle(title);
        bookTip.setIsbn(isbn);
        bookTip.setTags(tags);
        bookTip.setDescription(description);

        try {
            bookTipRepository.save(bookTip);
        } catch (Exception e) {
            List<String> errors = new ArrayList<>();
            Set<ConstraintViolation<BookTip>> violations = validator.validate(bookTip);
            for (ConstraintViolation<BookTip> violation : violations) {
                errors.add(violation.getMessage());
            }
            model.addAttribute("errors", errors);
            model.addAttribute("book", bookTipRepository.findById(id).get());
            return "bookTipDetails";
        }

        return "redirect:/book_tip/" + id;
    }

    @RequestMapping(value = "/book_tip/{id}/study", method = RequestMethod.POST)
    @ResponseBody
    public void study(@PathVariable long id, @RequestParam Integer studied) {
        BookTip bt = bookTipRepository.findById(id).get();

        if (studied == 1) {
            bt.setStudied(true);
        } else {
            bt.setStudied(false);
        }

        bookTipRepository.save(bt);
    }
}

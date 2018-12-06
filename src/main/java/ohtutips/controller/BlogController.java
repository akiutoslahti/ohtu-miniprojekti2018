package ohtutips.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import ohtutips.domain.BlogTip;
import ohtutips.repository.BlogTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogController {

    @Autowired
    private BlogTipRepository blogTipRepository;
    
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @RequestMapping(value = "/blog_tip/{id}", method = RequestMethod.GET)
    public String blogTipDetails(Model model, @PathVariable long id) {
        model.addAttribute("blog", blogTipRepository.findById(id).get());
        return "blogTipDetails";
    }

    @RequestMapping(value = "/blog_tip", method = RequestMethod.POST)
    public String addBlogTip(Model model, @RequestParam String author,
            @RequestParam String title,
            @RequestParam String url, @RequestParam String tags,
            @RequestParam String prerequisiteCourses,
            @RequestParam String relatedCourses) {

        List<String> errors = new ArrayList<>();

        BlogTip blogTip = new BlogTip();
        blogTip.setAuthor(author);
        blogTip.setTitle(title);
        blogTip.setUrl(url);
        blogTip.setTags(tags);
        blogTip.setPrerequisiteCourses(prerequisiteCourses);
        blogTip.setRelatedCourses(relatedCourses);
        
        Set<ConstraintViolation<BlogTip>> violations = validator.validate(blogTip);
        for (ConstraintViolation<BlogTip> violation : violations) {
            errors.add(violation.getMessage());
        }
        
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "addTip";
        }

        blogTipRepository.save(blogTip);
        return "redirect:/";
    }

    @RequestMapping(value = "/blog_tip/{id}", method = RequestMethod.DELETE)
    public String deleteBlogTip(@PathVariable long id) {
        blogTipRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/blog_tip/{id}", method = RequestMethod.PUT)
    public String modifyBlogTip(@PathVariable long id, Model model,
            @RequestParam String author, @RequestParam String title,
            @RequestParam String url,
            @RequestParam String tags, @RequestParam String prerequisiteCourses,
            @RequestParam String relatedCourses) {

        List<String> errors = new ArrayList<>();

        BlogTip blogTip = blogTipRepository.findById(id).get();
        String oldAuthor = blogTip.getAuthor();
        blogTip.setAuthor(author);
        blogTip.setTitle(title);
        blogTip.setUrl(url);
        blogTip.setTags(tags);
        blogTip.setPrerequisiteCourses(prerequisiteCourses);
        blogTip.setRelatedCourses(relatedCourses);
        
        Set<ConstraintViolation<BlogTip>> violations = validator.validate(blogTip);
        for (ConstraintViolation<BlogTip> violation : violations) {
            errors.add(violation.getMessage());
        }
        
        if (!errors.isEmpty()) {
            blogTip.setAuthor(oldAuthor);
            model.addAttribute("errors", errors);
            model.addAttribute("blog", blogTipRepository.findById(id).get());
            return "blogTipDetails";
        }

        blogTipRepository.save(blogTip);

        return "redirect:/blog_tip/" + id;
    }
    
    @RequestMapping(value = "/blog_tip/{id}/study", method = RequestMethod.POST)
    @ResponseBody
    public void study(@PathVariable long id, @RequestParam Integer studied) {
        BlogTip bt = blogTipRepository.findById(id).get();
        
        if (studied == 1) {
            bt.setStudied(true);
        } else {
            bt.setStudied(false);
        }
        
        blogTipRepository.save(bt);
    }
}

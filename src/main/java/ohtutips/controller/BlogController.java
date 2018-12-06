package ohtutips.controller;

import java.util.ArrayList;
import java.util.List;
import ohtutips.domain.LinkTip;
import ohtutips.repository.LinkTipRepository;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
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
    private LinkTipRepository linkTipRepository;
    
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @RequestMapping(value = "/blog_tip/{id}", method = RequestMethod.GET)
    public String blogTipDetails(Model model, @PathVariable long id) {
        model.addAttribute("blog", linkTipRepository.findById(id).get());
        return "blogTipDetails";
    }

    @RequestMapping(value = "/blog_tip", method = RequestMethod.POST)
    public String addBlogTip(Model model, @RequestParam String author,
            @RequestParam String title, @RequestParam String url, 
            @RequestParam String tags, @RequestParam String description) {

        List<String> errors = new ArrayList<>();

        LinkTip blogTip = new LinkTip();
        blogTip.setAuthor(author);
        blogTip.setTitle(title);
        blogTip.setType("blog");
        blogTip.setUrl(url);
        blogTip.setTags(tags);
        blogTip.setDescription(description);
        
        Set<ConstraintViolation<LinkTip>> violations = validator.validate(blogTip);
        for (ConstraintViolation<LinkTip> violation : violations) {
            errors.add(violation.getMessage());
        }
        
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "addTip";
        }

        linkTipRepository.save(blogTip);
        return "redirect:/";
    }

    @RequestMapping(value = "/blog_tip/{id}", method = RequestMethod.DELETE)
    public String deleteBlogTip(@PathVariable long id) {
        linkTipRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/blog_tip/{id}", method = RequestMethod.PUT)
    public String modifyBlogTip(@PathVariable long id, Model model,
            @RequestParam String author, @RequestParam String title,
            @RequestParam String url, @RequestParam String tags,
            @RequestParam String description) {

        LinkTip blogTip = linkTipRepository.findById(id).get();
        List<String> errors = new ArrayList<>();
        String oldAuthor = blogTip.getAuthor();
        blogTip.setAuthor(author);
        blogTip.setTitle(title);
        blogTip.setUrl(url);
        blogTip.setTags(tags);
        blogTip.setDescription(description);
        
        Set<ConstraintViolation<LinkTip>> violations = validator.validate(blogTip);
        for (ConstraintViolation<LinkTip> violation : violations) {
            errors.add(violation.getMessage());
        }
        
        if (!errors.isEmpty()) {
            blogTip.setAuthor(oldAuthor);
            model.addAttribute("errors", errors);
            model.addAttribute("blog", linkTipRepository.findById(id).get());
            return "blogTipDetails";
        }

        linkTipRepository.save(blogTip);

        return "redirect:/blog_tip/" + id;
    }
    
    @RequestMapping(value = "/blog_tip/{id}/study", method = RequestMethod.POST)
    @ResponseBody
    public void study(@PathVariable long id, @RequestParam Integer studied) {
        LinkTip bt = linkTipRepository.findById(id).get();
        
        if (studied == 1) {
            bt.setStudied(true);
        } else {
            bt.setStudied(false);
        }
        
        linkTipRepository.save(bt);
    }
}

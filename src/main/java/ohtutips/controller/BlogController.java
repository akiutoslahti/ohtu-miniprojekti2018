package ohtutips.controller;

import java.util.ArrayList;
import java.util.List;
import ohtutips.domain.BlogTip;
import ohtutips.repository.BlogTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {

    @Autowired
    private BlogTipRepository blogTipRepository;

    @RequestMapping(value = "/blog_tip/{id}", method = RequestMethod.GET)
    public String blogTipDetails(Model model, @PathVariable long id) {
        model.addAttribute("blog", blogTipRepository.findById(id).get());
        return "blogTipDetails";
    }

    @RequestMapping(value = "/blog_tip", method = RequestMethod.GET)
    public String blogTipForm() {
        return "addBlogTip";
    }

    @RequestMapping(value = "/blog_tip", method = RequestMethod.POST)
    public String addBlogTip(Model model, @RequestParam String author,
            @RequestParam String title,
            @RequestParam String url, @RequestParam String tags,
            @RequestParam String prerequisiteCourses,
            @RequestParam String relatedCourses) {

        List<String> errors = new ArrayList<>();
        if (author.trim().isEmpty() || title.trim().isEmpty()
                || url.trim().isEmpty()
                || tags.trim().isEmpty()) {
            errors.add("Please fill all fields marked with (*).");

            model.addAttribute("errors", errors);
            return "addBlogTip";
        }

        BlogTip blogTip = new BlogTip();
        blogTip.setAuthor(author);
        blogTip.setTitle(title);
        blogTip.setUrl(url);
        blogTip.setTags(tags);
        blogTip.setPrerequisiteCourses(prerequisiteCourses);
        blogTip.setRelatedCourses(relatedCourses);

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

        if (author.trim().isEmpty() || title.trim().isEmpty()
                || url.trim().isEmpty()
                || tags.trim().isEmpty()) {
            List<String> errors = new ArrayList<>();

            errors.add("Please do not empty fields marked with (*).");

            model.addAttribute("errors", errors);
            model.addAttribute("blog", blogTipRepository.findById(id).get());
            return "blogTipDetails";
        }

        BlogTip blogTip = blogTipRepository.findById(id).get();
        blogTip.setAuthor(author);
        blogTip.setTitle(title);
        blogTip.setUrl(url);
        blogTip.setTags(tags);
        blogTip.setPrerequisiteCourses(prerequisiteCourses);
        blogTip.setRelatedCourses(relatedCourses);

        blogTipRepository.save(blogTip);

        return "redirect:/blog_tip/" + id;
    }
}

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
public class LinkController {
    
    private static final String BLOG = "blog";
    private static final String TUBE = "tube";

    @Autowired
    private LinkTipRepository linkTipRepository;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    //
    // LINK TIP DETAILS
    //
    
    @RequestMapping(value = "/" + BLOG + "_tip/{id}", method = RequestMethod.GET)
    public String blogTipDetails(Model model, @PathVariable long id) {
        return linkTipDetails(model, id, BLOG);
    }

    @RequestMapping(value = "/" + TUBE + "_tip/{id}", method = RequestMethod.GET)
    public String tubeTipDetails(Model model, @PathVariable long id) {
        return linkTipDetails(model, id, TUBE);
    }
    
    private String linkTipDetails(Model model, long id, String type) {
        model.addAttribute(type, linkTipRepository.findById(id).get());
        return type + "TipDetails";
    }

    //
    // ADD LINK TIP
    //
    
    @RequestMapping(value = "/" + BLOG + "_tip", method = RequestMethod.POST)
    public String addBlogTip(Model model, @RequestParam String author,
            @RequestParam String title, @RequestParam String url,
            @RequestParam String tags, @RequestParam String description) {
        return addLinkTip(model, author, title, BLOG, url, tags, description);
    }

    @RequestMapping(value = "/" + TUBE + "_tip", method = RequestMethod.POST)
    public String addTubeTip(Model model, @RequestParam String author,
            @RequestParam String title, @RequestParam String url,
            @RequestParam String tags, @RequestParam String description) {
        return addLinkTip(model, author, title, TUBE, url, tags, description);
    }

    private String addLinkTip(Model model, String author, String title, String type, String url,
            String tags, String description) {

        System.out.println("Saving " + type + " tip");
        
        List<String> errors = new ArrayList<>();

        LinkTip linkTip = new LinkTip();
        linkTip.setAuthor(author);
        linkTip.setTitle(title);
        linkTip.setType(type);
        linkTip.setUrl(url);
        linkTip.setTags(tags);
        linkTip.setDescription(description);

        Set<ConstraintViolation<LinkTip>> violations = validator.validate(linkTip);
        for (ConstraintViolation<LinkTip> violation : violations) {
            errors.add(violation.getMessage());
        }

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "addTip";
        }

        linkTipRepository.save(linkTip);
        return "redirect:/";
    }

    //
    // DELETE LINK TIP
    //
    
    @RequestMapping(value = "/" + BLOG + "_tip/{id}", method = RequestMethod.DELETE)
    public String deleteBlogTip(@PathVariable long id) {
        linkTipRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/" + TUBE + "_tip/{id}", method = RequestMethod.DELETE)
    public String deleteTubeTip(@PathVariable long id) {
        linkTipRepository.deleteById(id);
        return "redirect:/";
    }

    //
    // MODIFY LINK TIP
    //
    
    @RequestMapping(value = "/" + BLOG + "_tip/{id}", method = RequestMethod.PUT)
    public String modifyBlogTip(@PathVariable long id, Model model,
            @RequestParam String author, @RequestParam String title,
            @RequestParam String url, @RequestParam String tags,
            @RequestParam String description) {
        return modifyLinkTip(id, model, author, title, BLOG, url, tags, description);
    }

    @RequestMapping(value = "/" + TUBE + "_tip/{id}", method = RequestMethod.PUT)
    public String modifyTubeTip(@PathVariable long id, Model model,
            @RequestParam String author, @RequestParam String title,
            @RequestParam String url, @RequestParam String tags,
            @RequestParam String description) {
        return modifyLinkTip(id, model, author, title, TUBE, url, tags, description);
    }

    private String modifyLinkTip(long id, Model model,
            String author, String title, String type,
            String url, String tags,
            String description) {

        LinkTip linkTip = linkTipRepository.findById(id).get();
        linkTip.setAuthor(author);
        linkTip.setTitle(title);
        linkTip.setUrl(url);
        linkTip.setTags(tags);
        linkTip.setDescription(description);

        try {
            linkTipRepository.save(linkTip);
        } catch (Exception e) {
            List<String> errors = new ArrayList<>();
            Set<ConstraintViolation<LinkTip>> violations = validator.validate(linkTip);
            for (ConstraintViolation<LinkTip> violation : violations) {
                errors.add(violation.getMessage());
            }
            model.addAttribute("errors", errors);
            model.addAttribute(type, linkTipRepository.findById(id).get());
            return type + "TipDetails";
        }

        return "redirect:/" + type + "_tip/" + id;
    }

    //
    // MARK LINK STUDIED
    //
    
    @RequestMapping(value = "/" + BLOG + "_tip/{id}/study", method = RequestMethod.POST)
    @ResponseBody
    public void blogStudied(@PathVariable long id, @RequestParam Integer studied) {
        linkStudied(id, studied);
    }
    
    @RequestMapping(value = "/" + TUBE + "_tip/{id}/study", method = RequestMethod.POST)
    @ResponseBody
    public void tubeStudied(@PathVariable long id, @RequestParam Integer studied) {
        linkStudied(id, studied);
    }

    private void linkStudied(long id, Integer studied) {
        LinkTip lt = linkTipRepository.findById(id).get();

        if (studied == 1) {
            lt.setStudied(true);
        } else {
            lt.setStudied(false);
        }

        linkTipRepository.save(lt);
    }
}

package MVC.Blog.Konstantin.controllers;

import MVC.Blog.Konstantin.DAO.BlogDAO;
import MVC.Blog.Konstantin.models.BlogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private final BlogDAO blogDAO;

    @Autowired
    public BlogController(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("blog", blogDAO.index());
        return "BlogDAO/blogtemplate";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("blogs", blogDAO.show(id));
        return "BlogDAO/blogshow";
    }

    @GetMapping("/addblog")
    public String addBlog(Model model) {
        model.addAttribute("blogs", new BlogModel());
        return "BlogDAO/blogadd";
    }

    @PostMapping()
    public String create(@ModelAttribute("blogs") BlogModel blogModel) {
        blogDAO.save(blogModel);
        return "redirect:/blog";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,
                       Model model) {
        model.addAttribute("blogs", blogDAO.show(id));
        return "BlogDAO/blogedit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("blogs") BlogModel blogModel,
                         @PathVariable("id") int id) {
        blogDAO.update(id, blogModel);
        return "redirect:/blog";

    }

}

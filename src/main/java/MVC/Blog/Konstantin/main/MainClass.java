package MVC.Blog.Konstantin.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainClass {
    @GetMapping()
    public String main(){
        return "lol";
    }

}

package fi.haagahelia.course.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {
    
	@RequestMapping(value = {"/index", "/"})
    
	@ResponseBody
    public String index() {
        return "<h1>Bookstore</h1>This is the main page.";
    }
}

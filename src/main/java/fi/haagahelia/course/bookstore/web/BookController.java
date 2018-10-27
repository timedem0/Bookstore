package fi.haagahelia.course.bookstore.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.course.bookstore.domain.Book;
import fi.haagahelia.course.bookstore.domain.BookRepository;
import fi.haagahelia.course.bookstore.domain.CategoryRepository;
import fi.haagahelia.course.bookstore.domain.UserRepository;
import fi.haagahelia.course.bookstore.domain.User;
import fi.haagahelia.course.bookstore.domain.SignupForm;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository book_repository;
	
	@Autowired
	private CategoryRepository category_repository;
	
	@Autowired
    private UserRepository user_repository; 
	
    @RequestMapping(value = {"/index", "/", "/login"})
    public String login() {	
        return "login";
    }
    
    @RequestMapping(value = "/success")
    public String loginsuccess() {
        return "forward:/login?success";
    }
    
	@RequestMapping(value = "/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", book_repository.findAll());
        return "booklist";
    }
	
    @RequestMapping(value = "/add")
    public String addBook(Model model) {
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", category_repository.findAll());
        return "addnewbook";
    }
    
    @RequestMapping(value = "/edit/{id}")
    public String editBook(@PathVariable("id") Long bookId, Model model) {
	    model.addAttribute("book", book_repository.findById(bookId));
	    model.addAttribute("categories", category_repository.findAll());
	    return "editbook";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book) {
        book_repository.save(book);
        return "redirect:booklist";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	book_repository.deleteById(bookId);
        return "redirect:../booklist";
    }
    
    // User control
    
    @RequestMapping(value = "/signup")
    public String addUser(Model model){
    	model.addAttribute("signupform", new SignupForm());
        return "signup";
    }
    
    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { // validation errors
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole("USER");
		    	if (user_repository.findByUsername(signupForm.getUsername()) == null) { // check if user exists
		    		user_repository.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/success";    	
    }      
    
	// RESTful services
    
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) book_repository.findAll();
    }    

    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return book_repository.findById(bookId);
    }   
}

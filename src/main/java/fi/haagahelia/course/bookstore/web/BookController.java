package fi.haagahelia.course.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.course.bookstore.domain.Book;
import fi.haagahelia.course.bookstore.domain.BookRepository;
import fi.haagahelia.course.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository book_repository;
	
	@Autowired
	private CategoryRepository category_repository;
    
	@RequestMapping(value = {"/index", "/"})
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
        return "redirect:index";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	book_repository.deleteById(bookId);
        return "redirect:../index";
    }  
}

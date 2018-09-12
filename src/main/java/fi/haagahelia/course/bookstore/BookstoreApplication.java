package fi.haagahelia.course.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.bookstore.domain.Book;
import fi.haagahelia.course.bookstore.domain.BookRepository;
import fi.haagahelia.course.bookstore.domain.Category;
import fi.haagahelia.course.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository book_repository, CategoryRepository category_repository) {
		return  (args) -> {
			
			Category cat1 = new Category("Fantasy");
			Category cat2 = new Category("Historical");
			Category cat3 = new Category("SCI-FI");
			
			category_repository.save(cat1);
			category_repository.save(cat2);
			category_repository.save(cat3);
			
			Book book1 = new Book("A Game of Thrones", "George R.R. Martin", 2010, "9-78134-567897", 35.15, category_repository.findByCategoryName("Fantasy").get(0));
			Book book2 = new Book("Foundation", "Isaac Asimov", 1951, "9-79123-456789", 49.95, category_repository.findByCategoryName("SCI-FI").get(0));
			Book book3 = new Book("The Fall of Constantinople", "Vintila Corbul", 1993, "9-78316-148410", 29.45, category_repository.findByCategoryName("Historical").get(0));
			Book book4 = new Book("Hyperion", "Dan Simmons", 1989, "9-32682-623854", 35.95, category_repository.findByCategoryName("SCI-FI").get(0));
			
			book_repository.save(book1);
			book_repository.save(book2);
			book_repository.save(book3);
			book_repository.save(book4);
		};
	}
}

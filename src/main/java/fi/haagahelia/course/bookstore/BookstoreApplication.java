package fi.haagahelia.course.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.bookstore.domain.Book;
import fi.haagahelia.course.bookstore.domain.BookRepository;
import fi.haagahelia.course.bookstore.domain.Category;
import fi.haagahelia.course.bookstore.domain.CategoryRepository;
import fi.haagahelia.course.bookstore.domain.User;
import fi.haagahelia.course.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository book_repository, CategoryRepository category_repository, UserRepository urepository) {
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
			
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
		};
	}
}

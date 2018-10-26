package fi.haagahelia.course.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.course.bookstore.domain.Book;
import fi.haagahelia.course.bookstore.domain.BookRepository;
import fi.haagahelia.course.bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository repository;
	
    @Test
    public void findByAuthorShouldReturnBook() {
        List<Book> books = repository.findByAuthor("Vintila Corbul");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("The Fall of Constantinople");
    }
    
    @Test
    public void insertBook() {
    	Book newBook = new Book("Test", "Testinen", 1970, "9-32682-623854", 95.95, new Category("TestCategory"));
    	repository.save(newBook);
    	assertThat(newBook.getId()).isNotNull();
    }
    
    @Test
    public void deleteBook() {
        List<Book> books = repository.findByAuthor("Vintila Corbul");
        assertThat(books).hasSize(1);
    	repository.deleteById(books.get(0).getId());
    	List<Book> deletedBooks = repository.findByAuthor("Vintila Corbul");
    	assertThat(deletedBooks).hasSize(0);
    }
}

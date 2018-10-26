package fi.haagahelia.course.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.course.bookstore.domain.Category;
import fi.haagahelia.course.bookstore.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository repository;
	
    @Test
    public void findByCategoryNameShouldReturnCategory() {
        List<Category> categories = repository.findByCategoryName("Fantasy");
        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getCategoryName()).isEqualTo("Fantasy");
    }
    
    @Test
    public void insertCategory() {
    	Category newCategory = new Category("TestCategory");
    	repository.save(newCategory);
    	assertThat(newCategory.getCategoryId()).isNotNull();
    }
    
    @Test
    public void deleteCategory() {
        List<Category> categories = repository.findByCategoryName("Fantasy");
        assertThat(categories).hasSize(1);
    	repository.deleteById(categories.get(0).getCategoryId());
        List<Category> deletedCategories = repository.findByCategoryName("Fantasy");
    	assertThat(deletedCategories).hasSize(0);
    }
}

package fi.haagahelia.course.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.course.bookstore.domain.User;
import fi.haagahelia.course.bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository repository;
	
    @Test
    public void findByUsernameShouldReturnUser() {
        User testUser = repository.findByUsername("admin");
        assertThat(testUser.getRole()).isEqualTo("ADMIN");
    }
    
    @Test
    public void insertUser() {
    	User newUser = new User("testinen", "password", "role");
    	repository.save(newUser);
    	assertThat(newUser.getId()).isNotNull();
    }
    
    @Test
    public void deleteUser() {
        List<User> users = repository.findByRole("USER");
        assertThat(users).hasSize(1);
    	repository.deleteById(users.get(0).getId());
        List<User> deletedUsers = repository.findByRole("USER");
    	assertThat(deletedUsers).hasSize(0);
    }
}

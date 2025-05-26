package net.dimjasevic.karlo.fer.evidentor.domain.users;

import net.dimjasevic.karlo.fer.evidentor.domain.TestJpaConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ContextConfiguration(classes = TestJpaConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TestUserRepository {

    @Autowired
    private UserRepository repository;

    @Test
    public void testFindAllWhenRepositoryIsEmpty() {
        // WHEN
        Iterable<User> users = repository.findAll();

        // THEN
        assertThat(users).isEmpty();
    }

    @Test
    public void testFindAllWhenRepositoryIsNotEmpty() {
        // GIVEN
        User user1 = new User("Jack", "Brown", "00-11-22", false);
        User user2 = new User("Mike", "Williams", "11-22-33", false);
        repository.saveAll(List.of(user1, user2));

        // WHEN
        long count = repository.count();
        User user1FromDb = repository.findById(user1.getId()).orElse(null);
        User user2FromDb = repository.findById(user2.getId()).orElse(null);

        // THEN
        assertThat(count).isEqualTo(2);
        assertThat(user1FromDb).isNotNull();
        assertThat(user2FromDb).isNotNull();
        assertThat(user1FromDb.getFirstName()).isEqualTo("Jack");
        assertThat(user1FromDb.getLastName()).isEqualTo("Brown");
        assertThat(user1FromDb.getCardId()).isEqualTo("00-11-22");
        assertThat(user2FromDb.getFirstName()).isEqualTo("Mike");
        assertThat(user2FromDb.getLastName()).isEqualTo("Williams");
        assertThat(user2FromDb.getCardId()).isEqualTo("11-22-33");
    }
}

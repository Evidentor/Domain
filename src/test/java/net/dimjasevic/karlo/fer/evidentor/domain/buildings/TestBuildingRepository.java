package net.dimjasevic.karlo.fer.evidentor.domain.buildings;

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
public class TestBuildingRepository {

    @Autowired
    private BuildingRepository repository;

    @Test
    public void testFindAllWhenRepositoryIsEmpty() {
        // WHEN
        Iterable<Building> buildings = repository.findAll();

        // THEN
        assertThat(buildings).isEmpty();
    }

    @Test
    public void testFindAllWhenRepositoryIsNotEmpty() {
        // GIVEN
        Building building1 = new Building("Building 1", "", false);
        Building building2 = new Building("Building 2", "", false);
        repository.saveAll(List.of(building1, building2));

        // WHEN
        long count = repository.count();

        // THEN
        assertThat(count).isEqualTo(2);
    }
}

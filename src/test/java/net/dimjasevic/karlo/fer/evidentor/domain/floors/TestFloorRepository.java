package net.dimjasevic.karlo.fer.evidentor.domain.floors;

import net.dimjasevic.karlo.fer.evidentor.domain.TestJpaConfig;
import net.dimjasevic.karlo.fer.evidentor.domain.buildings.Building;
import net.dimjasevic.karlo.fer.evidentor.domain.buildings.BuildingRepository;
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
class TestFloorRepository {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private FloorRepository repository;

    @Test
    public void testFindAllWhenRepositoryIsEmpty() {
        // WHEN
        Iterable<Floor> floors = repository.findAll();

        // THEN
        assertThat(floors).isEmpty();
    }

    @Test
    public void testFindAllWhenRepositoryIsNotEmpty() {
        // GIVEN
        Building building = new Building("Building", "", false);
        buildingRepository.save(building);

        Floor floor1 = new Floor(building, 0, false);
        Floor floor2 = new Floor(building, 1, false);
        repository.saveAll(List.of(floor1, floor2));

        // WHEN
        long count = repository.count();

        // THEN
        assertThat(count).isEqualTo(2);
    }
}
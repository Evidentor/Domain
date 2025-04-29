package net.dimjasevic.karlo.fer.evidentor.domain.buildings;

import net.dimjasevic.karlo.fer.evidentor.domain.TestJpaConfig;
import net.dimjasevic.karlo.fer.evidentor.domain.floors.Floor;
import net.dimjasevic.karlo.fer.evidentor.domain.rooms.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Test
    public void testFindByIdWithFloorsAndRooms() {
        // GIVEN
        Building building = new Building("Building", "", false);

        Floor floor1 = new Floor(building, 0, false);
        Floor floor2 = new Floor(building, 1, false);
        building.setFloors(new HashSet<>(List.of(floor1, floor2)));

        Room room1 = new Room(floor1, "Room11", false);
        Room room2 = new Room(floor1, "Room12", false);
        Room room3 = new Room(floor1, "Room13", false);
        Room room4 = new Room(floor2, "Room21", false);
        floor1.setRooms(new HashSet<>(List.of(room1, room2, room3)));
        floor2.setRooms(new HashSet<>(List.of(room4)));

        repository.save(building);

        // WHEN
        Optional<Building> buildingFromDb = repository.findByIdWithRoomsAndFloors(building.getId());

        // THEN
        assertThat(buildingFromDb).isPresent();
        building = buildingFromDb.get();
        assertThat(building.getFloors()).hasSize(2);
        for (Floor floor : building.getFloors()) {
            if (Objects.equals(floor.getId(), floor1.getId()))
                assertThat(floor.getRooms().size()).isEqualTo(3);
            else
                assertThat(floor.getRooms().size()).isEqualTo(1);
        }
    }
}

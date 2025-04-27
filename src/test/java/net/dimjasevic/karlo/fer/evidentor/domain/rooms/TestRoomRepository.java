package net.dimjasevic.karlo.fer.evidentor.domain.rooms;

import net.dimjasevic.karlo.fer.evidentor.domain.TestJpaConfig;
import net.dimjasevic.karlo.fer.evidentor.domain.buildings.Building;
import net.dimjasevic.karlo.fer.evidentor.domain.buildings.BuildingRepository;
import net.dimjasevic.karlo.fer.evidentor.domain.floors.Floor;
import net.dimjasevic.karlo.fer.evidentor.domain.floors.FloorRepository;
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
class TestRoomRepository {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private RoomRepository repository;

    @Test
    public void testFindAllWhenRepositoryIsEmpty() {
        // WHEN
        Iterable<Room> floors = repository.findAll();

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
        floorRepository.saveAll(List.of(floor1, floor2));

        Room room1 = new Room(floor1, "Room11", false);
        Room room2 = new Room(floor1, "Room12", false);
        Room room3 = new Room(floor1, "Room13", false);
        Room room4 = new Room(floor2, "Room21", false);
        repository.saveAll(List.of(room1, room2, room3, room4));

        // WHEN
        long count = repository.count();

        // THEN
        assertThat(count).isEqualTo(4);
    }
}

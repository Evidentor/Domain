package net.dimjasevic.karlo.fer.evidentor.domain.roomvisualizations;

import net.dimjasevic.karlo.fer.evidentor.domain.TestJpaConfig;
import net.dimjasevic.karlo.fer.evidentor.domain.buildings.Building;
import net.dimjasevic.karlo.fer.evidentor.domain.buildings.BuildingRepository;
import net.dimjasevic.karlo.fer.evidentor.domain.floors.Floor;
import net.dimjasevic.karlo.fer.evidentor.domain.rooms.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ContextConfiguration(classes = TestJpaConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TestRoomVisualizationRepository {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RoomVisualizationRepository repository;

    @Test
    public void testFindAllWhenRepositoryIsEmpty() {
        // WHEN
        Iterable<RoomVisualization> floors = repository.findAll();

        // THEN
        assertThat(floors).isEmpty();
    }

    @Test
    public void testFindAllWhenRepositoryIsNotEmpty() {
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

        buildingRepository.save(building);

        RoomVisualization rv1 = new RoomVisualization(room1, (short) 1, (short) 1, (short) 1, (short) 1, false);
        RoomVisualization rv2 = new RoomVisualization(room2, (short) 2, (short) 2, (short) 2, (short) 2, false);
        RoomVisualization rv3 = new RoomVisualization(room3, (short) 3, (short) 3, (short) 3, (short) 3, false);
        RoomVisualization rv4 = new RoomVisualization(room4, (short) 4, (short) 4, (short) 4, (short) 4, false);

        repository.saveAll(List.of(rv1, rv2, rv3, rv4));

        // WHEN
        long count = repository.count();

        // THEN
        assertThat(count).isEqualTo(4);
    }
}

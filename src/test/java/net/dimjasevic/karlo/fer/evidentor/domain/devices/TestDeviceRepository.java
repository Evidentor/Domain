package net.dimjasevic.karlo.fer.evidentor.domain.devices;

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

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ContextConfiguration(classes = TestJpaConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TestDeviceRepository {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private DeviceRepository repository;

    @Test
    public void testFindAllWhenRepositoryIsEmpty() {
        // WHEN
        Iterable<Device> devices = repository.findAll();

        // THEN
        assertThat(devices).isEmpty();
    }

    @Test
    public void testFindAllWhenRepositoryIsNotEmpty() {
        // GIVEN
        Building building = new Building("Building", "", false);

        Floor floor1 = new Floor(building, 0, false);
        Floor floor2 = new Floor(building, 1, false);

        Room room1 = new Room(floor1, "Room11", false);
        Room room2 = new Room(floor1, "Room12", false);
        new Room(floor1, "Room13", false);
        new Room(floor2, "Room21", false);

        buildingRepository.save(building);

        LocalDateTime now = LocalDateTime.now();
        Device d1 = new Device(room1, "s1", now, false);
        Device d2 = new Device(room2, "s2", now, false);
        Device d3 = new Device("s3", now, false);

        repository.saveAll(List.of(d1, d2, d3));

        // WHEN
        long count = repository.count();
        Device d1FromDb = repository.findById(d1.getId()).orElse(null);
        Device d2FromDb = repository.findById(d2.getId()).orElse(null);
        Device d3FromDb = repository.findById(d3.getId()).orElse(null);

        // THEN
        assertThat(count).isEqualTo(3);
        assertThat(d1FromDb).isNotNull();
        assertThat(d2FromDb).isNotNull();
        assertThat(d3FromDb).isNotNull();
        assertThat(d1FromDb.getRoom().getId()).isEqualTo(room1.getId());
        assertThat(d2FromDb.getRoom().getId()).isEqualTo(room2.getId());
        assertThat(d3FromDb.getRoom()).isNull();
        assertThat(d1FromDb.getInstallationDate()).isEqualTo(now);
        assertThat(d2FromDb.getInstallationDate()).isEqualTo(now);
        assertThat(d3FromDb.getInstallationDate()).isEqualTo(now);
    }
}

package net.dimjasevic.karlo.fer.evidentor.domain.telemetry;

import net.dimjasevic.karlo.fer.evidentor.domain.TestJpaConfig;
import net.dimjasevic.karlo.fer.evidentor.domain.buildings.Building;
import net.dimjasevic.karlo.fer.evidentor.domain.buildings.BuildingRepository;
import net.dimjasevic.karlo.fer.evidentor.domain.devices.Device;
import net.dimjasevic.karlo.fer.evidentor.domain.devices.DeviceRepository;
import net.dimjasevic.karlo.fer.evidentor.domain.floors.Floor;
import net.dimjasevic.karlo.fer.evidentor.domain.rooms.Room;
import net.dimjasevic.karlo.fer.evidentor.domain.users.User;
import net.dimjasevic.karlo.fer.evidentor.domain.users.UserRepository;
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
class TestTelemetryRepository {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private TelemetryRepository repository;

    @Test
    public void testFindAllWhenRepositoryIsEmpty() {
        // WHEN
        Iterable<Telemetry> telemetries = repository.findAll();

        // THEN
        assertThat(telemetries).isEmpty();
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

        User user1 = new User("Jack", "Brown", "00-11-22", false);
        User user2 = new User("Mike", "Williams", "11-22-33", false);
        userRepository.saveAll(List.of(user1, user2));

        LocalDateTime now = LocalDateTime.now();
        Device device1 = new Device(room1, "s1", now, false);
        Device device2 = new Device(room2, "s2", now, false);
        Device device3 = new Device("s3", now, false);
        deviceRepository.saveAll(List.of(device1, device2, device3));

        LocalDateTime now1 = LocalDateTime.now();
        LocalDateTime now2 = LocalDateTime.now();
        Telemetry t1 = new Telemetry(
                user1, device1, room1, now1
        );
        Telemetry t2 = new Telemetry(
                user2, device2, room2, now2
        );
        repository.saveAll(List.of(t1, t2));

        // WHEN
        long count = repository.count();
        Telemetry t1FromDb = repository.findById(t1.getId()).orElse(null);
        Telemetry t2FromDb = repository.findById(t2.getId()).orElse(null);

        // THEN
        assertThat(count).isEqualTo(2);
        assertThat(t1FromDb).isNotNull();
        assertThat(t2FromDb).isNotNull();
        assertThat(t1FromDb.getUser().getId()).isEqualTo(user1.getId());
        assertThat(t2FromDb.getUser().getId()).isEqualTo(user2.getId());
        assertThat(t1FromDb.getRoom().getId()).isEqualTo(room1.getId());
        assertThat(t2FromDb.getRoom().getId()).isEqualTo(room2.getId());
        assertThat(t1FromDb.getDevice().getId()).isEqualTo(device1.getId());
        assertThat(t2FromDb.getDevice().getId()).isEqualTo(device2.getId());
        assertThat(t1FromDb.getDevice().getRoom().getId()).isEqualTo(room1.getId());
        assertThat(t2FromDb.getDevice().getRoom().getId()).isEqualTo(room2.getId());
        assertThat(t1FromDb.getScanTime()).isEqualTo(now1);
        assertThat(t2FromDb.getScanTime()).isEqualTo(now2);
    }
}

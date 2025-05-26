package net.dimjasevic.karlo.fer.evidentor.domain.telemetry;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.dimjasevic.karlo.fer.evidentor.domain.devices.Device;
import net.dimjasevic.karlo.fer.evidentor.domain.rooms.Room;
import net.dimjasevic.karlo.fer.evidentor.domain.users.User;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "telemetry")
public class Telemetry {
    @EmbeddedId
    private TelemetryId id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("deviceId")
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("roomId")
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(nullable = false)
    private boolean deleted = false;

    public Telemetry(User user, Device device, Room room, LocalDateTime scanTime) {
        this.id = new TelemetryId(user.getId(), device.getId(), room.getId(), scanTime);
        this.user = user;
        this.device = device;
        this.room = room;
    }

    public LocalDateTime getScanTime() {
        return id.getScanTime();
    }
}

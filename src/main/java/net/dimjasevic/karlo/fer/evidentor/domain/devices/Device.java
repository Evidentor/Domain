package net.dimjasevic.karlo.fer.evidentor.domain.devices;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.dimjasevic.karlo.fer.evidentor.domain.rooms.Room;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "devices_seq")
    @SequenceGenerator(name = "devices_seq", sequenceName = "devices_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room = null;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "installation_date")
    private LocalDateTime installationDate;
    private Boolean deleted;

    public Device(String serialNumber, LocalDateTime installationDate, Boolean deleted) {
        this(null, serialNumber, installationDate, deleted);
    }

    public Device(Room room, String serialNumber, LocalDateTime installationDate, Boolean deleted) {
        this.room = room;
        this.serialNumber = serialNumber;
        this.installationDate = installationDate;
        this.deleted = deleted;

        if (room != null) {
            room.getDevices().add(this);
        }
    }
}

package net.dimjasevic.karlo.fer.evidentor.domain.rooms;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.dimjasevic.karlo.fer.evidentor.domain.devices.Device;
import net.dimjasevic.karlo.fer.evidentor.domain.floors.Floor;
import net.dimjasevic.karlo.fer.evidentor.domain.roomvisualizations.RoomVisualization;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rooms_seq")
    @SequenceGenerator(name = "rooms_seq", sequenceName = "rooms_id_seq", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;
    private String name;
    private Boolean deleted;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    private RoomVisualization roomVisualization = null;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Device> devices = new HashSet<>();

    public Room(Floor floor, String name, Boolean deleted) {
        this.floor = floor;
        this.name = name;
        this.deleted = deleted;

        this.floor.getRooms().add(this);
    }
}

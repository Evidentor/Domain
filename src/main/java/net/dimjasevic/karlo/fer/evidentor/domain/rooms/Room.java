package net.dimjasevic.karlo.fer.evidentor.domain.rooms;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.dimjasevic.karlo.fer.evidentor.domain.floors.Floor;

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

    public Room(Floor floor, String name, Boolean deleted) {
        this.floor = floor;
        this.name = name;
        this.deleted = deleted;
    }
}

package net.dimjasevic.karlo.fer.evidentor.domain.floors;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.dimjasevic.karlo.fer.evidentor.domain.buildings.Building;
import net.dimjasevic.karlo.fer.evidentor.domain.rooms.Room;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "floors")
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "floors_seq")
    @SequenceGenerator(name = "floors_seq", sequenceName = "floors_id_seq", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
    private Integer index;
    private Boolean deleted;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Room> rooms = new HashSet<>();

    public Floor(Building building, Integer index, Boolean deleted) {
        this.building = building;
        this.index = index;
        this.deleted = deleted;

        this.building.getFloors().add(this);
    }
}

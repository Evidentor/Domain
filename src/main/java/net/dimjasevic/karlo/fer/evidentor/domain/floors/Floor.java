package net.dimjasevic.karlo.fer.evidentor.domain.floors;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.dimjasevic.karlo.fer.evidentor.domain.buildings.Building;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "floors")
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "floors_seq")
    @SequenceGenerator(name = "floors_seq", sequenceName = "floors_id_seq", allocationSize = 1)
    public Long id;
    @ManyToOne
    public Building building;
    public Integer index;
    public Boolean deleted;

    public Floor(Building building, Integer index, Boolean deleted) {
        this.building = building;
        this.index = index;
        this.deleted = deleted;
    }
}

package net.dimjasevic.karlo.fer.evidentor.domain.buildings;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.dimjasevic.karlo.fer.evidentor.domain.floors.Floor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "buildings_seq")
    @SequenceGenerator(name = "buildings_seq", sequenceName = "buildings_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String image;
    private Boolean deleted;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Floor> floors = new HashSet<>();

    public Building(String name, String image, Boolean deleted) {
        this.name = name;
        this.image = image;
        this.deleted = deleted;
    }
}

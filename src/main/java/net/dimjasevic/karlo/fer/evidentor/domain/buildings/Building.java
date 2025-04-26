package net.dimjasevic.karlo.fer.evidentor.domain.buildings;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "buildings_seq")
    @SequenceGenerator(name = "buildings_seq", sequenceName = "buildings_id_seq", allocationSize = 1)
    public Long id;
    public String name;
    public String image;
    public Boolean deleted;

    public Building(String name, String image, Boolean deleted) {
        this.name = name;
        this.image = image;
        this.deleted = deleted;
    }
}

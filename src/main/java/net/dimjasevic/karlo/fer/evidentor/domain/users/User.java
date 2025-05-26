package net.dimjasevic.karlo.fer.evidentor.domain.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "card_id")
    private String cardId;
    private boolean deleted;

    public User(String firstName, String lastName, String cardId, boolean deleted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardId = cardId;
        this.deleted = deleted;
    }
}

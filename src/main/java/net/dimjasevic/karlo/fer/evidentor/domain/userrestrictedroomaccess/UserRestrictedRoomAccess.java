package net.dimjasevic.karlo.fer.evidentor.domain.userrestrictedroomaccess;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.dimjasevic.karlo.fer.evidentor.domain.rooms.Room;
import net.dimjasevic.karlo.fer.evidentor.domain.users.User;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "users_restricted_room_access")
public class UserRestrictedRoomAccess {
    @EmbeddedId
    private UserRestrictedRoomAccessId id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("roomId")
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    public UserRestrictedRoomAccess(User user, Room room) {
        this.id = new UserRestrictedRoomAccessId(user.getId(), room.getId());
        this.user = user;
        this.room = room;
    }
}

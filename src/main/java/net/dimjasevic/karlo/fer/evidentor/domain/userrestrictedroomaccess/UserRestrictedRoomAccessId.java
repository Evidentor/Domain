package net.dimjasevic.karlo.fer.evidentor.domain.userrestrictedroomaccess;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class UserRestrictedRoomAccessId implements Serializable {

    private Long userId;
    private Long roomId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserRestrictedRoomAccessId that = (UserRestrictedRoomAccessId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(roomId, that.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roomId);
    }
}

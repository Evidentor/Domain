package net.dimjasevic.karlo.fer.evidentor.domain.userrestrictedroomaccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRestrictedRoomAccessRepository extends JpaRepository<UserRestrictedRoomAccess, UserRestrictedRoomAccessId> {
}

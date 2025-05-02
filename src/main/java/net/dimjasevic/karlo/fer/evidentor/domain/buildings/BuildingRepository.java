package net.dimjasevic.karlo.fer.evidentor.domain.buildings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

    @Query(
            value = "SELECT b FROM Building b " +
                    "LEFT JOIN FETCH b.floors f " +
                    "LEFT JOIN FETCH f.rooms r " +
                    "WHERE b.id = :id"
    )
    Optional<Building> findByIdWithRoomsAndFloors(@Param("id") Long buildingId);

    @Query(
            value = "SELECT b FROM Building b " +
                    "LEFT JOIN FETCH b.floors f " +
                    "LEFT JOIN FETCH f.rooms r " +
                    "LEFT JOIN FETCH RoomVisualization rv ON r.id = rv.roomId " +
                    "WHERE b.id = :id"
    )
    Optional<Building> findByIdWithRoomVisualizations(@Param("id") Long buildingId);
}

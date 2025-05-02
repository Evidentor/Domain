package net.dimjasevic.karlo.fer.evidentor.domain.roomvisualizations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomVisualizationRepository extends JpaRepository<RoomVisualization, Long> {
}

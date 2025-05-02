package net.dimjasevic.karlo.fer.evidentor.domain.roomvisualizations;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.dimjasevic.karlo.fer.evidentor.domain.rooms.Room;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "room_visualizations")
public class RoomVisualization {
    @Id
    @JoinColumn(name = "room_id")
    private Long roomId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "room_id")
    private Room room;

    @JoinColumn(name = "start_row")
    private short startRow;
    @JoinColumn(name = "start_col")
    private short startCol;
    private short rowspan;
    private short colspan;
    private Boolean deleted;

    public RoomVisualization(Room room, short startRow, short startCol, short rowspan, short colspan, Boolean deleted) {
        this.room = room;
        this.startRow = startRow;
        this.startCol = startCol;
        this.rowspan = rowspan;
        this.colspan = colspan;
        this.deleted = deleted;
        
        room.setRoomVisualization(this);
    }
}

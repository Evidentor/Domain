package net.dimjasevic.karlo.fer.evidentor.domain.telemetry;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class TelemetryId implements Serializable {

    private Long userId;
    private Long deviceId;
    private Long roomId;
    @Column(name = "scan_time", nullable = false)
    private LocalDateTime scanTime;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TelemetryId that = (TelemetryId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(deviceId, that.deviceId) &&
                Objects.equals(roomId, that.roomId) &&
                Objects.equals(scanTime, that.scanTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, deviceId, roomId, scanTime);
    }
}

package net.dimjasevic.karlo.fer.evidentor.domain.devices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> getBySerialNumber(String serialNumber);
}

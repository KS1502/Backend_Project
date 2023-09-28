package de.ait.bp.repositories;

import de.ait.bp.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingsRepository extends JpaRepository<Booking, Long> {
}

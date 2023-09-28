package de.ait.bp.repositories;

import de.ait.bp.models.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentsRepository extends JpaRepository<Apartment, Long> {
}

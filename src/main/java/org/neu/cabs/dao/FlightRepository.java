package org.neu.cabs.dao;

import org.neu.cabs.orm.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}

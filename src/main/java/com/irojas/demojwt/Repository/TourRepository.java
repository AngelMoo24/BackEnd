package com.irojas.demojwt.Repository;

import com.irojas.demojwt.Entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Long> {
}

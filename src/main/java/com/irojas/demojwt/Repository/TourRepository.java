package com.irojas.demojwt.Repository;

import com.irojas.demojwt.Entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findByCategoriaTourId(Integer categoriaId);
}

package com.irojas.demojwt.Repository;

import com.irojas.demojwt.Entity.TourImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourImageRepository extends JpaRepository<TourImage, Long> {
    List<TourImage> findByTourId(Long tourId);
}

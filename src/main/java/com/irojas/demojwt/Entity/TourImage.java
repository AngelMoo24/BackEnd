package com.irojas.demojwt.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TourImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String UrlImagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id",nullable = false)
    private Tour tour;


}

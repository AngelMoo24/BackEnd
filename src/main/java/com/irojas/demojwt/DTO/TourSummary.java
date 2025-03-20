package com.irojas.demojwt.DTO;

import com.irojas.demojwt.Entity.Tour;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TourSummary {

    private Long id;
    private String nombre;
    private String ubicacion;
    private Double precio;
    private String imagen;

    public TourSummary(Tour tour) {
        this.id = tour.getId();
        this.nombre = tour.getNombre();
        this.ubicacion = tour.getUbicacion();
        this.precio = tour.getPrecio();
        this.imagen = tour.getImagen();
    }

}

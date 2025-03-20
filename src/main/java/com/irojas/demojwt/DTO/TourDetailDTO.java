package com.irojas.demojwt.DTO;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourDetailDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private Double precio;
    private List<String> imagenes; // Lista de URLs para el carrusel
}

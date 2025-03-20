package com.irojas.demojwt.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestDTO {

    private String nombre;
    private String descripcion;
    private String ubicacion;
    private Double precio;
    private Boolean disponibilidad;
    private String imagen; // Imagen principal
    private Integer categoriaId;
    private List<String> imagenes; // Lista de URLs para las imágenes

}

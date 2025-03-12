package com.irojas.demojwt.DTO;

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

}

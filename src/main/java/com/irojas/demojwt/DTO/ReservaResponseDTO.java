package com.irojas.demojwt.DTO;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaResponseDTO {
    private String nombreUsuario;
    private String nombreTour;
    private int cantidadPersonas;
    private String estado;
    private LocalDateTime fechaReserva;
}

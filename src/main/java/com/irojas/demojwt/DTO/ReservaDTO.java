package com.irojas.demojwt.DTO;

import com.irojas.demojwt.Entity.EstadoReserva;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaDTO {

    private Long tourId;
    private Integer usuarioId;
    private int cantidadPersonas;
    private EstadoReserva estado;
}

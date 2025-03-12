package com.irojas.demojwt.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;  // Relación con la entidad User

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;  // Relación con la entidad Tour (si tienes esta entidad creada)

    @Column(nullable = false)
    private LocalDateTime fechaReserva;

    @Column(nullable = false)
    private int cantidadPersonas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoReserva estado;


}

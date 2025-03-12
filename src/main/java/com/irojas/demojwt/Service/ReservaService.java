package com.irojas.demojwt.Service;

import com.irojas.demojwt.DTO.ReservaDTO;
import com.irojas.demojwt.DTO.ReservaResponseDTO;
import com.irojas.demojwt.Entity.EstadoReserva;
import com.irojas.demojwt.Entity.Reserva;
import com.irojas.demojwt.Entity.Tour;
import com.irojas.demojwt.Entity.User;
import com.irojas.demojwt.Repository.ReservaRepository;
import com.irojas.demojwt.Repository.TourRepository;
import com.irojas.demojwt.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaService {


    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TourRepository tourRepository;


    public Reserva crearReserva(ReservaDTO reservaRequestDTO) {
        User usuario = userRepository.findById(reservaRequestDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Tour tour = tourRepository.findById(reservaRequestDTO.getTourId())
                .orElseThrow(() -> new RuntimeException("Tour no encontrado"));

        Reserva reserva = Reserva.builder()
                .usuario(usuario)
                .tour(tour)
                .fechaReserva(LocalDateTime.now())
                .cantidadPersonas(reservaRequestDTO.getCantidadPersonas())
                .estado(EstadoReserva.PENDIENTE)
                .build();

        return reservaRepository.save(reserva);
    }

    public ReservaResponseDTO obtenerReserva(Integer id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        String nombreCompleto = reserva.getUsuario().getFirstname() + " " + reserva.getUsuario().getLastname();

        return ReservaResponseDTO.builder()
                .nombreUsuario(nombreCompleto) // Concatenamos firstName y lastName
                .nombreTour(reserva.getTour().getNombre())
                .cantidadPersonas(reserva.getCantidadPersonas())
                .estado(reserva.getEstado().name())
                .fechaReserva(reserva.getFechaReserva())
                .build();
    }


    public void actualizarReserva(Integer id, ReservaDTO reservaRequestDTO) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        var usuario = userRepository.findById(reservaRequestDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        var tour = tourRepository.findById(reservaRequestDTO.getTourId())
                .orElseThrow(() -> new RuntimeException("Tour no encontrado"));

        reserva.setUsuario(usuario);
        reserva.setTour(tour);
        reserva.setCantidadPersonas(reservaRequestDTO.getCantidadPersonas());
        reserva.setFechaReserva(java.time.LocalDateTime.now());

        if (reservaRequestDTO.getEstado() != null) {
            reserva.setEstado(reservaRequestDTO.getEstado());
        } else {
            reserva.setEstado(reserva.getEstado());
        }

        reservaRepository.save(reserva);
    }




    public void eliminarReserva(Integer id) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reservaRepository.delete(reserva);
    }

}


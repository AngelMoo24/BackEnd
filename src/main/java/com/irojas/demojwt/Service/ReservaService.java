package com.irojas.demojwt.Service;

import com.irojas.demojwt.DTO.ReservaDTO;
import com.irojas.demojwt.DTO.ReservaResponseDTO;
import com.irojas.demojwt.Entity.*;
import com.irojas.demojwt.Repository.HorariosRepository;
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

    @Autowired
    private HorariosRepository horariosRepository;


    public Reserva crearReserva(ReservaDTO reservaRequestDTO) {
        User usuario = userRepository.findById(reservaRequestDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Tour tour = tourRepository.findById(reservaRequestDTO.getTourId())
                .orElseThrow(() -> new RuntimeException("Tour no encontrado"));

        Horarios horarios = horariosRepository.findById(reservaRequestDTO.getHorarioId())
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        if (horarios.getCuposDisponibles() < reservaRequestDTO.getCantidadPersonas()) {
            throw new RuntimeException("No hay suficientes cupos disponibles para este horario.");
        }

        Reserva reserva = Reserva.builder()
                .usuario(usuario)
                .tour(tour)
                .horarios(horarios)
                .fechaReserva(LocalDateTime.now())
                .cantidadPersonas(reservaRequestDTO.getCantidadPersonas())
                .build();
        reserva = reservaRepository.save(reserva);


        horarios.setCuposDisponibles(horarios.getCuposDisponibles() - reservaRequestDTO.getCantidadPersonas());
        horariosRepository.save(horarios);

        return reserva;
    }

    public ReservaResponseDTO obtenerReserva(Integer id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        String nombreCompleto = reserva.getUsuario().getFirstname() + " " + reserva.getUsuario().getLastname();

        return ReservaResponseDTO.builder()
                .nombreUsuario(nombreCompleto)
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

        Horarios horarios = horariosRepository.findById(reservaRequestDTO.getHorarioId())
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        if (horarios.getCuposDisponibles() < reservaRequestDTO.getCantidadPersonas()) {
            throw new RuntimeException("No hay suficientes cupos disponibles para este horario.");
        }


        reserva.setUsuario(usuario);
        reserva.setTour(tour);
        reserva.setHorarios(horarios);
        reserva.setCantidadPersonas(reservaRequestDTO.getCantidadPersonas());
        reserva.setFechaReserva(java.time.LocalDateTime.now());

        horarios.setCuposDisponibles(horarios.getCuposDisponibles() - reservaRequestDTO.getCantidadPersonas());
        horariosRepository.save(horarios);

        reservaRepository.save(reserva);
    }


    public void eliminarReserva(Integer reservaId) {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        Horarios horario = reserva.getHorarios();

        horario.setCuposDisponibles(horario.getCuposDisponibles() + reserva.getCantidadPersonas());
        horariosRepository.save(horario);
        reservaRepository.delete(reserva);
    }

}


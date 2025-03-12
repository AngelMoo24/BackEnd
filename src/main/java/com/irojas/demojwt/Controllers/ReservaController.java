package com.irojas.demojwt.Controllers;

import com.irojas.demojwt.DTO.ReservaDTO;
import com.irojas.demojwt.DTO.ReservaResponseDTO;
import com.irojas.demojwt.Entity.Reserva;
import com.irojas.demojwt.Service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {


    @Autowired
    private ReservaService reservaService;


    @PostMapping
    public ResponseEntity<String> crearReserva(@RequestBody ReservaDTO ReservaDTO) {
        try {
            reservaService.crearReserva(ReservaDTO);
            return new ResponseEntity<>("La reserva ha sido guardada exitosamente.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Hubo un error al guardar la reserva.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> obtenerReserva(@PathVariable Integer id) {
        ReservaResponseDTO reserva = reservaService.obtenerReserva(id);
        return ResponseEntity.ok(reserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarReserva(@PathVariable Integer id, @RequestBody ReservaDTO reservaRequestDTO) {
        try {
            reservaService.actualizarReserva(id, reservaRequestDTO);
            return new ResponseEntity<>("La reserva ha sido actualizada exitosamente.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Hubo un error al actualizar la reserva.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarReserva(@PathVariable Integer id) {
        try {
            reservaService.eliminarReserva(id);
            return new ResponseEntity<>("La reserva ha sido eliminada exitosamente.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Hubo un error al eliminar la reserva.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

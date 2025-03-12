package com.irojas.demojwt.Controllers;

import com.irojas.demojwt.DTO.RequestDTO;
import com.irojas.demojwt.DTO.TourDetailDTO;
import com.irojas.demojwt.DTO.TourSummary;
import com.irojas.demojwt.Entity.Tour;
import com.irojas.demojwt.Service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tours")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TourContoller {

    @Autowired
    private  TourService tourService;


    @GetMapping
    public List<TourSummary> getAllTours() {
        return tourService.getAllToursSummary();
    }



    @GetMapping("/detalles/{id}")
    public TourDetailDTO getTourById(@PathVariable Long id) {
        return tourService.getTourById(id);
    }



    @DeleteMapping("/{id}")
    public void deleteTour(@PathVariable Long id) {
        tourService.deleteTour(id);
    }


    @PostMapping("/agregar")
    public ResponseEntity<String> addTour(@RequestBody RequestDTO tourRequestDTO) {
        String result = tourService.addTour(tourRequestDTO);
        if (result.equals("Tour agregado exitosamente")) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> updateTour(@PathVariable Long id, @RequestBody RequestDTO tourRequestDTO) {
        String result = tourService.updateTour(id, tourRequestDTO);
        if (result.equals("Tour actualizado exitosamente")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}

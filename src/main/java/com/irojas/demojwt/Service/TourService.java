package com.irojas.demojwt.Service;

import com.irojas.demojwt.DTO.RequestDTO;
import com.irojas.demojwt.DTO.TourDetailDTO;
import com.irojas.demojwt.DTO.TourSummary;
import com.irojas.demojwt.Entity.CategoriaTour;
import com.irojas.demojwt.Entity.Tour;
import com.irojas.demojwt.Entity.TourImage;
import com.irojas.demojwt.Repository.CategoriaTourRepository;
import com.irojas.demojwt.Repository.TourImageRepository;
import com.irojas.demojwt.Repository.TourRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourService {

    @Autowired
    private  TourRepository tourRepository;
    @Autowired
    private  TourImageRepository tourImageRepository;
    @Autowired
    private CategoriaTourRepository categoriaTourRepository ;


    public List<TourSummary> getToursByCategory(Integer categoriaId) {
        // Obtener tours por categoria desde el repositorio
        List<Tour> tours = tourRepository.findByCategoriaTourId(categoriaId); // Asegúrate de tener este método en el repositorio
        return tours.stream()
                .map(tour -> new TourSummary(tour))  // Usamos el constructor de TourSummary para convertir Tour a TourSummary
                .collect(Collectors.toList());  // Coleccionamos en una lista de TourSummary
    }



    public List<TourSummary> getAllToursSummary() {
        return tourRepository.findAll().stream()
                .map(tour -> TourSummary.builder()
                        .id(tour.getId())
                        .nombre(tour.getNombre())
                        .ubicacion(tour.getUbicacion())
                        .precio(tour.getPrecio())
                        .imagen(tour.getImagen())
                        .build())
                .collect(Collectors.toList());
    }

    public TourDetailDTO getTourById(Long id) {
        Tour tour = tourRepository.findById(id).orElseThrow(() -> new RuntimeException("Tour no encontrado"));
        List<String> imagenes = tourImageRepository.findByTourId(id)
                .stream()
                .map(TourImage::getUrlImagen)
                .collect(Collectors.toList());

        return TourDetailDTO.builder()
                .id(tour.getId())
                .nombre(tour.getNombre())
                .descripcion(tour.getDescripcion())
                .ubicacion(tour.getUbicacion())
                .precio(tour.getPrecio())
                .imagenes(imagenes)
                .build();
    }


    @Transactional
    public String addTour(RequestDTO tourRequestDTO) {
        try {
            // Buscar la categoría por ID
            CategoriaTour categoria = categoriaTourRepository.findById(tourRequestDTO.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

            Tour newTour = new Tour();
            newTour.setCategoriaTour(categoria); // Se asigna la entidad, no solo el ID
            newTour.setNombre(tourRequestDTO.getNombre());
            newTour.setDescripcion(tourRequestDTO.getDescripcion());
            newTour.setUbicacion(tourRequestDTO.getUbicacion());
            newTour.setPrecio(tourRequestDTO.getPrecio());
            newTour.setDisponibilidad(tourRequestDTO.getDisponibilidad());
            newTour.setImagen(tourRequestDTO.getImagen());

            tourRepository.save(newTour);

            List<String> imagenesUrls = tourRequestDTO.getImagenes();
            for (String urlImagen : imagenesUrls) {
                TourImage tourImage = new TourImage();
                tourImage.setTour(newTour);
                tourImage.setUrlImagen(urlImagen);
                tourImageRepository.save(tourImage);
            }

            return "Tour agregado exitosamente";
        } catch (Exception e) {
            return "Error al agregar el tour: " + e.getMessage();
        }
    }

    @Transactional
    public String updateTour(Long id, RequestDTO tourRequestDTO) {
        try {

            CategoriaTour categoria = categoriaTourRepository.findById(tourRequestDTO.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            Tour existingTour = tourRepository.findById(id).orElseThrow(() -> new RuntimeException("Tour no encontrado"));

            existingTour.setCategoriaTour(categoria);
            existingTour.setNombre(tourRequestDTO.getNombre());
            existingTour.setDescripcion(tourRequestDTO.getDescripcion());
            existingTour.setUbicacion(tourRequestDTO.getUbicacion());
            existingTour.setPrecio(tourRequestDTO.getPrecio());
            existingTour.setDisponibilidad(tourRequestDTO.getDisponibilidad());
            existingTour.setImagen(tourRequestDTO.getImagen());

            tourRepository.save(existingTour);


            List<String> imagenesUrls = tourRequestDTO.getImagenes();
            for (String urlImagen : imagenesUrls) {
                TourImage tourImage = new TourImage();
                tourImage.setTour(existingTour);
                tourImage.setUrlImagen(urlImagen);
                tourImageRepository.save(tourImage);
            }

            return "Tour actualizado exitosamente";
        } catch (Exception e) {
            return "Error al actualizar el tour: " + e.getMessage();
        }
    }

    public void deleteTour(Long id) {
        if (!tourRepository.existsById(id)) {
            throw new RuntimeException("Tour no encontrado");
        }
        tourRepository.deleteById(id);
    }
}

package com.irojas.demojwt.Service;

import com.irojas.demojwt.DTO.CategoriaTourRequest;
import com.irojas.demojwt.DTO.CategoriaTourResponse;
import com.irojas.demojwt.DTO.TourSummary;
import com.irojas.demojwt.Entity.CategoriaTour;
import com.irojas.demojwt.Repository.CategoriaTourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaTourService {

    @Autowired
    private CategoriaTourRepository categoriaTourRepository;

    // Crear categoría
    public CategoriaTour crearCategoria(CategoriaTourRequest request) {
        CategoriaTour categoria = CategoriaTour.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .imagen(request.getImagen())
                .build();
        return categoriaTourRepository.save(categoria);
    }

    // Listar categorías
    public List<CategoriaTourResponse> listarCategorias() {
        List<CategoriaTour> categorias = categoriaTourRepository.findAll();
        return categorias.stream().map(categoria -> CategoriaTourResponse.builder()
                        .id(categoria.getId())
                        .nombre(categoria.getNombre())
                        .descripcion(categoria.getDescripcion())
                        .imagen(categoria.getImagen())
                        .build())
                .collect(Collectors.toList());
    }


    // Obtener por ID
// Obtener por ID
    public CategoriaTourResponse obtenerCategoriaPorId(Integer id) {
        CategoriaTour categoria = categoriaTourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        return CategoriaTourResponse.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .imagen(categoria.getImagen())
                .build();
    }



    // Actualizar categoría
    public CategoriaTourResponse actualizarCategoria(Integer id, CategoriaTourRequest request) {
        CategoriaTour categoria = categoriaTourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());

        CategoriaTour categoriaActualizada = categoriaTourRepository.save(categoria);

        return CategoriaTourResponse.builder()
                .id(categoriaActualizada.getId())
                .nombre(categoriaActualizada.getNombre())
                .descripcion(categoriaActualizada.getDescripcion())
                .imagen(categoriaActualizada.getImagen())
                .build();
    }

    public void eliminarCategoria(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID de la categoría no puede ser nulo");
        }

        CategoriaTour categoria = categoriaTourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La categoría con ID " + id + " no fue encontrada"));

        categoriaTourRepository.delete(categoria);
    }


}

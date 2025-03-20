package com.irojas.demojwt.Controllers;

import com.irojas.demojwt.DTO.CategoriaTourRequest;
import com.irojas.demojwt.DTO.CategoriaTourResponse;
import com.irojas.demojwt.Entity.CategoriaTour;
import com.irojas.demojwt.Service.CategoriaTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaTourController {

    @Autowired
    private CategoriaTourService categoriaTourService;

    // Crear
    @PostMapping
    public ResponseEntity<CategoriaTour> crearCategoria(@RequestBody CategoriaTourRequest request) {
        CategoriaTour nuevaCategoria = categoriaTourService.crearCategoria(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }

    // Listar
    @GetMapping
    public ResponseEntity<List<CategoriaTourResponse>> listarCategorias() {
        return ResponseEntity.ok(categoriaTourService.listarCategorias());
    }


    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaTourResponse> obtenerCategoriaPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(categoriaTourService.obtenerCategoriaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaTourResponse> actualizarCategoria(@PathVariable Integer id, @RequestBody CategoriaTourRequest request) {
        CategoriaTourResponse response = categoriaTourService.actualizarCategoria(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Integer id) {
        categoriaTourService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}


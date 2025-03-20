package com.irojas.demojwt.DTO;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaTourRequest {

    private String nombre;
    private String descripcion;
}

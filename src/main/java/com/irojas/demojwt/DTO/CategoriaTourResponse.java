package com.irojas.demojwt.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaTourResponse {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String imagen;
}

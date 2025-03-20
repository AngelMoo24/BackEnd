package com.irojas.demojwt.Repository;

import com.irojas.demojwt.Entity.Horarios;
import com.irojas.demojwt.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorariosRepository extends JpaRepository<Horarios, Integer> {
}

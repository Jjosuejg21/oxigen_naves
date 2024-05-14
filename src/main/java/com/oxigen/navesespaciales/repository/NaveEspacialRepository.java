package com.oxigen.navesespaciales.repository;

import com.oxigen.navesespaciales.model.NaveEspacialModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NaveEspacialRepository extends JpaRepository<NaveEspacialModel, Long> {
    List<NaveEspacialModel> findByNombreContaining(String name);
}

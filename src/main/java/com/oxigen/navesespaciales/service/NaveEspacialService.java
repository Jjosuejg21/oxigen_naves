package com.oxigen.navesespaciales.service;

import com.oxigen.navesespaciales.exception.NaveInexistenteException;
import com.oxigen.navesespaciales.model.NaveEspacialModel;
import com.oxigen.navesespaciales.repository.NaveEspacialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NaveEspacialService {
    @Autowired
    private NaveEspacialRepository naveEspacialRepository;

    public Page<NaveEspacialModel> obtenerNaves(Pageable pageable) {
        return naveEspacialRepository.findAll(pageable);
    }

    public Optional<NaveEspacialModel> obtenerNaveEspacialPorId(Long id) {
        return naveEspacialRepository.findById(id);
    }

    public List<NaveEspacialModel> obtenerNavesEspacialesPorNombre(String name) {
        return naveEspacialRepository.findByNombreContaining(name);
    }

    public NaveEspacialModel crearNaveEspacial(NaveEspacialModel naveEspacial) {
        return naveEspacialRepository.save(naveEspacial);
    }

    public NaveEspacialModel actualizarNaveEspacial(Long id, NaveEspacialModel naveEspacial) {
        return naveEspacialRepository.findById(id).map(naveEspacialModelRegistrada -> {
            naveEspacialModelRegistrada.setNombre(naveEspacial.getNombre());
            naveEspacialModelRegistrada.setSerie(naveEspacial.getSerie());
            naveEspacialModelRegistrada.setPelicula(naveEspacial.getPelicula());
            return naveEspacialRepository.save(naveEspacialModelRegistrada);
        }).orElseThrow(() -> new NaveInexistenteException("Nave espacial con id: " + id + " INEXISTENTE"));
    }

    public void eliminarNaveEspacial(Long id) {
        naveEspacialRepository.deleteById(id);
    }
}

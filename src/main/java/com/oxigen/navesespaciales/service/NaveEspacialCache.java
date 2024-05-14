package com.oxigen.navesespaciales.service;

import com.oxigen.navesespaciales.exception.NaveInexistenteException;
import com.oxigen.navesespaciales.model.NaveEspacialModel;
import com.oxigen.navesespaciales.repository.NaveEspacialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NaveEspacialCache {
    @Autowired
    private NaveEspacialRepository naveEspacialRepository;

    public Page<NaveEspacialModel> obtenerNaves(Pageable pageable) {
        return naveEspacialRepository.findAll(pageable);
    }

    @Cacheable(value = "navesespaciales", key = "#id")
    public Optional<NaveEspacialModel> obtenerNaveEspacialPorId(Long id) {
        return naveEspacialRepository.findById(id);
    }

    public List<NaveEspacialModel> obtenerNavesEspacialesPorNombre(String name) {
        return naveEspacialRepository.findByNombreContaining(name);
    }

    @CacheEvict(value = "navesespaciales", allEntries = true)
    public NaveEspacialModel crearNaveEspacial(NaveEspacialModel naveEspacial) {
        return naveEspacialRepository.save(naveEspacial);
    }

    @CacheEvict(value = "navesespaciales", key = "#id")
    public NaveEspacialModel actualizarNaveEspacial(Long id, NaveEspacialModel naveEspacial) {
        return naveEspacialRepository.findById(id).map(naveEspacialRegistrada -> {
            naveEspacialRegistrada.setNombre(naveEspacial.getNombre());
            naveEspacialRegistrada.setSerie(naveEspacial.getSerie());
            naveEspacialRegistrada.setPelicula(naveEspacial.getPelicula());
            return naveEspacialRepository.save(naveEspacialRegistrada);
        }).orElseThrow(() -> new NaveInexistenteException("Nave espacial con id: " + id + " INEXISTENTE"));
    }

    @CacheEvict(value = "navesespaciales", key = "#id")
    public void eliminarNaveEspacial(Long id) {
        naveEspacialRepository.deleteById(id);
    }
}

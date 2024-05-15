package com.oxigen.navesespaciales.controller;

import com.oxigen.navesespaciales.model.NaveEspacialModel;
import com.oxigen.navesespaciales.service.NaveEspacialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar las operaciones relacionadas con las naves espaciales.
 */
@RestController
@RequestMapping("/api/naves")
public class NaveEspacialController {

    @Autowired
    private NaveEspacialService naveEspacialService;

    /**
     * Obtiene todas las naves espaciales con paginación.
     *
     * @param pageable Información de paginación.
     * @return Página de modelos de naves espaciales.
     */
    @GetMapping
    public Page<NaveEspacialModel> obtenerTodasLasNaves(Pageable pageable) {
        return naveEspacialService.obtenerNaves(pageable);
    }

    /**
     * Obtiene una nave espacial por su ID.
     *
     * @param id Identificador de la nave espacial.
     * @return Respuesta con el modelo de la nave espacial si se encuentra, o un estado 404 si no.
     */
    @GetMapping("/{id}")
    public ResponseEntity<NaveEspacialModel> obtenerNaveEspacialPorId(@PathVariable Long id) {
        return naveEspacialService.obtenerNaveEspacialPorId(id)
                .map(naveEspacial -> ResponseEntity.ok().body(naveEspacial))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Busca naves espaciales por nombre.
     *
     * @param nombre Nombre de la nave espacial.
     * @return Lista de modelos de naves espaciales que coinciden con el nombre.
     */
    @GetMapping("/buscar")
    public List<NaveEspacialModel> obtenerNavesEspacialesPorNombre(@RequestParam String nombre) {
        return naveEspacialService.obtenerNavesEspacialesPorNombre(nombre);
    }

    /**
     * Crea una nueva nave espacial.
     *
     * @param naveEspacial Modelo de la nueva nave espacial.
     * @return Respuesta con el modelo de la nave espacial creada y un estado 201.
     */
    @PostMapping
    public ResponseEntity<NaveEspacialModel> crearNaveEspacial(@RequestBody NaveEspacialModel naveEspacial) {
        return new ResponseEntity<>(naveEspacialService.crearNaveEspacial(naveEspacial), HttpStatus.CREATED);
    }

    /**
     * Actualiza una nave espacial existente.
     *
     * @param id           Identificador de la nave espacial.
     * @param naveEspacial Modelo de la nave espacial actualizada.
     * @return Respuesta con el modelo de la nave espacial actualizada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<NaveEspacialModel> actualizarNaveEspacial(@PathVariable Long id, @RequestBody NaveEspacialModel naveEspacial) {
        return ResponseEntity.ok(naveEspacialService.actualizarNaveEspacial(id, naveEspacial));
    }

    /**
     * Elimina una nave espacial por su ID.
     *
     * @param id Identificador de la nave espacial.
     * @return Respuesta con un estado 204 si la eliminación es exitosa.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNaveEspacial(@PathVariable Long id) {
        naveEspacialService.eliminarNaveEspacial(id);
        return ResponseEntity.noContent().build();
    }
}

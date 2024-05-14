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

@RestController
@RequestMapping("/api/naves")
public class NaveEspacialController {
    @Autowired
    private NaveEspacialService naveEspacialService;

    @GetMapping
    public Page<NaveEspacialModel> obtenerTodasLasNaves(Pageable pageable) {
        return naveEspacialService.obtenerNaves(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NaveEspacialModel> obtenerNaveEspacialPorId(@PathVariable Long id) {
        return naveEspacialService.obtenerNaveEspacialPorId(id)
                .map(naveEspacial -> ResponseEntity.ok().body(naveEspacial))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public List<NaveEspacialModel> obtenerNavesEspacialesPorNombre(@RequestParam String name) {
        return naveEspacialService.obtenerNavesEspacialesPorNombre(name);
    }

    @PostMapping
    public ResponseEntity<NaveEspacialModel> crearNaveEspacial(@RequestBody NaveEspacialModel naveEspacial) {
        return new ResponseEntity<>(naveEspacialService.crearNaveEspacial(naveEspacial), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NaveEspacialModel> actualizarNaveEspacial(@PathVariable Long id, @RequestBody NaveEspacialModel naveEspacial) {
        return ResponseEntity.ok(naveEspacialService.actualizarNaveEspacial(id, naveEspacial));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNaveEspacial(@PathVariable Long id) {
        naveEspacialService.eliminarNaveEspacial(id);
        return ResponseEntity.noContent().build();
    }
}

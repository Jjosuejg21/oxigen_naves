package com.oxigen.navesespaciales.service;

import com.oxigen.navesespaciales.model.NaveEspacialModel;
import com.oxigen.navesespaciales.repository.NaveEspacialRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NaveEspacialServiceTest {

    @Mock
    private NaveEspacialRepository naveEspacialRepository;

    @InjectMocks
    private NaveEspacialService naveEspacialService;

    @Test
    void testObtenerNaveEspacialPorId() {
        NaveEspacialModel naveEspacial = new NaveEspacialModel();
        naveEspacial.setId(1L);
        naveEspacial.setNombre("Test NaveEspacial");

        when(naveEspacialRepository.findById(anyLong())).thenReturn(Optional.of(naveEspacial));

        Optional<NaveEspacialModel> found = naveEspacialService.obtenerNaveEspacialPorId(1L);

        assertTrue(found.isPresent());
        assertEquals("Test NaveEspacial", found.get().getNombre());
    }

    @Test
    void testCrearNaveEspacial() {
        NaveEspacialModel naveEspacial = new NaveEspacialModel();
        naveEspacial.setNombre("Test NaveEspacial");

        when(naveEspacialRepository.save(any(NaveEspacialModel.class))).thenReturn(naveEspacial);

        NaveEspacialModel created = naveEspacialService.crearNaveEspacial(naveEspacial);

        assertEquals("Test NaveEspacial", created.getNombre());
    }

    @Test
    void testActualizarNaveEspacial() {
        NaveEspacialModel naveEspacial = new NaveEspacialModel();
        naveEspacial.setId(1L);
        naveEspacial.setNombre("Test NaveEspacial");

        when(naveEspacialRepository.findById(anyLong())).thenReturn(Optional.of(naveEspacial));
        when(naveEspacialRepository.save(any(NaveEspacialModel.class))).thenReturn(naveEspacial);

        NaveEspacialModel updated = naveEspacialService.actualizarNaveEspacial(1L, naveEspacial);

        assertEquals("Test NaveEspacial", updated.getNombre());
    }

    @Test
    void testEliminarNaveEspacial() {
        doNothing().when(naveEspacialRepository).deleteById(anyLong());

        naveEspacialService.eliminarNaveEspacial(1L);

        verify(naveEspacialRepository, times(1)).deleteById(1L);
    }
}

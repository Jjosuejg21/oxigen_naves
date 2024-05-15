package com.oxigen.navesespaciales;

import com.oxigen.navesespaciales.model.NaveEspacialModel;
import com.oxigen.navesespaciales.repository.NaveEspacialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class NaveEspacialIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NaveEspacialRepository naveEspacialRepository;

    private String basicAuthHeader;

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    @BeforeEach
    public void setup() {
        naveEspacialRepository.deleteAll();
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        basicAuthHeader = "Basic " + new String(encodedAuth);
    }

    @Test
    void testObtenerNaves() throws Exception {
        naveEspacialRepository.save(new NaveEspacialModel(null, "Test NaveEspacial", "Test NaveEspacial", "Test NaveEspacial"));

        mockMvc.perform(get("/api/naves")
                        .header("Authorization", basicAuthHeader)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)));
    }

    @Test
    void testCrearNave() throws Exception {
        mockMvc.perform(post("/api/naves")
                        .header("Authorization", basicAuthHeader)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"Test NaveEspacial\", \"serie\": \"Test NaveEspacial\", \"pelicula\": \"Test NaveEspacial\"}"))
                .andExpect(status().isCreated());
    }
}

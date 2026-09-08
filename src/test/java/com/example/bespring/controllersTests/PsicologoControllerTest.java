package com.example.bespring.controllersTests;

import com.example.bespring.controllers.PsicologoController;
import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Psicologo;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.domain.enums.TipoProfissional;
import com.example.bespring.dto.CriarPsicologoRequest;
import com.example.bespring.repository.UtilizadorRepository;
import com.example.bespring.security.TokenService;
import com.example.bespring.services.PsicologoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PsicologoController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PsicologoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PsicologoService psicologoService;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @MockitoBean
    private UtilizadorRepository utilizadorRepository;

    @MockitoBean
    private TokenService tokenService;

    @Test
    void deveCadastrarUmPsicologo() throws Exception {

        Psicologo psicologo = new Psicologo();
        psicologo.setIdUtilizador(1L);
        psicologo.setPrimeiroNome("Kwanzambi");
        psicologo.setSobrenome("Disila");
        psicologo.setTelefone("123456789");
        psicologo.setEmail("kwanzambi@gmail.com");
        psicologo.setSenha("123456");
        psicologo.setGenero(Genero.MASCULINO);
        psicologo.setTipo(TipoProfissional.PSICOLOGO);
        psicologo.setPerfil(Perfil.PSICOLOGO);

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
        escola.setIdEscola(1L);
        psicologo.setEscola(escola);

        CriarPsicologoRequest psicologoRequest = new CriarPsicologoRequest(
                psicologo.getPrimeiroNome(),
                psicologo.getSobrenome(),
                psicologo.getTelefone(),
                psicologo.getGenero(),
                psicologo.getEmail(),
                psicologo.getSenha(),
                psicologo.getTipo(),
                psicologo.getEscola().getIdEscola()
        );

        when(psicologoService.cadastrarPsicologo(any(CriarPsicologoRequest.class))).thenReturn(psicologo);

        String json = objectMapper.writeValueAsString(psicologoRequest);

        mockMvc.perform(post("/api/psicologos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))

                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idUtilizador").value(1L))
                .andExpect(jsonPath("$.primeiroNome").value("Kwanzambi"))
                .andExpect(jsonPath("$.sobrenome").value("Disila"))
                .andExpect(jsonPath("$.telefone").value("123456789"))
                .andExpect(jsonPath("$.genero").value(Genero.MASCULINO.toString()))
                .andExpect(jsonPath("$.email").value("kwanzambi@gmail.com"))
                .andExpect(jsonPath("$.tipo").value(TipoProfissional.PSICOLOGO.toString()))
                .andExpect(jsonPath("$.perfil").value(Perfil.PSICOLOGO.toString()))
                .andExpect(jsonPath("$.idEscola").value(escola.getIdEscola()));

                verify(psicologoService).cadastrarPsicologo(any(CriarPsicologoRequest.class));
    }

    @Test
    @WithMockUser(username = "kwanzambi@gmail.com")
    void deveProcurarUmPsicologoPeloId() throws Exception {

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
        escola.setIdEscola(1L);

        Psicologo psicologo = new Psicologo();
        psicologo.setIdUtilizador(1L);
        psicologo.setEmail("kwanzambi@gmail.com");

        long id = 1L;

        psicologo.setEscola(escola);


        String utilizadorLogado = psicologo.getEmail();

        when(psicologoService.procurarPsicologoPeloId(id, utilizadorLogado)).thenReturn(psicologo);

        mockMvc.perform(get("/api/psicologos/{idUtilizador}", id)
                        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUtilizador").value(1L))
                .andExpect(jsonPath("$.idEscola").value(escola.getIdEscola()))
                .andExpect(jsonPath("$.Email").value("Kwanzambi@gmail.com"));

        verify(psicologoService).procurarPsicologoPeloId(id, utilizadorLogado);


    }
}

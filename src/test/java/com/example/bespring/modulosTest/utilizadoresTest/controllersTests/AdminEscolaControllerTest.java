package com.example.bespring.modulosTest.utilizadoresTest.controllersTests;

import com.example.bespring.modulosTest.utilizadoresTest.controllers.AdminEscolaController;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Escola;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Utilizador;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.dto.AtualizarAdminEscolaRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.AtualizarEscolaRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarAdminEscolaRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarEscolaRequest;
import com.example.bespring.modulosTest.utilizadoresTest.repository.UtilizadorRepository;
import com.example.bespring.modulosTest.utilizadoresTest.services.AdminEscolaService;
import com.example.bespring.modulosTest.utilizadoresTest.services.EscolaService;
import com.example.bespring.security.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdminEscolaController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AdminEscolaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AdminEscolaService adminEscolaService;

    @MockitoBean
    private TokenService tokenService;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @MockitoBean
    private UtilizadorRepository utilizadorRepository;

    @Test
    void deveRegistarAdminEscola() throws Exception {
        Utilizador utilizador = new Utilizador();
        utilizador.setPrimeiroNome("Zuri");
        utilizador.setSobrenome("Jorge");
        utilizador.setGenero(Genero.FEMININO);
        utilizador.setDataNascimento(LocalDate.of(1999, 4, 3));
        utilizador.setLogin("admin");
        utilizador.setSenha("admin1234#");
        utilizador.setEstadoConta(EstadoConta.ATIVA);

        Long idAdminLogado = 1L;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423444", "school@gmail.com");
        escola.setIdEscola(1L);
        utilizador.setEscola(escola);

        Utilizador admin = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                LocalDate.of(2004,1,5), Perfil.ADMIN,"raquel@gmail.com", "maura1234", EstadoConta.ATIVA, null, null);
        admin.setIdUtilizador(1L);

        CriarAdminEscolaRequest request = new CriarAdminEscolaRequest(
                utilizador.getPrimeiroNome(),
                utilizador.getSobrenome(),
                utilizador.getGenero(),
                utilizador.getDataNascimento(),
                utilizador.getLogin(),
                utilizador.getSenha(),
                utilizador.getEscola().getIdEscola()
        );

        when(adminEscolaService.registarAdminEscola(any(CriarAdminEscolaRequest.class), any(Long.class))).thenReturn(utilizador);

        String json = objectMapper.writeValueAsString(request);


        mockMvc.perform(post("/api/adminEscola")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))

                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.primeiroNome").value("Zuri"))
                .andExpect(jsonPath("$.sobrenome").value("Jorge"))
                .andExpect(jsonPath("$.genero").value("FEMININO"))
                .andExpect(jsonPath("$.dataNascimento").value(LocalDate.of(2004,1,5)))
                .andExpect(jsonPath("$.login").value("admin"))
                .andExpect(jsonPath("$.estadoConta").value("ATIVA"))
                .andExpect(jsonPath("$.idEscola").value(1L));


        verify(adminEscolaService).registarAdminEscola(any(CriarAdminEscolaRequest.class), idAdminLogado);
    }


    @Test
    void deveProcurarAdminEscolaPorId() throws Exception {

        Utilizador utilizador = new Utilizador();
        utilizador.setIdUtilizador(1L);

        long idAdminLogado = 1L;
        long idUser = 1L;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423444", "school@gmail.com");
        escola.setIdEscola(1L);
        utilizador.setEscola(escola);

        Utilizador admin = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                LocalDate.of(2004,1,5), Perfil.ADMIN,"raquel@gmail.com", "maura1234", EstadoConta.ATIVA, null, null);
        admin.setIdUtilizador(1L);

        when(adminEscolaService.procurarAdminEscola(idUser, idAdminLogado)).thenReturn(utilizador);

        mockMvc.perform(get("/api/adminEscola/{idUtilizador}", idUser))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUser").value(1L))
                .andExpect(jsonPath("$.idUser").value(utilizador.getIdUtilizador()));

        verify(adminEscolaService).procurarAdminEscola(idUser, idAdminLogado);
    }

    @Test
    void deveAtualizarAdminEscola() throws Exception {
        Utilizador utilizador = new Utilizador();
        utilizador.setPrimeiroNome("Zuri");
        utilizador.setSobrenome("Jorge");
        utilizador.setGenero(Genero.FEMININO);
        utilizador.setLogin("admin");
        utilizador.setSenha("admin1234#");

        Long idAdminLogado = 1L;
        long idUser = 1L;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423444", "school@gmail.com");
        escola.setIdEscola(1L);
        utilizador.setEscola(escola);

        Utilizador admin = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                LocalDate.of(2004,1,5), Perfil.ADMIN,"raquel@gmail.com", "maura1234", EstadoConta.ATIVA, null, null);
        admin.setIdUtilizador(1L);

        AtualizarAdminEscolaRequest adminEscolaRequest = new AtualizarAdminEscolaRequest(
                utilizador.getPrimeiroNome(),
                utilizador.getSobrenome(),
                utilizador.getGenero(),
                utilizador.getLogin(),
                utilizador.getSenha()
        );

        doNothing().when(adminEscolaService).atualizarAdminEscola(idUser, adminEscolaRequest, idAdminLogado);

        String json = objectMapper.writeValueAsString(adminEscolaRequest);

        mockMvc.perform(put("/api/adminEscola/{idUtilizador}", idUser)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))

                .andExpect(status().isNoContent());

        verify(adminEscolaService).atualizarAdminEscola(idUser, adminEscolaRequest, idAdminLogado);
    }


    @Test
    void deveApagarAdminEscolaPorId() throws Exception {

        Utilizador utilizador = new Utilizador();
        utilizador.setIdUtilizador(1L);

        long idAdminLogado = 1L;
        long idUser = 1L;

        Escola escola = new Escola("School", "Rua francisco da silva", "923423444", "school@gmail.com");
        escola.setIdEscola(1L);
        utilizador.setEscola(escola);

        Utilizador admin = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                LocalDate.of(2004,1,5), Perfil.ADMIN,"raquel@gmail.com", "maura1234", EstadoConta.ATIVA, null, null);
        admin.setIdUtilizador(1L);

        doNothing().when(adminEscolaService).apagarAdminEscola(idUser, idAdminLogado);

        mockMvc.perform(get("/api/adminEscola/{idUtilizador}", idUser))
                .andExpect(status().isNoContent());

        verify(adminEscolaService).apagarAdminEscola(idUser, idAdminLogado);

    }

}

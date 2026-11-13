package com.example.bespring.modulosTest.utilizadoresTest.controllersTests;

import com.example.bespring.modulosTest.utilizadoresTest.controllers.EscolaController;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Escola;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Utilizador;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.dto.AtualizarEscolaRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarEscolaRequest;
import com.example.bespring.modulosTest.utilizadoresTest.repository.UtilizadorRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = EscolaController.class)
@AutoConfigureMockMvc(addFilters = false)
public class EscolaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EscolaService escolaService;

    @MockitoBean
    private TokenService tokenService;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @MockitoBean
    private UtilizadorRepository utilizadorRepository;

    @Test
    void deveRegistarEscola() throws Exception {

        Escola escola = new Escola();
        escola.setNome("School");
        escola.setEndereco("Rua do Antonio");
        escola.setEmail("school@gmail.com");
        escola.setTelefone("923456789");

        Long idAdmin = 1L;

        Utilizador admin = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                LocalDate.of(2004,1,5), Perfil.ADMIN,"raquel@gmail.com", "maura1234", EstadoConta.ATIVA, null);
        admin.setIdUtilizador(1L);
        escola.setAdministrador(admin);

        CriarEscolaRequest request = new CriarEscolaRequest(
                escola.getNome(),
                escola.getEndereco(),
                escola.getTelefone(),
                escola.getEmail()
        );

        when(escolaService.registarEscola(any(CriarEscolaRequest.class), any(Long.class))).thenReturn(escola);

        String json = objectMapper.writeValueAsString(request);


        mockMvc.perform(post("/api/escolas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))

                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idEscola").value(1L))
                .andExpect(jsonPath("$.nomeEscola").value("School"))
                .andExpect(jsonPath("$.endereco").value("Rua do Antonio"))
                .andExpect(jsonPath("$.telefone").value("923456789"))
                .andExpect(jsonPath("$.emailEscola").value("school@gmail.com"));

        verify(escolaService).registarEscola(any(CriarEscolaRequest.class), idAdmin);
    }

    @Test
    @WithMockUser(username = "teste1")
    void deveListarEscola() throws Exception {

        Escola escola1 = new Escola();
        escola1.setNome("School");
        escola1.setEndereco("Rua do Antonio");
        escola1.setEmail("school@gmail.com");
        escola1.setTelefone("923456789");

        Escola escola2 = new Escola();
        escola2.setNome("Rosa");
        escola2.setEndereco("Rua do Merry");
        escola2.setEmail("rosa@gmail.com");
        escola2.setTelefone("924436745");

        Long idAdminLogado = 1L;

        Utilizador admin = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                LocalDate.of(2004,1,5), Perfil.ADMIN,"raquel@gmail.com", "maura1234", EstadoConta.ATIVA, null);
        admin.setIdUtilizador(1L);
        escola1.setAdministrador(admin);
        escola2.setAdministrador(admin);

        List<Escola> escolas = List.of(escola1, escola2);

        when(escolaService.listarEscolas(idAdminLogado)).thenReturn(escolas);

        mockMvc.perform(get("/api/escolas"))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.leght()").value(2))
                .andExpect(jsonPath("$[0].idEscola").value(1L))
                .andExpect(jsonPath("$[0].nomeEscola").value("School"))
                .andExpect(jsonPath("$[0].endereco").value("Rua do Antonio"))
                .andExpect(jsonPath("$[0].emailEscola").value("school@gmail.com"))
                .andExpect(jsonPath("$[0].telefone").value("923456789"))
                .andExpect(jsonPath("$[1].idEscola").value(1L))
                .andExpect(jsonPath("$[1].nomeEscola").value("School"))
                .andExpect(jsonPath("$[1].endereco").value("Rua do Merry"))
                .andExpect(jsonPath("$[1].emailEscola").value("merry@gmail.com"))
                .andExpect(jsonPath("$[1].telefone").value("923456789"));

        verify(escolaService).listarEscolas(idAdminLogado);
    }

    @Test
    void deveProcurarEscolaPorId() throws Exception {

        Escola escola = new Escola();
        escola.setIdEscola(1L);

        long idAdminLogado = 2L;
        long idEscola = 1L;

        Utilizador admin = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                LocalDate.of(2004,1,5), Perfil.ADMIN,"raquel@gmail.com", "maura1234", EstadoConta.ATIVA, null);
        admin.setIdUtilizador(2L);
        escola.setAdministrador(admin);

        when(escolaService.procurarEscola(idEscola, idAdminLogado)).thenReturn(escola);

        mockMvc.perform(get("/api/escolas/{idEscola}", idEscola))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idEscola").value(1L))
                .andExpect(jsonPath("$.idEscola").value(escola.getIdEscola()));

        verify(escolaService).procurarEscola(idEscola, idAdminLogado);
    }

    @Test
    void deveAtualizarEscola() throws Exception {
        Escola escola = new Escola();
        escola.setNome("School");
        escola.setEndereco("Rua do Antonio");
        escola.setEmail("school@gmail.com");
        escola.setTelefone("923456789");

        Long idAdminLogado = 2L;
        long idEscola = 1L;

        Utilizador admin = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                LocalDate.of(2004,1,5), Perfil.ADMIN,"raquel@gmail.com", "maura1234", EstadoConta.ATIVA, null);
        admin.setIdUtilizador(2L);
        escola.setAdministrador(admin);

        AtualizarEscolaRequest escolaRequest = new AtualizarEscolaRequest(
                escola.getNome(),
                escola.getEndereco(),
                escola.getEmail(),
                escola.getTelefone()
        );

        doNothing().when(escolaService).atualizarEscola(idEscola, escolaRequest, idAdminLogado);

        String json = objectMapper.writeValueAsString(escolaRequest);

        mockMvc.perform(put("/api/escolas/{idEscola}", idEscola)
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .content(json))

                .andExpect(status().isNoContent());

        verify(escolaService).atualizarEscola(idEscola, escolaRequest, idAdminLogado);
    }


    @Test
    void deveApagarEscolaPorId() throws Exception {
        Escola escola = new Escola();
        escola.setIdEscola(1L);

        long idAdminLogado = 2L;
        long idEscola = 1L;

        Utilizador admin = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                LocalDate.of(2004,1,5), Perfil.ADMIN,"raquel@gmail.com", "maura1234", EstadoConta.ATIVA, null);
        admin.setIdUtilizador(2L);
        escola.setAdministrador(admin);

        doNothing().when(escolaService).apagarEscola(idEscola, idAdminLogado);

        mockMvc.perform(get("/api/escolas/{idEscola}", idEscola))
                .andExpect(status().isNoContent());

        verify(escolaService).apagarEscola(idEscola, idAdminLogado);

    }

}

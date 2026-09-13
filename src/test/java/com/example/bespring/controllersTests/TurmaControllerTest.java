package com.example.bespring.controllersTests;

import com.example.bespring.controllers.AlunoController;
import com.example.bespring.controllers.TurmaController;
import com.example.bespring.domain.Aluno;
import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Professor;
import com.example.bespring.domain.Turma;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.domain.enums.TipoProfissional;
import com.example.bespring.dto.CriarTurmaRequest;
import com.example.bespring.repository.AlunoRepository;
import com.example.bespring.repository.UtilizadorRepository;
import com.example.bespring.security.TokenService;
import com.example.bespring.services.TurmaService;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TurmaController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TurmaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TurmaService turmaService;

    @MockitoBean
    private TokenService tokenService;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @MockitoBean
    private UtilizadorRepository utilizadorRepository;

    @MockitoBean
    private AlunoRepository alunoRepository;

    @Test
    @WithMockUser(username = "teste")
    void deveCriarTurma() throws Exception {
        Turma turma = new Turma();
        turma.setNome("Rosas");
        turma.setSala("2B");
        turma.setAnoLetivo(2026);
        turma.setAnoSerie("3º");
        turma.setDescricao("Turma das rosas");

        long idProfessor = 1L;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
        escola.setIdEscola(1L);

        Professor professor = new Professor("Bruna", "Asa", "993838844", Genero.FEMININO, "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,  Perfil.PROFESSOR, escola);
        professor.setIdUtilizador(1L);

        Aluno aluno1 = new Aluno("Bahati", "Albertina", "AAl01", 12,
                "2B", "Azul", false, Genero.FEMININO, escola, Perfil.ALUNO,
                "122334", null,  professor);
        aluno1.setIdUtilizador(1L);

        Aluno aluno2 = new Aluno("Eshe", "Carmen", "Esh02", 15,
                "2B", "Rosa", true, Genero.FEMININO, escola, Perfil.ALUNO,
                "123456", null,  professor);
        aluno2.setIdUtilizador(2L);

        turma.setProfessor(professor);
        turma.setAlunos(List.of(aluno1, aluno2));

        CriarTurmaRequest turmaRequest = new CriarTurmaRequest(
                turma.getNome(),
                turma.getSala(),
                turma.getAnoLetivo(),
                turma.getAnoSerie(),
                turma.getDescricao(),
                turma.getAlunos().stream()
                        .map(Aluno::getIdUtilizador)
                        .toList()
        );

        when(turmaService.criarTurma(any(CriarTurmaRequest.class),
                any(Long.class))).thenReturn(turma);

        String json = objectMapper.writeValueAsString(turmaRequest);

        //Simular a requisição HTTP
        mockMvc.perform(post("/api/turmas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))

                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idTurma").value(1L))
                .andExpect(jsonPath("$.Nome").value("Rosa"))
                .andExpect(jsonPath("$.Sala").value("2B"))
                .andExpect(jsonPath("$.AnoLetivo").value(2026))
                .andExpect(jsonPath("$.AnoSerie").value("3º"))
                .andExpect(jsonPath("$.Descricao").value("Turma das rosas"))
                .andExpect(jsonPath("$.Alunos").value(List.of(aluno1, aluno2)));


               verify(turmaService).criarTurma(any(CriarTurmaRequest.class), idProfessor);


    }


}

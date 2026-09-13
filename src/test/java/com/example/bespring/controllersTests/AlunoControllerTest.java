package com.example.bespring.controllersTests;

import com.example.bespring.controllers.AlunoController;
import com.example.bespring.domain.Aluno;
import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Professor;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.domain.enums.TipoProfissional;
import com.example.bespring.dto.CriarAlunoRequest;
import com.example.bespring.dto.CriarProfessorRequest;
import com.example.bespring.repository.AlunoRepository;
import com.example.bespring.repository.UtilizadorRepository;
import com.example.bespring.security.TokenService;
import com.example.bespring.services.AlunoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AlunoController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AlunoService alunoService;

    @MockitoBean
    private TokenService tokenService;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @MockitoBean
    private UtilizadorRepository utilizadorRepository;

    @Test
    void deveRegistrarAluno() throws Exception {

        Aluno aluno = new Aluno();
        aluno.setPrimeiroNome("Ana");
        aluno.setSobrenome("Albertina");
        aluno.setNomeUtilizador("AAl01");
        aluno.setNumeroAluno(12);
        aluno.setSala("2B");
        aluno.setCorFavorita("Azul");
        aluno.setPossuiDaltonismo(false);
        aluno.setGenero(Genero.FEMININO);
        aluno.setSenha("123456");

        Long idProfessor = 1L;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
        escola.setIdEscola(1L);

        Professor professor = new Professor("Bruna", "Asa", "993838844", Genero.FEMININO, "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,  Perfil.PROFESSOR, escola);
        professor.setIdUtilizador(1L);
        aluno.setProfessor(professor);

        aluno.setEscola(escola);

        CriarAlunoRequest request = new CriarAlunoRequest(
                aluno.getPrimeiroNome(),
                aluno.getSobrenome(),
                aluno.getNomeUtilizador(),
                aluno.getNumeroAluno(),
                aluno.getSala(),
                aluno.getCorFavorita(),
                aluno.isPossuiDaltonismo(),
                aluno.getGenero(),
                aluno.getSenha(),
                aluno.getEscola().getIdEscola()
        );

        when(alunoService.registarAluno(any(CriarAlunoRequest.class), any(Long.class))).thenReturn(aluno);

        String json = objectMapper.writeValueAsString(request);

        //Simular uma requisição HTTP.
        mockMvc.perform(post("/api/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))

                //Verificação do resultado
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idUtilizador").value(1L))
                .andExpect(jsonPath("$.primeiroNome").value("Akin"))
                .andExpect(jsonPath("$.sobrenome").value("Asa"))
                .andExpect(jsonPath("$.nomeUtilizador").value("AAl01"))
                .andExpect(jsonPath("$.numeroAluno").value(12))
                .andExpect(jsonPath("$.sala").value("2B"))
                .andExpect(jsonPath("$.corFavorita").value("Azul"))
                .andExpect(jsonPath("$.possuiDaltonismo").value(false))
                .andExpect(jsonPath("$.genero").value(Genero.FEMININO))
                .andExpect(jsonPath("$.idEscola").value(escola.getIdEscola()));

        verify(alunoService).registarAluno(any(CriarAlunoRequest.class), idProfessor);
    }

}

package com.example.bespring.modulosTest.utilizadoresTest.controllersTests;

import com.example.bespring.modulosTest.utilizadoresTest.controllers.AlunoController;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Aluno;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Escola;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Professor;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Utilizador;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.TipoProfissional;
import com.example.bespring.modulosTest.utilizadoresTest.dto.AtualizarAlunoRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarAlunoRequest;
import com.example.bespring.modulosTest.utilizadoresTest.repository.ProfessorRepository;
import com.example.bespring.modulosTest.utilizadoresTest.repository.UtilizadorRepository;
import com.example.bespring.security.TokenService;
import com.example.bespring.modulosTest.utilizadoresTest.services.AlunoService;
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

    @MockitoBean
    private ProfessorRepository professorRepository;


    @Test
    @WithMockUser(username = "bruna@gmail.com")
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

        Utilizador admin = new Utilizador("Marta", "KingStone", Genero.FEMININO,
                LocalDate.of(1999,10,2), Perfil.ADMIN,"raquel@gmail.com", "maura12#",
                EstadoConta.ATIVA, null, null);
        admin.setIdUtilizador(1L);

        Utilizador adminEscola = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                LocalDate.of(2004,1,5), Perfil.ADMIN_ESCOLA,"raquel@gmail.com", "maura1234",
                EstadoConta.ATIVA, null, admin);
        adminEscola.setIdUtilizador(2L);

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
        escola.setIdEscola(1L);
        aluno.setEscola(escola);

        Professor professor = new Professor("92342A3444", "Bruna", "Asa", "993838844", Genero.FEMININO,
                LocalDate.of(1999,1,2), "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,
                Perfil.PROFESSOR, escola, EstadoConta.ATIVA, null, adminEscola);

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

    @Test
    @WithMockUser(username = "bruna@gmail.com")
    void deveListarAlunos() throws Exception {

        Aluno aluno1 = new Aluno();

        aluno1.setPrimeiroNome("Ana");
        aluno1.setSobrenome("Albertina");
        aluno1.setNomeUtilizador("AAl01");
        aluno1.setNumeroAluno(12);
        aluno1.setSala("2B");
        aluno1.setCorFavorita("Azul");
        aluno1.setPossuiDaltonismo(false);
        aluno1.setGenero(Genero.FEMININO);
        aluno1.setSenha("123456");

        Long idProfLogado = 1L;

        Utilizador admin = new Utilizador("Marta", "KingStone", Genero.FEMININO,
                LocalDate.of(1999,10,2), Perfil.ADMIN,"raquel@gmail.com", "maura12#",
                EstadoConta.ATIVA, null, null);
        admin.setIdUtilizador(1L);

        Utilizador adminEscola = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                LocalDate.of(2004,1,5), Perfil.ADMIN_ESCOLA,"raquel@gmail.com", "maura1234",
                EstadoConta.ATIVA, null, admin);
        adminEscola.setIdUtilizador(2L);

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
        escola.setIdEscola(1L);
        aluno1.setEscola(escola);

        Professor professor = new Professor("92342A3444", "Bruna", "Asa", "993838844", Genero.FEMININO,
                LocalDate.of(1999,1,2), "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,
                Perfil.PROFESSOR, escola, EstadoConta.ATIVA, null, adminEscola);

        professor.setIdUtilizador(1L);
        aluno1.setProfessor(professor);


        Aluno aluno2 = new Aluno();

        aluno2.setPrimeiroNome("Bella");
        aluno2.setSobrenome("Albert");
        aluno2.setNomeUtilizador("Bb02");
        aluno2.setNumeroAluno(10);
        aluno2.setSala("2B");
        aluno2.setCorFavorita("Rosa");
        aluno2.setPossuiDaltonismo(true);
        aluno2.setGenero(Genero.FEMININO);
        aluno2.setSenha("1238990");

        aluno2.setEscola(escola);
        aluno2.setProfessor(professor);

        List<Aluno> alunos = List.of(aluno1, aluno2);

        when(alunoService.listarAlunos(idProfLogado)).thenReturn(alunos);

        //Simular uma requisição HTTP.
        mockMvc.perform(get("/api/alunos"))

                //Verificação do resultado.
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].idUtilizador").value(1L))
                .andExpect(jsonPath("$[0].primeiroNome").value("Ana"))
                .andExpect(jsonPath("$[0].sobrenome").value("Albertina"))
                .andExpect(jsonPath("$[0].nomeUtilizador").value("AAl01"))
                .andExpect(jsonPath("$[0].numeroAluno").value(12))
                .andExpect(jsonPath("$[0].sala").value("2B"))
                .andExpect(jsonPath("$[0].corFavorita").value("Azul"))
                .andExpect(jsonPath("$[0].possuiDaltonismo").value(false))
                .andExpect(jsonPath("$[0].genero").value(Genero.FEMININO))
                .andExpect(jsonPath("$[0].idEscola").value(escola.getIdEscola()))
                .andExpect(jsonPath("$[1].idUtilizador").value(1L))
                .andExpect(jsonPath("$[1].primeiroNome").value("Bella"))
                .andExpect(jsonPath("$[1].sobrenome").value("Albert"))
                .andExpect(jsonPath("$[1].nomeUtilizador").value("Bb02"))
                .andExpect(jsonPath("$[1].numeroAluno").value(10))
                .andExpect(jsonPath("$[1].sala").value("2B"))
                .andExpect(jsonPath("$[1].corFavorita").value("Rosa"))
                .andExpect(jsonPath("$[1].possuiDaltonismo").value(true))
                .andExpect(jsonPath("$[1].genero").value(Genero.FEMININO))
                .andExpect(jsonPath("$[1].idEscola").value(escola.getIdEscola()));

        verify(alunoService).listarAlunos(idProfLogado);

    }


    @Test
    void deveProcurarAlunoPorId() throws Exception {

        Aluno aluno = new Aluno();
        aluno.setIdUtilizador(1L);

        long idProfLogado = 2L;
        long idAluno = 1L;

        Utilizador admin = new Utilizador("Marta", "KingStone", Genero.FEMININO,
                LocalDate.of(1999,10,2), Perfil.ADMIN,"raquel@gmail.com", "maura12#",
                EstadoConta.ATIVA, null, null);
        admin.setIdUtilizador(1L);

        Utilizador adminEscola = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                LocalDate.of(2004,1,5), Perfil.ADMIN_ESCOLA,"raquel@gmail.com", "maura1234",
                EstadoConta.ATIVA, null, admin);
        adminEscola.setIdUtilizador(2L);

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
        escola.setIdEscola(1L);
        aluno.setEscola(escola);

        Professor professor = new Professor("92342A3444", "Bruna", "Asa", "993838844", Genero.FEMININO,
                LocalDate.of(1999,1,2), "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,
                Perfil.PROFESSOR, escola, EstadoConta.ATIVA, null, adminEscola);
        professor.setIdUtilizador(2L);
        aluno.setProfessor(professor);

        when(alunoService.procurarAlunoPorId(idAluno, idProfLogado)).thenReturn(aluno);

        mockMvc.perform(get("/api/alunos/{idUtilizador}", idAluno))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUtilizador").value(1L))
                .andExpect(jsonPath("$.idEscola").value(escola.getIdEscola()));


        verify(alunoService).procurarAlunoPorId(idAluno, idProfLogado);

    }

    @Test
    @WithMockUser(username = "zaburi12@gmail.com", roles = {"PROFESSOR"})
    void deveAtualizarAluno() throws Exception{

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

        Long idProfLogado = 1L;
        long idAluno = 1L;

        Utilizador admin = new Utilizador("Marta", "KingStone", Genero.FEMININO,
                LocalDate.of(1999,10,2), Perfil.ADMIN,"raquel@gmail.com", "maura12#",
                EstadoConta.ATIVA, null, null);
        admin.setIdUtilizador(1L);

        Utilizador adminEscola = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                LocalDate.of(2004,1,5), Perfil.ADMIN_ESCOLA,"raquel@gmail.com", "maura1234",
                EstadoConta.ATIVA, null, admin);
        adminEscola.setIdUtilizador(2L);

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
        escola.setIdEscola(1L);
        aluno.setEscola(escola);

        Professor professor = new Professor("92342A3444", "Bruna", "Asa", "993838844", Genero.FEMININO,
                LocalDate.of(1999,1,2), "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,
                Perfil.PROFESSOR, escola, EstadoConta.ATIVA, null, adminEscola);
        professor.setIdUtilizador(1L);
        aluno.setProfessor(professor);

        AtualizarAlunoRequest alunoRequest = new AtualizarAlunoRequest(
                aluno.getPrimeiroNome(),
                aluno.getSobrenome(),
                aluno.getNomeUtilizador(),
                aluno.getNumeroAluno(),
                aluno.getSala(),
                aluno.getCorFavorita(),
                aluno.isPossuiDaltonismo(),
                aluno.getGenero(),
                aluno.getSenha()
        );

        doNothing().when(alunoService).atualizarAluno(idAluno, alunoRequest, idProfLogado);

        String json = objectMapper.writeValueAsString(alunoRequest);

        mockMvc.perform(put("/api/alunos/{idUtilizador}", idAluno)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))

                .andExpect(status().isNoContent());

        verify(alunoService).atualizarAluno(idAluno, alunoRequest, idProfLogado);
    }


    @Test
    void deveApagarAlunoPorId() throws Exception {

        Aluno aluno = new Aluno();
        aluno.setIdUtilizador(1L);
        long idProfLogado = 2L;
        long idAluno = 1L;

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
        escola.setIdEscola(1L);
        aluno.setEscola(escola);

        Professor professor = new Professor();
        professor.setIdUtilizador(2L);
        aluno.setProfessor(professor);

        doNothing().when(alunoService).apagarAluno(idAluno, idProfLogado);

        mockMvc.perform(delete("/api/alunos/{idUtilizador}", idAluno))
                .andExpect(status().isNoContent());


        verify(alunoService).apagarAluno(idAluno, idProfLogado);

    }




}

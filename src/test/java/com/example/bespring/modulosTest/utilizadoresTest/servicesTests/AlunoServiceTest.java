package com.example.bespring.modulosTest.utilizadoresTest.servicesTests;

import com.example.bespring.modulosTest.utilizadoresTest.domain.*;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.TipoProfissional;
import com.example.bespring.modulosTest.utilizadoresTest.dto.AtualizarAlunoRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarAlunoRequest;
import com.example.bespring.modulosTest.utilizadoresTest.repository.*;
import com.example.bespring.modulosTest.utilizadoresTest.services.AlunoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    @Mock
    private ProfessorRepository professorRepository;

    @Mock
    private EscolaRepository escolaRepository;

    @Mock
    private TurmaRepository turmaRepository;

    @Mock
    private UtilizadorRepository utilizadorRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Nested
    class registarAluno {

        @Test
        @DisplayName("Deve registar uma aluno")
        void deveRegistarUmAluno(){

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

            aluno.setEscola(escola);

            when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));

            when(escolaRepository.findById(1L)).thenReturn(Optional.of(escola));

            when(alunoRepository.save(any(Aluno.class))).thenAnswer(
                    invocation -> {
                        Aluno aluno1 = invocation.getArgument(0);
                        aluno1.setIdUtilizador(1L);
                        return aluno1;
                    });

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

            var result = alunoService.registarAluno(request, idProfessor);


            assertNotNull(result);
            assertEquals(1L, result.getIdUtilizador());
            assertEquals(aluno.getPrimeiroNome(), result.getPrimeiroNome());
            assertEquals(aluno.getSobrenome(), result.getSobrenome());
            assertEquals(aluno.getNomeUtilizador(), result.getNomeUtilizador());
            assertEquals(aluno.getNumeroAluno(), result.getNumeroAluno());
            assertEquals(aluno.getSala(), result.getSala());
            assertEquals(aluno.getCorFavorita(), result.getCorFavorita());
            assertEquals(aluno.isPossuiDaltonismo(), result.isPossuiDaltonismo());
            assertEquals(aluno.getGenero(), result.getGenero());
            assertTrue(passwordEncoder.matches(aluno.getSenha(), result.getSenha()));
            assertEquals(1L, result.getEscola().getIdEscola());

            verify(escolaRepository).findById(1L);

            verify(alunoRepository).save(any(Aluno.class));
        }

    }

    @Nested
    class listarAlunos{

        @Test
        @DisplayName("Deve listar todos os alunos")
        void deveListarTodosAlunos(){

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

            aluno1.setProfessor(professor);

            Turma turma = new Turma("Flor", "4B", 2026, "3º", "Flores verdes", professor);

            aluno1.setTurma(turma);

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
            aluno2.setTurma(turma);

            List<Aluno> alunos = List.of(aluno1, aluno2);

            //when(alunoRepository.findAll()).thenReturn(alunos);

            when(alunoRepository.findByProfessor_idUtilizador(idProfLogado)).thenReturn(alunos);

            List<Aluno> result = alunoService.listarAlunos(idProfLogado);

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(alunos, result);

            verify(alunoRepository).findByProfessor_idUtilizador(idProfLogado);
            //verify(alunoRepository).findAll();

        }

    }

    @Nested
    class buscarAluno{

        @Test
        @DisplayName("Deve buscar por um aluno")
        void deveBuscarUmAlunoPeloId(){

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
            aluno.setProfessor(professor);


            //Arrange - Preparar
            when(alunoRepository.findById(idAluno)).thenReturn(Optional.of(aluno));

            //when(alunoRepository.findById(idProfLogado)).thenReturn(Optional.of(aluno));

            //Act - Executar
            var result = alunoService.procurarAlunoPorId(idAluno, idProfLogado);

            //Assert - Verificar resultado
            assertNotNull(result);
            assertEquals(idAluno, result.getIdUtilizador());

        }



    }

    @Nested
    class atualizarAluno{

        @Test
        void deveAtualizarAluno(){

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

            when(alunoRepository.findById(idAluno)).thenReturn(Optional.of(aluno));

            when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);

            alunoService.atualizarAluno(idAluno, alunoRequest, idProfLogado);

            verify(alunoRepository).findById(idAluno);
            verify(alunoRepository).save(aluno);

        }

    }

    @Nested
    class apagarAluno{

        @Test
        void deveApagarAluno(){

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

            when(alunoRepository.findById(idAluno)).thenReturn(Optional.of(aluno));

            alunoService.apagarAluno(idAluno, idProfLogado);

            verify(alunoRepository).findById(idAluno);

        }

    }

}

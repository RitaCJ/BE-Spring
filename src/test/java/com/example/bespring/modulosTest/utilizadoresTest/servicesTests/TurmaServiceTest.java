package com.example.bespring.modulosTest.utilizadoresTest.servicesTests;

import com.example.bespring.modulosTest.utilizadoresTest.domain.*;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.TipoProfissional;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarTurmaRequest;
import com.example.bespring.modulosTest.utilizadoresTest.repository.*;
import com.example.bespring.modulosTest.utilizadoresTest.services.TurmaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TurmaServiceTest {

    @Mock
    TurmaRepository turmaRepository;

    @InjectMocks
    TurmaService turmaService;

    @Mock
    EscolaRepository escolaRepository;

    @Mock
    UtilizadorRepository utilizadorRepository;

    @Mock
    ProfessorRepository professorRepository;

    @Mock
    AlunoRepository alunoRepository;

    @Nested
    class criarTurma{

        @Test
        @DisplayName("deve criar uma turma")
        void deveCriarUmaNovaTurma(){

            Turma turma = new Turma();

            turma.setNome("Rosas");
            turma.setSala("2B");
            turma.setAnoLetivo(2026);
            turma.setAnoSerie("3º");
            turma.setDescricao("Turma das rosas");

            long idProfessor = 1L;

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

            Professor professor = new Professor("92342A3444", "Bruna", "Asa", "993838844", Genero.FEMININO,
                    LocalDate.of(1999,1,2), "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,
                    Perfil.PROFESSOR, escola, EstadoConta.ATIVA, null, adminEscola);

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

            when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));

            when(alunoRepository.findAllById(turmaRequest.idAlunos())).thenReturn(List.of(aluno1, aluno2));


            when(turmaRepository.save(any(Turma.class))).thenAnswer(
                    invocation -> {
                        Turma turma1 = invocation.getArgument(0);
                        turma1.setIdTurma(1L);
                        return turma1;
                    }
            );

            var result = turmaService.criarTurma(turmaRequest, idProfessor);

            aluno1.setTurma(result);
            aluno2.setTurma(result);

            assertNotNull(result);
            assertEquals(1L, result.getIdTurma());
            assertEquals(turma.getNome(), result.getNome());
            assertEquals(turma.getSala(), result.getSala());
            assertEquals(turma.getAnoLetivo(), result.getAnoLetivo());
            assertEquals(turma.getAnoSerie(), result.getAnoSerie());
            assertEquals(turma.getDescricao(), result.getDescricao());
            assertEquals(2, result.getAlunos().size());
            assertEquals(1L, result.getAlunos().get(0).getIdUtilizador());
            assertEquals(aluno2.getIdUtilizador(), result.getAlunos().get(1).getIdUtilizador());

            verify(professorRepository).findById(1L);

            verify(alunoRepository).findAllById(turmaRequest.idAlunos());

            verify(turmaRepository).save(any(Turma.class));

        }
    }
}

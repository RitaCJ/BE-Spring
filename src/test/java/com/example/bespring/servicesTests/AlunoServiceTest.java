package com.example.bespring.servicesTests;

import com.example.bespring.domain.Aluno;
import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Professor;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.domain.enums.TipoProfissional;
import com.example.bespring.dto.CriarAlunoRequest;
import com.example.bespring.repository.*;
import com.example.bespring.services.AlunoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        @DisplayName("Um professor deve registar uma aluno")
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

            Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
            escola.setIdEscola(1L);

            Professor professor = new Professor("Bruna", "Asa", "993838844", Genero.FEMININO, "bruna@gmail.com", "@Bemvindo123", TipoProfissional.PROFESSOR,  Perfil.PROFESSOR, escola);
            professor.setIdUtilizador(1L);
            aluno.setProfessor(professor);

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

}

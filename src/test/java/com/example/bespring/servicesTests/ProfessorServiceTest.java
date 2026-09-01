package com.example.bespring.servicesTests;

import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Professor;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.domain.enums.TipoProfissional;
import com.example.bespring.dto.CriarProfessorRequest;
import com.example.bespring.repository.EscolaRepository;
import com.example.bespring.repository.ProfessorRepository;
import com.example.bespring.services.ProfessorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfessorServiceTest {

    @Mock
    private ProfessorRepository professorRepository;

    @InjectMocks
    private ProfessorService professorService;

    @Mock
    private EscolaRepository escolaRepository;

    //Subclass
    @Nested
    class criarProfessor{

        @Test
        @DisplayName("Deve criar um novo professor")
        void deveCriarUmNovoProfessor(){

            //Arrange
            Professor professor = new Professor();
            professor.setPrimeiroNome("Akin");
            professor.setSobrenome("Asa");
            professor.setEmail("asa@gmail.com");
            professor.setTelefone("123456789");
            professor.setSenha("@MarEMar2000");
            professor.setTipo(TipoProfissional.PROFESSOR);
            professor.setGenero(Genero.MASCULINO);
            professor.setPerfil(Perfil.PROFESSOR);

            Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
            escola.setIdEscola(1L);
            professor.setEscola(escola);

            //Mock sobre a "procura" da escola.
            when(escolaRepository.findById(1L)).thenReturn(Optional.of(escola));

            //O Mock para o "save"
            when(professorRepository.save(any(Professor.class))).thenAnswer(
                    invocation -> {
                        Professor professor1 = invocation.getArgument(0);
                        professor1.setIdUtilizador(1L);
                        return professor1;
                    });

            //Act
            CriarProfessorRequest request = new CriarProfessorRequest(
                    professor.getPrimeiroNome(),
                    professor.getSobrenome(),
                    professor.getTelefone(),
                    professor.getGenero(),
                    professor.getEmail(),
                    professor.getSenha(),
                    professor.getTipo(),
                    professor.getPerfil(),
                    professor.getEscola().getIdEscola()
            );

            Professor result = professorService.cadastrarProfessor(request);

            //Assert
            assertNotNull(result);
            assertEquals(1L, result.getIdUtilizador());
            //assertEquals(professor.getIdUtilizador(), result.getIdUtilizador());
            assertEquals(professor.getPrimeiroNome(), result.getPrimeiroNome());
            assertEquals(professor.getSobrenome(), result.getSobrenome());
            assertEquals(professor.getEmail(), result.getEmail());
            assertEquals(professor.getTelefone(), result.getTelefone());
            assertEquals(professor.getSenha(), result.getSenha());
            assertEquals(professor.getTipo(), result.getTipo());
            assertEquals(professor.getGenero(), result.getGenero());
            assertEquals(professor.getPerfil(), result.getPerfil());
            assertEquals(1L, result.getEscola().getIdEscola());

            //Verificar se procurou pela escola.
            verify(escolaRepository).findById(1L);

            //Verificar se guardou o professor
            verify(professorRepository).save(any(Professor.class));

        }

    }

    @Nested
    class procurarProfessorPeloId{

        @Test
        @DisplayName("Deve buscar por um professor")
        void deveBuscarPorUmProfessor(){

            Professor professor = new Professor();
            professor.setIdUtilizador(1L);
            long id = 1L;

            //Arrange - Preparar
            when(professorRepository.findById(id)).thenReturn(Optional.of(professor));

            //Act - Executar
            var result = professorService.procurarProfessorPorId(id);

            //Assert - Verificar resultado
            assertNotNull(result);
            assertEquals(id, result.getIdUtilizador());

        }

    }

    @Nested
    class listarProfessor{

        @Test
        @DisplayName("Deve listar todos os professores")
        void deveListarTodosProfessores(){

            Professor professor1 = new Professor();
            professor1.setIdUtilizador(1L);
            professor1.setPrimeiroNome("Akin");
            professor1.setSobrenome("Asa");
            professor1.setEmail("asa@gmail.com");
            professor1.setTelefone("123456789");
            professor1.setSenha("@MarEMar2000");
            professor1.setTipo(TipoProfissional.PROFESSOR);
            professor1.setGenero(Genero.MASCULINO);
            professor1.setPerfil(Perfil.PROFESSOR);

            Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
            escola.setIdEscola(1L);
            professor1.setEscola(escola);

            Professor professor2 = new Professor();
            professor2.setIdUtilizador(1L);
            professor2.setPrimeiroNome("Lua");
            professor2.setSobrenome("Luna");
            professor2.setEmail("lua@gmail.com");
            professor2.setTelefone("923234323");
            professor2.setSenha("LunarELua4000#");
            professor2.setTipo(TipoProfissional.PROFESSOR);
            professor2.setGenero(Genero.FEMININO);
            professor2.setPerfil(Perfil.PROFESSOR);

            escola.setIdEscola(1L);
            professor2.setEscola(escola);

            when(professorRepository.findAll()).thenReturn(
                    List.of(professor1, professor2)
            );

            var result = professorService.listarProfessores();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertTrue(result.contains(professor1));
            assertTrue(result.contains(professor2));
        }
    }


}

package com.example.bespring.servicesTests;

import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Professor;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.TipoProfissional;
import com.example.bespring.dto.CriarProfessorRequest;
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


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfessorServiceTest {

    @Mock
    private ProfessorRepository professorRepository;

    @InjectMocks
    private ProfessorService professorService;

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

            Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

            professor.setEscola(escola);

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
                    professor.getEscola()
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
            assertEquals(professor.getEscola(), result.getEscola());

        }


    }


}

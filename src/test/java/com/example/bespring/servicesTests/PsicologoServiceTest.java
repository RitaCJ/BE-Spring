package com.example.bespring.servicesTests;

import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Psicologo;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.domain.enums.TipoProfissional;
import com.example.bespring.dto.CriarProfessorRequest;
import com.example.bespring.dto.CriarPsicologoRequest;
import com.example.bespring.repository.EscolaRepository;
import com.example.bespring.repository.PsicologoRepository;
import com.example.bespring.repository.UtilizadorRepository;
import com.example.bespring.services.PsicologoService;
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
public class PsicologoServiceTest {

    @Mock
    private PsicologoRepository psicologoRepository;

    @InjectMocks
    private PsicologoService psicologoService;

    @Mock
    private EscolaRepository escolaRepository;

    @Mock
    private UtilizadorRepository utilizadorRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Nested
    class criarPsicologo{

        @Test
        @DisplayName("Deve criar uma psicólogo.")
        void deveCriarUmNovoPsicologo(){

            //Arrange
            Psicologo psicologo = new Psicologo();
            psicologo.setPrimeiroNome("Bahati");
            psicologo.setSobrenome("Luck");
            psicologo.setEmail("luck@gmail.com");
            psicologo.setTelefone("153657789");
            psicologo.setSenha("@Tailong20004");
            psicologo.setTipo(TipoProfissional.PSICOLOGO);
            psicologo.setGenero(Genero.FEMININO);
            psicologo.setPerfil(Perfil.PSICOLOGO);

            Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");

            escola.setIdEscola(1L);

            psicologo.setEscola(escola);

            when(escolaRepository.findById(1L)).thenReturn(Optional.of(escola));

            when(psicologoRepository.save(any(Psicologo.class))).thenAnswer(
                    invocation -> {
                        Psicologo psicologo1 = invocation.getArgument(0);
                        psicologo1.setIdUtilizador(1L);
                        return psicologo1;
                    });
            //Act
            CriarPsicologoRequest request = new CriarPsicologoRequest(
                    psicologo.getPrimeiroNome(),
                    psicologo.getSobrenome(),
                    psicologo.getTelefone(),
                    psicologo.getGenero(),
                    psicologo.getEmail(),
                    psicologo.getSenha(),
                    psicologo.getTipo(),
                    psicologo.getEscola().getIdEscola()
            );

            Psicologo result = psicologoService.cadastrarPsicologo(request);

            assertNotNull(request);
            assertEquals(1L, result.getIdUtilizador());
            assertEquals(psicologo.getPrimeiroNome(), result.getPrimeiroNome());
            assertEquals(psicologo.getSobrenome(), result.getSobrenome());
            assertEquals(psicologo.getEmail(), result.getEmail());
            assertTrue(passwordEncoder.matches(psicologo.getSenha(), result.getSenha()));
            assertEquals(psicologo.getGenero(), result.getGenero());
            assertEquals(psicologo.getTipo(), result.getTipo());
            assertEquals(1L, result.getEscola().getIdEscola());

            verify(escolaRepository).findById(1L);

            verify(psicologoRepository).save(any(Psicologo.class));

        }

    }

    @Nested
    class ProcurarPsicologo{
        @Test
        @DisplayName("Deve procurar um psicologo pelo ID")
        void deveProcurarUmNovoPsicologo(){

            Psicologo psicologo = new Psicologo();
            psicologo.setIdUtilizador(1L);
            psicologo.setEmail("luck@gmail.com");
            long id = 1L;

            when(psicologoRepository.findById(id)).thenReturn(Optional.of(psicologo));

            String utilizadorLogado = psicologo.getEmail();

            var result = psicologoService.procurarPsicologoPeloId(id,utilizadorLogado);

            assertNotNull(result);
            assertEquals(id, result.getIdUtilizador());
        }
    }


    @Nested
    class atualizarPsicologo{

    }

    @Nested
    class detelarPsicologo{

    }


}

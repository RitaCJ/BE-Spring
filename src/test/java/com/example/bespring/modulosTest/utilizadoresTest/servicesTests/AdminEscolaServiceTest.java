package com.example.bespring.modulosTest.utilizadoresTest.servicesTests;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Escola;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Utilizador;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.dto.AtualizarAdminEscolaRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarAdminEscolaRequest;
import com.example.bespring.modulosTest.utilizadoresTest.repository.EscolaRepository;
import com.example.bespring.modulosTest.utilizadoresTest.repository.UtilizadorRepository;
import com.example.bespring.modulosTest.utilizadoresTest.services.AdminEscolaService;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminEscolaServiceTest {

    @Mock
    private UtilizadorRepository userRepository;

    @InjectMocks
    private AdminEscolaService adminEscolaService;

    @Mock
    private EscolaRepository escolaRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Nested
    class registarAdminEscola{

        @Test
        @DisplayName("Deve registar um administrador de escola")
        void deveRegistarAdminEscola(){

            Utilizador utilizador = new Utilizador();
            utilizador.setPrimeiroNome("Zuri");
            utilizador.setSobrenome("Jorge");
            utilizador.setGenero(Genero.FEMININO);
            utilizador.setDataNascimento(LocalDate.of(1999, 4, 3));
            utilizador.setLogin("admin");
            utilizador.setSenha("admin1234#");
            utilizador.setEstadoConta(EstadoConta.ATIVA);

            Long idAdminLogado = 1L;

            Escola escola = new Escola("School", "Rua francisco da silva", "923423444", "school@gmail.com");
            escola.setIdEscola(1L);
            utilizador.setEscola(escola);

            Utilizador admin = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                    LocalDate.of(2004,1,5), Perfil.ADMIN,"raquel@gmail.com", "maura1234", EstadoConta.ATIVA, null, null);
            admin.setIdUtilizador(1L);

            utilizador.setCriadoPor(admin);

            when(escolaRepository.findById(1L)).thenReturn(Optional.of(escola));
            when(userRepository.findById(1L)).thenReturn(Optional.of(admin));
            when(userRepository.save(any(Utilizador.class))).thenAnswer(
                    invocation -> {
                        Utilizador user = invocation.getArgument(0);
                        user.setIdUtilizador(1L);
                        return user;
                    }
            );

            CriarAdminEscolaRequest request = new CriarAdminEscolaRequest(
                    utilizador.getPrimeiroNome(),
                    utilizador.getSobrenome(),
                    utilizador.getGenero(),
                    utilizador.getDataNascimento(),
                    utilizador.getLogin(),
                    utilizador.getSenha(),
                    utilizador.getEscola().getIdEscola()
            );

            var result = adminEscolaService.registarAdminEscola(request, idAdminLogado);

            assertNotNull(result);
            assertEquals(1L, result.getIdUtilizador());
            assertEquals(utilizador.getPrimeiroNome(), result.getPrimeiroNome());
            assertEquals(utilizador.getSobrenome(), result.getSobrenome());
            assertEquals(utilizador.getGenero(), result.getGenero());
            assertEquals(utilizador.getDataNascimento(), result.getDataNascimento());
            assertEquals(utilizador.getLogin(), result.getLogin());
            assertTrue(passwordEncoder.matches(utilizador.getSenha(), result.getSenha()));
            assertEquals(1L, result.getEscola().getIdEscola());



            verify(userRepository).findById(1L);
            verify(userRepository).save(any(Utilizador.class));

        }
    }

    @Nested
    class buscarAdminEscola{

        @Test
        void deveBuscarAdminEscolaPeloId(){

            Utilizador userAdminEscola = new Utilizador();
            userAdminEscola.setIdUtilizador(1L);

            long idAdminLogado = 1L;
            long idUser = 1L;

            Escola escola = new Escola("School", "Rua francisco da silva", "923423444", "school@gmail.com");
            escola.setIdEscola(1L);
            userAdminEscola.setEscola(escola);

            Utilizador admin = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                    LocalDate.of(2004,1,5), Perfil.ADMIN,"raquel@gmail.com", "maura1234", EstadoConta.ATIVA, null, null);
            admin.setIdUtilizador(1L);

            userAdminEscola.setCriadoPor(admin);

            when(userRepository.findById(idUser)).thenReturn(Optional.of(userAdminEscola));

            var result = adminEscolaService.procurarAdminEscola(idUser, idAdminLogado);

            assertNotNull(result);
            assertEquals(idUser, result.getIdUtilizador());

            verify(userRepository).findById(idUser);
        }
    }

    @Nested
    class atualizarAdminEscola{

        @Test
        void deveAtualizarAdminEscola(){
            Utilizador utilizador = new Utilizador();
            utilizador.setPrimeiroNome("Zuri");
            utilizador.setSobrenome("Jorge");
            utilizador.setGenero(Genero.FEMININO);
            utilizador.setSenha("123456");
            utilizador.setLogin("admin@gmail.com");
            utilizador.setIdUtilizador(1L);

            long idAdminLogado = 1L;
            long userAdminEscola = 1L;

            Escola escola = new Escola("School", "Rua francisco da silva", "923423444", "school@gmail.com");
            escola.setIdEscola(1L);
            utilizador.setEscola(escola);

            Utilizador admin = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                    LocalDate.of(2004,1,5), Perfil.ADMIN,"raquel@gmail.com", "maura1234", EstadoConta.ATIVA, null, null);
            admin.setIdUtilizador(1L);

            utilizador.setCriadoPor(admin);

            AtualizarAdminEscolaRequest request = new AtualizarAdminEscolaRequest(
                    utilizador.getPrimeiroNome(),
                    utilizador.getSobrenome(),
                    utilizador.getGenero(),
                    utilizador.getSenha(),
                    utilizador.getLogin()
            );

            when(userRepository.findById(userAdminEscola)).thenReturn(Optional.of(utilizador));

            when(userRepository.save(any(Utilizador.class))).thenReturn(utilizador);

            adminEscolaService.atualizarAdminEscola(userAdminEscola, request, idAdminLogado);

            verify(userRepository).findById(userAdminEscola);
            verify(userRepository).save(utilizador);
        }
    }

    @Nested
    class apagarAdminEscola{
        @Test
        void deveApagarAdminEscola(){

            Utilizador utilizador = new Utilizador();
            utilizador.setIdUtilizador(1L);

            long idAdminLogado = 1L;
            long idUser = 1L;

            Escola escola = new Escola("School", "Rua francisco da silva", "923423444", "school@gmail.com");
            escola.setIdEscola(1L);
            utilizador.setEscola(escola);

            Utilizador admin = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                    LocalDate.of(2004,1,5), Perfil.ADMIN,"raquel@gmail.com", "maura1234", EstadoConta.ATIVA, null, null);
            admin.setIdUtilizador(1L);

            utilizador.setCriadoPor(admin);

            when(userRepository.findById(idUser)).thenReturn(Optional.of(utilizador));

            adminEscolaService.apagarAdminEscola(idUser, idAdminLogado);

            verify(userRepository).findById(idUser);

        }
    }



}

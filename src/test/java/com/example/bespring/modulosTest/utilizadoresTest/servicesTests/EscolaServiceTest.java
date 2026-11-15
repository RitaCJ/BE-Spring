package com.example.bespring.modulosTest.utilizadoresTest.servicesTests;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Escola;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Utilizador;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Genero;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.dto.AtualizarEscolaRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarEscolaRequest;
import com.example.bespring.modulosTest.utilizadoresTest.repository.EscolaRepository;
import com.example.bespring.modulosTest.utilizadoresTest.repository.UtilizadorRepository;
import com.example.bespring.modulosTest.utilizadoresTest.services.EscolaService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EscolaServiceTest {

    @Mock
    private EscolaRepository escolaRepository;

    @InjectMocks
    private EscolaService escolaService;

    @Mock
    private UtilizadorRepository utilizadorRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Nested
    class registarEscola{

        @Test
        @DisplayName("Deve registar uma escola")
        void deveRegistarEscola(){

            Escola escola = new Escola();
            escola.setNome("School");
            escola.setEndereco("Rua do Antonio");
            escola.setEmail("school@gmail.com");
            escola.setTelefone("923456789");

            Long idAdmin = 1L;

            Utilizador admin = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                    LocalDate.of(2004,1,5), Perfil.ADMIN,"raquel@gmail.com", "maura1234", EstadoConta.ATIVA, null, null);
            admin.setIdUtilizador(1L);
            escola.setAdministrador(admin);

            when(utilizadorRepository.findById(1L)).thenReturn(Optional.of(admin));
            when(escolaRepository.save(any(Escola.class))).thenAnswer(
                    invocation -> {
                        Escola escola1 = invocation.getArgument(0);
                        escola1.setIdEscola(1L);
                        return escola1;
                    }
            );

            CriarEscolaRequest escolaRequest = new CriarEscolaRequest(
                    escola.getNome(),
                    escola.getEndereco(),
                    escola.getTelefone(),
                    escola.getEmail()
            );

            var result = escolaService.registarEscola(escolaRequest, idAdmin);

            assertNotNull(result);
            assertEquals(1L, result.getIdEscola());
            assertEquals(escola.getNome(), result.getNome());
            assertEquals(escola.getEndereco(), result.getEndereco());
            assertEquals(escola.getTelefone(), result.getTelefone());
            assertEquals(escola.getEmail(), result.getEmail());

            verify(utilizadorRepository).findById(1L);
            verify(escolaRepository).save(any(Escola.class));

        }
    }

    @Nested
    class listarEscolas{

        @Test
        @DisplayName("Deve listar todos as escolas")
        void deveListarEscolas(){

            Escola escola1 = new Escola();
            escola1.setNome("School");
            escola1.setEndereco("Rua do Antonio");
            escola1.setEmail("school@gmail.com");
            escola1.setTelefone("923456789");

            Escola escola2 = new Escola();
            escola2.setNome("Rosa");
            escola2.setEndereco("Rua do Merry");
            escola2.setEmail("rosa@gmail.com");
            escola2.setTelefone("924436745");

            Long idAdminLogado = 1L;

            Utilizador admin = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                    LocalDate.of(2004,1,5), Perfil.ADMIN,"raquel@gmail.com", "maura1234", EstadoConta.ATIVA, null, null);
            admin.setIdUtilizador(1L);
            escola1.setAdministrador(admin);
            escola2.setAdministrador(admin);

            List<Escola> escolas = List.of(escola1, escola2);

            when(escolaRepository.findByAdministrador_idUtilizador(idAdminLogado)).thenReturn(escolas);

            List<Escola> result = escolaService.listarEscolas(idAdminLogado);

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(escolas, result);

            verify(escolaRepository).findByAdministrador_idUtilizador(idAdminLogado);
        }
    }

    @Nested
    class buscarEscola{

        @Test
        @DisplayName("Deve buscar por uma escola")
        void deveBuscarUmaEscolaPeloId(){

            Escola escola = new Escola();
            escola.setIdEscola(1L);

            long idAdminLogado = 2L;
            long idEscola = 1L;

            Utilizador admin = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                    LocalDate.of(2004,1,5), Perfil.ADMIN,"raquel@gmail.com", "maura1234", EstadoConta.ATIVA, null, null);
            admin.setIdUtilizador(2L);
            escola.setAdministrador(admin);

            when(escolaRepository.findById(idEscola)).thenReturn(Optional.of(escola));

            var result = escolaService.procurarEscola(idEscola, idAdminLogado);

            assertNotNull(result);
            assertEquals(idEscola, result.getIdEscola());

            verify(escolaRepository).findById(idEscola);
        }
    }

    @Nested
    class atualizarEscola{

        @Test
        void deveAtualizarEscola(){

            Escola escola = new Escola();
            escola.setNome("School");
            escola.setEndereco("Rua do Antonio");
            escola.setEmail("school@gmail.com");
            escola.setTelefone("923456789");

            long idAdminLogado = 2L;
            long idEscola = 1L;

            Utilizador admin = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                    LocalDate.of(2004,1,5), Perfil.ADMIN,"raquel@gmail.com", "maura1234", EstadoConta.ATIVA, null, null);
            admin.setIdUtilizador(2L);
            escola.setAdministrador(admin);

            AtualizarEscolaRequest escolaRequest = new AtualizarEscolaRequest(
                escola.getNome(),
                escola.getEndereco(),
                escola.getEmail(),
                escola.getTelefone()
            );

            when(escolaRepository.findById(idEscola)).thenReturn(Optional.of(escola));

            when(escolaRepository.save(any(Escola.class))).thenReturn(escola);

            escolaService.atualizarEscola(idEscola, escolaRequest, idAdminLogado);

            verify(escolaRepository).findById(idEscola);
            verify(escolaRepository).save(escola);
        }
    }

    @Nested
    class apagarEscola{
        @Test
        void deveApagarEscola(){
            Escola escola = new Escola();
            escola.setIdEscola(1L);

            long idAdminLogado = 2L;
            long idEscola = 1L;

            Utilizador admin = new Utilizador("Raquel", "KingStone", Genero.FEMININO,
                    LocalDate.of(2004,1,5), Perfil.ADMIN,"raquel@gmail.com", "maura1234", EstadoConta.ATIVA, null, null);
            admin.setIdUtilizador(2L);
            escola.setAdministrador(admin);

            when(escolaRepository.findById(idEscola)).thenReturn(Optional.of(escola));

            escolaService.apagarEscola(idEscola, idAdminLogado);

            verify(escolaRepository).findById(idEscola);

        }
    }


}

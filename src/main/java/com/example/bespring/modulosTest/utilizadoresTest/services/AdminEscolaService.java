package com.example.bespring.modulosTest.utilizadoresTest.services;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Escola;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Utilizador;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.EstadoConta;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.dto.AtualizarAdminEscolaRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarAdminEscolaRequest;
import com.example.bespring.modulosTest.utilizadoresTest.repository.EscolaRepository;
import com.example.bespring.modulosTest.utilizadoresTest.repository.UtilizadorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminEscolaService {

    private final EscolaRepository escolaRepository;
    private final UtilizadorRepository utilizadorRepository;

    public AdminEscolaService(EscolaRepository escolaRepository, UtilizadorRepository utilizadorRepository) {
        this.escolaRepository = escolaRepository;
        this.utilizadorRepository = utilizadorRepository;
    }

    public Utilizador registarAdminEscola(CriarAdminEscolaRequest criarAdminEscolaRequest, Long idAdminLogado){

        if(utilizadorRepository.findByLogin(criarAdminEscolaRequest.login()) != null){
            throw new RuntimeException("Ja existe utilizador com este login");
        }

        Utilizador admin = utilizadorRepository.findById(idAdminLogado)
                .orElseThrow( () -> new RuntimeException("Utilizador admin nao encontrado"));

        Escola escola = escolaRepository.findById(criarAdminEscolaRequest.idEscola())
                .orElseThrow(() -> new RuntimeException("Escola não encontrado"));

        //Validar perfil
        if(admin.getPerfil() != Perfil.ADMIN){
            throw new RuntimeException("Apenas utilizador admin pode registar administrador de uma escola");
        }

        String encryptPassword =  new BCryptPasswordEncoder().encode(criarAdminEscolaRequest.senha());

        Perfil perfil = Perfil.ADMIN_ESCOLA;

        EstadoConta estado = EstadoConta.ATIVA;

        //Criar admin_escola
        Utilizador userAdminEscolaEntity = new Utilizador(
                criarAdminEscolaRequest.primeiroNome(),
                criarAdminEscolaRequest.sobrenome(),
                criarAdminEscolaRequest.genero(),
                criarAdminEscolaRequest.dataNascimento(),
                perfil,
                criarAdminEscolaRequest.login(),
                encryptPassword,
                estado,
                escola,
                admin
        );

        return utilizadorRepository.save(userAdminEscolaEntity);

    }

    public Utilizador procurarAdminEscola(Long idUtilizador, Long idAdminLogado){

        var adminEscolaExistente = utilizadorRepository.findById(idUtilizador);

        if(adminEscolaExistente.isPresent()){
            var adminEscola = adminEscolaExistente.get();

            if(adminEscola.getCriadoPor().getIdUtilizador().equals(idAdminLogado)){
                return adminEscola;
            }else{
                throw new RuntimeException("Não pode procurar por este administrador de escola");
            }

        }

        return null;
    }

    public void atualizarAdminEscola(Long id, AtualizarAdminEscolaRequest request, Long idAdminLogado){

        var adminEscolaExiste = utilizadorRepository.findById(id);

        String encryptPassword = new BCryptPasswordEncoder().encode(request.senha());

        if(adminEscolaExiste.isPresent()){
            var adminEscola = adminEscolaExiste.get();

            if(!adminEscola.getCriadoPor().getIdUtilizador().equals(idAdminLogado)){
                throw new RuntimeException("Nao pode atualizar este admin_escola");
            }

            if(request.primeiroNome() != null){
                adminEscola.setPrimeiroNome(request.primeiroNome());
            }

            if(request.sobrenome() != null){
                adminEscola.setSobrenome(request.sobrenome());
            }

            if(request.genero() != null){
                adminEscola.setGenero(request.genero());
            }

            if(request.senha() != null){
                adminEscola.setSenha(encryptPassword);
            }

            if(request.login() != null){
                adminEscola.setLogin(request.login());
            }

            utilizadorRepository.save(adminEscola);
        }
    }

    public void apagarAdminEscola(Long idUser, Long idAdminLogado){

        var adminEscolaExiste = utilizadorRepository.findById(idUser);

        if(adminEscolaExiste.isPresent()){
            var adminEscola = adminEscolaExiste.get();
            if(adminEscola.getCriadoPor().getIdUtilizador().equals(idAdminLogado)){
                utilizadorRepository.deleteById(idUser);
            }else{
                throw new RuntimeException("Nao pode apagar este admin_escola");
            }
        }
    }


}

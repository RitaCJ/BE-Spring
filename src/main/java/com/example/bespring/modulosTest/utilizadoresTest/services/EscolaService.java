package com.example.bespring.modulosTest.utilizadoresTest.services;

import com.example.bespring.modulosTest.utilizadoresTest.domain.Escola;
import com.example.bespring.modulosTest.utilizadoresTest.domain.Utilizador;
import com.example.bespring.modulosTest.utilizadoresTest.domain.enums.Perfil;
import com.example.bespring.modulosTest.utilizadoresTest.dto.AtualizarAlunoRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.AtualizarEscolaRequest;
import com.example.bespring.modulosTest.utilizadoresTest.dto.CriarEscolaRequest;
import com.example.bespring.modulosTest.utilizadoresTest.repository.EscolaRepository;
import com.example.bespring.modulosTest.utilizadoresTest.repository.UtilizadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EscolaService {

    private final EscolaRepository escolaRepository;
    private final UtilizadorRepository utilizadorRepository;

    public EscolaService(EscolaRepository escolaRepository, UtilizadorRepository utilizadorRepository) {
        this.escolaRepository = escolaRepository;
        this.utilizadorRepository = utilizadorRepository;
    }

    public Escola registarEscola(CriarEscolaRequest criarEscolaRequest, Long idAdminLogado){

       Utilizador admin = utilizadorRepository.findById(idAdminLogado)
               .orElseThrow( () -> new RuntimeException("Utilizador admin nao encontrado"));

       //Validar perfil
        if(admin.getPerfil() != Perfil.ADMIN){
            throw new RuntimeException("Apenas utilizador admin pode registar escolas");
        }

        //Criar a escola
       Escola escolaEntity = new Escola(
               criarEscolaRequest.nome(),
               criarEscolaRequest.endereco(),
               criarEscolaRequest.telefone(),
               criarEscolaRequest.email()
       );

        escolaEntity.setAdministrador(admin);

       return escolaRepository.save(escolaEntity);

    }

    public List<Escola> listarEscolas(Long idAdminLogado) {
        return escolaRepository.findByAdministrador_idUtilizador(idAdminLogado);
    }

    public Escola procurarEscola(Long idEscola, Long idAdminLogado){

        var escolaExistente = escolaRepository.findById(idEscola);

        if(escolaExistente.isPresent()){
            var escola = escolaExistente.get();

            if(escola.getAdministrador().getIdUtilizador().equals(idAdminLogado)){
                return escola;
            }else{
                throw new RuntimeException("Não pode procurar por esta escola");
            }

        }

        return null;
    }

    public void atualizarEscola(Long idEscola, AtualizarEscolaRequest atualizarEscolaRequest, Long idAdminLogado){

        var escolaExiste = escolaRepository.findById(idEscola);

        if(escolaExiste.isPresent()){
            var escola = escolaExiste.get();

            if(!escola.getAdministrador().getIdUtilizador().equals(idAdminLogado)){
                throw new RuntimeException("Nao pode atualizar este escola");
            }

            if(atualizarEscolaRequest.nome() != null ){
                escola.setNome(atualizarEscolaRequest.nome());
            }

            if(atualizarEscolaRequest.endereco() != null ){
                escola.setEndereco(atualizarEscolaRequest.endereco());
            }

            if(atualizarEscolaRequest.email() != null ){
                escola.setEmail(atualizarEscolaRequest.email());
            }

            if(atualizarEscolaRequest.telefone() != null){
                escola.setTelefone(atualizarEscolaRequest.telefone());
            }

            escolaRepository.save(escola);
        }
    }

    public void apagarEscola(Long idEscola, Long idAdminLogado){

        var escolaExiste = escolaRepository.findById(idEscola);

        if(escolaExiste.isPresent()){
            var escola = escolaExiste.get();
            if(escola.getAdministrador().getIdUtilizador().equals(idAdminLogado)){
                escolaRepository.deleteById(idEscola);
            }else{
                throw new RuntimeException("Nao pode apagar esta escola");
            }
        }
    }
}

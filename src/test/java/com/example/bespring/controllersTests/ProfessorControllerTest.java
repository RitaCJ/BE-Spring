package com.example.bespring.controllersTests;

import com.example.bespring.controllers.ProfessorController;
import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Professor;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.domain.enums.TipoProfissional;
import com.example.bespring.dto.CriarProfessorRequest;
import com.example.bespring.dto.CriarProfessorResponse;
import com.example.bespring.services.ProfessorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//Será testado apenas a camada Web/Mvc do ProfessorController.
@WebMvcTest(controllers = ProfessorController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProfessorControllerTest {

    //Faz a injeção do MockMvc, que simula a requisição HTTP, sem precisar iniciar um servidor real.
    @Autowired
    private MockMvc mockMvc;

    //Faz a criação do Mocks dos serviços que o controller depende.
    //Mock -> Objecto falso que simula um ProfessorService.
    @MockitoBean
    private ProfessorService professorService;

    //Injeção do ObjectMapper
    //O ObjectMapper faz a conversão de um objecto Java para JSON ou de JSON para Java.
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void deveCadastrarProfessor() throws Exception {

        //Arrange - Preparar
        Professor professor = new Professor();
        professor.setIdUtilizador(1L);
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

        //Dados enviados pelo cliente para o endpoint.
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


        //Definir o comportamento esperado do mock "ProfessorService".
        when(professorService.cadastrarProfessor(any(CriarProfessorRequest.class))).thenReturn(professor);

        String json = objectMapper.writeValueAsString(request);

        // Act - Excutar | Assert - Verificar
        //Simular uma requisição HTTP.
        mockMvc.perform(post("/api/professores")
                        .contentType(MediaType.APPLICATION_JSON)
                        //Transforma o objecto java "request" em JSON
                        //.accept(MediaType.APPLICATION_JSON)
                        .content(json))
                //Verificação do resultado
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idUtilizador").value(1L))
                .andExpect(jsonPath("$.primeiroNome").value("Akin"))
                .andExpect(jsonPath("$.sobrenome").value("Asa"))
                .andExpect(jsonPath("$.telefone").value("123456789"))
                .andExpect(jsonPath("$.genero").value(Genero.MASCULINO.toString()))
                .andExpect(jsonPath("$.email").value("asa@gmail.com"))
                //.andExpect(jsonPath("$.senha").value("@MarEMar2000"))
                .andExpect(jsonPath("$.tipo").value(TipoProfissional.PROFESSOR.toString()))
                .andExpect(jsonPath("$.perfil").value(Perfil.PROFESSOR.toString()))
                .andExpect(jsonPath("$.idEscola").value(escola.getIdEscola()));

                verify(professorService).cadastrarProfessor(any(CriarProfessorRequest.class));
    }

}

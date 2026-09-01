package com.example.bespring.controllersTests;

import com.example.bespring.controllers.ProfessorController;
import com.example.bespring.domain.Escola;
import com.example.bespring.domain.Professor;
import com.example.bespring.domain.enums.Genero;
import com.example.bespring.domain.enums.Perfil;
import com.example.bespring.domain.enums.TipoProfissional;
import com.example.bespring.dto.AtualizarProfessorRequest;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    void deveProcurarProfessorPorId() throws Exception {

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
        escola.setIdEscola(1L);

        Professor professor = new Professor();
        professor.setIdUtilizador(1L);
        long id = 1L;

        professor.setEscola(escola);

        when(professorService.procurarProfessorPorId(id)).thenReturn(professor);

        mockMvc.perform(get("/api/professores/{idUtilizador}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUtilizador").value(1L))
                .andExpect(jsonPath("$.idEscola").value(escola.getIdEscola()));

        verify(professorService).procurarProfessorPorId(id);

    }

    @Test
    void deveListarProfessores() throws Exception {

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
        professor2.setIdUtilizador(2L);
        professor2.setPrimeiroNome("Lua");
        professor2.setSobrenome("Luna");
        professor2.setEmail("lua@gmail.com");
        professor2.setTelefone("923234323");
        professor2.setSenha("LunarELua4000#");
        professor2.setTipo(TipoProfissional.PROFESSOR);
        professor2.setGenero(Genero.FEMININO);
        professor2.setPerfil(Perfil.PROFESSOR);

        professor2.setEscola(escola);

        when(professorService.listarProfessores()).thenReturn(
                List.of(professor1, professor2));

        mockMvc.perform(get("/api/professores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].idUtilizador").value(1L))
                .andExpect(jsonPath("$[0].primeiroNome").value("Akin"))
                .andExpect(jsonPath("$[0].sobrenome").value("Asa"))
                .andExpect(jsonPath("$[0].email").value("asa@gmail.com"))
                .andExpect(jsonPath("$[0].telefone").value("123456789"))
                .andExpect(jsonPath("$[0].tipo").value(TipoProfissional.PROFESSOR.toString()))
                .andExpect(jsonPath("$[0].genero").value(Genero.MASCULINO.toString()))
                .andExpect(jsonPath("$[0].perfil").value(Perfil.PROFESSOR.toString()))
                .andExpect(jsonPath("$[0].idEscola").value(escola.getIdEscola()))
                .andExpect(jsonPath("$[1].idUtilizador").value(2L))
                .andExpect(jsonPath("$[1].primeiroNome").value("Lua"))
                .andExpect(jsonPath("$[1].sobrenome").value("Luna"))
                .andExpect(jsonPath("$[1].email").value("lua@gmail.com"))
                .andExpect(jsonPath("$[1].telefone").value("923234323"))
                .andExpect(jsonPath("$[1].tipo").value(TipoProfissional.PROFESSOR.toString()))
                .andExpect(jsonPath("$[1].genero").value(Genero.FEMININO.toString()))
                .andExpect(jsonPath("$[1].perfil").value(Perfil.PROFESSOR.toString()))
                .andExpect(jsonPath("$[1].idEscola").value(escola.getIdEscola()));

                verify(professorService).listarProfessores();
    }

    @Test
    void deveAtualizarProfessor() throws Exception{

        Professor professor = new Professor();
        professor.setIdUtilizador(1L);
        professor.setPrimeiroNome("Zaburi");
        professor.setSobrenome("Shuri");
        professor.setEmail("zaburi12@gmail.com");
        professor.setTelefone("123456769");
        professor.setSenha("@MarEMar2007");
        professor.setGenero(Genero.MASCULINO);

        long id = 1L;

        AtualizarProfessorRequest professorRequest = new AtualizarProfessorRequest(
                professor.getPrimeiroNome(),
                professor.getSobrenome(),
                professor.getTelefone(),
                professor.getGenero(),
                professor.getEmail(),
                professor.getSenha()
        );

        doNothing().when(professorService).atualizarProfessor(id, professorRequest);

        String json = objectMapper.writeValueAsString(professorRequest);

        mockMvc.perform(put("/api/professores/{idUtilizador}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))

                .andExpect(status().isNoContent());

                verify(professorService).atualizarProfessor(id, professorRequest);

    }


    @Test
    void deveApagarProfessorPorId() throws Exception {

        Escola escola = new Escola("School", "Rua francisco da silva", "9234234445", "school@gmail.com");
        escola.setIdEscola(1L);

        Professor professor = new Professor();
        professor.setIdUtilizador(1L);
        long id = 1L;

        professor.setEscola(escola);

        doNothing().when(professorService).apagarProfessor(id);

        mockMvc.perform(delete("/api/professores/{idUtilizador}", id))
                .andExpect(status().isNoContent());


        verify(professorService).apagarProfessor(id);

    }



}

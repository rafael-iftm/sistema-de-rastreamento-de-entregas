package br.edu.iftm.rastreamento.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.iftm.rastreamento.dto.EnderecoDTO;
import br.edu.iftm.rastreamento.service.EnderecoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;


@RestController
@RequestMapping("/enderecos")
@Tag(name = "", description = "Endpoint para gerenciar endereços.")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    @Operation(summary = "Obter todos os endereços", description = "Retorna uma lista de todos os endereços", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content),
            @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
    })
    public List<EnderecoDTO> getAllEnderecos() {
        return enderecoService.getAllEnderecos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter endereço por ID", description = "Encontra um endereço pelo seu ID", responses = {
            @ApiResponse(description = "Encontrado", responseCode = "302", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content)
    })
    public ResponseEntity<EnderecoDTO> getById(@PathVariable Long id) {
        EnderecoDTO endereco = enderecoService.getEnderecoById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(endereco);
    }

    @PostMapping
    @Operation(summary = "Criar um novo endereço", description = "Cria um novo endereço", responses = {
            @ApiResponse(description = "Criado", responseCode = "201", content = @Content),
            @ApiResponse(description = "Requisição inválida", responseCode = "400", content = @Content)
    })
    public ResponseEntity<EnderecoDTO> createEndereco(@RequestBody EnderecoDTO enderecoDTO) {
        EnderecoDTO novoEnderecoDTO = enderecoService.createEndereco(enderecoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEnderecoDTO);
    }
}

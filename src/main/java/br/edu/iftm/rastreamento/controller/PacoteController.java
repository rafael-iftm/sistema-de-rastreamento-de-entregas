package br.edu.iftm.rastreamento.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import br.edu.iftm.rastreamento.model.Pacote;
import br.edu.iftm.rastreamento.service.PacoteService;
import br.edu.iftm.rastreamento.service.exceptions.pacoteException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;


@RestController
@RequestMapping("/pacotes")
@Tag(name = "Pacotes", description = "Endpoint para gerenciar pacotes.")
public class PacoteController {

    @Autowired
    private PacoteService pacoteService;

    @GetMapping
    @Operation(summary = "Obter todos os pacotes", description = "Retorna uma lista de todos os pacotes", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content),
            @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
    })
    public List<Pacote> getAllPacotes() {
        return pacoteService.getAllPacotes();
    }

    @PostMapping
    @Operation(summary = "Criar um novo pacote", description = "Cria um novo pacote", responses = {
            @ApiResponse(description = "Criado", responseCode = "201", content = @Content),
            @ApiResponse(description = "Requisição inválida", responseCode = "400", content = @Content)
    })
    public Pacote createPacote(@RequestBody Pacote pacote) {
        return pacoteService.createPacote(pacote);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter pacote por ID", description = "Encontra um pacote pelo seu ID", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content)
    })
    public Pacote getPacoteById(@PathVariable Long id) {
        Pacote pacote = pacoteService.getPacoteById(id).orElseThrow(
            () -> new pacoteException("Pacote não encontrado"));
        return pacote;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um pacote", description = "Atualiza um pacote pelo seu ID", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Requisição inválida", responseCode = "400", content = @Content)
    })
    public Pacote updatePacote(@PathVariable Long id, @RequestBody Pacote pacoteDetails) {
        Pacote pacote = pacoteService.updatePacote(id, pacoteDetails).orElseThrow(
            () -> new pacoteException("Pacote não encontrado"));
        return pacote;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um pacote", description = "Exclui um pacote pelo seu ID", responses = {
            @ApiResponse(description = "Sem conteúdo", responseCode = "204", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content)
    })
    public void deletePacote(@PathVariable Long id) {
        pacoteService.deletePacote(id);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Obter pacotes por status", description = "Encontra pacotes pelo status", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content)
    })
    public List<Pacote> getPacotesByStatus(@PathVariable String status) {
        return pacoteService.findByStatus(status);
    }

    @GetMapping("/destinatario/{destinatario}")
    @Operation(summary = "Obter pacotes por destinatário", description = "Encontra pacotes pelo destinatário", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content)
    })
    public List<Pacote> getPacotesByDestinatario(@PathVariable String destinatario) {
        return pacoteService.findByDestinatario(destinatario);
    }
}

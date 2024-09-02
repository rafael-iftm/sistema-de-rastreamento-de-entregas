package br.edu.iftm.rastreamento.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.iftm.rastreamento.model.Rastreamento;
import br.edu.iftm.rastreamento.service.RastreamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/rastreamentos")
@Tag(name = "Rastreamento", description = "Endpoint para gerenciar rastreamento.")

public class RastreamentoController {

	@Autowired
	private RastreamentoService rastreamentoService;

	
	@GetMapping("/{id}")
	@Operation(summary = "Encontra um pacote pelo ID.", description = "Encontra um pacote pelo ID.", tags = {
			"Usuário" }, responses = {
					@ApiResponse(description = "Sem conteúdo", responseCode = "204", content = @Content),
					@ApiResponse(description = "Requisição inválida", responseCode = "400", content = @Content),
					@ApiResponse(description = "Não autorizado", responseCode = "401", content = @Content),
					@ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
					@ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
			})
	public List<Rastreamento> getRastreamentosPacote(@PathVariable Long id) {
		return rastreamentoService.getRastreamentos(id);
	}

}

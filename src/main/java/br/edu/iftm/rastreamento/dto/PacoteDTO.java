package br.edu.iftm.rastreamento.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PacoteDTO {
	private Long id;
	private String idUnico;
	private String destinatario;
	private EnderecoDTO endereco;
	private String status;
	private List<RastreamentoDTO> rastreamentos = new ArrayList<>();

	public void atualizarStatus(String novoStatus, Date dataHora, String localizacao) {
		this.status = novoStatus;
		RastreamentoDTO rastreamento = new RastreamentoDTO();
		rastreamento.setDataHora(dataHora);
		rastreamento.setLocalizacao(localizacao);
		rastreamento.setStatus(novoStatus);
		rastreamentos.add(rastreamento);
	}

	public String consultarInformacoes() {
		StringBuilder sb = new StringBuilder();
		for (RastreamentoDTO r : rastreamentos) {
			sb.append(r.getResumo());
			sb.append("\n");
		}
		return "Pacote [id=" + id + ", idUnico=" + idUnico + ", destinatario=" + destinatario + ", endereco=" + endereco
				+ ", status=" + status + ", rastreamentos=" + sb.toString() + "]";
	}
}

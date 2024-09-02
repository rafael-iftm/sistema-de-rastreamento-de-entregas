package br.edu.iftm.rastreamento.dto;

import java.util.Date;

import lombok.Data;

@Data
public class RastreamentoDTO {
	private Long id;
	private Date dataHora;
	private String status;
	private String localizacao;

	public String getResumo() {
		return "Data/Hora: " + dataHora + " - Localização: " + localizacao + " - Status: " + status;
	}

}

package br.edu.iftm.rastreamento.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.rastreamento.dto.EnderecoDTO;
import br.edu.iftm.rastreamento.model.Endereco;
import br.edu.iftm.rastreamento.repository.EnderecoRepository;
import br.edu.iftm.rastreamento.service.exceptions.pacoteException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public List<EnderecoDTO> getAllEnderecos() {
		Iterable<Endereco> enderecosIterable = enderecoRepository.findAll();
		List<EnderecoDTO> enderecosList = new ArrayList<>();
		enderecosIterable.forEach((endereco) ->
		{
			EnderecoDTO enderecoDTO = new EnderecoDTO();
			enderecoDTO.setId(endereco.getId());
			enderecoDTO.setRua(endereco.getRua());
			enderecoDTO.setNumero(endereco.getNumero());
			enderecoDTO.setCidade(endereco.getCidade());
			enderecoDTO.setEstado(endereco.getEstado());
			enderecoDTO.setCep(endereco.getCep());
			enderecosList.add(enderecoDTO);
		}
		);
		return enderecosList;
	}

	public EnderecoDTO getEnderecoById(Long id) {
		Endereco endereco = enderecoRepository.findById(id).orElseThrow(
				() -> new pacoteException("Endereco não encontrado"));
		endereco = enderecoRepository.save(endereco);
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		enderecoDTO.setId(endereco.getId());
		enderecoDTO.setRua(endereco.getRua());
		enderecoDTO.setNumero(endereco.getNumero());
		enderecoDTO.setCidade(endereco.getCidade());
		enderecoDTO.setEstado(endereco.getEstado());
		enderecoDTO.setCep(endereco.getCep());
		return enderecoDTO;
	}

	public EnderecoDTO createEndereco(EnderecoDTO enderecoDTO) {
		Endereco endereco = new Endereco();
		endereco.setRua(enderecoDTO.getRua());
		endereco.setNumero(enderecoDTO.getNumero());
		endereco.setCidade(enderecoDTO.getCidade());
		endereco.setEstado(enderecoDTO.getEstado());
		endereco.setCep(enderecoDTO.getCep());

		endereco = enderecoRepository.save(endereco);
		EnderecoDTO enderecoDTOCreated = new EnderecoDTO();
		enderecoDTOCreated.setId(endereco.getId());
		enderecoDTOCreated.setRua(endereco.getRua());
		enderecoDTOCreated.setNumero(endereco.getNumero());
		enderecoDTOCreated.setCidade(endereco.getCidade());
		enderecoDTOCreated.setEstado(endereco.getEstado());
		enderecoDTOCreated.setCep(endereco.getCep());

		return enderecoDTOCreated;
	}

	public Endereco updateEndereco(Long id, Endereco enderecoDetails) {
		Endereco endereco = enderecoRepository.findById(id).get();
		endereco.setRua(enderecoDetails.getRua());
		endereco.setCidade(enderecoDetails.getCidade());
		endereco.setEstado(enderecoDetails.getEstado());
		endereco.setCep(enderecoDetails.getCep());
		return enderecoRepository.save(endereco);
	}

	public void deleteEndereco(Long id) {
		Endereco endereco = enderecoRepository.findById(id).get();
		enderecoRepository.delete(endereco);
	}
}

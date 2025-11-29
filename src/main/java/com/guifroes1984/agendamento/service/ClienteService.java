package com.guifroes1984.agendamento.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guifroes1984.agendamento.dto.ClienteDTO;
import com.guifroes1984.agendamento.entity.Cliente;
import com.guifroes1984.agendamento.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<ClienteDTO> findAll() {
		return clienteRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}

	public ClienteDTO findById(Long id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
		return toDTO(cliente);
	}

	public ClienteDTO create(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		cliente.setNome(clienteDTO.getNome());
		cliente.setTelefone(clienteDTO.getTelefone());

		return toDTO(clienteRepository.save(cliente));
	}

	public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

		cliente.setNome(clienteDTO.getNome());
		cliente.setTelefone(clienteDTO.getTelefone());

		return toDTO(clienteRepository.save(cliente));
	}

	public void delete(Long id) {
		clienteRepository.deleteById(id);
	}

	private ClienteDTO toDTO(Cliente cliente) {
		return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getTelefone());
	}
}

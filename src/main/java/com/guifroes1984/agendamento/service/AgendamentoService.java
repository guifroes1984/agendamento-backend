package com.guifroes1984.agendamento.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guifroes1984.agendamento.dto.AgendamentoDTO;
import com.guifroes1984.agendamento.entity.Agendamento;
import com.guifroes1984.agendamento.entity.Cliente;
import com.guifroes1984.agendamento.entity.Manicure;
import com.guifroes1984.agendamento.enuns.StatusAgendamento;
import com.guifroes1984.agendamento.repository.AgendamentoRepository;
import com.guifroes1984.agendamento.repository.ClienteRepository;
import com.guifroes1984.agendamento.repository.ManicureRepository;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	@Autowired
	private ManicureRepository manicureRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public List<AgendamentoDTO> buscarTudo() {
		return agendamentoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}

	public List<AgendamentoDTO> findByManicureId(Long manicureId) {
		return agendamentoRepository.findByManicureIdOrderByDataHora(manicureId).stream().map(this::toDTO)
				.collect(Collectors.toList());
	}

	public AgendamentoDTO findById(Long id) {
		Agendamento agendamento = agendamentoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
		return toDTO(agendamento);
	}

	public AgendamentoDTO criar(AgendamentoDTO agendamentoDTO) {
		// Verificar se já existe agendamento para a mesma manicure no mesmo horário
		List<Agendamento> agendamentosExistentes = agendamentoRepository
				.findByManicureAndDataHora(agendamentoDTO.getManicureId(), agendamentoDTO.getDataHora());

		if (!agendamentosExistentes.isEmpty()) {
			throw new RuntimeException("Já existe um agendamento para esta manicure no horário selecionado");
		}

		Manicure manicure = manicureRepository.findById(agendamentoDTO.getManicureId())
				.orElseThrow(() -> new RuntimeException("Manicure não encontrada"));

		Cliente cliente = clienteRepository.findById(agendamentoDTO.getClienteId())
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

		Agendamento agendamento = new Agendamento();
		agendamento.setDataHora(agendamentoDTO.getDataHora());
		agendamento.setManicure(manicure);
		agendamento.setCliente(cliente);
		agendamento.setStatus(StatusAgendamento.AGENDADO);

		return toDTO(agendamentoRepository.save(agendamento));
	}

	public AgendamentoDTO cancelar(Long id) {
		Agendamento agendamento = agendamentoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

		agendamento.setStatus(StatusAgendamento.CANCELADO);

		return toDTO(agendamentoRepository.save(agendamento));
	}

	public AgendamentoDTO atualizarStatus(Long id, StatusAgendamento status) {
		Agendamento agendamento = agendamentoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

		agendamento.setStatus(status);

		return toDTO(agendamentoRepository.save(agendamento));
	}

	public void delete(Long id) {
		agendamentoRepository.deleteById(id);
	}

	private AgendamentoDTO toDTO(Agendamento agendamento) {
		return new AgendamentoDTO(agendamento.getId(), agendamento.getDataHora(), agendamento.getStatus(),
				agendamento.getManicure().getId(), agendamento.getCliente().getId(),
				agendamento.getManicure().getNome(), agendamento.getCliente().getNome());
	}
}

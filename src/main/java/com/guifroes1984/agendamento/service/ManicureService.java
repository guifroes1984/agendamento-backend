package com.guifroes1984.agendamento.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guifroes1984.agendamento.dto.ManicureDTO;
import com.guifroes1984.agendamento.entity.Manicure;
import com.guifroes1984.agendamento.exception.RecursoNaoEncontradoException;
import com.guifroes1984.agendamento.repository.ManicureRepository;

@Service
public class ManicureService {

	@Autowired
	private ManicureRepository manicureRepository;

	public List<ManicureDTO> buscarTudo() {
		return manicureRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}

	public ManicureDTO findById(Long id) {
		Manicure manicure = manicureRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Manicure não encontrada"));
		return toDTO(manicure);
	}

	public ManicureDTO criar(ManicureDTO manicureDTO) {
		if (manicureRepository.existsByEmail(manicureDTO.getEmail())) {
			throw new RuntimeException("Email já cadastrado");
		}

		Manicure manicure = new Manicure();
		manicure.setNome(manicureDTO.getNome());
		manicure.setEmail(manicureDTO.getEmail());
		manicure.setTelefone(manicureDTO.getTelefone());

		return toDTO(manicureRepository.save(manicure));
	}

	public ManicureDTO atualizar(Long id, ManicureDTO manicureDTO) {
		Manicure manicure = manicureRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Manicure não encontrada"));

		manicure.setNome(manicureDTO.getNome());
		manicure.setEmail(manicureDTO.getEmail());
		manicure.setTelefone(manicureDTO.getTelefone());

		return toDTO(manicureRepository.save(manicure));
	}

	public void deletar(Long id) {
		manicureRepository.deleteById(id);
	}

	private ManicureDTO toDTO(Manicure manicure) {
		return new ManicureDTO(manicure.getId(), manicure.getNome(), manicure.getEmail(), manicure.getTelefone());
	}
}

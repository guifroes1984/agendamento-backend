package com.guifroes1984.agendamento.dto;

import java.time.LocalDateTime;

import com.guifroes1984.agendamento.enuns.StatusAgendamento;

import jakarta.validation.constraints.NotNull;

public class AgendamentoDTO {

	private Long id;

	@NotNull(message = "Data e hora são obrigatórias")
	private LocalDateTime dataHora;

	private StatusAgendamento status;

	@NotNull(message = "Manicure é obrigatória")
	private Long manicureId;

	@NotNull(message = "Cliente é obrigatório")
	private Long clienteId;

	private String manicureNome;
	private String clienteNome;

	public AgendamentoDTO() {
	}

	public AgendamentoDTO(Long id, LocalDateTime dataHora, StatusAgendamento status, Long manicureId, Long clienteId,
			String manicureNome, String clienteNome) {
		this.id = id;
		this.dataHora = dataHora;
		this.status = status;
		this.manicureId = manicureId;
		this.clienteId = clienteId;
		this.manicureNome = manicureNome;
		this.clienteNome = clienteNome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public StatusAgendamento getStatus() {
		return status;
	}

	public void setStatus(StatusAgendamento status) {
		this.status = status;
	}

	public Long getManicureId() {
		return manicureId;
	}

	public void setManicureId(Long manicureId) {
		this.manicureId = manicureId;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public String getManicureNome() {
		return manicureNome;
	}

	public void setManicureNome(String manicureNome) {
		this.manicureNome = manicureNome;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}
}

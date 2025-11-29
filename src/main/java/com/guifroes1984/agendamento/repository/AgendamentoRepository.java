package com.guifroes1984.agendamento.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.guifroes1984.agendamento.entity.Agendamento;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

	List<Agendamento> findByManicureIdOrderByDataHora(Long manicureId);

	@Query("SELECT a FROM Agendamento a WHERE a.manicure.id = :manicureId AND a.dataHora = :dataHora AND a.status <> 'CANCELADO'")
	List<Agendamento> findByManicureAndDataHora(@Param("manicureId") Long manicureId,
			@Param("dataHora") LocalDateTime dataHora);

	List<Agendamento> findByManicureIdAndDataHoraBetweenOrderByDataHora(Long manicureId, LocalDateTime start,
			LocalDateTime end);
}

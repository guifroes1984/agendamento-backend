package com.guifroes1984.agendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guifroes1984.agendamento.entity.Manicure;

@Repository
public interface ManicureRepository extends JpaRepository<Manicure, Long> {
	boolean existsByEmail(String email);
}

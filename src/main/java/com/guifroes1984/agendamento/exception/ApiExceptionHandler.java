package com.guifroes1984.agendamento.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<Map<String, Object>> tratarRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
		Map<String, Object> erro = new HashMap<>();
		erro.put("timestamp", Instant.now());
		erro.put("status", HttpStatus.NOT_FOUND.value());
		erro.put("error", "Recurso não encontrado");
		erro.put("message", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> tratarValidacao(MethodArgumentNotValidException ex) {
		Map<String, Object> erro = new HashMap<>();
		erro.put("timestamp", Instant.now());
		erro.put("status", HttpStatus.BAD_REQUEST.value());
		erro.put("error", "Erro de validação");

		String campo = ex.getBindingResult().getFieldError().getField();
		String mensagem = ex.getBindingResult().getFieldError().getDefaultMessage();

		Map<String, String> detalhes = new HashMap<>();
		detalhes.put(campo, mensagem);
		erro.put("errors", detalhes);

		return ResponseEntity.badRequest().body(erro);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> tratarErroGeral(Exception ex) {
		Map<String, Object> erro = new HashMap<>();
		erro.put("timestamp", Instant.now());
		erro.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		erro.put("error", "Erro interno do servidor");
		erro.put("message", ex.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
	}
}

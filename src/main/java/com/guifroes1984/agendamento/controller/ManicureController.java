package com.guifroes1984.agendamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guifroes1984.agendamento.dto.ManicureDTO;
import com.guifroes1984.agendamento.service.ManicureService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/manicures")
@Tag(name = "Manicures", description = "Gerenciamento de manicures")
@CrossOrigin(origins = "*")
public class ManicureController {
    
    @Autowired
    private ManicureService manicureService;
    
    @GetMapping
    @Operation(summary = "Listar todas as manicures")
    public ResponseEntity<List<ManicureDTO>> findAll() {
        return ResponseEntity.ok(manicureService.buscarTudo());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar manicure por ID")
    public ResponseEntity<ManicureDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(manicureService.findById(id));
    }
    
    @PostMapping
    @Operation(summary = "Criar nova manicure")
    public ResponseEntity<ManicureDTO> criar(@Valid @RequestBody ManicureDTO manicureDTO) {
        return ResponseEntity.ok(manicureService.criar(manicureDTO));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar manicure")
    public ResponseEntity<ManicureDTO> update(@PathVariable Long id, @Valid @RequestBody ManicureDTO manicureDTO) {
        return ResponseEntity.ok(manicureService.atualizar(id, manicureDTO));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir manicure")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        manicureService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

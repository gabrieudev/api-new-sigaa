package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.rest.dto.TurmaDto;
import br.edu.ifs.apinewsigaa.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @Operation(
            summary = "Salvar turma",
            description = "Salva uma turma no banco de dados",
            tags = "Turma"
    )
    @PostMapping
    public ResponseEntity<TurmaDto> salvar(@Valid @RequestBody TurmaDto turmaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaService.salvar(turmaDto));
    }

    @Operation(
            summary = "Obter turmas",
            description = "Obtém todas as turmas registradas",
            tags = "Turma"
    )
    @GetMapping
    public ResponseEntity<Page<TurmaDto>> obterTodas(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.obterTodas(pageable));
    }

    @Operation(
            summary = "Obter por ID",
            description = "Obtém uma turma por ID",
            tags = "Turma"
    )
    @GetMapping("/{id}")
    public ResponseEntity<TurmaDto> obterPorId(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.obterPorId(id));
    }

    @Operation(
            summary = "Atualizar turma",
            description = "Atualiza uma turma existente no banco de dados",
            tags = "Turma"
    )
    @PutMapping
    public ResponseEntity<TurmaDto> atualizar(@Valid @RequestBody TurmaDto turmaDto) {
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.atualizar(turmaDto));
    }

    @Operation(
            summary = "Deletar turma",
            description = "Deleta uma turma existente no banco de dados",
            tags = "Turma"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") int id) {
        turmaService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Obter por professor",
            description = "Obtém todas as turmas que um determinado professor leciona",
            tags = "Turma"
    )
    @GetMapping("/obter-por-professor/{id}")
    public ResponseEntity<Page<TurmaDto>> obterPorProfessor(
            @PathVariable("id") int id,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.obterPorIdProfessor(id, pageable));
    }

    @Operation(
            summary = "Obter por disciplina",
            description = "Obtém todas as turmas de uma determinada disciplina",
            tags = "Turma"
    )
    @GetMapping("/obter-por-disciplina/{id}")
    public ResponseEntity<Page<TurmaDto>> obterPorDisciplina(
            @PathVariable("id") int id,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.obterPorIdDisciplina(id, pageable));
    }

}

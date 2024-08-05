package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.rest.dto.MatriculaDto;
import br.edu.ifs.apinewsigaa.service.MatriculaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @Operation(
            summary = "Salvar matrícula",
            description = "Salva uma matrícula no banco de dados",
            tags = "Matricula"
    )
    @PostMapping
    public ResponseEntity<MatriculaDto> salvar(@Valid @RequestBody MatriculaDto matriculaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaService.salvar(matriculaDto));
    }

    @Operation(
            summary = "Obter matrículas",
            description = "Obtém todas as matrículas registradas",
            tags = "Matricula"
    )
    @GetMapping
    public ResponseEntity<Page<MatriculaDto>> obterTodas(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(matriculaService.obterTodas(pageable));
    }

    @Operation(
            summary = "Obter matrícula por ID",
            description = "Obtém uma matrícula por ID",
            tags = "Matricula"
    )
    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDto> obterPorId(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(matriculaService.obterPorId(id));
    }

    @Operation(
            summary = "Atualizar matrícula",
            description = "Atualiza uma matrícula existente no banco de dados",
            tags = "Matricula"
    )
    @PutMapping
    public ResponseEntity<MatriculaDto> atualizar(@Valid @RequestBody MatriculaDto matriculaDto) {
        return ResponseEntity.status(HttpStatus.OK).body(matriculaService.atualizar(matriculaDto));
    }

    @Operation(
            summary = "Deletar matrícula",
            description = "Deleta uma matrícula existente no banco de dados",
            tags = "Matricula"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") int id) {
        matriculaService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Obter por aluno",
            description = "Obtém todas as matrículas de um determinado aluno",
            tags = "Matricula"
    )
    @GetMapping("/obter-por-aluno/{id}")
    public ResponseEntity<Page<MatriculaDto>> obterPorAluno(
            @PathVariable("id") int id,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(matriculaService.obterPorIdAluno(id, pageable));
    }

    @Operation(
            summary = "Obter por turma",
            description = "Obtém todas as matrículas efetuadas para uma determinada turma",
            tags = "Matricula"
    )
    @GetMapping("/obter-por-turma/{id}")
    public ResponseEntity<Page<MatriculaDto>> obterPorTurma(
            @PathVariable("id") int id,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(matriculaService.obterPorIdTurma(id, pageable));
    }

}

package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.rest.dto.DisciplinaDto;
import br.edu.ifs.apinewsigaa.service.DisciplinaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @Operation(
            summary = "Salvar disciplina",
            description = "Salva uma disciplina no banco de dados",
            tags = "Disciplina"
    )
    @PostMapping
    public ResponseEntity<DisciplinaDto> salvar(@Valid @RequestBody DisciplinaDto disciplinaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaService.salvar(disciplinaDto));
    }

    @Operation(
            summary = "Obter disciplinas",
            description = "Obtém todas as disciplinas",
            tags = "Disciplina"
    )
    @GetMapping
    public ResponseEntity<Page<DisciplinaDto>> obterTodos(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.obterTodas(pageable));
    }

    @Operation(
            summary = "Obter disciplina por ID",
            description = "Obtém uma disciplina por seu ID",
            tags = "Disciplina"
    )
    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaDto> obterPorId(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.obterPorId(id));
    }

    @Operation(
            summary = "Atualizar disciplina",
            description = "Atualiza uma disciplina existente no banco de dados",
            tags = "Disciplina"
    )
    @PutMapping
    public ResponseEntity<DisciplinaDto> atualizar(@Valid @RequestBody DisciplinaDto disciplinaDto) {
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.atualizar(disciplinaDto));
    }

    @Operation(
            summary = "Deletar disciplina",
            description = "Deleta uma disciplina existente no banco de dados",
            tags = "Disciplina"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") int id) {
        disciplinaService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Obter por aluno",
            description = "Obtém todas as disciplinas que um determinado aluno está matriculado atualmente",
            tags = "Disciplina"
    )
    @GetMapping("/obter-por-aluno/{id}")
    public ResponseEntity<Page<DisciplinaDto>> obterDisciplinasAtuaisPorAluno(
            @PathVariable("id") int id,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.obterDisciplinasAtuaisPorAluno(id, pageable));
    }

    @Operation(
            summary = "Obter histórico por aluno",
            description = "Obtém o histórico de disciplinas que um determinado aluno já se matriculou",
            tags = "Disciplina"
    )
    @GetMapping("/historico-por-aluno/{id}")
    public ResponseEntity<Page<DisciplinaDto>> obterHistoricoDeDisciplinasPorAluno(
            @PathVariable("id") int id,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.obterHistoricoDeDisciplinasPorAluno(id, pageable));
    }

    @Operation(
            summary = "Obter histórico por professor",
            description = "Obtém o histórico de disciplinas que um determinado professor já lecionou",
            tags = "Disciplina"
    )
    @GetMapping("/historico-por-professor/{id}")
    public ResponseEntity<Page<DisciplinaDto>> obterHistoricoDeDisciplinasPorProfessor(
            @PathVariable("id") int id,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.obterHistoricoDeDisciplinasPorProfessor(id, pageable));
    }

    @Operation(
            summary = "Obter por professor",
            description = "Obtém todas as disciplinas que um professor está lecionando atualmente",
            tags = "Disciplina"
    )
    @GetMapping("/obter-por-professor/{id}")
    public ResponseEntity<Page<DisciplinaDto>> obterDisciplinasAtuaisPorProfessor(
            @PathVariable("id") int id,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.obterDisciplinasAtuaisPorProfessor(id, pageable));
    }

}

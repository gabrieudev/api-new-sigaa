package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.rest.dto.DisciplinaDto;
import br.edu.ifs.apinewsigaa.service.DisciplinaService;
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

    @PostMapping
    public ResponseEntity<DisciplinaDto> salvar(@Valid @RequestBody DisciplinaDto disciplinaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaService.salvar(disciplinaDto));
    }

    @GetMapping
    public ResponseEntity<Page<DisciplinaDto>> obterTodos(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.obterTodas(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaDto> obterPorId(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.obterPorId(id));
    }

    @PutMapping
    public ResponseEntity<DisciplinaDto> atualizar(@Valid @RequestBody DisciplinaDto disciplinaDto) {
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.atualizar(disciplinaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") int id) {
        disciplinaService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/obter-por-aluno/{id}")
    public ResponseEntity<Page<DisciplinaDto>> obterDisciplinasAtuaisPorAluno(
            @PathVariable("id") int id,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.obterDisciplinasAtuaisPorAluno(id, pageable));
    }

    @GetMapping("/historico-por-aluno/{id}")
    public ResponseEntity<Page<DisciplinaDto>> obterHistoricoDeDisciplinasPorAluno(
            @PathVariable("id") int id,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.obterHistoricoDeDisciplinasPorAluno(id, pageable));
    }

    @GetMapping("/historico-por-professor/{id}")
    public ResponseEntity<Page<DisciplinaDto>> obterHistoricoDeDisciplinasPorProfessor(
            @PathVariable("id") int id,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.obterHistoricoDeDisciplinasPorProfessor(id, pageable));
    }

    @GetMapping("/obter-por-professor/{id}")
    public ResponseEntity<Page<DisciplinaDto>> obterDisciplinasAtuaisPorProfessor(
            @PathVariable("id") int id,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(disciplinaService.obterDisciplinasAtuaisPorProfessor(id, pageable));
    }

}

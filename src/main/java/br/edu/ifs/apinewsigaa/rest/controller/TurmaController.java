package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.rest.dto.TurmaDto;
import br.edu.ifs.apinewsigaa.service.TurmaService;
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

    @PostMapping
    public ResponseEntity<TurmaDto> salvar(@Valid @RequestBody TurmaDto turmaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaService.salvar(turmaDto));
    }

    @GetMapping
    public ResponseEntity<Page<TurmaDto>> obterTodas(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.obterTodas(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDto> obterPorId(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.obterPorId(id));
    }

    @PutMapping
    public ResponseEntity<TurmaDto> atualizar(@Valid @RequestBody TurmaDto turmaDto) {
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.atualizar(turmaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") int id) {
        turmaService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/obter-por-professor/{id}")
    public ResponseEntity<Page<TurmaDto>> obterPorProfessor(
            @PathVariable("id") int id,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.obterPorIdProfessor(id, pageable));
    }

    @GetMapping("/obter-por-disciplina/{id}")
    public ResponseEntity<Page<TurmaDto>> obterPorDisciplina(
            @PathVariable("id") int id,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.obterPorIdDisciplina(id, pageable));
    }

}

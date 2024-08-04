package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.rest.dto.DisciplinaDto;
import br.edu.ifs.apinewsigaa.rest.dto.ProfessorDto;
import br.edu.ifs.apinewsigaa.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<ProfessorDto> salvar(@Valid @RequestBody ProfessorDto professorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.salvar(professorDto));
    }

    @GetMapping
    public ResponseEntity<Page<ProfessorDto>> obterTodos(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.obterTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDto> obterPorMatricula(@PathVariable("mat") int matricula) {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.obterPorMatricula(matricula));
    }

    @PutMapping
    public ResponseEntity<ProfessorDto> atualizar(@Valid @RequestBody ProfessorDto professorDto) {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.atualizar(professorDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") int id) {
        professorService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.rest.dto.ProfessorDto;
import br.edu.ifs.apinewsigaa.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(
            summary = "Salvar professor",
            description = "Salva um professor no banco de dados",
            tags = "Professor"
    )
    @PostMapping
    public ResponseEntity<ProfessorDto> salvar(@Valid @RequestBody ProfessorDto professorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.salvar(professorDto));
    }

    @Operation(
            summary = "Obter professores",
            description = "Obtém todos os professores registrados",
            tags = "Professor"
    )
    @GetMapping
    public ResponseEntity<Page<ProfessorDto>> obterTodos(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.obterTodos(pageable));
    }

    @Operation(
            summary = "Obter por matrícula",
            description = "Obtém um professor por matrícula",
            tags = "Professor"
    )
    @GetMapping("/{mat}")
    public ResponseEntity<ProfessorDto> obterPorMatricula(@PathVariable("mat") int matricula) {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.obterPorMatricula(matricula));
    }

    @Operation(
            summary = "Atualizar professor",
            description = "Atualiza um professor existente no banco de dados",
            tags = "Professor"
    )
    @PutMapping
    public ResponseEntity<ProfessorDto> atualizar(@Valid @RequestBody ProfessorDto professorDto) {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.atualizar(professorDto));
    }

    @Operation(
            summary = "Deletar professor",
            description = "Deleta um professor existente no banco de dados",
            tags = "Professor"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") int id) {
        professorService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Obter por disciplina",
            description = "Obtém todos os professores que lecionam uma determinada disciplina",
            tags = "Professor"
    )
    @GetMapping("/obter-por-disciplina/{id}")
    public ResponseEntity<Page<ProfessorDto>> obterProfessoresPorDisciplina(
            @PathVariable("id") int id,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.obterProfessoresPorDisciplina(id, pageable));
    }

}

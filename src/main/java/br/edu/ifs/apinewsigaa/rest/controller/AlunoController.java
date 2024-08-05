package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.rest.dto.AlunoDto;
import br.edu.ifs.apinewsigaa.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @Operation(
            summary = "Obter alunos",
            description = "Obtém todos os alunos registrados",
            tags = "Aluno"
    )
    @GetMapping
    public ResponseEntity<Page<AlunoDto>> ObterTodos(Pageable pageable) {
        return ResponseEntity.ok(alunoService.obterTodos(pageable));
    }

    @Operation(
            summary = "Obter por matrícula",
            description = "Obtém um aluno pela sua matrícula",
            tags = "Aluno"
    )
    @GetMapping("/{mat}")
    public ResponseEntity<AlunoDto> ObterPorMatricula(@PathVariable("mat") String matricula) {
        AlunoDto alunoDto = alunoService.obterPorMatricula(matricula);
        return ResponseEntity.ok(alunoDto);
    }

    @Operation(
            summary = "Salvar aluno",
            description = "Salva um aluno no banco de dados",
            tags = "Aluno"
    )
    @PostMapping
    public ResponseEntity<AlunoDto> Salvar(@RequestBody @Valid AlunoDto novoAluno) {
        AlunoDto alunoDto = alunoService.salvar(novoAluno);
        return ResponseEntity.ok(novoAluno);
    }

    @Operation(
            summary = "Atualizar aluno",
            description = "Atualiza um aluno existente no banco de dados",
            tags = "Aluno"
    )
    @PutMapping
    public ResponseEntity<AlunoDto> Atualizar(@RequestBody @Valid AlunoDto alunoExistente) {
        AlunoDto alunoDto = alunoService.atualizar(alunoExistente);
        return ResponseEntity.ok(alunoExistente);
    }

    @Operation(
            summary = "Deletar aluno",
            description = "Deleta um aluno existente no banco de dados",
            tags = "Aluno"
    )
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") int id) {
        alunoService.deletar(id);
    }

    @Operation(
            summary = "Obter por disciplina",
            description = "Obtém todos os alunos matriculados em uma determinada disciplina",
            tags = "Aluno"
    )
    @GetMapping("/obter-por-disciplina/{id}")
    public ResponseEntity<Page<AlunoDto>> obterAlunosPorDisciplina(
            @PathVariable("id") int id,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.obterAlunosPorDisciplina(id, pageable));
    }

    @Operation(
            summary = "Obter por professor",
            description = "Obtém todos os alunos lecionados por um determinado professor",
            tags = "Aluno"
    )
    @GetMapping("/obter-por-professor/{id}")
    public ResponseEntity<Page<AlunoDto>> obterAlunosLecionadosAtualmentePorProfessor(
            @PathVariable("id") int id,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.obterAlunosLecionadosAtualmentePorProfessor(id, pageable));
    }

}

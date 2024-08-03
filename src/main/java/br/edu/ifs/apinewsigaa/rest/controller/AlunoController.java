package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.model.AlunoModel;
import br.edu.ifs.apinewsigaa.rest.dto.AlunoDto;
import br.edu.ifs.apinewsigaa.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoDto>> ObterTodos() {
        List<AlunoDto> alunoDtoList = alunoService.ObterTodos();
        return ResponseEntity.ok(alunoDtoList);
    }

    @GetMapping("/{mat}")
    public ResponseEntity<AlunoDto> ObterPorMatricula(@PathVariable("mat") String matricula) {
        AlunoDto alunoDto = alunoService.ObterPorMatricula(matricula);
        return ResponseEntity.ok(alunoDto);
    }

    @PostMapping
    public ResponseEntity<AlunoDto> Salvar(@RequestBody @Valid AlunoModel novoAluno) {
        AlunoDto alunoDto = alunoService.salvar(novoAluno);
        return ResponseEntity.ok(novoAluno.toDTO());
    }

    @PutMapping
    public ResponseEntity<AlunoDto> Atualizar(@RequestBody @Valid AlunoModel alunoExistente) {
        AlunoDto alunoDto = alunoService.atualizar(alunoExistente);
        return ResponseEntity.ok(alunoExistente.toDTO());
    }

    @DeleteMapping
    public void deletar(@RequestBody @Valid AlunoModel aluno) {
        alunoService.deletar(aluno);
    }
}

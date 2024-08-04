package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.model.AlunoModel;
import br.edu.ifs.apinewsigaa.rest.dto.AlunoDto;
import br.edu.ifs.apinewsigaa.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<Page<AlunoDto>> ObterTodos(Pageable pageable) {
        return ResponseEntity.ok(alunoService.obterTodos(pageable));
    }

    @GetMapping("/{mat}")
    public ResponseEntity<AlunoDto> ObterPorMatricula(@PathVariable("mat") String matricula) {
        AlunoDto alunoDto = alunoService.obterPorMatricula(matricula);
        return ResponseEntity.ok(alunoDto);
    }

    @PostMapping
    public ResponseEntity<AlunoDto> Salvar(@RequestBody @Valid AlunoDto novoAluno) {
        AlunoDto alunoDto = alunoService.salvar(novoAluno);
        return ResponseEntity.ok(novoAluno);
    }

    @PutMapping
    public ResponseEntity<AlunoDto> Atualizar(@RequestBody @Valid AlunoDto alunoExistente) {
        AlunoDto alunoDto = alunoService.atualizar(alunoExistente);
        return ResponseEntity.ok(alunoExistente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") int id) {
        alunoService.deletar(id);
    }
}

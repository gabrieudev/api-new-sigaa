package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.rest.dto.MatriculaDto;
import br.edu.ifs.apinewsigaa.service.MatriculaService;
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

    @PostMapping
    public ResponseEntity<MatriculaDto> salvar(@Valid @RequestBody MatriculaDto matriculaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaService.salvar(matriculaDto));
    }

    @GetMapping
    public ResponseEntity<Page<MatriculaDto>> obterTodas(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(matriculaService.obterTodas(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDto> obterPorId(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(matriculaService.obterPorId(id));
    }

    @PutMapping
    public ResponseEntity<MatriculaDto> atualizar(@Valid @RequestBody MatriculaDto matriculaDto) {
        return ResponseEntity.status(HttpStatus.OK).body(matriculaService.atualizar(matriculaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") int id) {
        matriculaService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

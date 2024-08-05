package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.DataIntegrityException;
import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.MatriculaModel;
import br.edu.ifs.apinewsigaa.repository.MatriculaRepository;
import br.edu.ifs.apinewsigaa.rest.dto.MatriculaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private TurmaService turmaService;

    @Transactional(readOnly = true)
    public MatriculaDto obterPorId(int id) {
        MatriculaModel matriculaModel = matriculaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("ERRO: Matrícula não encontrada! ID: " + id));
        return matriculaModel.toDto();
    }

    @Transactional(readOnly = true)
    public Page<MatriculaDto> obterTodas(Pageable pageable) {
        return matriculaRepository.findAll(pageable)
                .map(MatriculaModel::toDto);
    }

    @Transactional
    public MatriculaDto salvar (MatriculaDto matriculaDto){
        if (matriculaRepository.existsByIdAlunoAndIdTurma(matriculaDto.getIdAluno(), matriculaDto.getIdTurma())) {
            throw new DataIntegrityException("Aluno já está matriculado nesta turma.");
        }
        if (!alunoService.existsById(matriculaDto.getIdAluno())) {
            throw new DataIntegrityException("Erro: ID do aluno não encontrado!");
        }
        if (!turmaService.existsById(matriculaDto.getIdTurma())) {
            throw new DataIntegrityException("Erro: ID da turma não encontrado!");
        }
        try {
            return matriculaRepository.save(matriculaDto.toModel()).toDto();
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Erro ao criar uma nova matrícula.");
        }
    }

    @Transactional
    public MatriculaDto atualizar (MatriculaDto matriculaDto){
        if (!matriculaRepository.existsById(matriculaDto.getId())) {
            throw new ObjectNotFoundException("ERRO: Matrícula não encontrada! ID: " + matriculaDto.getId());
        }
        try {
            return matriculaRepository.save(matriculaDto.toModel()).toDto();
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Erro ao atualizar uma matrícula.");
        }
    }

    @Transactional
    public void deletar(int id){
        if (!matriculaRepository.existsById(id)) {
            throw new ObjectNotFoundException("ERRO: Matrícula não encontrada! ID: " + id);
        }
        try {
            matriculaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Erro ao deletar uma matrícula.");
        }
    }

    @Transactional(readOnly = true)
    public Page<MatriculaDto> obterPorIdAluno(int idAluno, Pageable pageable) {
        try {
            return matriculaRepository.findByIdAluno(idAluno, pageable)
                    .map(MatriculaModel::toDto);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Erro ao obter matrículas.");
        }
    }

    @Transactional(readOnly = true)
    public Page<MatriculaDto> obterPorIdTurma(int idTurma, Pageable pageable) {
        try {
            return matriculaRepository.findByIdTurma(idTurma, pageable)
                    .map(MatriculaModel::toDto);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Erro ao obter matrículas.");
        }
    }

}

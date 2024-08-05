package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.DataIntegrityException;
import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.TurmaModel;
import br.edu.ifs.apinewsigaa.repository.TurmaRepository;
import br.edu.ifs.apinewsigaa.rest.dto.TurmaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private DisciplinaService disciplinaService;

    @Transactional(readOnly = true)
    public TurmaDto obterPorId(int id) {
        TurmaModel turmaModel = turmaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("ERRO: Turma não encontrada! ID: " + id));
        return turmaModel.toDto();
    }

    @Transactional(readOnly = true)
    public Page<TurmaDto> obterTodas(Pageable pageable) {
        return turmaRepository.findAll(pageable)
                .map(TurmaModel::toDto);
    }

    @Transactional
    public TurmaDto salvar (TurmaDto turmaDto){
        if (turmaRepository.existsByIdProfessorAndIdDisciplina(turmaDto.getIdProfessor(), turmaDto.getIdDisciplina())) {
            throw new DataIntegrityException("Já existe uma turma os IDs fornecidos.");
        }
        if (!professorService.existsById(turmaDto.getIdProfessor())) {
            throw new DataIntegrityException("Erro: ID do professor não encontrado!");
        }
        if (!disciplinaService.existsById(turmaDto.getIdDisciplina())) {
            throw new DataIntegrityException("Erro: ID da disciplina não encontrado!");
        }
        try {
            return turmaRepository.save(turmaDto.toModel()).toDto();
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Erro ao criar uma nova turma.");
        }
    }

    @Transactional
    public TurmaDto atualizar (TurmaDto turmaDto){
        if (!turmaRepository.existsById(turmaDto.getId())) {
            throw new ObjectNotFoundException("ERRO: Turma não encontrada! ID: " + turmaDto.getId());
        }
        try {
            return turmaRepository.save(turmaDto.toModel()).toDto();
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Erro ao atualizar uma turma.");
        }
    }

    @Transactional
    public void deletar(int id){
        if (!turmaRepository.existsById(id)) {
            throw new ObjectNotFoundException("ERRO: Turma não encontrada! ID: " + id);
        }
        try {
            turmaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Erro ao deletar uma turma.");
        }
    }

    @Transactional(readOnly = true)
    public Page<TurmaDto> obterPorIdDisciplina(int idDisciplina, Pageable pageable) {
        try {
            return turmaRepository.findByIdDisciplina(idDisciplina, pageable)
                    .map(TurmaModel::toDto);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Erro ao obter turmas.");
        }
    }

    @Transactional(readOnly = true)
    public Page<TurmaDto> obterPorIdProfessor(int idProfessor, Pageable pageable) {
        try {
            return turmaRepository.findByIdProfessor(idProfessor, pageable)
                    .map(TurmaModel::toDto);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Erro ao obter turmas.");
        }
    }

    public boolean existsById(int id) {
        return turmaRepository.existsById(id);
    }

}

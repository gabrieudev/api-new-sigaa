package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.DataIntegrityException;
import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.AlunoModel;
import br.edu.ifs.apinewsigaa.repository.AlunoRepository;
import br.edu.ifs.apinewsigaa.rest.dto.AlunoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional(readOnly = true)
    public AlunoDto obterPorMatricula(String matricula) {
        Optional<AlunoModel> alunoOptional = alunoRepository.findByMatricula(matricula);
        AlunoModel alunoModel = alunoOptional.orElseThrow(() ->
                new ObjectNotFoundException("ERRO: Matrícula não encontrada! Matrícula: " + matricula));

        return alunoModel.toDTO();
    }

    @Transactional(readOnly = true)
    public Page<AlunoDto> obterTodos(Pageable pageable) {
        return alunoRepository.findAll(pageable)
                .map(AlunoModel::toDTO);
    }

     @Transactional
    public AlunoDto salvar (AlunoDto novoAluno){
        try {
            return alunoRepository.save(novoAluno.toModel()).toDTO();
        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro ao criar um novo aluno.");
        }
    }

    @Transactional
    public AlunoDto atualizar (AlunoDto alunoExistente){
        try {
            return alunoRepository.save(alunoExistente.toModel()).toDTO();
        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro ao atualizar um aluno.");
        }
    }

    @Transactional
    public void deletar(int id){
        try {
            alunoRepository.deleteById(id);
        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro ao deletar um aluno.");
        }
    }

    @Transactional(readOnly = true)
    public Page<AlunoDto> obterAlunosPorDisciplina(int idDisciplina, Pageable pageable) {
        try {
            return alunoRepository.obterAlunosPorDisciplina(idDisciplina, pageable)
                    .map(AlunoModel::toDTO);
        } catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro ao obter os alunos.");
        }
    }

    @Transactional(readOnly = true)
    public Page<AlunoDto> obterAlunosLecionadosAtualmentePorProfessor(int idProfessor, Pageable pageable) {
        try {
            return alunoRepository.obterAlunosLecionadosAtualmentePorProfessor(idProfessor, Date.from(Instant.now()), pageable)
                    .map(AlunoModel::toDTO);
        } catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro ao obter os alunos.");
        }
    }

}

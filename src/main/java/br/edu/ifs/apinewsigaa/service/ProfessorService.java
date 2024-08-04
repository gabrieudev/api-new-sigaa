package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.DataIntegrityException;
import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.ProfessorModel;
import br.edu.ifs.apinewsigaa.repository.ProfessorRepository;
import br.edu.ifs.apinewsigaa.rest.dto.ProfessorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Transactional(readOnly = true)
    public ProfessorDto ObterPorMatricula(int matricula) {
        ProfessorModel professorModel = professorRepository.findByMatricula(matricula)
                .orElseThrow(() -> new ObjectNotFoundException("ERRO: Matrícula não encontrada! Matrícula: " + matricula));
        return professorModel.toDto();
    }

    @Transactional(readOnly = true)
    public Page<ProfessorDto> ObterTodos(Pageable pageable) {
        return professorRepository.findAll(pageable)
                .map(ProfessorModel::toDto);
    }

    @Transactional
    public ProfessorDto salvar (ProfessorDto professorDto){
        try {
            return professorRepository.save(professorDto.toModel()).toDto();
        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro ao criar um novo professor.");
        }
    }

    @Transactional
    public ProfessorDto atualizar (ProfessorDto professorDto){
        if (!professorRepository.existsById(professorDto.getId())) {
            throw new ObjectNotFoundException("ERRO: Professor não encontrado! ID: " + professorDto.getId());
        }
        try {
            return professorRepository.save(professorDto.toModel()).toDto();
        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro ao atualizar um professor.");
        }
    }

    @Transactional
    public void deletar(int id){
        if (!professorRepository.existsById(id)) {
            throw new ObjectNotFoundException("ERRO: Professor não encontrado! ID: " + id);
        }
        try {
            professorRepository.deleteById(id);
        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro ao deletar um professor.");
        }
    }

}

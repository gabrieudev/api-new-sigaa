package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.DataIntegrityException;
import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.DisciplinaModel;
import br.edu.ifs.apinewsigaa.repository.DisciplinaRepository;
import br.edu.ifs.apinewsigaa.rest.dto.DisciplinaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Transactional(readOnly = true)
    public DisciplinaDto obterPorId(int id) {
        DisciplinaModel disciplinaModel = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("ERRO: Disciplina não encontrada! ID: " + id));
        return disciplinaModel.toDto();
    }

    @Transactional(readOnly = true)
    public Page<DisciplinaDto> obterTodas(Pageable pageable) {
        return disciplinaRepository.findAll(pageable)
                .map(DisciplinaModel::toDto);
    }

    @Transactional
    public DisciplinaDto salvar (DisciplinaDto disciplinaDto){
        try {
            return disciplinaRepository.save(disciplinaDto.toModel()).toDto();
        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro ao criar uma nova disciplina.");
        }
    }

    @Transactional
    public DisciplinaDto atualizar (DisciplinaDto disciplinaDto){
        if (!disciplinaRepository.existsById(disciplinaDto.getId())) {
            throw new ObjectNotFoundException("ERRO: Disciplina não encontrada! ID: " + disciplinaDto.getId());
        }
        try {
            return disciplinaRepository.save(disciplinaDto.toModel()).toDto();
        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro ao atualizar uma disciplina.");
        }
    }

    @Transactional
    public void deletar(int id){
        if (!disciplinaRepository.existsById(id)) {
            throw new ObjectNotFoundException("ERRO: Disciplina não encontrada! ID: " + id);
        }
        try {
            disciplinaRepository.deleteById(id);
        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro ao deletar uma disciplina.");
        }
    }

}

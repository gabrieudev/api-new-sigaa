package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.DataIntegrityException;
import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.MatriculaModel;
import br.edu.ifs.apinewsigaa.repository.MatriculaRepository;
import br.edu.ifs.apinewsigaa.rest.dto.MatriculaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Transactional(readOnly = true)
    public MatriculaDto ObterPorId(int id) {
        MatriculaModel matriculaModel = matriculaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("ERRO: Matrícula não encontrada! ID: " + id));
        return matriculaModel.toDto();
    }

    @Transactional(readOnly = true)
    public Page<MatriculaDto> ObterTodas(Pageable pageable) {
        return matriculaRepository.findAll(pageable)
                .map(MatriculaModel::toDto);
    }

    @Transactional
    public MatriculaDto salvar (MatriculaDto matriculaDto){
        try {
            return matriculaRepository.save(matriculaDto.toModel()).toDto();
        }catch (DataIntegrityException e){
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
        }catch (DataIntegrityException e){
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
        }catch (DataIntegrityException e){
            throw new DataIntegrityException("Erro ao deletar uma matrícula.");
        }
    }

}

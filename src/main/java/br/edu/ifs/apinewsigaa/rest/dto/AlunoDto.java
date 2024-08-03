package br.edu.ifs.apinewsigaa.rest.dto;

import br.edu.ifs.apinewsigaa.model.AlunoModel;
import jakarta.persistence.Column;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class AlunoDto {
    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    public AlunoModel toModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, AlunoModel.class);
    }
}

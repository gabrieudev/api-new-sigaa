package br.edu.ifs.apinewsigaa.model;

import br.edu.ifs.apinewsigaa.rest.dto.MatriculaDto;
import br.edu.ifs.apinewsigaa.rest.dto.ProfessorDto;
import jakarta.persistence.*;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
@Entity
@Table(name = "matricula")
public class MatriculaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "idTurma", nullable = false)
    private int idTurma;
    @Column(name = "idAluno", nullable = false)
    private int idAluno;

    public MatriculaDto toDto() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, MatriculaDto.class);
    }
}

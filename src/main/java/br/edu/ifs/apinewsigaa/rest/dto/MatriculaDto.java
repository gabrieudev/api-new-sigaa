package br.edu.ifs.apinewsigaa.rest.dto;

import br.edu.ifs.apinewsigaa.model.MatriculaModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaDto {
    private long id;

    @NotNull(message = "ID da turma obrigatório")
    @Positive(message = "ID da turma precisa ser positivo")
    private int idTurma;

    @NotNull(message = "ID do aluno obrigatório")
    @Positive(message = "ID do aluno precisa ser positivo")
    private int idAluno;

    public MatriculaModel toModel() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, MatriculaModel.class);
    }
}

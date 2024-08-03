package br.edu.ifs.apinewsigaa.rest.dto;

import br.edu.ifs.apinewsigaa.model.TurmaModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurmaDto {
    private long id;

    @NotNull(message = "Data de início obrigatória")
    private Date dataInicio;

    private Date dataFim;

    @NotNull(message = "ID do(a) professor(a) é obrigatório")
    @Positive(message = "ID do(a) professor(a) precisa ser positivo")
    private int idProfessor;

    @NotNull(message = "ID da disciplina é obrigatório")
    @Positive(message = "ID da disciplina precisa ser positivo")
    private int idDisciplina;

    public TurmaModel toModel() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, TurmaModel.class);
    }
}

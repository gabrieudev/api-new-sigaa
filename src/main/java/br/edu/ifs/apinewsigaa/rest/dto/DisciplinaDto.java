package br.edu.ifs.apinewsigaa.rest.dto;

import br.edu.ifs.apinewsigaa.model.DisciplinaModel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisciplinaDto {
    private int id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Número de créditos é obrigatório")
    @Positive(message = "Número de créditos precisa ser positivo")
    private int numeroCreditos;

    public DisciplinaModel toModel() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, DisciplinaModel.class);
    }
}

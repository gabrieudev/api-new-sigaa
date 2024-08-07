package br.edu.ifs.apinewsigaa.rest.dto;

import br.edu.ifs.apinewsigaa.model.ProfessorModel;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDto {
    private int id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @CPF(message = "CPF inválido")
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento precisa estar no passado")
    private Date dataNascimento;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Celular é obrigatório")
    @Size(min = 14, max = 14, message = "Celular inválido")
    private String celular;

    @NotNull(message = "Matrícula é obrigatória")
    private int matricula;

    public ProfessorModel toModel() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ProfessorModel.class);
    }
}

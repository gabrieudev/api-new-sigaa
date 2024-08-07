package br.edu.ifs.apinewsigaa.rest.dto;

import br.edu.ifs.apinewsigaa.model.AlunoModel;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDto {
    private int id;

    @NotBlank(message = "Nome obrigatório")
    private String nome;

    @CPF(message = "CPF inválido")
    @NotBlank(message = "CPF obrigatório")
    private String cpf;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail obrigatório")
    private String email;

    @NotNull(message = "Data de nascimento obrigatória")
    @Past(message = "Data de nascimento precisa estar no passado")
    private Date dataNascimento;

    @NotBlank(message = "Celular obrigatório")
    @Size(min = 14, max = 14, message = "Celular inválido")
    private String celular;

    private String apelido;

    @NotBlank(message = "Matrícula obrigatória")
    private String matricula;

    public AlunoModel toModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, AlunoModel.class);
    }
}

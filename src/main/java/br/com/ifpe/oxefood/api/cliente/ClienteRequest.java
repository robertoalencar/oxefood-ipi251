package br.com.ifpe.oxefood.api.cliente;

import java.time.LocalDate;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.acesso.Perfil;
import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    @NotBlank(message = "O e-mail é de preenchimento obrigatório")
    @Email
    private String email;

    @NotBlank(message = "A senha é de preenchimento obrigatório")
    private String password;

    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private String cpf;

    private String foneCelular;

    private String foneFixo;

    public Usuario buildUsuario() {

        return Usuario.builder()
            .username(email)
            .password(password)
            .roles(Arrays.asList(new Perfil(Perfil.ROLE_CLIENTE)))
            .build();
    }

    public Cliente build() {

        Cliente c = Cliente.builder()
            .usuario(buildUsuario())
            .nome(nome)
            .dataNascimento(dataNascimento)
            .cpf(cpf)
            .foneCelular(foneCelular)
            .foneFixo(foneFixo)
            .build();

        return c;
    }

}


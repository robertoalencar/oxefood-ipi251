package br.com.ifpe.oxefood.api.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import br.com.ifpe.oxefood.modelo.cliente.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin
@Tag(
    name = "API Cliente",
    description = "API responsável pelos servidos de cliente no sistema"
)
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuarioService usuarioService;

    @Operation(
        summary = "Serviço responsável por salvar um cliente no sistema.",
        description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
    )
    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody ClienteRequest clienteRequest, HttpServletRequest request) {

        Cliente cliente = clienteService.save(clienteRequest.build(), usuarioService.obterUsuarioLogado(request));
        return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Serviço responsável por listar dos os clientes do sistema.",
        description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
    )
    @GetMapping
    public List<Cliente> listarTodos() {

        return clienteService.listarTodos();
    }

    @Operation(
        summary = "Serviço responsável consultar um cliente de acordo com o ID recebido na URL.",
        description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
    )
    @GetMapping("/{id}")
    public Cliente obterPorID(@PathVariable Long id) {
        
        return clienteService.obterPorID(id);
    }

    @Operation(
        summary = "Serviço responsável por alterar um cliente no sistema.",
        description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
    )
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable("id") Long id, @RequestBody ClienteRequest clienteRequest, HttpServletRequest request) {

        clienteService.update(id, clienteRequest.build(), usuarioService.obterUsuarioLogado(request));
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Serviço responsável por remover um cliente no sistema.",
        description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        clienteService.delete(id);
        return ResponseEntity.ok().build();
    }

}

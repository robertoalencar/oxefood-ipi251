package br.com.ifpe.oxefood.modelo.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.modelo.acesso.Perfil;
import br.com.ifpe.oxefood.modelo.acesso.PerfilRepository;
import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import br.com.ifpe.oxefood.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefood.modelo.mensagens.EmailService;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilRepository perfilUsuarioRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public Cliente save(Cliente cliente, Usuario usuarioLogado) {

        usuarioService.save(cliente.getUsuario());

        for (Perfil perfil : cliente.getUsuario().getRoles()) {
            perfil.setHabilitado(Boolean.TRUE);
            perfilUsuarioRepository.save(perfil);
        }

        cliente.setHabilitado(Boolean.TRUE);
        cliente.setCriadoPor(usuarioLogado);

        Cliente c = repository.save(cliente);

        emailService.enviarEmailConfirmacaoCadastroCliente(c);

        return c;
    }

    public List<Cliente> listarTodos() {

        return repository.findAll(); // SELECT * FROM Cliente
    }

    public Cliente obterPorID(Long id) {

        return repository.findById(id).get(); // SELECT * FROM Cliente WHERE id = ?
    }

    @Transactional
    public void update(Long id, Cliente clienteAlterado, Usuario usuarioLogado) {

        Cliente cliente = repository.findById(id).get();

        cliente.setNome(clienteAlterado.getNome());
        cliente.setDataNascimento(clienteAlterado.getDataNascimento());
        cliente.setCpf(clienteAlterado.getCpf());
        cliente.setFoneCelular(clienteAlterado.getFoneCelular());
        cliente.setFoneFixo(clienteAlterado.getFoneFixo());
        cliente.setUltimaModificacaoPor(usuarioLogado);

        repository.save(cliente);
    }

    @Transactional
    public void delete(Long id) {

        Cliente cliente = repository.findById(id).get();
        cliente.setHabilitado(Boolean.FALSE);

        repository.save(cliente);
    }

}

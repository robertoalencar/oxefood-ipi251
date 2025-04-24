package br.com.ifpe.oxefood.modelo.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repository;

    @Transactional
    public Cliente save(Cliente cliente) {

        cliente.setHabilitado(Boolean.TRUE);
        Cliente c  = repository.save(cliente);

        return c;
    }

    public List<Cliente> listarTodos() {
  
        return repository.findAll(); //SELECT * FROM Cliente
    }

    public Cliente obterPorID(Long id) {

        return repository.findById(id).get(); //SELECT * FROM Cliente WHERE id = ?
    }

}

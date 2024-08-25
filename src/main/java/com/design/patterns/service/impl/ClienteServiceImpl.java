package com.design.patterns.service.impl;

import com.design.patterns.handler.NoClientsFoundException;
import com.design.patterns.handler.ResourceNotFoundException;
import com.design.patterns.handler.ValidationException;
import com.design.patterns.model.Cliente;
import com.design.patterns.model.Endereco;
import com.design.patterns.repository.ClienteRepository;
import com.design.patterns.repository.EnderecoRepository;
import com.design.patterns.service.ClienteService;
import com.design.patterns.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.Optional;

@Service
@Validated
public class ClienteServiceImpl implements ClienteService {

    // Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    // Strategy: Implementar os métodos definidos na interface.
    // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Cliente> buscarTodos() {
       Iterable<Cliente> clientes = clienteRepository.findAll();
        if (!clientes.iterator().hasNext()) {
            throw new NoClientsFoundException("Não tem clientes cadastrados neste banco.");
        }
        return clientes;
    }

    @Override
    public Cliente buscarPorId(Long id) {
       return clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente com ID " + id + " não encontrado."));
    }

    @Override
    public Cliente inserir(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new ValidationException("O nome não pode ser null ou vazio.");
        }
        if (cliente.getNome().length() < 3) {
            throw new ValidationException("O nome deve ter pelo menos 3 caracteres.");
        }
        String cep = cliente.getEndereco().getCep();
        if (cliente.getEndereco() == null ||
                cliente.getEndereco().getCep() == null ||
                cliente.getEndereco().getCep().trim().isEmpty()) {
            throw new ValidationException("O CEP não pode ser nulo ou vazio.");
        }
        if (cep.length() < 8) {
            throw new ValidationException("CEP inválido. O CEP deve ter pelo menos 8 caracteres.");
        }
        salvarClienteComCep(cliente);
        return cliente;
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new ValidationException("O nome não pode ser null ou vazio.");
        }
        if (cliente.getNome().length() < 3) {
            throw new ValidationException("O nome deve ter pelo menos 3 caracteres.");
        }
        String cep = cliente.getEndereco().getCep();
        if (cliente.getEndereco() == null ||
                cliente.getEndereco().getCep() == null ||
                cliente.getEndereco().getCep().trim().isEmpty()) {
            throw new ValidationException("O CEP não pode ser nulo ou vazio.");
        }
        if (cep.length() < 8) {
            throw new ValidationException("CEP inválido. O CEP deve ter pelo menos 8 caracteres.");
        }
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (!clienteBd.isPresent()) {
            throw new NoClientsFoundException("Cliente não encontrado com o ID: " + id);
        }
        salvarClienteComCep(cliente);
    }

    @Override
    public void deletar(Long id) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (!clienteBd.isPresent()) {
            throw new NoClientsFoundException("Cliente não encontrado com o ID: " + id);
        }
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

}

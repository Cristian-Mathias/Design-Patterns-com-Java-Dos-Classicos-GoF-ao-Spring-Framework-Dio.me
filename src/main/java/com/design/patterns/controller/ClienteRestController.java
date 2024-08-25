package com.design.patterns.controller;

import com.design.patterns.model.Cliente;
import com.design.patterns.service.ClienteService;
import com.design.patterns.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Iterable<Cliente>>buscarTodos(){
        return ResponseEntity.ok(clienteService.buscarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }
    @PostMapping
    public ResponseEntity<Map<String,Object>>inserir(@RequestBody Cliente cliente){
       clienteService.inserir(cliente);
        Map<String,Object> responseBody = ResponseUtil.createResponse("Cliente inserido com sucesso", HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body((Map<String, Object>) responseBody);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>>atualizar(@PathVariable Long id, @RequestBody Cliente cliente){
        clienteService.atualizar(id, cliente);
        Map<String,Object> responseBody = ResponseUtil.createResponse("Dados do cliente atualizado com sucesso", HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body((Map<String, Object>) responseBody);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        Map<String, Object> responseBody = ResponseUtil.createResponse("Recurso deletado com sucesso", HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}

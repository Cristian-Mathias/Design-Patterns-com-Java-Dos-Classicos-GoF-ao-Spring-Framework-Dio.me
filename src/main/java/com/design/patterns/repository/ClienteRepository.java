package com.design.patterns.repository;

import com.design.patterns.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository                              //CrudRepository é uma strategy
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}

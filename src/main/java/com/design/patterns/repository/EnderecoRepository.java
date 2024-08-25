package com.design.patterns.repository;

import com.design.patterns.model.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository                               //CrudRepository é uma strategy
public interface EnderecoRepository extends CrudRepository<Endereco,String> {
}

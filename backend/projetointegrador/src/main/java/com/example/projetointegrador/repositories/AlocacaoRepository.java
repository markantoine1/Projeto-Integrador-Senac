package com.example.projetointegrador.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.projetointegrador.entities.Alocacao;

	
@Repository
public interface AlocacaoRepository extends MongoRepository<Alocacao, String>{

}

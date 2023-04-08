package com.example.senac.alocacao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.senac.alocacao.entities.Curso;

@Repository
public interface CursoRepository extends MongoRepository<Curso, String>{

}

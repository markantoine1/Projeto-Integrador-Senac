package com.example.projetointegrador.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.projetointegrador.entities.Curso;


@Repository
public interface CursoRepository extends MongoRepository<Curso, String>{

}

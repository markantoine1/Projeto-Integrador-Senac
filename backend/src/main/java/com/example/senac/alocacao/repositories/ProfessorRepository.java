package com.example.senac.alocacao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.senac.alocacao.entities.Professor;

@Repository
public interface ProfessorRepository extends MongoRepository<Professor, String>{

}

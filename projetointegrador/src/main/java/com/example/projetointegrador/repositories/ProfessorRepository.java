package com.example.projetointegrador.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.projetointegrador.entities.Professor;

@Repository
public interface ProfessorRepository extends MongoRepository<Professor, String>{

}

package com.example.senac.alocacao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.senac.alocacao.entities.Curso;
import com.example.senac.alocacao.entities.Professor;
import com.example.senac.alocacao.repositories.CursoRepository;
import com.example.senac.alocacao.repositories.ProfessorRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Professor p1 = new Professor(null, "João", "Manhã");
		professorRepository.save(p1);
		
		Curso c1 = new Curso(null, "Técnico em Enfermagem", "Tarde");
		cursoRepository.save(c1);
	}

}

package com.example.projetointegrador.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.projetointegrador.entities.Curso;
import com.example.projetointegrador.repositories.CursoRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

		@Autowired
		private CursoRepository cursoRepository;
		
		@Override
		public void run(String... args) throws Exception {
			
			Curso c1 = new Curso(null, "Técnico em Enfermagem", "Noite");
			cursoRepository.save(c1);
		}

	}
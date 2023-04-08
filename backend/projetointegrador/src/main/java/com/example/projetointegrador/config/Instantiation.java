package com.example.projetointegrador.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.projetointegrador.entities.Alocacao;
import com.example.projetointegrador.repositories.AlocacaoRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

		@Autowired
		private AlocacaoRepository alocacaoRepository;
		
		@Override
		public void run(String... args) throws Exception {
			Alocacao a1 = new Alocacao(null, "Sala 1", new Date(), "marcos", "Téc em Enfermagem");
			alocacaoRepository.save(a1);
		}

	}
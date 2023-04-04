package com.example.senac.alocacao.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.senac.alocacao.entities.Professor;
import com.example.senac.alocacao.services.ProfessorService;

@RestController
@RequestMapping(value = "/professores")
public class ProfessorResource {
	
	@Autowired
	private ProfessorService professorService;
	
	@PostMapping
	private ResponseEntity<Professor> create(@RequestBody Professor professor){
		professorService.create(professor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(professor.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	private ResponseEntity<List<Professor>> read(){
		List<Professor> resposta  = professorService.read();
		return ResponseEntity.ok().body(resposta);
	}
}

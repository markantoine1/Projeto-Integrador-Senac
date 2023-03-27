package com.example.projetointegrador.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.projetointegrador.entities.Professor;
import com.example.projetointegrador.services.ProfessorService;

@RestController
@RequestMapping(value = "/professores")
public class ProfessorResource {
	@Autowired
	private ProfessorService service;
	
	@GetMapping
	public ResponseEntity<List<Professor>> findAll(){
		List<Professor> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Professor> findById(@PathVariable String id){
		Professor obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
 	public ResponseEntity<Professor> insert(@RequestBody Professor obj) {
		service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
 	public ResponseEntity<Professor> update(@RequestBody Professor obj,@PathVariable String id) {
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
 	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}

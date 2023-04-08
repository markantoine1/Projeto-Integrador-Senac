package com.example.projetointegrador.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.projetointegrador.entities.Alocacao;
import com.example.projetointegrador.services.AlocacaoService;

@CrossOrigin
@RestController
@RequestMapping(value = "/alocacoes")
public class AlocacaoResource {
	@Autowired
	private AlocacaoService service;
	
	@GetMapping
	public ResponseEntity<List<Alocacao>> findAll(){
		List<Alocacao> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Alocacao> findById(@PathVariable String id){
		Alocacao obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
 	public ResponseEntity<Alocacao> insert(@RequestBody Alocacao obj) {
		service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping(value = "/fromnode")
 	public ResponseEntity<Alocacao> insertFromNode(@RequestBody Alocacao obj) {
		obj.setDate(new Date());
		service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
 	public ResponseEntity<Alocacao> update(@RequestBody Alocacao obj,@PathVariable String id) {
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

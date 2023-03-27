package com.example.projetointegrador.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetointegrador.entities.Curso;
import com.example.projetointegrador.repositories.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository repository;
	
	public List<Curso> findAll(){
		return repository.findAll();
	}
	
	public Curso findById(String id) {
		Optional<Curso> obj = repository.findById(id);
		return obj.get();
	}
	
	public Curso insert(Curso obj){
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Curso update(Curso obj) {
		Curso newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	private void updateData(Curso newObj, Curso obj) {
		newObj.setNome(obj.getNome());
		newObj.setTurno(obj.getTurno());
	}
}

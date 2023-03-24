package com.example.projetointegrador.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetointegrador.entities.Professor;
import com.example.projetointegrador.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository repository;
	
	public List<Professor> findAll(){
		return repository.findAll();
	}
	public Professor findById(String id) {
		Optional<Professor> obj = repository.findById(id);
		return obj.get();
	}
	
	public Professor insert(Professor obj){
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Professor update(Professor obj) {
		Professor newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	private void updateData(Professor newObj, Professor obj) {
		newObj.setNome(obj.getNome());
		newObj.setTurno(obj.getTurno());
	}
}


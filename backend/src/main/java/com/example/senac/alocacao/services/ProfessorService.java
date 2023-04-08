package com.example.senac.alocacao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.senac.alocacao.entities.Professor;
import com.example.senac.alocacao.repositories.ProfessorRepository;




@Service
public class ProfessorService {
	@Autowired
	private ProfessorRepository professorRepository;
	
	public void create(Professor professor) {
		professorRepository.insert(professor);
	}
	
	
	public List<Professor> read(){
		return professorRepository.findAll(); 
	}
	
	public Professor update(Professor oldObject) {
		Professor newObject =  findById(oldObject.getId());
		updateData(oldObject, newObject);
		return professorRepository.save(newObject);
	}
	
	public void delete(String id) {
		professorRepository.deleteById(id);
	}
	
	public Professor findById(String id) {
		Optional<Professor> professor = professorRepository.findById(id);
		return professor.get();
	}
	
	private void updateData(Professor oldObject, Professor newObject) {
		newObject.setNome(oldObject.getNome());
		newObject.setTurno(oldObject.getTurno());
	}
}

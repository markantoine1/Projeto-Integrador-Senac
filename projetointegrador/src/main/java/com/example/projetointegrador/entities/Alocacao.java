package com.example.projetointegrador.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Alocacao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String sala;
	private Date data;
	private String nomeProfessor;
	private String nomeCurso;

	public Alocacao() {
	}
	
	public Alocacao(String id, String sala, Date date, String nomeProfessor, String nomeCurso) {
		this.id = id;
		this.sala = sala;
		this.data = date;
		this.nomeProfessor = nomeProfessor;
		this.nomeCurso = nomeCurso;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public Date getDate() {
		return data;
	}

	public void setDate(Date date) {
		this.data = date;
	}

	public String getProfessor() {
		return nomeProfessor;
	}

	public void setProfessor(String professor) {
		this.nomeProfessor = professor;
	}

	public String getCurso() {
		return nomeCurso;
	}

	public void setCurso(String curso) {
		this.nomeCurso = curso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alocacao other = (Alocacao) obj;
		return Objects.equals(id, other.id);
	}
}

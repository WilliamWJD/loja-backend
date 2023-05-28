package com.william.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.william.cursomc.domain.Categoria;

import jakarta.validation.constraints.NotEmpty;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

	public CategoriaDTO() {
	}
	
	public CategoriaDTO(Categoria entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "CategoriaDTO [id=" + id + ", nome=" + nome + "]";
	}
}

package com.william.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.william.cursomc.domain.Categoria;
import com.william.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(Integer id){
		return categoriaRepository.findById(id).orElseThrow(()->new RuntimeException("Categoria não encontrada"));
	}
}

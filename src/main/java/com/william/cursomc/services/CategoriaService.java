package com.william.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.william.cursomc.domain.Categoria;
import com.william.cursomc.repositories.CategoriaRepository;
import com.william.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria buscar(Integer id) {
		return categoriaRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada com o id: " + id));
	}
	
	public Categoria salvar(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}
}

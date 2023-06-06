package com.william.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.william.cursomc.domain.Categoria;
import com.william.cursomc.domain.Produto;
import com.william.cursomc.repositories.CategoriaRepository;
import com.william.cursomc.repositories.ProdutoRepository;
import com.william.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto find(Integer id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado com o id: " + id));
	}

	public Page<Produto> search(final String nome, List<Integer> ids, Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		List<Categoria> categorias = categoriaRepository.findAllById(ids);

		return produtoRepository.search(nome, categorias, pageRequest);
	}
}

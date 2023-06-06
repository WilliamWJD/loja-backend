package com.william.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.william.cursomc.domain.Produto;
import com.william.cursomc.dto.ProdutoDTO;
import com.william.cursomc.resources.utils.URL;
import com.william.cursomc.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Produto produto = produtoService.find(id);
		return ResponseEntity.ok(produto);
	}

	@GetMapping("/page")
	public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(name = "nome", defaultValue = "") String nome,
			@RequestParam(name = "categorias", defaultValue = "") String categorias,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) {
		Page<Produto> produtos = produtoService.search(URL.decodeParam(nome), URL.decodeIntList(categorias), page,
				linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = produtos.map(ProdutoDTO::new);
		return ResponseEntity.ok().body(listDto);
	}
}

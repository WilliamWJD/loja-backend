package com.william.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.william.cursomc.domain.Categoria;
import com.william.cursomc.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/{id}")
	public ResponseEntity<?> listar(@PathVariable Integer id) {
		Categoria buscar = categoriaService.buscar(id);
		return ResponseEntity.ok(buscar);
	}

	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Categoria categoria) {
		categoria = categoriaService.salvar(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
}

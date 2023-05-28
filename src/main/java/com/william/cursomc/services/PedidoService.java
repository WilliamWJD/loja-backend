package com.william.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.william.cursomc.domain.Pedido;
import com.william.cursomc.repositories.PedidoRepository;
import com.william.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido buscar(Integer id) {
		return pedidoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado com o id: " + id));
	}
}

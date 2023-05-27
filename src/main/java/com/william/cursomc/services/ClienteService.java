package com.william.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.william.cursomc.domain.Cliente;
import com.william.cursomc.repositories.ClienteRepository;
import com.william.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente buscar(Integer id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado com o id: " + id));
	}
}

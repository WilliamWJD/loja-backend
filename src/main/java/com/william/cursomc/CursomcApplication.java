package com.william.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.william.cursomc.domain.Categoria;
import com.william.cursomc.domain.Cidade;
import com.william.cursomc.domain.Cliente;
import com.william.cursomc.domain.Endereco;
import com.william.cursomc.domain.Estado;
import com.william.cursomc.domain.Produto;
import com.william.cursomc.domain.enums.TipoCliente;
import com.william.cursomc.repositories.CategoriaRepository;
import com.william.cursomc.repositories.CidadeRepository;
import com.william.cursomc.repositories.ClienteRepository;
import com.william.cursomc.repositories.EnderecoRepository;
import com.william.cursomc.repositories.EstadoRepository;
import com.william.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// PRODUTO CATEGORIA
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		cat1.getProdutos().addAll(Arrays.asList(p1, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		// ESTADO CIDADE
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		// CLIENTE ENDEREÇO TELEFON
		Cliente cli1 = new Cliente(null, "William Dias", "william@gmail.com", "44421231", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("38645547","38542514"));
		
		Endereco e1 = new Endereco(null, "R. Denilson", "123", "casa", "Nova Terra", "13175254", cli1, c3);
		Endereco e2 = new Endereco(null, "R. Dos Bobos", "123", "casa", "Nova Bobo", "1317512", cli1, c1);
		
		cli1.setEnderecos(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
	}

}

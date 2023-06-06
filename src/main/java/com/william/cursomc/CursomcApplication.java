package com.william.cursomc;

import java.text.SimpleDateFormat;
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
import com.william.cursomc.domain.ItemPedido;
import com.william.cursomc.domain.Pagamento;
import com.william.cursomc.domain.PagamentoComBoleto;
import com.william.cursomc.domain.PagamentoComCartao;
import com.william.cursomc.domain.Pedido;
import com.william.cursomc.domain.Produto;
import com.william.cursomc.domain.enums.EstadoPagamento;
import com.william.cursomc.domain.enums.TipoCliente;
import com.william.cursomc.repositories.CategoriaRepository;
import com.william.cursomc.repositories.CidadeRepository;
import com.william.cursomc.repositories.ClienteRepository;
import com.william.cursomc.repositories.EnderecoRepository;
import com.william.cursomc.repositories.EstadoRepository;
import com.william.cursomc.repositories.ItemPedidoRepository;
import com.william.cursomc.repositories.PagamentoRepository;
import com.william.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// PRODUTO CATEGORIA
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 1250.00);
		Produto p5 = new Produto(null, "Toalha", 40.00);
		Produto p6 = new Produto(null, "Roteador", 230.00);
		Produto p7 = new Produto(null, "Teclado mecanico", 550.00);
		Produto p8 = new Produto(null, "Abajour Harry Potter", 180.00);
		Produto p9 = new Produto(null, "Fita de led", 25.00);

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Eletrodomestico");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Decoração");
		Categoria cat6 = new Categoria(null, "Permumaria");
		Categoria cat7 = new Categoria(null, "Produto de limpeza");

		cat1.getProdutos().addAll(Arrays.asList(p1, p3, p7, p6));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4, p9));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat7.getProdutos().addAll(Arrays.asList(p5));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat1));
		p5.getCategorias().addAll(Arrays.asList(cat7));
		p6.getCategorias().addAll(Arrays.asList(cat1));
		p7.getCategorias().addAll(Arrays.asList(cat1));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat2));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9));

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
		
		// PEDIDO
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("27/05/2023 14:56"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("25/05/2023 12:56"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("05/06/2023 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		// ITEMS DE PEDIDO
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));	
	}

}

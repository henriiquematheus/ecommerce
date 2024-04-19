package com.example.ecommerce;

import com.example.ecommerce.controllers.MenuController;
import com.example.ecommerce.controllers.ProdutoController;
import com.example.ecommerce.db.DatabaseInitializer;
import com.example.ecommerce.model.Produto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class EcommerceApplication {

	private static final Logger logger = LoggerFactory.getLogger(EcommerceApplication.class);

	private final ProdutoController produtoController;
	private final MenuController menuController;
	private final Scanner scanner;

	public EcommerceApplication(ProdutoController produtoController, MenuController menuController) {
		this.produtoController = produtoController;
		this.menuController = menuController;
		this.scanner = menuController.getScanner();
	}
//
	public static void main(String[] args) {
		DatabaseInitializer.main(args);

		ConfigurableApplicationContext context = SpringApplication.run(EcommerceApplication.class, args);
		EcommerceApplication application = context.getBean(EcommerceApplication.class);
		MenuController menuController = context.getBean(MenuController.class);

		int opcao;
		do {
			opcao = menuController.exibirMenu();
			application.processarOpcaoMenu(opcao);
		} while (opcao != 5);
		menuController.fecharScanner();
	}

	public void processarOpcaoMenu(int opcao) {
		switch (opcao) {
			case 1:
				System.out.println("Digite o nome do produto:");
				String nomeProduto = scanner.nextLine();
				System.out.println("Digite o preço do produto:");
				BigDecimal precoProduto = scanner.nextBigDecimal();
				scanner.nextLine();

				Produto novoProduto = new Produto();
				novoProduto.setNome(nomeProduto);
				novoProduto.setPreco(precoProduto);

				ResponseEntity<Produto> responseAdicionar = produtoController.adicionarProduto(novoProduto);
				if (responseAdicionar.getStatusCode() == HttpStatus.CREATED) {
					System.out.println("Produto adicionado com sucesso!");
				} else {
					System.out.println("Erro ao adicionar o produto.");
				}
				break;
			case 2:

				ResponseEntity<List<Produto>> responseListar = produtoController.listarProdutos();
				List<Produto> produtosListados = responseListar.getBody();
				produtosListados.forEach(System.out::println);
				break;
			case 3:

				System.out.println("Digite o ID do produto que deseja atualizar:");
				long idAtualizar = scanner.nextLong();
				scanner.nextLine();

				ResponseEntity<Produto> responseObter = produtoController.obterProduto(idAtualizar);
				if (responseObter.getStatusCode() == HttpStatus.OK) {
					Produto produtoExistente = responseObter.getBody();
					ResponseEntity<Produto> responseAtualizar = produtoController.atualizarProduto(idAtualizar, produtoExistente);
					System.out.println("Produto atualizado com sucesso!");
				} else {
					System.out.println("Produto não encontrado.");
				}
				break;
			case 4:

				System.out.println("Digite o ID do produto que deseja remover:");
				long idRemover = scanner.nextLong();
				scanner.nextLine(); //çimpar o buffer do scanner

				ResponseEntity<Void> responseRemover = produtoController.removerProduto(idRemover);
				if (responseRemover.getStatusCode() == HttpStatus.NO_CONTENT) {
					System.out.println("Produto removido com sucesso!");
				} else {
					System.out.println("Produto não encontrado.");
				}
				break;
			case 5:
				menuController.fecharScanner();
				break;
			default:
				System.out.println("Opção inválida!");
				break;
		}
	}
}

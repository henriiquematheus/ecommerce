package com.example.ecommerce;

import com.example.ecommerce.controllers.MenuController;
import com.example.ecommerce.controllers.ProdutoController;
import com.example.ecommerce.db.DatabaseInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EcommerceApplication {

	private final ProdutoController produtoController;
	private final MenuController menuController;

	public EcommerceApplication(ProdutoController produtoController, MenuController menuController) {
		this.produtoController = produtoController;
		this.menuController = menuController;
	}

	public static void main(String[] args) {
		DatabaseInitializer.main(args); // Chama o método main da classe DatabaseInitializer

		ConfigurableApplicationContext context = SpringApplication.run(EcommerceApplication.class, args);
		EcommerceApplication application = context.getBean(EcommerceApplication.class);
		MenuController menuController = context.getBean(MenuController.class);

		int opcao;
		do {
			opcao = menuController.exibirMenu();
			application.processarOpcaoMenu(opcao);
		} while (opcao != 5);
		// Fechar o scanner após o loop
		menuController.fecharScanner();
	}

	public void processarOpcaoMenu(int opcao) {
		switch (opcao) {
			case 1:
				produtoController.adicionarProduto();
				break;
			case 2:
				produtoController.listarProdutos();
				break;
			case 3:
				produtoController.atualizarProduto();
				break;
			case 4:
				produtoController.removerProduto();
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

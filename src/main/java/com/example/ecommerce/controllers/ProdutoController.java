package com.example.ecommerce.controllers;

import com.example.ecommerce.model.Produto;
import com.example.ecommerce.controllers.MenuController;
import com.example.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
public class ProdutoController {

    private final ProdutoService produtoService;
    private final MenuController menuController;

    @Autowired
    public ProdutoController(ProdutoService produtoService, MenuController menuController) {
        this.produtoService = produtoService;
        this.menuController = menuController;
    }

    public void adicionarProduto() {
        System.out.println("Digite o nome do produto:");
        String nome = menuController.getScanner().nextLine();
        System.out.println("Digite o preço do produto:");
        BigDecimal preco = menuController.getScanner().nextBigDecimal();
        menuController.getScanner().nextLine(); // Limpa o buffer do scanner

        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(preco);

        produtoService.adicionarProduto(produto);

        System.out.println("Produto adicionado com sucesso!");
    }

    public void listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        if (!produtos.isEmpty()) {
            System.out.println("Lista de Produtos:");
            produtos.forEach(System.out::println);
        } else {
            System.out.println("Não há produtos cadastrados.");
        }
    }

    public void atualizarProduto() {
        System.out.println("Digite o ID do produto que deseja atualizar:");
        long id = menuController.getScanner().nextLong();
        menuController.getScanner().nextLine(); // Limpa o buffer do scanner

        Optional<Produto> optionalProduto = produtoService.obterProduto(id);
        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            System.out.println("Digite o novo nome do produto:");
            String novoNome = menuController.getScanner().nextLine();
            System.out.println("Digite o novo preço do produto:");
            BigDecimal novoPreco = menuController.getScanner().nextBigDecimal();
            menuController.getScanner().nextLine(); // Limpa o buffer do scanner

            produto.setNome(novoNome);
            produto.setPreco(novoPreco);

            produtoService.atualizarProduto(produto);

            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public void removerProduto() {
        System.out.println("Digite o ID do produto que deseja remover:");
        long id = menuController.getScanner().nextLong();
        menuController.getScanner().nextLine(); // Limpa o buffer do scanner

        boolean removido = produtoService.removerProduto(id);
        if (removido) {
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}

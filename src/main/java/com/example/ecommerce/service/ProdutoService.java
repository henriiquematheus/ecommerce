package com.example.ecommerce.service;

import com.example.ecommerce.model.Produto;
import com.example.ecommerce.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto adicionarProduto(Produto produto) {
        log.info("Adicionando produto: {}", produto);
        Produto novoProduto = produtoRepository.save(produto);
        log.info("Produto adicionado: {}", novoProduto);
        return novoProduto;
    }

    @Transactional(readOnly = true)
    public List<Produto> listarProdutos() {
        log.info("Listando produtos...");
        List<Produto> produtos = produtoRepository.findAll();
        log.info("Produtos listados: {}", produtos);
        return produtos;
    }

    @Transactional
    public Produto atualizarProduto(Long id, Produto produto) {
        log.info("Atualizando produto com ID {}: {}", id, produto);
        produto.setId(id);
        Produto produtoAtualizado = produtoRepository.save(produto);
        log.info("Produto atualizado: {}", produtoAtualizado);
        return produtoAtualizado;
    }

    @Transactional
    public void removerProduto(Long id) {
        log.info("Removendo produto com ID {}", id);
        produtoRepository.deleteById(id);
        log.info("Produto removido com ID {}", id);
    }

    @Transactional(readOnly = true)
    public Optional<Produto> obterProduto(Long id) {
        log.info("Obtendo produto com ID {}", id);
        Optional<Produto> produto = produtoRepository.findById(id);
        log.info("Produto obtido: {}", produto.orElse(null));
        return produto;
    }
}

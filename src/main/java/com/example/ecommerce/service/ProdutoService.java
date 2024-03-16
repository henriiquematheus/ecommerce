package com.example.ecommerce.service;

import com.example.ecommerce.model.Produto;
import com.example.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void adicionarProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> obterProduto(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto atualizarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public boolean removerProduto(Long id) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isPresent()) {
            produtoRepository.delete(optionalProduto.get());
            return true;
        }
        return false;
    }
}
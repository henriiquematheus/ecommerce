package com.example.ecommerce.repository;

import com.example.ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Logger logger = LoggerFactory.getLogger(ProdutoRepository.class);

    default Produto saveWithLog(Produto produto) {
        logger.info("Salvando produto: {}", produto);
        return save(produto);
    }

    default void deleteByIdWithLog(Long id) {
        logger.info("Removendo produto com ID {}", id);
        deleteById(id);
        logger.info("Produto removido com ID {}", id);
    }

    default Produto findByIdWithLog(Long id) {
        logger.info("Obtendo produto com ID {}", id);
        return findById(id).orElse(null);
    }

    default List<Produto> findAllWithLog() {
        logger.info("Listando produtos...");
        return findAll();
    }



}

package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.entities.Produto;
import com.example.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@SpringBootTest // Carrega o contexto completo do Spring para testes
@Transactional  // Garante que as operações no banco de dados serão revertidas após cada teste
class ProdutoServiceTest {

	 @Autowired
	    private ProdutoService produtoService;

	    @Autowired
	    private ProdutoRepository produtoRepository;
	    
	    @BeforeEach
	    void setUp() {
	        produtoRepository.deleteAll(); // Limpa o banco de dados antes de cada teste
	    }
	    
	    @DisplayName("Testando salvar Produto")
	    @Test
	    void testSalvarProduto() {
	        Produto produto = new Produto(null, "Arroz", "Arroz Branco", 23.00);

	        Produto resultado = produtoService.salvarProduto(produto);

	        assertNotNull(resultado);
	        assertEquals("Arroz", resultado.getNome());
	        assertTrue(resultado.getId() > 0);
	    }
	    
	    @DisplayName("Testando listar todos os Produto")
	    @Test
	    void testListarTodos() {
	        Produto produto1 = new Produto(null, "Arroz", "Arroz Branco", 23.00);
	        Produto produto2 = new Produto(null, "Pó de café", "Melitta", 12.50);

	        produtoService.salvarProduto(produto1);
	        produtoService.salvarProduto(produto2);

	        List<Produto> resultado = produtoService.listarTodos();

	        assertNotNull(resultado);
	        assertEquals(2, resultado.size());
	    }
	    
	    @DisplayName("Testando buscar Produto por ID")
	    @Test
	    void testBuscarPorId() {
	        Produto produto = new Produto(null, "Arroz", "Arroz Branco", 23.00);

	        Produto salvo = produtoService.salvarProduto(produto);
	        Optional<Produto> resultado = produtoService.buscarPorId(salvo.getId());

	        assertTrue(resultado.isPresent());
	        assertEquals("Arroz", resultado.get().getNome());
	    }
	    
	    @DisplayName("Testando atualizar Produto")
	    @Test
	    void testAtualizarProduto() {
	        Produto produto = new Produto(null, "Arroz", "Arroz Branco", 23.00);
	        Produto salvo = produtoService.salvarProduto(produto);

	        salvo.setNome("Chocolate");
	        salvo.setDescricao("Cacau Show");

	        Produto atualizado = produtoService.atualizarproduto(salvo);

	        assertNotNull(atualizado);
	        assertEquals("Chocolate", atualizado.getNome());
	        assertEquals("Cacau Show", atualizado.getDescricao());
	    }
	    
	    @DisplayName("Testando deletar Produto")
	    @Test
	    void testDeletarProduto() {
	        Produto produto = new Produto(null, "Arroz", "Arroz Branco", 23.00);

	        Produto salvo = produtoService.salvarProduto(produto);
	        produtoService.deletarProduto(salvo.getId());

	        Optional<Produto> resultado = produtoService.buscarPorId(salvo.getId());

	        assertTrue(resultado.isEmpty());
	    }
	    
	    
   

}

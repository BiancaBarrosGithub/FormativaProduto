package com.example.entities;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProdutoTest {

private Produto produto;
	
	@BeforeEach
	void setUp() {
		//Arrange
		produto = new Produto(1L,"Arroz", "Arroz branco", 23.00);
	}
	
	@Test
	@DisplayName("Testando o getter e setter do campo ID")
	void testId() {
		//Act
		produto.setId(2L);
		//Assert
		assertEquals(2L,produto.getId());
	}
	
	@Test
	@DisplayName("Testando o getter e setter do campo nome")
	void testNome() {
		//Act
		produto.setNome("Pó de café");
		//Assert
		assertEquals("Pó de café", produto.getNome());
	}
	
	@Test
	@DisplayName("Testando o getter e setter do campo descrição")
	void testDescricao() {
		//Act
		produto.setDescricao("Melitta");
		//Assert
		assertEquals("Melitta", produto.getDescricao());
	}
	
	@Test
	@DisplayName("Testando o getter e setter do campo preco")
	void testPreco() {
		//Act
		produto.setPreco(12.50);
		//Assert
		assertEquals(12.50, produto.getPreco());
	}
	
	@Test
	@DisplayName("Testando o construtor com todos os argumentos")
	void testContrutorAll() {
		//Act
		Produto novoProduto = new Produto(3L,"Chocolate","Cacau Show", 18.30);
		//Assertion
		assertAll("novoHospede",
				()-> assertEquals(3L, novoProduto.getId()),
				()-> assertEquals("Chocolate", novoProduto.getNome()),
				()-> assertEquals("Cacau Show", novoProduto.getDescricao()),
				()-> assertEquals(18.30, novoProduto.getPreco()));
	}


}

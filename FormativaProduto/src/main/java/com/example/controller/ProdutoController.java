package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Produto;
import com.example.service.ProdutoService;

@RestController
@RequestMapping("produto")
public class ProdutoController {
	
	 @Autowired
	    private ProdutoService produtoService;

	    @PostMapping
	    public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) {
	        Produto novoProduto = produtoService.salvarProduto(produto);
	        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
	    }

	  
	    @GetMapping
	    public ResponseEntity<List<Produto>> listarTodos() {
	        List<Produto> produto = produtoService.listarTodos();
	        return new ResponseEntity<>(produto, HttpStatus.OK);
	    }

	  
	    @GetMapping("/{id}")
	    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
	        Optional<Produto> produto = produtoService.buscarPorId(id);
	        return produto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
	                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	  
	    @PutMapping("/{id}")
	    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, 
	    											@RequestBody Produto produto) {
	        if (!produtoService.buscarPorId(id).isPresent()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        produto.setId(id);
	        Produto produtoAtualizado = produtoService.atualizarproduto(produto);
	        return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
	    }

	   
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
	        if (!produtoService.buscarPorId(id).isPresent()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        produtoService.deletarProduto(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }


	

}

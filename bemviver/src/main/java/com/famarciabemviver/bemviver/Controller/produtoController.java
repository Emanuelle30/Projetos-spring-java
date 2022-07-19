package com.famarciabemviver.bemviver.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.famarciabemviver.bemviver.Repository.produtoRepository;
import com.famarciabemviver.bemviver.model.produto;

@RestController
@RequestMapping(value = "/produtos")
@CrossOrigin("*")
public class produtoController {
	
	@Autowired
	private produtoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<produto>> GetAll(){
		return ResponseEntity.ok().body(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<produto> GetById(@PathVariable Long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nomeProduto/{nomeProduto}")
	public ResponseEntity<List<produto>> getByNome(@PathVariable String nomeProduto) {
		return ResponseEntity.ok(repository.findAllByNomeProdutoContainingIgnoreCase(nomeProduto));
	}

	@PostMapping
	public ResponseEntity<produto> post(@Valid @RequestBody produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}

	@PutMapping
	public ResponseEntity<produto> put(@Valid @RequestBody produto produto) {
		return repository.findById(produto.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<produto> produto = repository.findById(id);

		if (produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		repository.deleteById(id);
	}

}
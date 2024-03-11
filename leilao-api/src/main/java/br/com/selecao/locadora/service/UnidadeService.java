package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.UnidadeBO;
import br.com.selecao.locadora.dto.UnidadeDTO;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "/unidade")
public class UnidadeService {

	@Autowired
	private UnidadeBO unidadeBO;

	@GetMapping
	public ResponseEntity<Object> buscarTodos() {
		return new ResponseEntity<>(unidadeBO.buscarTodos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarUnidade(@PathVariable Long id) {
		return new ResponseEntity<>(unidadeBO.buscarUnidadePorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> criarUnidade(@RequestBody UnidadeDTO unidadeDTO) {
		return new ResponseEntity<>(unidadeBO.salvarUnidade(unidadeDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarUnidade(@PathVariable Long id) {
    return new ResponseEntity<>(unidadeBO.deletarUnidade(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizarUnidade(@RequestBody UnidadeDTO
	novaUnidadeDTO, @PathVariable Long id) {		
		return new ResponseEntity<>(unidadeBO.atualizarUnidade(novaUnidadeDTO, id), HttpStatus.OK);
	};
}
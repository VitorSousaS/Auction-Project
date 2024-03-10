package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.CompradorBO;
import br.com.selecao.locadora.entity.Comprador;

import java.util.HashMap;
import java.util.Map;

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
@RequestMapping(value = "/comprador")
public class CompradorService {

	@Autowired
	private CompradorBO compradorBO;

	@GetMapping
	public ResponseEntity<Object> buscarTodos() {
		return new ResponseEntity<>(compradorBO.buscarTodos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarComprador(@PathVariable Long id) {
		return new ResponseEntity<>(compradorBO.buscarCompradorPorLeilaoId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> criarComprador(@RequestBody Comprador comprador) {
		return new ResponseEntity<>(compradorBO.salvarComprador(comprador), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarCompradorPorLeilaoId(@PathVariable Long id) {
		compradorBO.deletarCompradorPorLeilaoId(id);
		
    Map<String, Long> resposta = new HashMap<>();
    resposta.put("id", id);

    return new ResponseEntity<>(resposta, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizarComprador(@RequestBody Comprador
	novaComprador, @PathVariable Long id) {		
		return new ResponseEntity<>(compradorBO.atualizarCompradorPorLeilaoId(novaComprador, id), HttpStatus.OK);
	};
}
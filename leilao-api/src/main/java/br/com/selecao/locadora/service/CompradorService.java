package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.CompradorBO;
import br.com.selecao.locadora.dto.CompradorDTO;

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
@CrossOrigin(origins = "*")
@RequestMapping(value = "/comprador")
public class CompradorService {

	@Autowired
	private CompradorBO compradorBO;

	@GetMapping
	public ResponseEntity<Object> buscarTodos() {
		return new ResponseEntity<>(compradorBO.buscarTodos(), HttpStatus.OK);
	}

	@GetMapping("/empresa/{empresaId}/leilao/{leilaoId}")
	public ResponseEntity<Object> buscarComprador(@PathVariable Long empresaId, @PathVariable Long leilaoId) {
		return new ResponseEntity<>(compradorBO.buscarCompradorPorId(empresaId, leilaoId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> criarComprador(@RequestBody CompradorDTO compradorDTO) {
		return new ResponseEntity<>(compradorBO.salvarComprador(compradorDTO), HttpStatus.OK);
	}

	@DeleteMapping("/empresa/{empresaId}/leilao/{leilaoId}")
	public ResponseEntity<Object> deletarCompradorPorLeilaoId(@PathVariable Long empresaId, @PathVariable Long leilaoId) {
		return new ResponseEntity<>(compradorBO.deletarCompradorPorLeilaoId(empresaId, leilaoId), HttpStatus.OK);
	}

	@PutMapping("/empresa/{empresaId}/leilao/{leilaoId}")
	public ResponseEntity<Object> atualizarComprador(@RequestBody CompradorDTO compradorDTO, @PathVariable Long empresaId, @PathVariable Long leilaoId) {
		return new ResponseEntity<>(compradorBO.atualizarCompradorPorLeilaoId(compradorDTO, empresaId, leilaoId), HttpStatus.OK);
	};
}
package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.LoteBO;
import br.com.selecao.locadora.dto.LoteDTO;

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
@RequestMapping(value = "/lote")
public class LoteService {

	@Autowired
	private LoteBO loteBO;

	@GetMapping
	public ResponseEntity<Object> buscarTodos() {
		return new ResponseEntity<>(loteBO.buscarTodos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarLote(@PathVariable Long id) {
		return new ResponseEntity<>(loteBO.buscarLotePorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> criarLote(@RequestBody LoteDTO loteDTO) {
		return new ResponseEntity<>(loteBO.salvarLote(loteDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarLote(@PathVariable Long id) {
		return new ResponseEntity<>(loteBO.deletarLote(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizarLote(@RequestBody LoteDTO
	novaLoteDTO, @PathVariable Long id) {		
		return new ResponseEntity<>(loteBO.atualizarLote(novaLoteDTO, id), HttpStatus.OK);
	};
}
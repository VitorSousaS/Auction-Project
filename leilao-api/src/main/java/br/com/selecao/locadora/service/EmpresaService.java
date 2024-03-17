package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.EmpresaBO;
import br.com.selecao.locadora.dto.EmpresaDTO;

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
@RequestMapping(value = "/empresa")
public class EmpresaService {

	@Autowired
	private EmpresaBO empresaBO;

	@GetMapping
	public ResponseEntity<Object> buscarTodos() {
		return new ResponseEntity<>(empresaBO.buscarTodos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarEmpresa(@PathVariable Long id) {
		return new ResponseEntity<>(empresaBO.buscarEmpresaPorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> criarEmpresa(@RequestBody EmpresaDTO empresaDTO) {
		return new ResponseEntity<>(empresaBO.salvarEmpresa(empresaDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarEmpresa(@PathVariable Long id) {
    return new ResponseEntity<>(empresaBO.deletarEmpresa(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizarEmpresa(@RequestBody EmpresaDTO
	novaEmpresaDTO, @PathVariable Long id) {		
		return new ResponseEntity<>(empresaBO.atualizarEmpresa(novaEmpresaDTO, id), HttpStatus.OK);
	};
}
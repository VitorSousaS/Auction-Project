package br.com.selecao.locadora.business;

import br.com.selecao.locadora.business.exception.ExessaoConteudoNaoEncontrado;
import br.com.selecao.locadora.entity.Empresa;
import br.com.selecao.locadora.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaBO {

	@Autowired
	private EmpresaRepository empresaRepository;

	public List<Empresa> buscarTodos() {
		return empresaRepository.findAll();
	}

	public Empresa salvarEmpresa(Empresa empresa) {
		empresa.setCreatedAt(LocalDateTime.now());
		empresa.setUpdatedAt(LocalDateTime.now());
		System.out.println(empresa);
		return empresaRepository.save(empresa);
	}

	public Optional<Empresa> buscarEmpresaPorId(Long id) {
		return empresaRepository.findById(id);
	}

	public void deletarEmpresa(Long id) {
		Optional<Empresa> empresaOptional = empresaRepository.findById(id);
		if (empresaOptional.isPresent()) {
			empresaRepository.deleteById(id);
		} else {
			throw new ExessaoConteudoNaoEncontrado("Empresa não encontrada com o ID: " + id);
		}
	}

	public Empresa atualizarEmpresa(Empresa novaEmpresa, Long id) {
		Optional<Empresa> empresaOptional = empresaRepository.findById(id);

		if (empresaOptional.isPresent()) {
			Empresa empresaExistente = empresaOptional.get();
			
			if (novaEmpresa.getRazaoSocial() != null) {
				empresaExistente.setRazaoSocial(novaEmpresa.getRazaoSocial());
			}
			
			if (novaEmpresa.getCnpj() != null) {
				empresaExistente.setCnpj(novaEmpresa.getCnpj());
			}
			
			if (novaEmpresa.getLogradouro() != null) {
				empresaExistente.setLogradouro(novaEmpresa.getLogradouro());
			}
			
			if (novaEmpresa.getMunicipio() != null) {
				empresaExistente.setMunicipio(novaEmpresa.getMunicipio());
			}
		
			if (novaEmpresa.getNumero() != null) {
				empresaExistente.setNumero(novaEmpresa.getNumero());
			}
		
			if (novaEmpresa.getComplemento() != null) {
				empresaExistente.setComplemento(novaEmpresa.getComplemento());
			}
		
			if (novaEmpresa.getBairro() != null) {
				empresaExistente.setBairro(novaEmpresa.getBairro());
			}
		
			if (novaEmpresa.getCep() != null) {
				empresaExistente.setCep(novaEmpresa.getCep());
			}
		
			if (novaEmpresa.getTelefone() != null) {
				empresaExistente.setTelefone(novaEmpresa.getTelefone());
			}
		
			if (novaEmpresa.getEmail() != null) {
				empresaExistente.setEmail(novaEmpresa.getEmail());
			}
		
			if (novaEmpresa.getSite() != null) {
				empresaExistente.setSite(novaEmpresa.getSite());
			}
		
			if (novaEmpresa.getUsuario() != null) {
				empresaExistente.setUsuario(novaEmpresa.getUsuario());
			}
		
			if (novaEmpresa.getSenha() != null) {
				empresaExistente.setSenha(novaEmpresa.getSenha());
			}

			empresaExistente.setUpdatedAt(LocalDateTime.now());

			return empresaRepository.save(empresaExistente);

		} else {
			throw new ExessaoConteudoNaoEncontrado("Empresa não encontrada com o ID: " + id);
		}
	}}
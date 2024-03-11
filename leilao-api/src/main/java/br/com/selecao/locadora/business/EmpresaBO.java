package br.com.selecao.locadora.business;

import br.com.selecao.locadora.business.exception.ExessaoConteudoNaoEncontrado;
import br.com.selecao.locadora.dto.EmpresaDTO;
import br.com.selecao.locadora.entity.Empresa;
import br.com.selecao.locadora.mapper.EmpresaMapper;
import br.com.selecao.locadora.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmpresaBO {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private EmpresaMapper empresaMapper;

	public List<EmpresaDTO> buscarTodos() {
		List<Empresa> empresas = empresaRepository.findAll();
		return empresas.stream()
				.map(empresaMapper::toDTO)
				.collect(Collectors.toList());
	}

	public Boolean validarExistencia(Long id) {
		return empresaRepository.findById(id).isPresent();
	}

	public Empresa buscarEmpresaPorIdInterno(Long id) {
		return empresaRepository.findById(id).get();
	}

	public EmpresaDTO buscarEmpresaPorId(Long id) {
		Optional<Empresa> empresaOptional = empresaRepository.findById(id);
		return empresaOptional.map(empresaMapper::toDTO).orElse(null);
	}
	
	public EmpresaDTO salvarEmpresa(EmpresaDTO empresaDTO) {
		Empresa empresa = empresaMapper.toEntity(empresaDTO);
		empresa.setCreatedAt(LocalDateTime.now());
		empresa.setUpdatedAt(LocalDateTime.now());
		Empresa savedEmpresa = empresaRepository.save(empresa);
		return empresaMapper.toDTO(savedEmpresa);
	}

	public Map<String, Long> deletarEmpresa(Long id) {
		Optional<Empresa> empresaOptional = empresaRepository.findById(id);
		if (empresaOptional.isPresent()) {
			empresaRepository.deleteById(id);
    	Map<String, Long> resposta = new HashMap<>();
    	resposta.put("id", id);
			return resposta;
		} else {
			throw new ExessaoConteudoNaoEncontrado("Empresa não encontrada com o ID: " + id);
		}
	}

	public EmpresaDTO atualizarEmpresa(EmpresaDTO novaEmpresaDTO, Long id) {
		Optional<Empresa> empresaOptional = empresaRepository.findById(id);

		if (empresaOptional.isPresent()) {
			Empresa empresaExistente = empresaOptional.get();

			if (novaEmpresaDTO.getRazaoSocial() != null) {
				empresaExistente.setRazaoSocial(novaEmpresaDTO.getRazaoSocial());
			}

			if (novaEmpresaDTO.getCnpj() != null) {
				empresaExistente.setCnpj(novaEmpresaDTO.getCnpj());
			}

			if (novaEmpresaDTO.getLogradouro() != null) {
				empresaExistente.setLogradouro(novaEmpresaDTO.getLogradouro());
			}

			if (novaEmpresaDTO.getMunicipio() != null) {
				empresaExistente.setMunicipio(novaEmpresaDTO.getMunicipio());
			}

			if (novaEmpresaDTO.getNumero() != null) {
				empresaExistente.setNumero(novaEmpresaDTO.getNumero());
			}

			if (novaEmpresaDTO.getComplemento() != null) {
				empresaExistente.setComplemento(novaEmpresaDTO.getComplemento());
			}

			if (novaEmpresaDTO.getBairro() != null) {
				empresaExistente.setBairro(novaEmpresaDTO.getBairro());
			}

			if (novaEmpresaDTO.getCep() != null) {
				empresaExistente.setCep(novaEmpresaDTO.getCep());
			}

			if (novaEmpresaDTO.getTelefone() != null) {
				empresaExistente.setTelefone(novaEmpresaDTO.getTelefone());
			}

			if (novaEmpresaDTO.getEmail() != null) {
				empresaExistente.setEmail(novaEmpresaDTO.getEmail());
			}

			if (novaEmpresaDTO.getSite() != null) {
				empresaExistente.setSite(novaEmpresaDTO.getSite());
			}

			if (novaEmpresaDTO.getUsuario() != null) {
				empresaExistente.setUsuario(novaEmpresaDTO.getUsuario());
			}

			if (novaEmpresaDTO.getSenha() != null) {
				empresaExistente.setSenha(novaEmpresaDTO.getSenha());
			}

			empresaExistente.setUpdatedAt(LocalDateTime.now());

			empresaRepository.save(empresaExistente);
			
			return empresaMapper.toDTO(empresaExistente);

		} else {
			throw new ExessaoConteudoNaoEncontrado("Empresa não encontrada com o ID: " + id);
		}
	}
}
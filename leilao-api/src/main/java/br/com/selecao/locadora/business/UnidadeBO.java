package br.com.selecao.locadora.business;

import br.com.selecao.locadora.business.exception.ExessaoConteudoNaoEncontrado;
import br.com.selecao.locadora.dto.UnidadeDTO;
import br.com.selecao.locadora.entity.Unidade;
import br.com.selecao.locadora.mapper.UnidadeMapper;
import br.com.selecao.locadora.repository.UnidadeRepository;
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
public class UnidadeBO {

	@Autowired
	private UnidadeRepository unidadeRepository;

	@Autowired
	private UnidadeMapper unidadeMapper;

	public List<UnidadeDTO> buscarTodos() {
		List<Unidade> unidades = unidadeRepository.findAll();
		return unidades.stream()
				.map(unidadeMapper::toDTO)
				.collect(Collectors.toList());
	}

	public UnidadeDTO buscarUnidadePorId(Long id) {
		Optional<Unidade> unidadeOptional = unidadeRepository.findById(id);
		return unidadeOptional.map(unidadeMapper::toDTO).orElse(null);
	}

	public UnidadeDTO salvarUnidade(UnidadeDTO unidadeDTO) {
		Unidade unidade = unidadeMapper.toEntity(unidadeDTO);
		unidade.setCreatedAt(LocalDateTime.now());
		unidade.setUpdatedAt(LocalDateTime.now());
		Unidade savedUnidade = unidadeRepository.save(unidade);
		return unidadeMapper.toDTO(savedUnidade);
	}

	public Map<String, Long> deletarUnidade(Long id) {
		Optional<Unidade> unidadeOptional = unidadeRepository.findById(id);
		if (unidadeOptional.isPresent()) {
			unidadeRepository.deleteById(id);
			
			Map<String, Long> resposta = new HashMap<>();
   		resposta.put("id", id);
			return resposta;

		} else {
			throw new ExessaoConteudoNaoEncontrado("Unidade não encontrada com o ID: " + id);
		}
	}

	public Unidade atualizarUnidade(UnidadeDTO novaUnidadeDTO, Long id) {
		Optional<Unidade> unidadeOptional = unidadeRepository.findById(id);

		if (unidadeOptional.isPresent()) {
			Unidade unidadeExistente = unidadeOptional.get();
			
			if (novaUnidadeDTO.getNome() != null) {
				unidadeExistente.setNome(novaUnidadeDTO.getNome());
			}
			unidadeExistente.setUpdatedAt(LocalDateTime.now());

			return unidadeRepository.save(unidadeExistente);

		} else {
			throw new ExessaoConteudoNaoEncontrado("Unidade não encontrada com o ID: " + id);
		}
	}
}
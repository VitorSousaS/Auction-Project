package br.com.selecao.locadora.business;

import br.com.selecao.locadora.business.exception.ExessaoConteudoNaoEncontrado;
import br.com.selecao.locadora.dto.LoteDTO;
import br.com.selecao.locadora.entity.Lote;
import br.com.selecao.locadora.mapper.LoteMapper;
import br.com.selecao.locadora.repository.LoteRepository;
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
public class LoteBO {

	@Autowired
	private LoteRepository loteRepository;

	@Autowired
	private LoteMapper loteMapper;

	@Autowired
	private LeilaoBO leilaoBO;

	public List<LoteDTO> buscarTodos() {
		List<Lote> lotes = loteRepository.findAll();
		return lotes.stream()
				.map(loteMapper::toDTO)
				.collect(Collectors.toList());
	}

	public Lote buscarLotePorIdInterno(Long id) {
		return loteRepository.findById(id).get();
	}

	public LoteDTO buscarLotePorId(Long id) {
		Optional<Lote> loteOptional = loteRepository.findById(id);
		return loteOptional.map(loteMapper::toDTO).orElse(null);
	}
	
	public LoteDTO salvarLote(LoteDTO loteDTO) {
		Lote lote = loteMapper.toEntity(loteDTO);
		lote.setCreatedAt(LocalDateTime.now());
		lote.setUpdatedAt(LocalDateTime.now());
		Lote savedLote = loteRepository.save(lote);
		return loteMapper.toDTO(savedLote);
	}

	public Map<String, Long> deletarLote(Long id) {
		Optional<Lote> loteOptional = loteRepository.findById(id);
		if (loteOptional.isPresent()) {
			loteRepository.deleteById(id);

			Map<String, Long> resposta = new HashMap<>();
			resposta.put("id", id);
			return resposta; 
		} else {
			throw new ExessaoConteudoNaoEncontrado("Lote não encontrada com o ID: " + id);
		}
	}

	public LoteDTO atualizarLote(LoteDTO novaLoteDTO, Long id) {
		Optional<Lote> loteOptional = loteRepository.findById(id);

		if (loteOptional.isPresent()) {
			Lote loteExistente = loteOptional.get();

			if (novaLoteDTO.getNumeroLote() != null) {
				loteExistente.setNumeroLote(novaLoteDTO.getNumeroLote());
			}

			if (novaLoteDTO.getDescricao() != null) {
				loteExistente.setDescricao(novaLoteDTO.getDescricao());
			}

			if (novaLoteDTO.getQuantidade() != null) {
				loteExistente.setQuantidade(novaLoteDTO.getQuantidade());
			}

			if (novaLoteDTO.getValorInicial() != null) {
				loteExistente.setValorInicial(novaLoteDTO.getValorInicial());
			}

			if (novaLoteDTO.getLeilaoId() != null) {
				loteExistente.setLeilao(leilaoBO.buscarLeilaoPorIdInterno(novaLoteDTO.getLeilaoId()));
			}

			loteExistente.setUpdatedAt(LocalDateTime.now());

			loteRepository.save(loteExistente);

			return loteMapper.toDTO(loteExistente);

		} else {
			throw new ExessaoConteudoNaoEncontrado("Lote não encontrada com o ID: " + id);
		}
	}
}
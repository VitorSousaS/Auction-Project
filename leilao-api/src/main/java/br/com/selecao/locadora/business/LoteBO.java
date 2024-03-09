package br.com.selecao.locadora.business;

import br.com.selecao.locadora.business.exception.ExessaoConteudoNaoEncontrado;
import br.com.selecao.locadora.entity.Lote;
import br.com.selecao.locadora.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LoteBO {

	@Autowired
	private LoteRepository loteRepository;

	public List<Lote> buscarTodos() {
		return loteRepository.findAll();
	}

	public Lote salvarLote(Lote lote) {
		lote.setCreatedAt(LocalDateTime.now());
		lote.setUpdatedAt(LocalDateTime.now());
		return loteRepository.save(lote);
	}

	public Optional<Lote> buscarLotePorId(Long id) {
		return loteRepository.findById(id);
	}

	public void deletarLote(Long id) {
		Optional<Lote> loteOptional = loteRepository.findById(id);
		if (loteOptional.isPresent()) {
			loteRepository.deleteById(id);
		} else {
			throw new ExessaoConteudoNaoEncontrado("Lote não encontrada com o ID: " + id);
		}
	}

	public Lote atualizarLote(Lote novaLote, Long id) {
		Optional<Lote> loteOptional = loteRepository.findById(id);

		if (loteOptional.isPresent()) {
			Lote loteExistente = loteOptional.get();

			if (novaLote.getNumeroLote() != null) {
				loteExistente.setNumeroLote(novaLote.getNumeroLote());
			}

			if (novaLote.getDescricao() != null) {
				loteExistente.setDescricao(novaLote.getDescricao());
			}

			if (novaLote.getQuantidade() != null) {
				loteExistente.setQuantidade(novaLote.getQuantidade());
			}

			if (novaLote.getValorInicial() != null) {
				loteExistente.setValorInicial(novaLote.getValorInicial());
			}

			if (novaLote.getUnidade() != null) {
				loteExistente.setUnidade(novaLote.getUnidade());
			}

			if (novaLote.getLeilao() != null) {
				loteExistente.setLeilao(novaLote.getLeilao());
			}
			
			loteExistente.setUpdatedAt(LocalDateTime.now());
			
			return loteRepository.save(loteExistente);

		} else {
			throw new ExessaoConteudoNaoEncontrado("Lote não encontrada com o ID: " + id);
		}
	}
}
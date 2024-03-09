package br.com.selecao.locadora.business;

import br.com.selecao.locadora.business.exception.ExessaoConteudoNaoEncontrado;
import br.com.selecao.locadora.entity.Leilao;
import br.com.selecao.locadora.repository.LeilaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LeilaoBO {

	@Autowired
	private LeilaoRepository leilaoRepository;

	public List<Leilao> buscarTodos() {
		return leilaoRepository.findAll();
	}

	public Leilao salvarLeilao(Leilao leilao) {
		leilao.setCreatedAt(LocalDateTime.now());
		leilao.setUpdatedAt(LocalDateTime.now());
		return leilaoRepository.save(leilao);
	}

	public Optional<Leilao> buscarLeilaoPorId(Long id) {
		return leilaoRepository.findById(id);
	}

	public void deletarLeilao(Long id) {
		Optional<Leilao> leilaoOptional = leilaoRepository.findById(id);
		if (leilaoOptional.isPresent()) {
			leilaoRepository.deleteById(id);
		} else {
			throw new ExessaoConteudoNaoEncontrado("Leilao não encontrada com o ID: " + id);
		}
	}

	public Leilao atualizarLeilao(Leilao novaLeilao, Long id) {
		Optional<Leilao> leilaoOptional = leilaoRepository.findById(id);

		if (leilaoOptional.isPresent()) {
			Leilao leilaoExistente = leilaoOptional.get();

			if (novaLeilao.getCodigo() != null) {
				leilaoExistente.setCodigo(novaLeilao.getCodigo());
			}

			if (novaLeilao.getDescricao() != null) {
				leilaoExistente.setDescricao(novaLeilao.getDescricao());
			}

			if (novaLeilao.getVendedor() != null) {
				leilaoExistente.setVendedor(novaLeilao.getVendedor());
			}

			if (novaLeilao.getInicioPrevisto() != null) {
				leilaoExistente.setInicioPrevisto(novaLeilao.getInicioPrevisto());
			}
			
			leilaoExistente.setUpdatedAt(LocalDateTime.now());
			
			return leilaoRepository.save(leilaoExistente);

		} else {
			throw new ExessaoConteudoNaoEncontrado("Leilao não encontrada com o ID: " + id);
		}
	}
}
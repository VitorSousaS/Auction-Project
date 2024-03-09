package br.com.selecao.locadora.business;

import br.com.selecao.locadora.business.exception.ExessaoConteudoNaoEncontrado;
import br.com.selecao.locadora.entity.Comprador;
import br.com.selecao.locadora.entity.CompradorId;
import br.com.selecao.locadora.repository.CompradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
public class CompradorBO {

	@Autowired
	private CompradorRepository compradorRepository;

	public List<Comprador> buscarTodos() {
		return compradorRepository.findAll();
	}

	public Comprador salvarComprador(Comprador comprador) {
		return compradorRepository.save(comprador);
	}

	public Optional<Comprador> buscarCompradorPorLeilaoId(Long leilaoId) {
		return compradorRepository.findByIdLeilaoId(leilaoId);
	}

	@Transactional
	public void deletarCompradorPorLeilaoId(Long leilaoId) {
		Optional<Comprador> compradorOptional = compradorRepository.findByIdLeilaoId(leilaoId);
		if (compradorOptional.isPresent()) {
			compradorRepository.deleteByIdLeilaoId(leilaoId);
		} else {
			throw new ExessaoConteudoNaoEncontrado("Comprador não encontrada com o ID: " + leilaoId);
		}
	}

	public Comprador atualizarCompradorPorLeilaoId(Comprador novoComprador, Long leilaoId) {
		// Optional<Comprador> compradorOptional = compradorRepository.findByIdLeilaoId(leilaoId);

		// if (compradorOptional.isPresent()) {
		// 	Comprador compradorExistente = compradorOptional.get();

		// 	compradorExistente.setEmpresaId(novoComprador.getByIdEmpresaId());

		// 	return compradorRepository.save(compradorExistente);

		// } else {
		// 	throw new ExessaoConteudoNaoEncontrado("Comprador não encontrada com o ID: " + leilaoId);
		// }
		return null;
	}
}
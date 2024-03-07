package br.com.selecao.locadora.business;

import br.com.selecao.locadora.business.exception.ExessaoConteudoNaoEncontrado;
import br.com.selecao.locadora.entity.Unidade;
import br.com.selecao.locadora.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeBO {

	@Autowired
	private UnidadeRepository unidadeRepository;

	public List<Unidade> buscarTodos() {
		return unidadeRepository.findAll();
	}

	public Unidade salvarUnidade(Unidade unidade) {
		return unidadeRepository.save(unidade);
	}

	public Optional<Unidade> buscarUnidadePorId(Long id) {
		return unidadeRepository.findById(id);
	}

	public void deletarUnidade(Long id) {
		Optional<Unidade> unidadeOptional = unidadeRepository.findById(id);
		if (unidadeOptional.isPresent()) {
			unidadeRepository.deleteById(id);
		} else {
			throw new ExessaoConteudoNaoEncontrado("Unidade não encontrada com o ID: " + id);
		}
	}
	
	public Unidade atualizarUnidade(Unidade novUnidade) {
		return null;
	}
}
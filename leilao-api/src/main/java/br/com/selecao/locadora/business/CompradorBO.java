package br.com.selecao.locadora.business;

import br.com.selecao.locadora.business.exception.ExessaoConteudoNaoEncontrado;
import br.com.selecao.locadora.business.exception.ExessaoRequisicaoInvalida;
import br.com.selecao.locadora.dto.CompradorDTO;
import br.com.selecao.locadora.entity.Comprador;
import br.com.selecao.locadora.entity.CompradorId;
import br.com.selecao.locadora.mapper.CompradorMapper;
import br.com.selecao.locadora.repository.CompradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
@Transactional
public class CompradorBO {

	@Autowired
	private CompradorRepository compradorRepository;

	@Autowired
	private CompradorMapper compradorMapper;

	@Autowired
	private EmpresaBO empresaBO;

	@Autowired
	private LeilaoBO leilaoBO;

	public List<CompradorDTO> buscarTodos() {
		List<Comprador> compradores = compradorRepository.findAll();
		return compradores.stream()
				.map(compradorMapper::toDTO)
				.collect(Collectors.toList());
	}

	public CompradorDTO buscarCompradorPorId(Long empresaId, Long leilaoId) {
		CompradorId compradorId = CompradorId.builder()
				.empresa(empresaBO.buscarEmpresaPorIdInterno(empresaId))
				.leilao(leilaoBO.buscarLeilaoPorIdInterno(leilaoId))
				.build();

		Optional<Comprador> compradorOptional = compradorRepository.findById(compradorId);
		return compradorOptional.map(compradorMapper::toDTO).orElse(null);
	}

	public CompradorDTO salvarComprador(CompradorDTO compradorDTO) {

		Comprador comprador = compradorMapper.toEntity(compradorDTO);
		Comprador savedComprador = compradorRepository.save(comprador);
		return compradorMapper.toDTO(savedComprador);
	}

	public void deltarCompradoresPorEmpresaId(Long empresaId) {
		compradorRepository.deletaCompradoresComEmpresaId(empresaId);
	}

	public Map<String, Long> deletarCompradorPorLeilaoId(Long empresaId, Long leilaoId) {
		CompradorId compradorId = CompradorId.builder()
		.empresa(empresaBO.buscarEmpresaPorIdInterno(empresaId))
		.leilao(leilaoBO.buscarLeilaoPorIdInterno(leilaoId))
		.build();

		Optional<Comprador> compradorOptional = compradorRepository.findById(compradorId);
		if (compradorOptional.isPresent()) {
			compradorRepository.deleteById(compradorId);
    	Map<String, Long> resposta = new HashMap<>();
    	resposta.put("EmpresaId", compradorId.getEmpresa().getId());
    	resposta.put("LeilaoId", compradorId.getLeilao().getId());
			return resposta;
		} else {
			throw new ExessaoConteudoNaoEncontrado("Empresa não encontrada com o ID: " + compradorId.toString());
		}
	}

	public CompradorDTO atualizarCompradorPorLeilaoId(CompradorDTO novoCompradorDTO, Long empresaId, Long leilaoId) {
		CompradorId compradorId = CompradorId.builder()
		.empresa(empresaBO.buscarEmpresaPorIdInterno(empresaId))
		.leilao(leilaoBO.buscarLeilaoPorIdInterno(leilaoId))
		.build();
		
		Optional<Comprador> compradorOptional = compradorRepository.findById(compradorId);
		
		if (compradorOptional.isPresent()) {
			Boolean empresaValida = novoCompradorDTO.getEmpresaId() != null && empresaBO.validarExistencia(novoCompradorDTO.getEmpresaId());
			Boolean leilaoValido = novoCompradorDTO.getLeilaoId() != null && leilaoBO.validarExistencia(novoCompradorDTO.getEmpresaId());
			
			if (empresaValida && leilaoValido) {
				Comprador novoComprador = compradorMapper.toEntity(novoCompradorDTO);
				compradorRepository.deleteById(compradorId);
				compradorRepository.save(novoComprador);
				return compradorMapper.toDTO(novoComprador);
			} else {
				throw new ExessaoRequisicaoInvalida("Operação inválida, verifique os valroes");
			}
		} else {
			throw new ExessaoConteudoNaoEncontrado("Comprador não encontrada com o ID: " + compradorId.toString());
		}
	}
}
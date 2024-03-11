package br.com.selecao.locadora.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.selecao.locadora.business.EmpresaBO;
import br.com.selecao.locadora.business.LeilaoBO;
import br.com.selecao.locadora.dto.CompradorDTO;
import br.com.selecao.locadora.entity.Comprador;
import br.com.selecao.locadora.entity.CompradorId;

@Component
public class CompradorMapper {

	@Autowired
	private EmpresaBO empresaBO;

	@Autowired
	private LeilaoBO leilaoBO;

	public CompradorDTO toDTO(Comprador comprador) {
		return CompradorDTO.builder()
				.empresaId(comprador.getId().getEmpresa().getId())
				.leilaoId(comprador.getId().getLeilao().getId())
				.build();
	}

	public Comprador toEntity(CompradorDTO compradorDTO) {
		return Comprador.builder()
				.id(CompradorId.builder()
						.empresa(empresaBO.buscarEmpresaPorIdInterno(compradorDTO.getEmpresaId()))
						.leilao(leilaoBO.buscarLeilaoPorIdInterno(compradorDTO.getLeilaoId()))
						.build())
				.build();
	}
}

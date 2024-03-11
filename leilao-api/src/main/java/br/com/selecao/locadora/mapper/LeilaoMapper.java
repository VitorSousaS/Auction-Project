package br.com.selecao.locadora.mapper;

import org.springframework.stereotype.Component;

import br.com.selecao.locadora.dto.LeilaoDTO;
import br.com.selecao.locadora.entity.Leilao;

@Component
public class LeilaoMapper {

	public LeilaoDTO toDTO(Leilao leilao) {
		return LeilaoDTO.builder()
				.id(leilao.getId())
				.codigo(leilao.getCodigo())
				.descricao(leilao.getDescricao())
				.vendedor(leilao.getVendedor())
				.inicioPrevisto(leilao.getInicioPrevisto())
				.createdAt(leilao.getCreatedAt())
				.updatedAt(leilao.getUpdatedAt())
				.build();
	}

	public Leilao toEntity(LeilaoDTO leilaoDTO) {
		return Leilao.builder()
				.id(leilaoDTO.getId())
				.codigo(leilaoDTO.getCodigo())
				.descricao(leilaoDTO.getDescricao())
				.vendedor(leilaoDTO.getVendedor())
				.inicioPrevisto(leilaoDTO.getInicioPrevisto())
				.createdAt(leilaoDTO.getCreatedAt())
				.updatedAt(leilaoDTO.getUpdatedAt())
				.build();
	}
}

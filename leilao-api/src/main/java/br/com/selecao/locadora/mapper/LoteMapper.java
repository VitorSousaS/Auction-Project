package br.com.selecao.locadora.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.selecao.locadora.business.LeilaoBO;
import br.com.selecao.locadora.dto.LoteDTO;
import br.com.selecao.locadora.entity.Lote;

@Component
public class LoteMapper {

	@Autowired
	private LeilaoBO leilaoBO;
	
	public LoteDTO toDTO(Lote lote) {
		return LoteDTO.builder()
				.id(lote.getId())
				.numeroLote(lote.getNumeroLote())
				.descricao(lote.getDescricao())
				.quantidade(lote.getQuantidade())
				.valorInicial(lote.getValorInicial())
				.unidade(lote.getUnidade())
				.leilaoId(lote.getLeilao().getId())
				.createdAt(lote.getCreatedAt())
				.updatedAt(lote.getUpdatedAt())
				.build();
	}

	public Lote toEntity(LoteDTO loteDTO) {
		return Lote.builder()
				.id(loteDTO.getId())
				.numeroLote(loteDTO.getNumeroLote())
				.descricao(loteDTO.getDescricao())
				.quantidade(loteDTO.getQuantidade())
				.valorInicial(loteDTO.getValorInicial())
				.unidade(loteDTO.getUnidade())
				.leilao(leilaoBO.buscarLeilaoPorIdInterno(loteDTO.getLeilaoId()))
				.createdAt(loteDTO.getCreatedAt())
				.updatedAt(loteDTO.getUpdatedAt())
				.build();
	}
}

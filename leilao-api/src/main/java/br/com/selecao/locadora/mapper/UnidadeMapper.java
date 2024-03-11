package br.com.selecao.locadora.mapper;

import org.springframework.stereotype.Component;

import br.com.selecao.locadora.dto.UnidadeDTO;
import br.com.selecao.locadora.entity.Unidade;

@Component
public class UnidadeMapper {

	public UnidadeDTO toDTO(Unidade unidade) {
		return UnidadeDTO.builder()
				.id(unidade.getId())
				.nome(unidade.getNome())
				.createdAt(unidade.getCreatedAt())
				.updatedAt(unidade.getUpdatedAt())
				.build();
	}

	public Unidade toEntity(UnidadeDTO unidadeDTO) {
		return Unidade.builder()
				.id(unidadeDTO.getId())
				.nome(unidadeDTO.getNome())
				.createdAt(unidadeDTO.getCreatedAt())
				.updatedAt(unidadeDTO.getUpdatedAt())
				.build();
	}
}

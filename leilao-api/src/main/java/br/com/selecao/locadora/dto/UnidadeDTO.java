package br.com.selecao.locadora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeDTO {
	private Long id;
	private String nome;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
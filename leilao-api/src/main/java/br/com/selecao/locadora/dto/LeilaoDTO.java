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
public class LeilaoDTO {
	private Long id;
	private Long codigo;
	private String descricao;
	private Long vendedor;
	private LocalDateTime inicioPrevisto;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
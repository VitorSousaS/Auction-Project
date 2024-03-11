package br.com.selecao.locadora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoteDTO {
	private Long id;
	private Long numeroLote;
	private String descricao;
	private BigDecimal quantidade;
	private BigDecimal valorInicial;
	private String unidade;
	private Long leilaoId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
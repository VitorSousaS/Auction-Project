package br.com.selecao.locadora.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "lote")
@SequenceGenerator(name = "seq_lote", sequenceName = "seq_lote", allocationSize = 1)
public class Lote implements Serializable {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_lote")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "numerolote")
	private Long numeroLote;

	@Column(name = "descricao", length = 60, nullable = false)
	private String descricao;

	@Column(name = "quantidade", nullable = false)
	private BigDecimal quantidade;

	@Column(name = "valorinicial")
	private BigDecimal valorInicial;

	@Column(name = "unidade", length = 128, nullable = false)
	private String unidade;

	@ManyToOne
	@JoinColumn(name = "leilao", referencedColumnName = "id", nullable = false)
	private Leilao leilao;

	@Column(name = "createdat", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updatedat", nullable = false)
	private LocalDateTime updatedAt;
}
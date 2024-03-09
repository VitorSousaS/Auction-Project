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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "leilao")
@SequenceGenerator(name = "seq_leilao", sequenceName = "seq_leilao", allocationSize = 1)
public class Leilao implements Serializable {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_leilao")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "codigo")
	private Long codigo;

	@Column(name = "descricao", length = 60, nullable = false)
	private String descricao;

	@Column(name = "vendedor", nullable = false)
	private Long vendedor;

	@Column(name = "inicioprevisto", nullable = false)
	private LocalDateTime inicioPrevisto;

	@Column(name = "createdat", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updatedat", nullable = false)
	private LocalDateTime updatedAt;
}
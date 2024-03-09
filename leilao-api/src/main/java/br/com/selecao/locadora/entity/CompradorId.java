package br.com.selecao.locadora.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CompradorId implements Serializable {
	@ManyToOne
	@MapsId("empresa")
	@JoinColumn(name = "empresa", referencedColumnName = "id", insertable = false, updatable = false)
	private Empresa empresa;

	@ManyToOne
	@MapsId("leilao")
	@JoinColumn(name = "leilao", referencedColumnName = "id", insertable = false, updatable = false)
	private Leilao leilao;

	// public void setIdEmpresa(Long empresaId) {
	// 	this.empresa.setId(empresaId);
	// }

	// public Long getIdEmpresa() {
	// 	return this.empresa.getId();
	// }
}
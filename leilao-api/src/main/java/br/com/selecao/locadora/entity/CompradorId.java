package br.com.selecao.locadora.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

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

	@Override
	public String toString() {
		return "EmpresaId: " + this.getEmpresa().getId() + ", LeilaoId: " + this.getLeilao().getId();
	}
}
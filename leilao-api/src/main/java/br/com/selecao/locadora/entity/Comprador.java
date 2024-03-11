package br.com.selecao.locadora.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonUnwrapped;


import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comprador")
public class Comprador implements Serializable {

	@EmbeddedId
	@JsonUnwrapped
	private CompradorId id;

	// public void setEmpresaId(Long empresaId) {
	// 	this.id.setIdEmpresa(empresaId);
	// }

	// public Long getByIdEmpresaId() {
	// 	return this.id.getIdEmpresa();
	// }
}

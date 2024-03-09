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
@Table(name = "empresa")
@SequenceGenerator(name = "seq_empresa", sequenceName = "seq_empresa", allocationSize = 1)
public class Empresa implements Serializable {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_empresa")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "razaosocial", length = 64, nullable = false)
	private String razaoSocial;

	@Column(name = "cnpj", length = 32, nullable = false, unique = true)
	private String cnpj;

	@Column(name = "logradouro", length = 64)
	private String logradouro;

	@Column(name = "municipio", length = 64)
	private String municipio;

	@Column(name = "numero", length = 10)
	private String numero;

	@Column(name = "complemento", length = 64)
	private String complemento;

	@Column(name = "bairro", length = 64)
	private String bairro;

	@Column(name = "cep", length = 16)
	private String cep;

	@Column(name = "telefone", length = 32)
	private String telefone;

	@Column(name = "email", length = 254, nullable = false)
	private String email;

	@Column(name = "site", length = 254)
	private String site;

	@Column(name = "usuario", length = 20, nullable = false, unique = true)
	private String usuario;

	@Column(name = "senha", length = 128)
	private String senha;

	@Column(name = "createdat", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updatedat", nullable = false)
	private LocalDateTime updatedAt;

	public Empresa(Long id) {
		this.id = id;
	}
}
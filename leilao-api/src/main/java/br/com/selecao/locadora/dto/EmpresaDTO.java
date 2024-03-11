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
public class EmpresaDTO {
	private Long id;
	private String razaoSocial;
	private String cnpj;
	private String logradouro;
	private String municipio;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String telefone;
	private String email;
	private String site;
	private String usuario;
	private String senha;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
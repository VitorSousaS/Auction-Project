package br.com.selecao.locadora.mapper;

import org.springframework.stereotype.Component;

import br.com.selecao.locadora.dto.EmpresaDTO;
import br.com.selecao.locadora.entity.Empresa;

@Component
public class EmpresaMapper {

	public EmpresaDTO toDTO(Empresa empresa) {
		return EmpresaDTO.builder()
				.id(empresa.getId())
				.razaoSocial(empresa.getRazaoSocial())
				.cnpj(empresa.getCnpj())
				.logradouro(empresa.getLogradouro())
				.municipio(empresa.getMunicipio())
				.numero(empresa.getNumero())
				.complemento(empresa.getComplemento())
				.bairro(empresa.getBairro())
				.cep(empresa.getCep())
				.telefone(empresa.getTelefone())
				.email(empresa.getEmail())
				.site(empresa.getSite())
				.usuario(empresa.getUsuario())
				.senha(empresa.getSenha())
				.createdAt(empresa.getCreatedAt())
				.updatedAt(empresa.getUpdatedAt())
				.build();
	}

	public Empresa toEntity(EmpresaDTO empresaDTO) {
		return Empresa.builder()
				.id(empresaDTO.getId())
				.razaoSocial(empresaDTO.getRazaoSocial())
				.cnpj(empresaDTO.getCnpj())
				.logradouro(empresaDTO.getLogradouro())
				.municipio(empresaDTO.getMunicipio())
				.numero(empresaDTO.getNumero())
				.complemento(empresaDTO.getComplemento())
				.bairro(empresaDTO.getBairro())
				.cep(empresaDTO.getCep())
				.telefone(empresaDTO.getTelefone())
				.email(empresaDTO.getEmail())
				.site(empresaDTO.getSite())
				.usuario(empresaDTO.getUsuario())
				.senha(empresaDTO.getSenha())
				.createdAt(empresaDTO.getCreatedAt())
				.updatedAt(empresaDTO.getUpdatedAt())
				.build();
	}
}

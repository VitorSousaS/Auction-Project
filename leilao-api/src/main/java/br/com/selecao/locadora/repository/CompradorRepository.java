package br.com.selecao.locadora.repository;

import br.com.selecao.locadora.entity.Comprador;
import br.com.selecao.locadora.entity.CompradorId;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompradorRepository extends JpaRepository<Comprador, CompradorId> {
		
		@Transactional
    @Modifying
    @Query("DELETE FROM Comprador c WHERE c.id.empresa.id = :empresaId")
	void deletaCompradoresComEmpresaId(@Param("empresaId") Long empresaId);
}




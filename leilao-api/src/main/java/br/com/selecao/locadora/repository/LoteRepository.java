package br.com.selecao.locadora.repository;

import br.com.selecao.locadora.entity.Lote;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Long> {

	@Query("SELECT SUM(lo.quantidade * lo.valorInicial) AS totalLeilao FROM Lote lo WHERE lo.leilao.id = :leilaoId")
	BigDecimal somaProdutoQuantidadeValorInicialPorLeilaoId(@Param("leilaoId") Long leilaoId);
}

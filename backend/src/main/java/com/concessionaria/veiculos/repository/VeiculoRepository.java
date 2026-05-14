package com.concessionaria.veiculos.repository;

import com.concessionaria.veiculos.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repositório de Veículos - herda operações CRUD do JpaRepository
 * Conceito de POO aplicado: Herança (herda de JpaRepository)
 */
@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    // Busca por status
    List<Veiculo> findByStatus(Veiculo.StatusVeiculo status);

    // Busca por marca (via modelo)
    List<Veiculo> findByModeloMarcaId(Long marcaId);

    // Busca por modelo
    List<Veiculo> findByModeloId(Long modeloId);

    // Busca por ano de fabricação
    List<Veiculo> findByAnoFabricacao(Integer ano);

    // Busca por intervalo de preço
    List<Veiculo> findByPrecoBetween(BigDecimal precoMin, BigDecimal precoMax);

    // Busca combinada com JPQL
    @Query("SELECT v FROM Veiculo v WHERE " +
           "(:marcaId IS NULL OR v.modelo.marca.id = :marcaId) AND " +
           "(:modeloId IS NULL OR v.modelo.id = :modeloId) AND " +
           "(:status IS NULL OR v.status = :status) AND " +
           "(:anoMin IS NULL OR v.anoFabricacao >= :anoMin) AND " +
           "(:anoMax IS NULL OR v.anoFabricacao <= :anoMax) AND " +
           "(:precoMin IS NULL OR v.preco >= :precoMin) AND " +
           "(:precoMax IS NULL OR v.preco <= :precoMax)")
    List<Veiculo> filtrar(
            @Param("marcaId") Long marcaId,
            @Param("modeloId") Long modeloId,
            @Param("status") Veiculo.StatusVeiculo status,
            @Param("anoMin") Integer anoMin,
            @Param("anoMax") Integer anoMax,
            @Param("precoMin") BigDecimal precoMin,
            @Param("precoMax") BigDecimal precoMax
    );

    // Conta veículos disponíveis
    long countByStatus(Veiculo.StatusVeiculo status);
}

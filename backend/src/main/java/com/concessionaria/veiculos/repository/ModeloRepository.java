package com.concessionaria.veiculos.repository;

import com.concessionaria.veiculos.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    List<Modelo> findByMarcaId(Long marcaId);
    boolean existsByNomeAndMarcaId(String nome, Long marcaId);
}

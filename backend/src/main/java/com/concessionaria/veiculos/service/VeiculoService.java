package com.concessionaria.veiculos.service;

import com.concessionaria.veiculos.model.Veiculo;
import com.concessionaria.veiculos.repository.ModeloRepository;
import com.concessionaria.veiculos.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final ModeloRepository modeloRepository;

    public VeiculoService(VeiculoRepository veiculoRepository, ModeloRepository modeloRepository) {
        this.veiculoRepository = veiculoRepository;
        this.modeloRepository = modeloRepository;
    }

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado com ID: " + id));
    }

    public Veiculo cadastrar(Veiculo veiculo) {
        modeloRepository.findById(veiculo.getModelo().getId())
                .orElseThrow(() -> new RuntimeException("Modelo não encontrado"));
        if (veiculo.getStatus() == null) {
            veiculo.setStatus(Veiculo.StatusVeiculo.DISPONIVEL);
        }
        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizar(Long id, Veiculo veiculoAtualizado) {
        Veiculo existente = buscarPorId(id);
        existente.setModelo(veiculoAtualizado.getModelo());
        existente.setAnoFabricacao(veiculoAtualizado.getAnoFabricacao());
        existente.setCor(veiculoAtualizado.getCor());
        existente.setPreco(veiculoAtualizado.getPreco());
        existente.setQuilometragem(veiculoAtualizado.getQuilometragem());
        existente.setStatus(veiculoAtualizado.getStatus());
        existente.setPlaca(veiculoAtualizado.getPlaca());
        existente.setChassi(veiculoAtualizado.getChassi());
        existente.setObservacoes(veiculoAtualizado.getObservacoes());
        return veiculoRepository.save(existente);
    }

    public void remover(Long id) {
        buscarPorId(id);
        veiculoRepository.deleteById(id);
    }

    public List<Veiculo> filtrar(Long marcaId, Long modeloId, Veiculo.StatusVeiculo status,
                                  Integer anoMin, Integer anoMax,
                                  BigDecimal precoMin, BigDecimal precoMax) {
        return veiculoRepository.filtrar(marcaId, modeloId, status, anoMin, anoMax, precoMin, precoMax);
    }

    public long contarDisponiveis() {
        return veiculoRepository.countByStatus(Veiculo.StatusVeiculo.DISPONIVEL);
    }

    public long contarVendidos() {
        return veiculoRepository.countByStatus(Veiculo.StatusVeiculo.VENDIDO);
    }
}

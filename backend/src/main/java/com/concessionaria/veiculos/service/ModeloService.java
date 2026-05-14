package com.concessionaria.veiculos.service;

import com.concessionaria.veiculos.model.Modelo;
import com.concessionaria.veiculos.repository.MarcaRepository;
import com.concessionaria.veiculos.repository.ModeloRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ModeloService {

    private final ModeloRepository modeloRepository;
    private final MarcaRepository marcaRepository;

    public ModeloService(ModeloRepository modeloRepository, MarcaRepository marcaRepository) {
        this.modeloRepository = modeloRepository;
        this.marcaRepository = marcaRepository;
    }

    public List<Modelo> listarTodos() {
        return modeloRepository.findAll();
    }

    public List<Modelo> listarPorMarca(Long marcaId) {
        return modeloRepository.findByMarcaId(marcaId);
    }

    public Modelo buscarPorId(Long id) {
        return modeloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modelo não encontrado com ID: " + id));
    }

    public Modelo cadastrar(Modelo modelo) {
        marcaRepository.findById(modelo.getMarca().getId())
                .orElseThrow(() -> new RuntimeException("Marca não encontrada"));
        if (modeloRepository.existsByNomeAndMarcaId(modelo.getNome(), modelo.getMarca().getId())) {
            throw new RuntimeException("Este modelo já existe para essa marca");
        }
        return modeloRepository.save(modelo);
    }

    public Modelo atualizar(Long id, Modelo modeloAtualizado) {
        Modelo existente = buscarPorId(id);
        existente.setNome(modeloAtualizado.getNome());
        existente.setCategoria(modeloAtualizado.getCategoria());
        existente.setMarca(modeloAtualizado.getMarca());
        return modeloRepository.save(existente);
    }

    public void remover(Long id) {
        buscarPorId(id);
        modeloRepository.deleteById(id);
    }
}

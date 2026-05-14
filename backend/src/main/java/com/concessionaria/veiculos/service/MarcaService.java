package com.concessionaria.veiculos.service;

import com.concessionaria.veiculos.model.Marca;
import com.concessionaria.veiculos.repository.MarcaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public List<Marca> listarTodas() {
        return marcaRepository.findAll();
    }

    public Marca buscarPorId(Long id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca não encontrada com ID: " + id));
    }

    public Marca cadastrar(Marca marca) {
        if (marcaRepository.existsByNomeIgnoreCase(marca.getNome())) {
            throw new RuntimeException("Já existe uma marca com o nome: " + marca.getNome());
        }
        return marcaRepository.save(marca);
    }

    public Marca atualizar(Long id, Marca marcaAtualizada) {
        Marca existente = buscarPorId(id);
        existente.setNome(marcaAtualizada.getNome());
        existente.setPaisOrigem(marcaAtualizada.getPaisOrigem());
        return marcaRepository.save(existente);
    }

    public void remover(Long id) {
        buscarPorId(id);
        marcaRepository.deleteById(id);
    }
}

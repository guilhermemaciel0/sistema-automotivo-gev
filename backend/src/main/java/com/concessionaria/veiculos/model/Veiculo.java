package com.concessionaria.veiculos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "veiculos")
public class Veiculo {

    public enum StatusVeiculo {
        DISPONIVEL, VENDIDO, RESERVADO, MANUTENCAO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O modelo é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modelo_id", nullable = false)
    @JsonIgnoreProperties("veiculos")
    private Modelo modelo;

    @NotNull(message = "O ano de fabricação é obrigatório")
    @Column(nullable = false)
    private Integer anoFabricacao;

    @NotBlank(message = "A cor é obrigatória")
    @Column(nullable = false, length = 50)
    private String cor;

    @NotNull(message = "O preço é obrigatório")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal preco;

    @Column(nullable = false)
    private Integer quilometragem = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusVeiculo status = StatusVeiculo.DISPONIVEL;

    @Column(length = 20)
    private String placa;

    @Column(length = 50)
    private String chassi;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    public Veiculo() {}

    public boolean isDisponivel() {
        return StatusVeiculo.DISPONIVEL.equals(this.status);
    }

    public void vender() {
        this.status = StatusVeiculo.VENDIDO;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Modelo getModelo() { return modelo; }
    public void setModelo(Modelo modelo) { this.modelo = modelo; }

    public Integer getAnoFabricacao() { return anoFabricacao; }
    public void setAnoFabricacao(Integer anoFabricacao) { this.anoFabricacao = anoFabricacao; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public Integer getQuilometragem() { return quilometragem; }
    public void setQuilometragem(Integer quilometragem) { this.quilometragem = quilometragem; }

    public StatusVeiculo getStatus() { return status; }
    public void setStatus(StatusVeiculo status) { this.status = status; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getChassi() { return chassi; }
    public void setChassi(String chassi) { this.chassi = chassi; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}

package com.example.decorator;

public class ConsultaBasica implements ServicoVeterinario {
    private final String descricao;
    private final double valorBase;

    public ConsultaBasica(String descricao, double valorBase) {
        this.descricao = descricao;
        this.valorBase = valorBase;
    }

    @Override
    public String getDescricao() { return descricao; }

    @Override
    public double getPreco() { return valorBase; }
}
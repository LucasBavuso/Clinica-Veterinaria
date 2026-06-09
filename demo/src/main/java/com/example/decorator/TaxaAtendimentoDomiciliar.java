package com.example.decorator;

public class TaxaAtendimentoDomiciliar extends ServicoDecorator {
    public TaxaAtendimentoDomiciliar(ServicoVeterinario servico) { super(servico); }

    @Override
    public String getDescricao() { return servicoDecorado.getDescricao() + " + Taxa Domiciliar"; }

    @Override
    public double getPreco() { return servicoDecorado.getPreco() + 40.0; } 
}
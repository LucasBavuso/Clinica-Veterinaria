package com.example.decorator;

public class BanhoPosConsulta extends ServicoDecorator {
    public BanhoPosConsulta(ServicoVeterinario servico) { super(servico); }

    @Override
    public String getDescricao() { return servicoDecorado.getDescricao() + " + Banho Pós-Consulta"; }

    @Override
    public double getPreco() { return servicoDecorado.getPreco() + 35.0; } 
}
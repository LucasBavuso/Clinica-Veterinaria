package com.example.decorator;

public class DescontoAnimalAdotado extends ServicoDecorator {
    public DescontoAnimalAdotado(ServicoVeterinario servico) { super(servico); }

    @Override
    public String getDescricao() { return servicoDecorado.getDescricao() + " + Desconto Adotado"; }

    @Override
    public double getPreco() { return servicoDecorado.getPreco() - 15.0; }
}

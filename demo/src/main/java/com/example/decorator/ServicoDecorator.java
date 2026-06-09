package com.example.decorator;

public abstract class ServicoDecorator implements ServicoVeterinario {
    protected ServicoVeterinario servicoDecorado;

    public ServicoDecorator(ServicoVeterinario servico) {
        this.servicoDecorado = servico;
    }
}
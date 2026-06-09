package com.example.observer;

import com.example.model.Atendimento;

public interface InteressadoObserver {
    void atualizar(Atendimento atendimento);
}
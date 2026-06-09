package com.example.observer;

import com.example.model.Atendimento;
import com.example.state.FinalizadoState;

public class NotificadorRecepcao implements InteressadoObserver {
    @Override
    public void atualizar(Atendimento atendimento) {
        if (atendimento.getSituacaoAtual() instanceof FinalizadoState) {
            System.out.println("[NOTIFICAÇÃO] Enviada para a Recepção: O atendimento foi FINALIZADO.");
        }
    }
}
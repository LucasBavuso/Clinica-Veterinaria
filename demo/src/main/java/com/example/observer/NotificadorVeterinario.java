package com.example.observer;

import com.example.model.Atendimento;
import com.example.state.CanceladoState;

public class NotificadorVeterinario implements InteressadoObserver {
    @Override
    public void atualizar(Atendimento atendimento) {
        if (atendimento.getSituacaoAtual() instanceof CanceladoState) {
            System.out.println("[NOTIFICAÇÃO] Enviada para o Veterinário: O atendimento foi CANCELADO.");
        }
    }
}
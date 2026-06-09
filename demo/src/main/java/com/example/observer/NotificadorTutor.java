package com.example.observer;

import com.example.model.Atendimento;
import com.example.state.EmAtendimentoState;

public class NotificadorTutor implements InteressadoObserver {
    @Override
    public void atualizar(Atendimento atendimento) {
        if (atendimento.getSituacaoAtual() instanceof EmAtendimentoState) {
            System.out.println("[NOTIFICAÇÃO] Enviada para o Tutor: O atendimento de " 
                + atendimento.getAnimal().getNome() + " foi INICIADO.");
        }
    }
}

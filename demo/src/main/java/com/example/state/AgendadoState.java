package com.example.state;

import com.example.model.Atendimento;

public class AgendadoState implements SituacaoState {
    @Override public String getNomeEstado() { return "Agendado"; }
    @Override
    public void iniciar(Atendimento contexto) { contexto.setEstado(new EmAtendimentoState()); }
    @Override
    public void cancelar(Atendimento contexto) { contexto.setEstado(new CanceladoState()); }
    @Override
    public void finalizar(Atendimento contexto) {
        throw new IllegalStateException("Não é possível finalizar um atendimento ainda agendado.");
    }
}

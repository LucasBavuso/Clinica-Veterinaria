package com.example.state;

import com.example.model.Atendimento;

public class FinalizadoState implements SituacaoState {
    @Override public String getNomeEstado() { return "Finalizado"; }
    @Override public void iniciar(Atendimento c) { throw new IllegalStateException("Atendimento já finalizado."); }
    @Override public void finalizar(Atendimento c) { throw new IllegalStateException("Atendimento já finalizado."); }
    @Override public void cancelar(Atendimento c) { throw new IllegalStateException("Um atendimento Finalizado não pode ser cancelado."); }
}

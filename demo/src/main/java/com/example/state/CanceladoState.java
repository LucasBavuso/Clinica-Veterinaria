package com.example.state;

import com.example.model.Atendimento;

public class CanceladoState implements SituacaoState {
    @Override public String getNomeEstado() { return "Cancelado"; }
    @Override public void iniciar(Atendimento c) { throw new IllegalStateException("Atendimento cancelado."); }
    @Override public void finalizar(Atendimento c) { throw new IllegalStateException("Atendimento cancelado."); }
    @Override public void cancelar(Atendimento c) { throw new IllegalStateException("Atendimento já se encontra cancelado."); }
}

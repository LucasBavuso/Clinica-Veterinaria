package com.example.state;

import com.example.model.Atendimento;

public class EmAtendimentoState implements SituacaoState {
    @Override public String getNomeEstado() { return "Em Atendimento"; }
    @Override
    public void finalizar(Atendimento contexto) { contexto.setEstado(new FinalizadoState()); }
    @Override
    public void iniciar(Atendimento contexto) { throw new IllegalStateException("O atendimento já está em curso."); }
    @Override
    public void cancelar(Atendimento contexto) { throw new IllegalStateException("Não é possível cancelar um atendimento em andamento."); }
}

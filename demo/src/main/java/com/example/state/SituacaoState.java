package com.example.state;

import com.example.model.Atendimento;

public interface SituacaoState {
    void iniciar(Atendimento contexto);
    void finalizar(Atendimento contexto);
    void cancelar(Atendimento contexto);
    String getNomeEstado();
}

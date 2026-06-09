package com.example.model;

import java.util.ArrayList;
import java.util.List;

import com.example.decorator.ServicoVeterinario;
import com.example.observer.InteressadoObserver;
import com.example.state.AgendadoState;
import com.example.state.SituacaoState;

public class Atendimento {
    private final Tutor tutor;
    private final Animal animal;
    private ServicoVeterinario servico;
    private SituacaoState situacaoAtual;
    private final List<InteressadoObserver> observadores = new ArrayList<>();

    public Atendimento(Tutor tutor, Animal animal, ServicoVeterinario servico) {
        this.tutor = tutor;
        this.animal = animal;
        this.servico = servico;
        this.situacaoAtual = new AgendadoState(); 
    }

    public void iniciarAtendimento() {
        this.situacaoAtual.iniciar(this);
        notificarObservadores();
    }

    public void finalizarAtendimento() {
        this.situacaoAtual.finalizar(this);
        notificarObservadores();
    }

    public void cancelarAtendimento() {
        this.situacaoAtual.cancelar(this);
        notificarObservadores();
    }

    public void setEstado(SituacaoState novoEstado) {
        this.situacaoAtual = novoEstado;
    }

    public void registrarInteressado(InteressadoObserver obs) { this.observadores.add(obs); }
    
    private void notificarObservadores() {
        for (InteressadoObserver obs : observadores) {
            obs.atualizar(this);
        }
    }

    public double calcularValorFinal() { return this.servico.getPreco(); }
    public SituacaoState getSituacaoAtual() { return situacaoAtual; }
    public Animal getAnimal() { return animal; }
    public Tutor getTutor() { return tutor; }
}
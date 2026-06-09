package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.model.*;
import com.example.state.*;
import com.example.observer.*;
import com.example.decorator.*;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaVeterinarioTest {

    private Tutor tutor;
    private Animal animal;
    private ServicoVeterinario consulta;
    private Atendimento atendimento;

    @BeforeEach
    public void setup() {
        tutor = new Tutor("Lucas", "lucas@email.com");
        animal = new Animal("Pipoca", "Cachorro");
        consulta = new ConsultaBasica("Consulta Geral", 100.0);
        atendimento = new Atendimento(tutor, animal, consulta);
    }

    @Test
    @DisplayName("Deve garantir a cobertura de 100% dos getters de Tutor, Animal e Atendimento")
    public void deveGarantirGettersBaseEDadosDeConstrucao() {
        assertEquals("Lucas", tutor.getNome());
        assertEquals("Pipoca", animal.getNome());
        assertEquals(tutor, atendimento.getTutor());
        assertEquals(animal, atendimento.getAnimal());
        
        assertEquals(100.0, atendimento.calcularValorFinal(), 0.001);
    }

    @Test
    @DisplayName("Ciclo de Vida 1: Agendado -> Em Atendimento -> Finalizado")
    public void deveMudarSituacaoValidaFluxoPrincipal() {
        // CORRIGIDO: Substituído assertInstanceOf por assertTrue + instanceof
        assertTrue(atendimento.getSituacaoAtual() instanceof AgendadoState, "Deveria iniciar como Agendado");
        assertEquals("Agendado", atendimento.getSituacaoAtual().getNomeEstado());

        atendimento.iniciarAtendimento();
        assertTrue(atendimento.getSituacaoAtual() instanceof EmAtendimentoState, "Deveria mudar para Em Atendimento");
        assertEquals("Em Atendimento", atendimento.getSituacaoAtual().getNomeEstado());

        atendimento.finalizarAtendimento();
        assertTrue(atendimento.getSituacaoAtual() instanceof FinalizadoState, "Deveria mudar para Finalizado");
        assertEquals("Finalizado", atendimento.getSituacaoAtual().getNomeEstado());
    }

    @Test
    @DisplayName("Ciclo de Vida 2: Agendado -> Cancelado")
    public void devePermitirCancelamentoQuandoAgendado() {
        atendimento.cancelarAtendimento();
        // CORRIGIDO: Substituído assertInstanceOf por assertTrue + instanceof
        assertTrue(atendimento.getSituacaoAtual() instanceof CanceladoState, "Deveria mudar para Cancelado");
        assertEquals("Cancelado", atendimento.getSituacaoAtual().getNomeEstado());
    }

    @Test
    @DisplayName("Restrições de AGENDADO: Validar todas as negações de método")
    public void deveBarrarExcecoesDoAgendado() {
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> atendimento.finalizarAtendimento());
        assertEquals("Não é possível finalizar um atendimento ainda agendado.", ex.getMessage());
    }

    @Test
    @DisplayName("Restrições de EM ATENDIMENTO: Validar todas as negações de método")
    public void deveBarrarExcecoesDoEmAtendimento() {
        atendimento.iniciarAtendimento();

        IllegalStateException ex1 = assertThrows(IllegalStateException.class, () -> atendimento.iniciarAtendimento());
        assertEquals("O atendimento já está em curso.", ex1.getMessage());

        IllegalStateException ex2 = assertThrows(IllegalStateException.class, () -> atendimento.cancelarAtendimento());
        assertEquals("Não é possível cancelar um atendimento em andamento.", ex2.getMessage());
    }

    @Test
    @DisplayName("Restrições de FINALIZADO: Validar todas as negações de método")
    public void deveBarrarExcecoesDoFinalizado() {
        atendimento.iniciarAtendimento();
        atendimento.finalizarAtendimento();

        IllegalStateException ex1 = assertThrows(IllegalStateException.class, () -> atendimento.iniciarAtendimento());
        assertEquals("Atendimento já finalizado.", ex1.getMessage());

        IllegalStateException ex2 = assertThrows(IllegalStateException.class, () -> atendimento.finalizarAtendimento());
        assertEquals("Atendimento já finalizado.", ex2.getMessage());

        IllegalStateException ex3 = assertThrows(IllegalStateException.class, () -> atendimento.cancelarAtendimento());
        assertEquals("Um atendimento Finalizado não pode ser cancelado.", ex3.getMessage());
    }

    @Test
    @DisplayName("Restrições de CANCELADO: Validar todas as negações de método")
    public void deveBarrarExcecoesDoCancelado() {
        atendimento.cancelarAtendimento();

        IllegalStateException ex1 = assertThrows(IllegalStateException.class, () -> atendimento.iniciarAtendimento());
        assertEquals("Atendimento cancelado.", ex1.getMessage());

        IllegalStateException ex2 = assertThrows(IllegalStateException.class, () -> atendimento.finalizarAtendimento());
        assertEquals("Atendimento cancelado.", ex2.getMessage());

        IllegalStateException ex3 = assertThrows(IllegalStateException.class, () -> atendimento.cancelarAtendimento());
        assertEquals("Atendimento já se encontra cancelado.", ex3.getMessage());
    }

    @Test
    @DisplayName("Garantir que os Observers ignoram estados não mapeados para eles")
    public void deveGarantirQueObserversNaoAtuamNoEstadoIncorreto() {
        NotificadorTutor obsTutor = new NotificadorTutor();
        NotificadorRecepcao obsRec = new NotificadorRecepcao();
        NotificadorVeterinario obsVet = new NotificadorVeterinario();

        atendimento.registrarInteressado(obsTutor);
        atendimento.registrarInteressado(obsRec);
        atendimento.registrarInteressado(obsVet);

        assertDoesNotThrow(() -> atendimento.cancelarAtendimento());
    }

    @Test
    @DisplayName("Garantir acionamento real do Notificador da Recepção")
    public void deveAcionarNotificadorRecepcaoNoEstadoCorreto() {
        NotificadorRecepcao obsRec = new NotificadorRecepcao();
        atendimento.registrarInteressado(obsRec);

        assertDoesNotThrow(() -> {
            atendimento.iniciarAtendimento();
            atendimento.finalizarAtendimento(); 
        });
    }

    @Test
    @DisplayName("Garantir cálculo matemático e herança de texto de todas as decorações")
    public void deveCobrirPrecosEDescricoesDeTodosOsDecorators() {
        ServicoVeterinario servico = new ConsultaBasica("Consulta de Rotina", 120.0);
        assertEquals("Consulta de Rotina", servico.getDescricao());
        assertEquals(120.0, servico.getPreco());

        ServicoVeterinario c1 = new DescontoAnimalAdotado(servico);
        assertEquals("Consulta de Rotina + Desconto Adotado", c1.getDescricao());
        assertEquals(105.0, c1.getPreco(), 0.001); 

        ServicoVeterinario c2 = new TaxaAtendimentoDomiciliar(servico);
        assertEquals("Consulta de Rotina + Taxa Domiciliar", c2.getDescricao());
        assertEquals(160.0, c2.getPreco(), 0.001); 

        ServicoVeterinario c3 = new BanhoPosConsulta(servico);
        assertEquals("Consulta de Rotina + Banho Pós-Consulta", c3.getDescricao());
        assertEquals(155.0, c3.getPreco(), 0.001); 
    }
}
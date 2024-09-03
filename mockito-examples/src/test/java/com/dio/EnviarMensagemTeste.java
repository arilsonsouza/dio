package com.dio;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.dio.mockito.examplos.EnviarMensagem;
import com.dio.mockito.examplos.Mensagem;

@RunWith(MockitoJUnitRunner.class)
public class EnviarMensagemTeste {

  @Spy
  private EnviarMensagem enviarMensagem;

  @Test
  public void verificarComportamentoDaClasse() {
    Mockito.verifyNoInteractions(enviarMensagem);

    Mensagem mensagem = new Mensagem("Hello World!");
    enviarMensagem.adicionarMensagem(mensagem);

    Mockito.verify(enviarMensagem).adicionarMensagem(mensagem);

    Assertions.assertFalse(enviarMensagem.getMensagens().isEmpty());
  }
}

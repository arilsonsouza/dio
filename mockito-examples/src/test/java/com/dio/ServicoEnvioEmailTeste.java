package com.dio;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.dio.mockito.examplos.Email;
import com.dio.mockito.examplos.Formato;
import com.dio.mockito.examplos.PlataformaDeEnvio;
import com.dio.mockito.examplos.ServicoEnvioEmail;

@RunWith(MockitoJUnitRunner.class)
public class ServicoEnvioEmailTeste {

  @Mock
  private PlataformaDeEnvio plataformaDeEnvio;

  @InjectMocks
  private ServicoEnvioEmail servicoEnvioEmail;

  @Captor
  private ArgumentCaptor<Email> captor;

  @Test
  public void validarDadosEnviadosParaPlataforma() {
    String email = "teste@email.com.br";
    String messagem = "Olá mundo teste mensagem";
    boolean eFormatoHtml = false;

    servicoEnvioEmail.enviaEmail(email, messagem, eFormatoHtml);

    Mockito.verify(plataformaDeEnvio).enviaEmail(captor.capture());

    Email emailCapturado = captor.getValue();

    Assertions.assertEquals(email, emailCapturado.getEnderecoEmail());
    Assertions.assertEquals(messagem, emailCapturado.getMensagem());
    Assertions.assertEquals(Formato.TEXTO, emailCapturado.getFormato());
  }
}

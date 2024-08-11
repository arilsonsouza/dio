package com.dio;

import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.dio.mockito.examplos.Conta;

@RunWith(MockitoJUnitRunner.class)
public class ContaTeste {

  @Spy
  private Conta conta = new Conta(1_000);

  @Test
  public void validarOrdemDeChamadas() {
    conta.pagaBoleto(300);

    InOrder inOrder = Mockito.inOrder(conta);
    inOrder.verify(conta).pagaBoleto(300);
    inOrder.verify(conta).validaSaldo(300);
    inOrder.verify(conta).debita(300);
    inOrder.verify(conta).enviaCreditoParaEmissor(300);
  }

  @Test
  public void validarQuantidadeDeChamadas() {
    conta.validaSaldo(300);
    conta.validaSaldo(500);
    conta.validaSaldo(600);

    Mockito.verify(conta, Mockito.times(3)).validaSaldo(anyInt());
  }

  @Test
  public void retornaTrueParaQualquerValorNaValidacaoDeSaldo() {
    Mockito.doNothing().when(conta).validaSaldo(anyInt());

    conta.validaSaldo(3500);
  }
}

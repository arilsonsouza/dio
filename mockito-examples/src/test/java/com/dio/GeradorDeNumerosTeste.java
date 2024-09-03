package com.dio;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.dio.mockito.examplos.GeradorDeNumeros;

@RunWith(MockitoJUnitRunner.class)
public class GeradorDeNumerosTeste {

  @Test
  public void testaGeracaoComTamanhoDefinido() {
    MockedStatic<GeradorDeNumeros> mockedStatic = Mockito.mockStatic(GeradorDeNumeros.class);
  }
}

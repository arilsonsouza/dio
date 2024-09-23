package com.dio;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.dio.mockito.examplos.ApiDosCorreios;
import com.dio.mockito.examplos.CadastrarPessoa;
import com.dio.mockito.examplos.DadosLocalizacao;
import com.dio.mockito.examplos.Pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarPessoaTeste {

  @Mock
  private ApiDosCorreios apiDosCorreios;

  @InjectMocks
  private CadastrarPessoa cadastrarPessoa;

  @Test
  public void validarDadosDeCadastro() {
    DadosLocalizacao dadosLocalizacao = new DadosLocalizacao("MG", "Patos de Minas", "Rua 2", "Apto", "Centro");
    Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep("44905000")).thenReturn(dadosLocalizacao);

    Pessoa pessoa = cadastrarPessoa.cadastrarPessoa("Arilson", "00000000000", LocalDate.now(), "44905000");

    assertEquals("Arilson", pessoa.getNome());
    assertEquals("00000000000", pessoa.getDocumento());
    assertEquals("MG", pessoa.getEndereco().getUf());
    assertEquals("Patos de Minas", pessoa.getEndereco().getCidade());

  }

  @Test
  public void lancarExceptionQuandoChamarApiCorreios() {
    Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep(anyString())).thenThrow(IllegalArgumentException.class);

    Assertions.assertThrows(IllegalArgumentException.class,
        () -> cadastrarPessoa.cadastrarPessoa("Arilson", "00000000000", LocalDate.now(), "44905000"));
  }
}

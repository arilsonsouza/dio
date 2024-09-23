package com.dio.mockito.examplos;

import java.time.LocalDate;

public class Pessoa {
  private String nome;
  private String documento;
  private LocalDate nascimento;
  private DadosLocalizacao endereco;

  public Pessoa(final String nome, final String documento, final LocalDate nascimento) {
    this.nome = nome;
    this.documento = documento;
    this.nascimento = nascimento;
  }

  public void adicionaDadosDeEndereco(DadosLocalizacao dadosLocalizacao) {
    this.endereco = dadosLocalizacao;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDocumento() {
    return documento;
  }

  public void setDocumento(String documento) {
    this.documento = documento;
  }

  public LocalDate getNascimento() {
    return nascimento;
  }

  public void setNascimento(LocalDate nascimento) {
    this.nascimento = nascimento;
  }

  public DadosLocalizacao getEndereco() {
    return endereco;
  }

}

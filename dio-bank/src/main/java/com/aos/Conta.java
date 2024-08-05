package com.aos;

public abstract class Conta implements IConta {
  private static final int AGENCIA_PADRAO = 1;
  private static int SEQUENCIAL = 1;

  protected int agencia;
  protected int numero;
  protected double saldo;
  protected Cliente cliente;

  public Conta(Cliente cliente) {
    agencia = AGENCIA_PADRAO;
    numero = SEQUENCIAL++;
    saldo = 0;
    this.cliente = cliente;
  }

  public int getAgencia() {
    return agencia;
  }

  public int getNumero() {
    return numero;
  }

  public double getSaldo() {
    return saldo;
  }

  @Override
  public void depositar(double valor) {
    saldo += valor;
  }

  @Override
  public void sacar(double valor) {
    saldo -= valor;
  }

  @Override
  public void transferir(double valor, Conta contaDestino) {
    this.sacar(valor);
    contaDestino.depositar(valor);
  }

  protected void imprimirInfoExtrato() {
    System.out.println(String.format("Titular: %s", this.cliente.getNome()));
    System.out.println(String.format("Agencia: %d", agencia));
    System.out.println(String.format("Número: %d", numero));
    System.out.println(String.format("Saldo: %.2f", saldo));

  }
}

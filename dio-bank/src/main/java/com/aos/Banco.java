package com.aos;

import java.util.ArrayList;
import java.util.List;

public class Banco {
  private String nome;
  private List<Conta> contas = new ArrayList<>();

  public Banco(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }

  public Conta novaContaCorrente(Cliente cliente) {
    Conta conta = new ContaCorrente(cliente);
    return adicionaConta(conta);
  }

  public Conta novaContaPoupanca(Cliente cliente) {
    Conta conta = new ContaPoupanca(cliente);
    return adicionaConta(conta);
  }

  private Conta adicionaConta(Conta conta) {
    this.contas.add(conta);
    return conta;
  }

  public List<Conta> getContas() {
    return contas;
  }

  public Cliente novoCliente(String string) {
    return new Cliente("Arilson");
  }

  public void imprimirClientes() {
    System.out.println("=== Clientes ===");
    this.contas.forEach(conta -> {
      System.out.println(conta.cliente.getNome());
    });
  }
}

package com.aos;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Que Banco");

        Cliente cliente = banco.novoCliente("Arilson");

        Conta cc = banco.novaContaCorrente(cliente);
        cc.depositar(100);

        Conta cp = banco.novaContaPoupanca(cliente);

        cc.transferir(50, cp);

        cc.imprimirExtrato();
        cp.imprimirExtrato();

        banco.imprimirClientes();
    }
}

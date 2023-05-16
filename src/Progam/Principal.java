package Progam;

import entities.ContaCorrente;

import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {

        ContaCorrente conta = new ContaCorrente("Gustavo", "123.456.789-98", "senha");
        
        System.out.println(conta.toString());

        conta.depositar(500.0);
        System.out.println("Depósito realizado. Saldo atual: " + conta.getSaldo());

        conta.sacar(200.0);
        System.out.println("Saque realizado. Saldo atual: " + conta.getSaldo());


        conta.transferirPix("gustavo@gmail.com", 100.0);
        System.out.println("Transferência via Pix realizada. Saldo atual: " + conta.getSaldo());


        conta.transferir("12234", 567389, 150.0);
        System.out.println("Transferência realizada. Saldo atual: " + conta.getSaldo());


        ArrayList<String> extrato = conta.getExtrato();
        System.out.println("Extrato da conta:");
        for (String operacao : extrato) {
            System.out.println(operacao);
        }
    }
}

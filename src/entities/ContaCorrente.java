package entities;

import java.util.ArrayList;
import java.util.Random;

public class ContaCorrente {
    String titular;
    private double saldo;
    String cpf;

    String cartao;
    String senha;
    double cheque_especial;
    double limite_cartao;
    int numero;
    String agencia;

    String[] chave_pix;
    private double juros_cheque = 0.2;
    private double valorEmJuizo;

    ArrayList<String> extrato;

    public ContaCorrente(String titular, String cpf, String senha) {
        this.titular = titular;
        this.cpf = cpf;
        this.senha = senha;
        this.saldo = 0;
        this.cheque_especial = 0;
        this.limite_cartao = 0;
        this.agencia = "0001";
        this.numero = (1000000 % new Random().nextInt()) + 1000;
        this.valorEmJuizo = 0;
        this.extrato = new ArrayList<>();
    }

    public double getSaldo() {
        return this.saldo;
    }

    public double getLimiteChequeEspecialTotal() {
        if (this.saldo < 0) {
            return this.cheque_especial + (-1 * this.saldo);
        } else {
            return this.cheque_especial;
        }
    }

    public double getLimiteChequeEspecialAtual() {
        return this.cheque_especial;
    }

    public String toString() {
        String retorno = "Titular: " + this.getTitular() + "\n";
        retorno += "Ag: " + this.agencia + " Cc: " + this.numero + "\n";
        retorno += "Saldo atual: " + this.getSaldo();
        return retorno;
    }

    public void depositar(double valor) {
        if (this.saldo < 0) {
            valor += this.saldo * this.juros_cheque;
            this.cheque_especial += (valor - (-this.saldo));
        }
        if (this.valorEmJuizo > 0) {
            valor -= this.valorEmJuizo;
            this.valorEmJuizo = 0;
        }
        this.saldo += valor;
        this.extrato.add("Depósito: +" + valor);
    }

    public void sacar(double valor) {
        if (this.saldo + this.cheque_especial >= valor) {
            if (this.saldo < valor) {
                this.cheque_especial -= (valor - this.saldo);
                this.saldo = 0;
            } else {
                this.saldo -= valor;
            }
            this.extrato.add("Saque: -" + valor);
        } else {
            System.out.println("Não é possível sacar");
        }
    }

    public void transferir(String agencia, int conta, double valor) {
        this.sacar(valor);
        this.extrato.add("Transferência para conta " + agencia + "/" + conta + ": -" + valor);
    }

    public void transferirPix(String pix, double valor) {
        this.extrato.add("Transferência via Pix para " + pix + ": -" + valor);
        this.sacar(valor);
    }

    public String getTitular() {
        return this.titular + " [" + this.cpf + "]";
    }

    public double getValorEmJuizo() {
        return this.valorEmJuizo;
    }

    public void setValorEmJuizo(double valorEmJuizo) {
        this.valorEmJuizo = valorEmJuizo;
    }

    public ArrayList<String> getExtrato() {
        return this.extrato;
    }
}


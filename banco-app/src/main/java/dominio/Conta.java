package dominio;

import excecoes.SaldoInsuficienteError;
import excecoes.ValorInvalidoError;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Conta {
    protected String numero;
    protected Double saldo;

    public Conta(String numero, Double saldo){
        this.numero = numero;
        depositar(saldo);
    }

    public String getNumero(){
        return this.numero;
    }

    public Double getSaldo(){
        return this.saldo;
    }

    public void sacar(Double valor){
        validarValor(valor);
        if(saldo < valor){
            throw new SaldoInsuficienteError("Saldo insuficiente");
        }
        this.saldo = new BigDecimal(saldo - valor).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
    }

    public void depositar(Double valor){
        validarValor(valor);
        this.saldo = new BigDecimal(valor).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
    }

    public void transferir(Conta contaCredito, Double valor){
        this.sacar(valor);
        contaCredito.depositar(valor);
    }

    @Override
    public String toString() {
        return "Conta{" +
                "numero='" + numero + '\'' +
                ", saldo=" + saldo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return this.numero.equals(((Conta) o).numero);
    }

    public void validarValor(Double valor){
        if(valor.isNaN() || valor < 0.0){
            throw new ValorInvalidoError("Valor inválido: ".concat(valor.toString()));
        }
    }
}

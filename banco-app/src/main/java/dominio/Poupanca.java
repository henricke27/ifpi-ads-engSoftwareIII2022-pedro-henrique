package dominio;

import java.math.BigDecimal;

public class Poupanca extends Conta{

    private Integer taxaJuros;

    public Poupanca(String numero, Double saldo, Integer taxaJuros){
        super(numero,saldo);
        this.taxaJuros = taxaJuros;
    }

    public Integer getTaxaJuros() {
        return taxaJuros;
    }

    public void renderJuros(){
        double juros = new BigDecimal(taxaJuros).divide(new BigDecimal("100")).doubleValue();
        this.depositar(this.saldo + (this.saldo * juros));
    }

    @Override
    public String toString() {
        return "Poupanca{" +
                "taxaJuros=" + taxaJuros +
                ", numero='" + numero + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}

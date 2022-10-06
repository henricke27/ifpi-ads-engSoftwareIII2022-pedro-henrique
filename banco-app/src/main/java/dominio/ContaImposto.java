package dominio;

import excecoes.SaldoInsuficienteError;

public class ContaImposto extends Conta{

    private Integer taxaDesconto;

    public ContaImposto(String numero, Double saldo, Integer taxaDesconto) {
        super(numero, saldo);
        this.taxaDesconto = taxaDesconto;
    }

    @Override
    public void sacar(Double valor){
        super.validarValor(valor);
        if(saldo < valor){
            throw new SaldoInsuficienteError("Saldo insuficiente");
        }
        var total = valor + (valor * (this.taxaDesconto / 100));
        System.out.println(total);
        super.sacar(total);
    }
}

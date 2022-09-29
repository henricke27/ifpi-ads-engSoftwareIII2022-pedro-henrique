import dominio.Banco;
import dominio.Conta;
import dominio.Poupanca;

import java.math.BigDecimal;

public class Teste {
    public static void main(String[] args){
        Banco banco = new Banco();

        Conta conta1 = new Conta("123", 600.059);
        Poupanca conta2 = new Poupanca("456", 600.05, 1);

        banco.inserirConta(conta1);
        banco.inserirConta(conta2);
        banco.inserirConta(new Poupanca("789", 1234.56, 1));


        banco.alterarConta(new Poupanca("456", 600.05, 2));
        //banco.excluirConta("123");
        conta2.transferir(conta1, new BigDecimal("10").doubleValue());
        //conta2.renderJuros();

        banco.renderJuros("456");
        //banco.contas.forEach(System.out::println);
        System.out.println(banco.quantidadeContas());
        System.out.println(banco.totalSaldos());
        System.out.println(banco.mediaSaldos());
    }
}

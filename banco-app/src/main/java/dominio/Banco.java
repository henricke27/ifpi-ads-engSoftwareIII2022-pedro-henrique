package dominio;

import excecoes.ContaInexistenteError;
import excecoes.PoupancaInvalidaError;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Banco {
    public ArrayList<Conta> contas;

    public Banco() {
        this.contas = new ArrayList<>();
    }

    public void inserirConta(Conta conta){
        try {
            Conta contaConsultada = consultarConta(conta.numero);
        } catch(ContaInexistenteError exception){
            contas.add(conta);
        }
    }

    public Conta consultarConta(String numero){
        try {
            return contas.stream()
                    .filter((conta) -> conta.getNumero().equals(numero))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException exception){
            throw new ContaInexistenteError("Conta inexistente");
        }
    }
    
    public void alterarConta(Conta conta){
        contas.remove(consultarConta(conta.numero));
        contas.add(conta);
    }

    public void excluirConta(String numero){
        contas.remove(consultarConta(numero));
    }

    public void depositar(String numero, Double valor){
        consultarConta(numero).depositar(valor);
    }

    public void sacar(String numero, Double valor){
        consultarConta(numero).sacar(valor);
    }

    public void transferir(String sourceNumero, String targetNumero, Double valor){
        Conta source = consultarConta(sourceNumero);
        Conta target = consultarConta(targetNumero);

        source.transferir(target, valor);
    }

    public Integer quantidadeContas(){
        return contas.size();
    }

    public Double totalSaldos(){
        double soma = contas.stream()
                .mapToDouble(Conta::getSaldo)
                .sum();

        return new BigDecimal(soma).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
    }

    public Double mediaSaldos(){
        double media = contas.stream()
                .mapToDouble(Conta::getSaldo)
                .average()
                .orElse(0.0);

        return new BigDecimal(media).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
    }

    public void renderJuros(String numero){
        Conta conta = consultarConta(numero);

        if(!(conta instanceof Poupanca)) throw new PoupancaInvalidaError("Esta conta não é poupança!");

        ((Poupanca) conta).renderJuros();
    }

}

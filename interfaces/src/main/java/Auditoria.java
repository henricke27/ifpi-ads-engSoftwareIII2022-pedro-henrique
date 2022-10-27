import java.util.ArrayList;
import java.util.List;

public class Auditoria {

    public static void main(String[] args) {
        AuditoriaInterna auditoriaInterna = new AuditoriaInterna();

        auditoriaInterna.adicionar(new SeguroDeVida());
        auditoriaInterna.adicionar(new SeguroDeVida());
        auditoriaInterna.adicionar(new SeguroDeVida());
        auditoriaInterna.adicionar(new ContaCorrente("conta1", 10));
        auditoriaInterna.adicionar(new ContaCorrente("conta2", 100));
        auditoriaInterna.adicionar(new ContaCorrente("conta3", 1000));

        System.out.format("Total de tributos: %.2f%n", auditoriaInterna.calcularTributos());

    }
}

class Conta {
    private String nome;
    private double saldo;

    public Conta(String nome, double saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

class ContaCorrente extends Conta implements Tributavel{

    public ContaCorrente(String nome, double saldo) {
        super(nome, saldo);
    }

    @Override
    public double calculaTributos() {
        return this.getSaldo() * 0.1;
    }
}

class SeguroDeVida implements Tributavel {

    @Override
    public double calculaTributos() {
        return 50.0;
    }
}

interface Tributavel {
    double calculaTributos();
}

class AuditoriaInterna {
    private List<Tributavel> tributaveis;

    public AuditoriaInterna() {
        this.tributaveis = new ArrayList<>();
    }

    public void adicionar(Tributavel tributavel){
        this.tributaveis.add(tributavel);
    }

    public double calcularTributos(){
        return tributaveis.stream()
                .mapToDouble(Tributavel::calculaTributos)
                .sum();
    }
}
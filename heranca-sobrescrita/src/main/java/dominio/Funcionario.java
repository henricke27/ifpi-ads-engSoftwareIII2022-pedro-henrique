package dominio;

import java.math.BigDecimal;

public class Funcionario extends Pessoa{

    private String matricula;
    private BigDecimal salario;

    public Funcionario(String nome, String sobrenome, String matricula, BigDecimal salario) {
        super(nome, sobrenome);
        this.matricula = matricula;
        this.salario = salario;
    }

    public String getMatricula() {
        return matricula;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public BigDecimal calcularSalarioPrimeiraParcela() {
        return salario.multiply(new BigDecimal("0.6"));
    }

    public BigDecimal calcularSalarioSegundaParcela() {
        return salario.multiply(new BigDecimal("0.4"));
    }

}

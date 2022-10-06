package dominio;

import java.math.BigDecimal;

public class Professor extends Funcionario{

    private String titulacao;

    public Professor(String nome, String sobrenome, String matricula, BigDecimal salario, String titulacao) {
        super(nome, sobrenome, matricula, salario);
        this.titulacao = titulacao;
    }

    @Override
    public BigDecimal calcularSalarioPrimeiraParcela() {
        return super.getSalario();
    }

    @Override
    public BigDecimal calcularSalarioSegundaParcela() {
        return BigDecimal.ZERO;
    }
}

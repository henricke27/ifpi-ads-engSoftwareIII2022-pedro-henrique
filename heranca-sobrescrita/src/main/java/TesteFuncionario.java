import dominio.Funcionario;

import java.math.BigDecimal;

public class TesteFuncionario {
    public static void main(String[] args) {
        Funcionario funcionario = new Funcionario("Pedro", "Henrique", "2021111tads0162", new BigDecimal("5000"));
        System.out.format("Nome completo: %s%n", funcionario.nomeCompleto());
        System.out.format("Primeira parcela: %.2f%n", funcionario.calcularSalarioPrimeiraParcela());
        System.out.format("Segunda parcela: %.2f%n", funcionario.calcularSalarioSegundaParcela());
    }
}

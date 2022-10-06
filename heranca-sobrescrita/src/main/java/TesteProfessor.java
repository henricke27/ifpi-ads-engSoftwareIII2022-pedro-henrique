import dominio.Professor;

import java.math.BigDecimal;

public class TesteProfessor {
    public static void main(String[] args) {
        Professor professor = new Professor("Pedro", "Henrique", "202111tads0162", new BigDecimal("5000"), "Graduação");
        System.out.format("Primeira parcela: %.2f%n", professor.calcularSalarioPrimeiraParcela());
        System.out.format("Segunda parcela: %.2f%n", professor.calcularSalarioSegundaParcela());
    }
}

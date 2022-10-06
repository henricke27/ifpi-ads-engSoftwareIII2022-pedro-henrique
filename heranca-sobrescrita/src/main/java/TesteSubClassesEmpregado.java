import dominio.Diarista;
import dominio.Horista;

public class TesteSubClassesEmpregado {

    public static void main(String[] args) {
        Diarista diarista = new Diarista();
        Horista horista = new Horista();

        System.out.format("Salário diarista: %.2f%n", diarista.calcularSalario().doubleValue());
        System.out.format("Salário horista: %.2f", horista.calcularSalario().doubleValue());
    }
}

import dominio.Diretor;
import dominio.Gerente;
import dominio.Presidente;

public class TesteFuncionario {

    public static void main(String[] args) {
        Gerente gerente = new Gerente(1000);
        Diretor diretor = new Diretor(1000);
        Presidente presidente = new Presidente(1000);

        System.out.format("Gerente: %.2f%n",gerente.getBonificacao());
        System.out.format("Diretor: %.2f%n",diretor.getBonificacao());
        System.out.format("Presidente: %.2f%n",presidente.getBonificacao());
    }
}

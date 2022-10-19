package dominio;

public class Diretor extends Funcionario{

    public Diretor(double salario){
        super.salario = salario;
    }

    @Override
    public double getBonificacao() {
        return this.salario * 1.60;
    }
}

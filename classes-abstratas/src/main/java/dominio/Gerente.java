package dominio;

public class Gerente extends Funcionario{

    public Gerente(double salario){
        super.salario = salario;
    }

    @Override
    public double getBonificacao() {
        return this.salario * 1.40;
    }
}

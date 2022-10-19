package dominio;

public class Presidente extends Funcionario{

    public Presidente(double salario){
        super.salario = salario;
    }

    @Override
    public double getBonificacao() {
        return (this.salario * 2.0) + 1000;
    }
}

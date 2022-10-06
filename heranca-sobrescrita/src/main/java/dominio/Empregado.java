package dominio;

import java.math.BigDecimal;

public class Empregado {;
    private BigDecimal salario;

    public Empregado (){
        this.salario = new BigDecimal("500");;
    }

    public BigDecimal calcularSalario(){
        return salario;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
}

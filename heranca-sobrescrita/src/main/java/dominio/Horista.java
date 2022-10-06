package dominio;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Horista extends Empregado{

    @Override
    public BigDecimal calcularSalario() {
        return super.calcularSalario().divide(new BigDecimal("24"), 2, RoundingMode.DOWN);
    }
}

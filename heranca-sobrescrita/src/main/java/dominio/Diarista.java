package dominio;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Diarista extends Empregado{

    @Override
    public BigDecimal calcularSalario() {
        return super.calcularSalario().divide(new BigDecimal("30"),2, RoundingMode.DOWN);
    }
}

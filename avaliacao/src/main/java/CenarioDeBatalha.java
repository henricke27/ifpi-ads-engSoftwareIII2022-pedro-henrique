import java.util.List;

public class CenarioDeBatalha {

    public String avaliar(List<Defensivel> defensiveis1, List<Defensivel> defensiveis2){
        if(defensiveis1.size() > defensiveis2.size()){
            return "Vitoria do exercito 1";
        }else if (defensiveis1.size() == defensiveis2.size()){
            return "Empate";
        }else {
            return "Vitoria do exercito 2";
        }
    }
}

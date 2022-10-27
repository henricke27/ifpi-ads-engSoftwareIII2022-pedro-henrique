import java.util.ArrayList;
import java.util.List;

public class Testes {
    public static void main(String[] args) {
        int quantidadeDeTurnos = 10;

        List<Defensivel> exercito1 = new ArrayList<>();
        List<Defensivel> exercito2 = new ArrayList<>();
        List<Guerreiro> guerreirosExercito1 = new ArrayList<>();
        List<Guerreiro> guerreirosExercito2 = new ArrayList<>();

        //Populando Exercito 1
        Guerreiro guerreiro1 = new Guerreiro(1l, "bom", numeroAleatorio(), numeroAleatorio());
        Guerreiro guerreiro2 = new Guerreiro(2l, "ruim", numeroAleatorio(), numeroAleatorio());
        Guerreiro guerreiro3 = new Guerreiro(3l, "médio", numeroAleatorio(), numeroAleatorio());
        guerreirosExercito1.add(guerreiro1);
        guerreirosExercito1.add(guerreiro2);
        guerreirosExercito1.add(guerreiro3);
        exercito1.addAll(guerreirosExercito1);
        exercito1.add(new BaseMilitar(1l, "111", "111"));
        exercito1.add(new BaseMilitar(1l, "222", "222"));
        exercito1.add(new BaseMilitar(1l, "333", "333"));

        //Populando Exercito 2
        Guerreiro guerreiro4 = new Guerreiro(4l, "bom", numeroAleatorio(), numeroAleatorio());
        Guerreiro guerreiro5 = new Guerreiro(5l, "ruim", numeroAleatorio(), numeroAleatorio());
        Guerreiro guerreiro6 = new Guerreiro(6l, "médio", numeroAleatorio(), numeroAleatorio());
        guerreirosExercito2.add(guerreiro4);
        guerreirosExercito2.add(guerreiro5);
        guerreirosExercito2.add(guerreiro6);
        exercito2.addAll(guerreirosExercito2);
        exercito2.add(new BaseMilitar(1l, "777", "777"));
        exercito2.add(new BaseMilitar(1l, "888", "888"));
        exercito2.add(new BaseMilitar(1l, "999", "999"));

        for (int i = 0; i < quantidadeDeTurnos; i++) {
            Guerreiro guerreiroExercito1 = guerreirosExercito1.stream()
                    .findAny()
                    .get();
            Defensivel defensivelDoExercito2 = exercito2.stream()
                    .findAny()
                    .get();

            try{
                guerreiroExercito1.atacar(defensivelDoExercito2);

            }catch (JaEliminadoException exception){
                exercito2.remove(defensivelDoExercito2);
            }

            Guerreiro guerreiroExercito2 = guerreirosExercito2.stream()
                    .findAny()
                    .get();

            Defensivel defensivelDoExercito1 = exercito1.stream()
                    .findAny()
                    .get();
            try{
                guerreiroExercito2.atacar(defensivelDoExercito1);
            }catch (JaEliminadoException exception){
                exercito1.remove(defensivelDoExercito1);
            }
        }

        CenarioDeBatalha cenarioDeBatalha = new CenarioDeBatalha();
        System.out.println(cenarioDeBatalha.avaliar(exercito1, exercito2));
    }

    public static double numeroAleatorio(){
        return Math.random() * 100;
    }
}

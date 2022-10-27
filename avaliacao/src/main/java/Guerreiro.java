public class Guerreiro implements Defensivel{
    private long id;
    private String descricao;
    private double forcaAtaque;
    private double life;

    public Guerreiro(Long id, String descricao, double forcaAtaque, double life) {
        this.id = id;
        this.descricao = descricao;
        this.forcaAtaque = forcaAtaque;
        this.life = life;
    }

    @Override
    public boolean estaEliminado() {
        return life <= 0;
    }

    @Override
    public void defenderAtaque(double valorAtaque) {
        this.life -= valorAtaque;
    }

    public void atacar(Defensivel defensivel){
        if (defensivel.estaEliminado()){
            throw new JaEliminadoException("Defensível já está eliminado");
        };

        defensivel.defenderAtaque(forcaAtaque);
    }

}

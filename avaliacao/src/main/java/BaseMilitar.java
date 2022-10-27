public class BaseMilitar implements Defensivel{
    private long id;
    private String eixoX;
    private String eixoY;
    private double percentualDeDano;

    public BaseMilitar(long id, String eixoX, String eixoY) {
        this.id = id;
        this.eixoX = eixoX;
        this.eixoY = eixoY;
        this.percentualDeDano = 0.0;
    }

    @Override
    public boolean estaEliminado() {
        return percentualDeDano > 90;
    }

    @Override
    public void defenderAtaque(double valorAtaque) {
        this.percentualDeDano += (valorAtaque / 100);
    }

    public double getPercentualDeDano() {
        return percentualDeDano;
    }
}

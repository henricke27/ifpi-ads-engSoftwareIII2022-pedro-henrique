package figuras_geometricas;

public class FigurasGeometricas{
    public static void main(String[] args){
        Triangulo triangulo = new Triangulo(5, 5, 2);
        Quadrado quadrado = new Quadrado(5);
        Circulo circulo = new Circulo(5);

        System.out.format("Triângulo: %.2f%n", triangulo.calcularPerimetro());
        System.out.format("Quadrado: %.2f%n", quadrado.calcularPerimetro());
        System.out.format("Circulo: %.2f%n", circulo.calcularPerimetro());
    }
}

abstract class FiguraGeometrica {

    protected abstract double calcularPerimetro();

}

class Triangulo extends FiguraGeometrica{

    private double lado1;
    private double lado2;
    private double lado3;

    public Triangulo(double lado1, double lado2, double lado3){
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
    }

    @Override
    protected double calcularPerimetro() {
        return lado1 + lado2 + lado3;
    }

}

class Quadrado extends FiguraGeometrica{

    private double lado;

    public Quadrado(double lado){
        this.lado = lado;
    }

    @Override
    protected double calcularPerimetro() {
        return lado * 4.0;
    }
}

class Circulo extends FiguraGeometrica{

    private double raio;
    private double pi;

    public Circulo(double raio){
        this.raio = raio;
        this.pi = Math.PI;
    }

    @Override
    protected double calcularPerimetro() {
        return 2 * pi * raio;
    }
}

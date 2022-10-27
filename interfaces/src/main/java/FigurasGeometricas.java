import java.util.Comparator;
import java.util.List;

public class FigurasGeometricas {
    public static void main(String[] args) {
        Quadrado quadrado = new Quadrado(5);
        Triangulo triangulo = new Triangulo(4.0, 5.0, 6.0);
        Circulo circulo = new Circulo(5);

        System.out.format("Área do quadraoo: %.2f%n", quadrado.calcularArea());
        System.out.format("Perímetro do quadraoo: %.2f%n%n", quadrado.calcularPerimetro());

        System.out.format("Área do triângulo: %.2f%n", triangulo.calcularArea());
        System.out.format("Perímetro do triângulo: %.2f%n%n", triangulo.calcularPerimetro());

        System.out.format("Área do circulo: %.2f%n", circulo.calcularArea());
        System.out.format("Perímetro do circulo: %.2f%n", circulo.calcularPerimetro());

        System.out.println("########### Comparações de área ###########");

        System.out.format("quadrado/triangulo: %d%n",quadrado.comparar(triangulo));
        System.out.format("triangulo/quadrado: %d%n",triangulo.comparar(quadrado));
        System.out.format("circulo/quadrado: %d%n",circulo.comparar(quadrado));
        System.out.format("quadrado/circulo: %d%n",quadrado.comparar(circulo));
        System.out.format("circulo/triangulo: %d%n",circulo.comparar(triangulo));
        System.out.format("triangulo/circulo: %d%n",triangulo.comparar(circulo));

        Quadrado quadrado2 = new Quadrado(5);
        Triangulo triangulo2 = new Triangulo(4.0, 5.0, 6.0);
        Circulo circulo2 = new Circulo(5);

        System.out.format("quadrado/quadrado2: %d%n",quadrado.comparar(quadrado2));
        System.out.format("triangulo/triangulo2: %d%n",triangulo.comparar(triangulo2));
        System.out.format("circulo/circulo2: %d%n",circulo.comparar(circulo2));
    }
}

interface FiguraGeometrica{
    double calcularArea();
    double calcularPerimetro();
}

interface Comparavel{
    int comparar(FiguraGeometrica figuraGeometrica);
}

class Quadrado implements FiguraGeometrica, Comparavel {

    private double lado;

    public Quadrado(double lado){
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return Math.pow(lado, 2.0);
    }

    @Override
    public double calcularPerimetro() {
        return 4 * lado;
    }

    @Override
    public int comparar(FiguraGeometrica figura) {
        return Double.compare(calcularArea(), figura.calcularArea());
    }
}

class Triangulo implements FiguraGeometrica, Comparavel { //equilátero

    private double lado1;
    private double lado2;
    private double lado3;

    private double altura;

    public Triangulo(double lado1, double lado2, double lado3){
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
        this.altura = (lado1 + lado2 + lado3) / 2;
    }

    @Override
    public double calcularArea() {
        double ladoMin = List.of(lado1, lado2, lado3).
                stream()
                .min(Comparator.comparingDouble(Double::doubleValue))
                .get();

        return (ladoMin * altura) / 2;
    }

    @Override
    public double calcularPerimetro() {
        return lado1 + lado2 + lado3;
    }

    @Override
    public int comparar(FiguraGeometrica figura) {
        return Double.compare(calcularArea(), figura.calcularArea());
    }
}

class Circulo implements FiguraGeometrica, Comparavel {

    private double pi;
    private double raio;


    public Circulo(double raio){
        this.raio = raio;
        this.pi = Math.PI;
    }

    @Override
    public double calcularArea() {
        return pi * (Math.pow(raio, 2.0));
    }

    @Override
    public double calcularPerimetro() {
        return 2 * pi * raio;
    }

    @Override
    public int comparar(FiguraGeometrica figura) {
        return Double.compare(calcularArea(), figura.calcularArea());
    }
}

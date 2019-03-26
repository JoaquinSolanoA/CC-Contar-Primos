public class Primos implements Runnable {
    private double numero;

    Primos(double num) {
        this.numero = num;
    }

    @Override
    public void run() {
        ContarPrimos.esPrimo(this.numero);
    }
}
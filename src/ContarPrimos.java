import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContarPrimos {
    private static final int NTHREDS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {

    	/*Metodo con proceso lineal
        System.out.println("Empezando proceso Lineal");
        long startTime = System.currentTimeMillis();
        for (double i = 2; i <= 1000000; i++) {
            esPrimo(i);
        }
        long endTime = System.currentTimeMillis() - startTime;
        double segundosLineal = (double) endTime / 1000;
        System.out.println("Terminando en " + segundosLineal + " segundo");
    	 */
        System.out.println("Empezando proceso Paralelismo");
        long startTime = System.currentTimeMillis();

        ExecutorService service = Executors.newFixedThreadPool(NTHREDS);

        for (double i = 2; i <= 100000000; i++) {
            service.submit(new Primos(i));
        }
        service.shutdown();

        long endTime = System.currentTimeMillis() - startTime;
        double segundosParalelismo = (double) endTime / 1000;
        System.out.println("Terminando en " + segundosParalelismo + " segundo");
        long speedup = (long) ((double) segundosParalelismo / NTHREDS);
        System.out.println("Speed-Up: "+speedup);
    }

    static void esPrimo(double numero) {
        int contador = 2;
        boolean primo = true;
        while ((primo) && (contador != numero)) {
            if (numero % contador == 0)
                primo = false;
            contador++;
        }
    }
}

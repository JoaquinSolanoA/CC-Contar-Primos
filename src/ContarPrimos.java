import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 
 * @author Joaquín Solano
 *
 */
public class ContarPrimos {
	/**
	 * Constante de la cantidad maxima de hilos
	 */
    private static final int NTHREDS = 16;
    /**
     * Constante de la cantidad maximo de primos
     */
    private static final int MAXPRIME = 600000;
    /**
     * Constante de tiempo maximo de espera
     */
    private static final int MAXTIME = 120;
    /**
     * Main del programa
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {

    	// Ciclo for que recorrera los numeros limites que recorrera los hilos
    	for (int numerosPrimos=(100*1000); numerosPrimos<=MAXPRIME; numerosPrimos+=(100*1000)) {
    		
    		double tiempoParalelismoT1 = 0;
    		System.out.println("Cantidad limite: "+numerosPrimos);
    		// Ciclo for que recorrera la cantidad de hilos usados en paralelismo
    		for(int cantHilos=1; cantHilos<=NTHREDS; cantHilos++) {
    			
		        // Se mide el tiempo inicial del programa
		        long startTime = System.currentTimeMillis();
		        // Se define la cantidad de hilos a usar en el paralelismo
		        ExecutorService service = Executors.newFixedThreadPool(cantHilos);
		        // Ciclo for que entrega las tareas a los hilos por el limite dado
		        for (double i = 2; i <= numerosPrimos; i++) {
		        	// Se ejecuta la tarea de primos entregando el numero correspondiente
		            service.submit(new Primos(i));
		        }
		        // Se termina el uso service
		        service.shutdown();
		        // Funcion que espera a que los hilos terminen sus tareas
		        service.awaitTermination(MAXTIME, TimeUnit.SECONDS);
		        // Se mide el tiempo final y se resta con el tiempo inicial y se obtiene el tiempo
		        long endTime = System.currentTimeMillis() - startTime;
		        double tiempoParalelismo = (double) endTime;
		        if (cantHilos==1) {
		        	tiempoParalelismoT1 = tiempoParalelismo;
		        }
		        double speedup = ((double) tiempoParalelismoT1 / tiempoParalelismo);
		        System.out.println("Hilos "+cantHilos+" Tiempo: " + tiempoParalelismo + " milisegundo Speed-Up: "+speedup+" segundos por hilos");
	    	}
    	}   
    }
    /**
     * Metodo que verificara si un numero ingresado es primo o no
     * @param numero que se verificara
     */
    static void esPrimo(double numero) {
        // Numero inicial del divisor
    	int contador = 2;
    	// Booleano que termina el ciclo cuando el numero sea divisible
        boolean primo = true;
        // Ciclo while que pregunta si el numero fue dividido o se llego el divisor maximo
        while ((primo) && (contador != numero)) {
        	// Condicion if si el modulo  numero con el contador es 0
            if (numero % contador == 0) {
            	primo = false;
            }
            contador++;
        }
    }
}

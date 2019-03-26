/*
 *  Clase Primos que ejecuta una funcion del Main
 */
public class Primos implements Runnable {
	/**
	 * Variable numero que se verificara si es primo o no
	 */
    private double numero;
    /**
     * 	Constructor de la Clase
     * @param num
     */
    Primos(double num) {
    	// Se entrega el valor del parametro a la variable numero
        this.numero = num;
    }
    /**
     * Funcion Run que ejecuta la funcion que esta en el main
     */
    @Override
    public void run() {
    	// Ejecuta la funcion es primo entregando el numero que se envio
        ContarPrimos.esPrimo(this.numero);
    }
}
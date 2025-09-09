package main;

import java.util.Scanner;

/**
 * Clase principal que ejecuta la aplicación bancaria.
 * 
 * Esta clase contiene el método main que inicia la aplicación,
 * muestra el menú principal y maneja la interacción con el usuario.
 * Utiliza la clase {@link Banco} para gestionar cuentas bancarias
 * y la clase {@link Menu} para mostrar y procesar opciones del menú.
 * 
 * @author Gaston
 * @version 1.0
 */
public class Main {
    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     * 
     * Crea un objeto {@link Scanner} para leer la entrada del usuario,
     * instancia un {@link Banco} para manejar las cuentas bancarias,
     * y gestiona la cuenta activa según la opción seleccionada en el menú.
     * El bucle principal se ejecuta hasta que el usuario elige la opción de salir ("3").
     * 
     * @param args Argumentos de línea de comandos (no utilizados en esta aplicación)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Escaner para entrada de usuario
        Banco banco = new Banco();           // Banco que contiene las cuentas
        CuentaBancaria cuentaActiva = null;  // Cuenta actualmente activa
        String opcion;                       // Opción elegida por el usuario

        do {
            Menu.mostrarMenuPrincipal(); 
            opcion = sc.nextLine();
            cuentaActiva = Menu.manejarOpcionPrincipal(opcion, banco, sc, cuentaActiva); 
        } while (!opcion.equals("3"));

        sc.close(); // Cierra el scanner al finalizar la aplicación
    }
}

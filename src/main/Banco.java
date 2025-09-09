package main;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Representa un banco que administra múltiples cuentas bancarias.
 * Permite crear nuevas cuentas, listar las existentes y seleccionar una cuenta
 * mediante su CBU.
 * 
 * @author Gaston
 * @version 1.0
 */

public class Banco {
    /** Mapa que almacena las cuentas bancarias con su CBU como clave */
    private HashMap<String, CuentaBancaria> cuentas;
    
    /**
     * Constructor que inicializa el banco con un HashMap vacío de cuentas.
     */
    public Banco(){
        cuentas = new HashMap<>(); 
    }
    
    /**
     * Crea una nueva cuenta bancaria.
     * Solicita al usuario un CBU único y un alias válido, y luego agrega
     * la cuenta al banco.
     * 
     * @param sc Scanner para leer la entrada del usuario
     * @return La cuenta bancaria creada
     */
    public CuentaBancaria crearCuenta(Scanner sc) {
        CuentaBancaria cuenta = null;
        
        // --- Validar CBU ---
        String cbuIngresado;
        do {
            System.out.println("Ingrese un CBU para la cuenta: ");
            cbuIngresado = sc.nextLine();

            if (cuentas.containsKey(cbuIngresado)) {
                System.out.println("Error: el CBU indicado ya existe para otra cuenta. Intente nuevamente.\n");
            } else {
                cuenta = new CuentaBancaria(cbuIngresado);
                cuentas.put(cbuIngresado, cuenta);
                System.out.println("Cuenta creada con éxito.\n");
            }
        } while (cuenta == null);

        // --- Validar Alias ---
        String aliasIngresado;
        do {
            System.out.println("Ingrese un alias para la cuenta: ");
            aliasIngresado = sc.nextLine();

            if (aliasIngresado == null || aliasIngresado.isBlank()) {
                System.out.println("El alias ingresado no puede estar en blanco. Intente nuevamente.\n");
            }
        } while (aliasIngresado == null || aliasIngresado.isBlank());

        cuenta.setAlias(aliasIngresado);
        System.out.println("Alias asignado con éxito.\n");
        
        return cuenta;
    }
    
    /**
     * Muestra en consola todas las cuentas existentes en el banco.
     * Si no hay cuentas registradas, se muestra un mensaje informativo.
     */
    public void listarCuentas(){
      if(cuentas.isEmpty()){
        System.out.println("No hay cuentas registradas.\n");
        return;
      }
      for (CuentaBancaria cuenta : cuentas.values()){
        System.out.println(cuenta); 
      }
    }
    
    /**
     * Permite al usuario seleccionar una cuenta bancaria mediante su CBU.
     * Muestra las cuentas disponibles y solicita al usuario que ingrese un CBU válido.
     * El proceso se repite hasta que se ingrese un CBU existente.
     * 
     * @param sc Scanner para leer la entrada del usuario
     * @return La cuenta bancaria seleccionada
     */
    public CuentaBancaria seleccionarCuenta(Scanner sc){
      while (true){ // Pedir el CBU hasta que sea valido
        // Mostrar las cuentas y pedir el CBU
        System.out.println("Cuentas disponibles: ");
        for (CuentaBancaria cuenta : cuentas.values()){
          System.out.println(cuenta);
        }
        System.out.println("Ingrese el CBU de la cuenta que desee usar: ");
        String cbuIngresado = sc.nextLine();
        
        // Validar si el CBU existe en el HasMap
        if (cuentas.containsKey(cbuIngresado)){ // verifica si hay una cuenta con ese CBU
          return cuentas.get(cbuIngresado); // devuelve la cuenta asociada al CBU
        } else {
          System.out.println("El CBU ingresado no existe. Intente nuevamente.\n");
        }
      }
      
    }
}


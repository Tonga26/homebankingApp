package main;

import java.util.Scanner;

/**
 * Clase que maneja la interfaz del usuario para la aplicación bancaria.
 * Proporciona métodos para mostrar los menús principal y de cuenta,
 * y para ejecutar las acciones seleccionadas por el usuario.
 * 
 * Los métodos son todos estáticos, ya que no se requiere instanciar
 * la clase Menu.
 * 
 * @author Gaston
 * @version 1.0
 */
public class Menu {
    
     /**
     * Muestra el menú principal con las opciones:
     * 1. Crear cuenta
     * 2. Seleccionar cuenta
     * 3. Salir
     */
    public static void mostrarMenuPrincipal(){
      System.out.println("--- MENÚ PRINCIPAL ---");
      System.out.println("1. Crear cuenta.");
      System.out.println("2. Seleccionar cuenta.");
      System.out.println("3. Salir.");
      System.out.print("Opción: ");
    }
    
    /**
     * Maneja la opción seleccionada en el menú principal.
     * Según la opción, puede crear una nueva cuenta, seleccionar una existente
     * o salir de la aplicación.
     * 
     * @param opcion Opción ingresada por el usuario
     * @param banco Banco donde se gestionan las cuentas
     * @param sc Scanner para leer la entrada del usuario
     * @param cuentaActiva Cuenta actualmente activa (puede ser modificada)
     * @return La cuenta activa después de ejecutar la opción
     */
    public static CuentaBancaria manejarOpcionPrincipal(String opcion, Banco banco, Scanner sc, CuentaBancaria cuentaActiva){
      switch (opcion){
        case "1"-> {
          CuentaBancaria nueva = banco.crearCuenta(sc);
          if (nueva != null){
            cuentaActiva = nueva;
            Menu.subMenuCuenta(cuentaActiva, sc);
          }
        }
        case "2"-> {
          CuentaBancaria seleccionada = banco.seleccionarCuenta(sc);
          if (seleccionada != null){
            cuentaActiva = seleccionada;
            Menu.subMenuCuenta(cuentaActiva, sc);
          }
        }
        case "3"-> System.out.println("Gracias por usar nuestro homebanking.\n");
        default -> System.out.println("Opción inválida. Intente nuevamente.\n");
      }
      
      return cuentaActiva;
    }
    
    /**
     * Muestra el submenú de cuenta y gestiona la interacción hasta que
     * el usuario decida volver al menú principal.
     * 
     * @param cuenta Cuenta activa sobre la que se realizarán operaciones
     * @param sc Scanner para leer la entrada del usuario
     */
    public static void subMenuCuenta(CuentaBancaria cuenta, Scanner sc){
      String opcion;
      do{
        mostrarSubMenuCuenta();
        opcion = sc.nextLine().trim();
        if (opcion.equals("6")) {
            System.out.println("Volviendo al menú principal...\n");
              break; 
        }
        manejarOpcion(opcion, cuenta, sc);
      } while (!opcion.equals("6"));
    }
    
    /**
     * Muestra el submenú de operaciones de cuenta:
     * 1. Depositar
     * 2. Retirar
     * 3. Ver saldo actual
     * 4. Ver datos de la cuenta
     * 5. Modificar alias
     * 6. Volver al menú principal
     */
    public static void mostrarSubMenuCuenta() {
        System.out.println("\n--- BANCO STARFIELD ---");
        System.out.println("""
            Seleccione la operación que desea realizar(1-6):
            1. Depositar
            2. Retirar
            3. Ver saldo actual
            4. Ver datos de la cuenta
            5. Modificar alias
            6. Volver al menú principal
            """);
        System.out.print("Opción: ");
    }

    /**
     * Maneja la opción seleccionada en el submenú de cuenta.
     * 
     * @param opcion Opción ingresada por el usuario
     * @param cuenta Cuenta activa sobre la que se realizará la operación
     * @param sc Scanner para leer la entrada del usuario
     */
    public static void manejarOpcion(String opcion, CuentaBancaria cuenta, Scanner sc) {
        switch (opcion) {
            case "1" -> depositar(cuenta, sc);
            case "2" -> retirar(cuenta, sc);
            case "3" -> verSaldo(cuenta);
            case "4" -> mostrarDatos(cuenta);
            case "5" -> cambiarAlias(cuenta, sc);
            case "6" -> System.out.println("Volviendo al menú principal.\n");
            default -> System.out.println("Opción inválida. Intente nuevamente.\n");
        }
    }
    
    /**
     * Solicita al usuario un monto y realiza un depósito en la cuenta.
     * Valida que el monto ingresado sea un número positivo.
     * 
     * @param cuenta Cuenta donde se realizará el depósito
     * @param sc Scanner para leer la entrada del usuario
     */
    public static void depositar(CuentaBancaria cuenta, Scanner sc) {
        boolean depositoValido = false;
        
        while (!depositoValido){ 
            System.out.println("Ingrese el monto a depositar: ");
            String montoStr = sc.nextLine(); // leer como texto
            
            try {
                double montoValidado = Double.parseDouble(montoStr); // Intentar convertir a double
                boolean exito = cuenta.depositar(montoValidado); // Intentar realizar el deposito
                
                if (exito){
                    System.out.println("Depósito realizado con éxito.\n");
                    depositoValido = true;
                } else {
                    System.out.println("Error: el monto debe ser mayor a 0. Intente nuevamente.\n");
                }
            } catch (NumberFormatException e){
                System.out.println("Error: debe ingresar un número válido.\n");
            }
        }
    }
    
    /**
     * Solicita al usuario un monto y realiza un retiro de la cuenta.
     * Valida que el monto sea positivo y que no supere el saldo disponible.
     * 
     * @param cuenta Cuenta de donde se realizará el retiro
     * @param sc Scanner para leer la entrada del usuario
     */
    public static void retirar(CuentaBancaria cuenta, Scanner sc) {
        boolean retiroValido = false;
        
        while (!retiroValido){
            System.out.println("Ingrese el monto a retirar: ");
            String montoStr = sc.nextLine();
            
            try{
                double montoValidado = Double.parseDouble(montoStr);
                boolean exito = cuenta.retirar(montoValidado);
                
                if (exito){
                    System.out.println("Retiro realizado con éxito.\n");
                    retiroValido = true;
                } else {
                    System.out.println("Error: monto inválido o fondos insuficientes. Intente nuevamente.\n");
                }
            } catch (NumberFormatException e){
                System.out.println("Error: debe ingresar un número válido.\n");
            }
        }
    }

    /**
     * Muestra el saldo actual de la cuenta.
     * 
     * @param cuenta Cuenta de la cual se mostrará el saldo
     */ 
    public static void verSaldo(CuentaBancaria cuenta) {
        System.out.println("Saldo actual: $ " + String.format("%.2f", cuenta.getSaldo()) + "\n");
    }

    /**
     * Muestra los datos completos de la cuenta (CBU, alias y saldo).
     * 
     * @param cuenta Cuenta de la cual se mostrarán los datos
     */
    public static void mostrarDatos(CuentaBancaria cuenta) {
        System.out.println(cuenta.toString() + "\n");
    }

    /**
     * Solicita al usuario un nuevo alias y lo asigna a la cuenta.
     * 
     * @param cuenta Cuenta a la cual se cambiará el alias
     * @param sc Scanner para leer la entrada del usuario
     */ 
    public static void cambiarAlias(CuentaBancaria cuenta, Scanner sc) {
        System.out.print("Ingrese el nuevo alias: ");
        String nuevoAlias = sc.nextLine();
        cuenta.setAlias(nuevoAlias);
        System.out.println("Alias cambiado correctamente. Nuevo alias: " + cuenta.getAlias() + "\n");
    }
}

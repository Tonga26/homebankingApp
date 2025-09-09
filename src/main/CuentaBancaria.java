
package main;

/**
 * Representa una cuenta bancaria con CBU, alias y saldo.
 * Permite realizar operaciones básicas como depositar y retirar dinero,
 * así como consultar y modificar el alias de la cuenta.
 * 
 * @author Gaston
 * @version 1.0
 */
class CuentaBancaria {
    /** CBU único de la cuenta bancaria */
    private String cbu;
    
    /** Alias de la cuenta, puede ser modificado */
    private String alias;
    
    /** Saldo actual de la cuenta */
    private double saldo;
    
     /**
     * Constructor por defecto.
     * Crea una cuenta con CBU "0000000000000000000000", alias "sin.alias" y saldo 0.0.
     */
    public CuentaBancaria() {
        this.cbu = "0000000000000000000000";
        this.alias = "sin.alias";
        this.saldo = 0.0;
    }
    
    /**
     * Constructor que recibe el CBU de la cuenta.
     * Inicializa el alias como "sin.alias" y el saldo en 0.0.
     * 
     * @param cbu CBU de la cuenta bancaria
     */ 
    public CuentaBancaria(String cbu){
        this.cbu = cbu;
        this.alias = "sin.alias";
        this.saldo = 0.0;
    } 

    /**
     * Deposita un monto en la cuenta.
     * 
     * @param montoDeposito Monto a depositar (debe ser mayor a 0)
     * @return true si el depósito fue exitoso, false si el monto es inválido
     */
    public boolean depositar(double montoDeposito){
        if (montoDeposito > 0){
            this.saldo += montoDeposito;
            return true;
        }
        return false;
    } 

    /**
     * Retira un monto de la cuenta.
     * 
     * @param montoRetiro Monto a retirar (debe ser mayor a 0 y menor o igual al saldo)
     * @return true si el retiro fue exitoso, false si el monto es inválido o excede el saldo
     */
    public boolean retirar(double montoRetiro){ 
        if (montoRetiro <= saldo && montoRetiro > 0){
            this.saldo -= montoRetiro;
            return true; 
        } 
        return false; 
    }

    public String getCbu(){
        return this.cbu;
    }
    
    public String getAlias(){
        return this.alias;
    }

     /**
     * Modifica el alias de la cuenta.
     * Solo se permite si el alias no es nulo ni está en blanco.
     * 
     * @param alias Nuevo alias de la cuenta
     */
    public void setAlias(String alias){
        if (alias != null && !alias.isBlank()){
            this.alias = alias;
        }
    }
    
    public double getSaldo(){
        return this.saldo;
    }
    
    /**
     * Representación en cadena de la cuenta bancaria.
     * Muestra el CBU, alias y saldo con dos decimales.
     * 
     * @return String con la información de la cuenta
     */
    @Override
    public String toString(){
        return "CBU: " + cbu + " || Alias: " + alias + " || Saldo actual: " + String.format("%.2f", saldo);
    }
}
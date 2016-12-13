/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepcion;

/**
 *
 * @author Sergio
 */
public class ExcepcionTipoEdificioIncorrecto extends Excepcion {
  public ExcepcionTipoEdificioIncorrecto(){
    super("Tipo de edificio incorrecto");
  }
  public ExcepcionTipoEdificioIncorrecto(String mensaje){
    super(mensaje);
  }
}

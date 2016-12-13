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
public class ExcepcionEdificioIncorrecto extends Excepcion{
  public ExcepcionEdificioIncorrecto(){
    super("Este edificio no puede construir el tipo de unidad deseado");
  }
  public ExcepcionEdificioIncorrecto(String mensaje){
    super(mensaje);
  }
  
}

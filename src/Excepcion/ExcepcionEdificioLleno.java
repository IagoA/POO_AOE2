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
public class ExcepcionEdificioLleno extends Excepcion{
  public ExcepcionEdificioLleno (){
    super("El edificio está lleno");
  }
  public ExcepcionEdificioLleno (String mensaje){
    super(mensaje);
  }
  
}

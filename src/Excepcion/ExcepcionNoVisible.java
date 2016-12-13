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
public class ExcepcionNoVisible extends Excepcion {
  public ExcepcionNoVisible(){
    super("Esta celda no es visible");
  }
  public ExcepcionNoVisible(String mensaje){
    super(mensaje);
  }
  
}

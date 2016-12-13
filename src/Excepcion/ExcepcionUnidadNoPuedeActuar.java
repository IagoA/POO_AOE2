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
public class ExcepcionUnidadNoPuedeActuar extends Excepcion{
  public ExcepcionUnidadNoPuedeActuar(){
    super("Esta unidad no puede actuar sola");
  }
  public ExcepcionUnidadNoPuedeActuar(String mensaje){
    super(mensaje);
  }
}

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
public class ExcepcionUnidadNoExiste extends ExcepcionNoExiste{
  public ExcepcionUnidadNoExiste(){
    super("Esta unidad no existe");
  }
  public ExcepcionUnidadNoExiste(String mensaje){
    super(mensaje);
  }
}

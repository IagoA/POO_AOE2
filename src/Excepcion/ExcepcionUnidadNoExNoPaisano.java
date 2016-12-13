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
public class ExcepcionUnidadNoExNoPaisano extends Excepcion{
  public ExcepcionUnidadNoExNoPaisano(){
    super("Esta unidad no es un paisano o no existe");
  }
  public ExcepcionUnidadNoExNoPaisano(String mensaje){
    super(mensaje);
  }
  
}

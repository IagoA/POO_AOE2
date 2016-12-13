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
public class ExcepcionNoHayEdificio extends Excepcion{
  public ExcepcionNoHayEdificio(){
    super("No hay ningún edificio en esta celda");
  }
  public ExcepcionNoHayEdificio(String mensaje){
    super(mensaje);
  }
}

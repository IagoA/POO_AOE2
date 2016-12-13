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
public class ExcepcionEdificioNoDanado extends Excepcion{

  public ExcepcionEdificioNoDanado() {
    super("El edificio no está dañado");
  }
  public ExcepcionEdificioNoDanado(String mensaje){
    super(mensaje);
  }
}

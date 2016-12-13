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
public class ExcepcionCeldaUnaUnidad extends Excepcion{
  public ExcepcionCeldaUnaUnidad(){
    super("Esta celda sólo tiene una unidad");
  }
  public ExcepcionCeldaUnaUnidad(String mensaje){
    super(mensaje);
  }
}

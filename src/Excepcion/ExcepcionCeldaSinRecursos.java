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
public class ExcepcionCeldaSinRecursos extends Excepcion{
  public ExcepcionCeldaSinRecursos(){
    super("La celda no tiene recursos");
  }
  public ExcepcionCeldaSinRecursos(String mensaje){
    super(mensaje);
  }
  
}

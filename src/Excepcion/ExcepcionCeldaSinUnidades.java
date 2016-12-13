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
public class ExcepcionCeldaSinUnidades extends Excepcion{
  public ExcepcionCeldaSinUnidades(){
    super("Esta celda no tiene unidades");
  }
  public ExcepcionCeldaSinUnidades(String mensaje){
    super(mensaje);
  }
}

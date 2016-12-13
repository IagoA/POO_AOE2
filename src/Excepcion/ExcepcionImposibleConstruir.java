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
public class ExcepcionImposibleConstruir extends Excepcion {
  public ExcepcionImposibleConstruir(){
    super("No se puede construir en esta celda");
  }
  public ExcepcionImposibleConstruir(String mensaje){
    super(mensaje);
  }
}

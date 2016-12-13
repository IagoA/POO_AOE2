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
public class ExcepcionCeldaEnemiga extends Excepcion {
  public ExcepcionCeldaEnemiga(){
    super("La celda contiene unidades enemigas");
  }
  public ExcepcionCeldaEnemiga(String mensaje){
    super(mensaje);
  }
}

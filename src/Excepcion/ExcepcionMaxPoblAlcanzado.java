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
public class ExcepcionMaxPoblAlcanzado extends Excepcion{
  public ExcepcionMaxPoblAlcanzado(){
    super("No se pueden crear más unidades, población máxima alcanzada");
  }
  public ExcepcionMaxPoblAlcanzado(String mensaje){
    super(mensaje);
  }
}

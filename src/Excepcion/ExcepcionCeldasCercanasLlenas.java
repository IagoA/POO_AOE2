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
public class ExcepcionCeldasCercanasLlenas extends Excepcion{
  public ExcepcionCeldasCercanasLlenas(){
    super("Todas las celdas cercanas están llenas");
  }
  public ExcepcionCeldasCercanasLlenas(String mensaje){
    super(mensaje);
  }
}

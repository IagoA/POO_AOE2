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
public class ExcepcionEdificioNoCreaUnidades extends Excepcion{
  public ExcepcionEdificioNoCreaUnidades(){
    super("Este edificio no puede crear unidades");
  }
  public ExcepcionEdificioNoCreaUnidades(String mensaje){
    super(mensaje);
  }
  
}

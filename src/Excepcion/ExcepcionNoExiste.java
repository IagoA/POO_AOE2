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
public class ExcepcionNoExiste extends Excepcion{
  public ExcepcionNoExiste(){
    super("No existe este esta unidad o grupo");
  }
  public ExcepcionNoExiste(String mensaje){
    super(mensaje);
  }
  
}

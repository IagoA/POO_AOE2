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
public class ExcepcionGrupoNoExiste extends ExcepcionNoExiste{
  public ExcepcionGrupoNoExiste(){
    super("El grupo no existe");
  }
  public ExcepcionGrupoNoExiste(String mensaje){
    super(mensaje);
  }
}

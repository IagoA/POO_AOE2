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
public class ExcepcionGrupoConSoldados extends Excepcion{
  public ExcepcionGrupoConSoldados(){
    super("El grupo contiene soldados");
  }
  public ExcepcionGrupoConSoldados(String mensaje){
    super(mensaje);
  }
}

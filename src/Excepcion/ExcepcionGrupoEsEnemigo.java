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
public class ExcepcionGrupoEsEnemigo extends Excepcion {
  public ExcepcionGrupoEsEnemigo(){
    super("El grupo es enemigo");
  }
  public ExcepcionGrupoEsEnemigo(String mensaje){
    super(mensaje);
  }
}

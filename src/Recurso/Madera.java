/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recurso;

/**
 *
 * @author Sergio
 */
public class Madera extends Recurso {

  public Madera(int cant){
    if(cant>0){
    cantidad=cant;
    } else {
      cantidad=0;
    }
  }
  @Override
  public char getTipo() {
    return 'M';
  }

}

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
public class ExcepcionDireccionIncorrecta extends ExcepcionSyntaxError{
  public ExcepcionDireccionIncorrecta(){
    super("Las únicas direcciones soportadas son: NORTE, SUR, ESTE, OESTE");
  }
  public ExcepcionDireccionIncorrecta(String mensaje){
    super(mensaje);
  }
}

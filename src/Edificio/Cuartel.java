/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Edificio;

import Mapa.Civilizacion;

/**
 *
 * @author Sergio
 */
public class Cuartel extends Edificio {

  public Cuartel(Civilizacion civi) {
        saludMax = 75;
        civilizacion = civi;
        civi.setCuartCreados(civi.getCuartCreados()+ 1);
        nombre = "Cuartel-" + (civi.getCuartCreados());
        salud = saludMax;
        ataque = 0;
        defensa = 0;
        capacidad = 5;
    }
  @Override
  public void setCapacidad(int c) {
    if (c >= 0 && c <= 10) {
      capacidad = c;
    } else if (c < 0) {
      capacidad = 0;
    } else {
      capacidad = 10;
    }
  }

  @Override
  public int[] costeReparar() {
    int[] ret = {0, 0};
    float por1 = (float) this.getSalud() / (float) this.getSaludMax();
    por1 = (1f - por1);
    ret[1] = (int) (100 * por1);//coste piedra
    ret[0] = (int) (150 * por1);
    return ret;

  }

  @Override
  public char getTipo() {
    return 'M';
  }

}
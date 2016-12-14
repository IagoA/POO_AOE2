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
public class Casa extends Edificio {

  public Casa(Civilizacion civi) {
    saludMax = 50;
    civilizacion = civi;
    civi.setCasasCreadas(civi.getCasasCreadas() + 1);
    nombre = "Casa-" + (civi.getCasasCreadas());
    salud = saludMax;
    ataque = 0;
    defensa = 0;
    capacidad = 5;
  }

  @Override
  public void setCapacidad(int c) {
    if (c >= 0 && c <= 5) {
      capacidad = c;
    } else if (c < 0) {
      capacidad = 0;
    } else {
      capacidad = 5;
    }
  }

  @Override
  public int[] costeReparar() {
    int[] ret = {0, 0};
    float por1 = (float) this.getSalud() / (float) this.getSaludMax();
    por1 = (1f - por1);
    ret[0] = (int) (30 * por1);//coste madera
    return ret;
  }

  @Override
  public char getTipo() {
    return 'H';
  }
}

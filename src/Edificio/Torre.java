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
public class Torre extends Edificio {

  public Torre(Civilizacion civi) {
    saludMax = 60;
    civilizacion = civi;
    civi.setTorresCreadas(civi.getTorresCreadas()+ 1);
    nombre = "Casa-" + (civi.getTorresCreadas());
    salud = saludMax;
    ataque = 10;
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
    ret[1] = (int) (100 * por1);
    ret[0] = (int) (50 * por1);
    return ret;
  }

  @Override
  public char getTipo() {
    return 'T';
  }
}

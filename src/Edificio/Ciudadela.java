/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Edificio;

import Mapa.Civilizacion;

/**
 *
 * @author yago
 */
public class Ciudadela extends Edificio {

    public Ciudadela(Civilizacion civi) {
        saludMax = 125;
        civilizacion = civi;
        civi.setCiudaCreadas(civi.getCiudaCreadas() + 1);
        nombre = "Ciudadela-" + (civi.getCiudaCreadas());
        salud = saludMax;
        ataque = 0;
        defensa = 0;
        capacidad = 10;
    }

    public int getMadera() {
        return civilizacion.getMadera();
    }

    public int modMadera(int mod) {
        return civilizacion.modMadera(mod);
    }

    public void setMadera(int set) {
        civilizacion.setMadera(set);
    }

    public int getPiedra() {
        return civilizacion.getPiedra();
    }

    public int modPiedra(int mod) {

        return civilizacion.modPiedra(mod);
    }

    public void setPiedra(int set) {
        civilizacion.setPiedra(set);
    }

    public int getComida() {
        return civilizacion.getComida();
    }

    public int modComida(int mod) {
        return civilizacion.modComida(mod);
    }

    public void setComida(int set) {
        civilizacion.setComida(set);
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
        por1=(1f-por1);
        ret[1] = (int) (150 * por1);
        ret[0] = (int) (275 * por1);
        return ret;
    }
}

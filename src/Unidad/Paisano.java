/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unidad;

import Edificio.Ciudadela;
import Edificio.Edificio;
import Mapa.Civilizacion;
import FuenteRecurso.FuenteRecursos;
import Recurso.Recurso;

/**
 *
 * @author yago
 */
public final class Paisano extends Unidad {

    private int salud;
    private final int SALUDMAX;
    private final int capacidadRecurso;
    private Recurso r;

    public Paisano(Civilizacion civiL) {
        civi = civiL;
        SALUDMAX = 25;
        salud = SALUDMAX;
        civi.setPaiCreados(civi.getPaiCreados() + 1);
        nombre = "Paisano-" + (civi.getPaiCreados());
        ataque = 3;
        armadura = 0;
        capacidadRecurso = 20;
    }

    public int getCapacidadRecurso() {
        return capacidadRecurso;
    }

    public Recurso getR() {
        return r;
    }

    public int modR(FuenteRecursos fuente) {
        return fuente.modCantidad(this.modR(fuente.getRecurso().getTipo(), fuente.getRecurso().getCantidad()));
    }

    public int modR(char tipo, int cant) {
        if (r == null) {
            if (cant < 0) {
                return 0;
            }
            if (cant <= capacidadRecurso) {
                r = new Recurso(tipo, cant);
                return cant;
            }
            if (cant > capacidadRecurso) {
                r = new Recurso(tipo, capacidadRecurso);
                return capacidadRecurso;
            }
        }
        if (r.getTipo() == tipo) {
            if (r.getCantidad() + cant <= capacidadRecurso) {
                int ret = r.modCantidad(cant);
                if (r.getCantidad() == 0) {
                    r = null;
                }
                return ret;
            } else {
                int i = capacidadRecurso - r.getCantidad();
                r.setCantidad(capacidadRecurso);
                return i;
            }
        } else if (cant >= 0) {
            r.setTipo(tipo);
            r.setCantidad(0);
            if (r.getCantidad() + cant <= capacidadRecurso) {
                int ret = r.modCantidad(cant);
                if (r.getCantidad() == 0) {
                    r = null;
                }
                return ret;
            } else {
                r.setCantidad(capacidadRecurso);
                return capacidadRecurso;
            }
        }
        return 0;
    }
    
    public int getSALUDMAX() {
        return SALUDMAX;
    }
    
    public int getSalud() {
        return salud;
    }

    @Override
    public char getTipo() {
        return 'P';
    }

    @Override
    public void modSalud(int mod) {
        if (salud + mod >= SALUDMAX) {
            salud = SALUDMAX;
        } else if (salud + mod <= 0) {
            int i = salud;
            salud = 0;
        } else {
            salud += mod;
        }
    }

    @Override
    public void recolectar(FuenteRecursos contenedor) {
        if(getR().getTipo() != contenedor.getRecurso().getTipo()) r = null;
        int cant1;
        if (getR() != null) {
            cant1 = getCapacidadRecurso() - getR().getCantidad();
        } else {
            cant1 = getCapacidadRecurso();
        }
        char tipo = contenedor.getRecurso().getTipo();
        int cant2 = contenedor.modCantidad((-1) * cant1);
        modR(tipo, (-1) * cant2);
    }

    @Override
    public void almacenar(Ciudadela ciudadela) {
        switch (getR().getTipo()) {
            case 'M':
                modR('M', (-1) * ciudadela.modMadera(getR().getCantidad()));
                break;
            case 'P':
                modR('P', (-1) * ciudadela.modPiedra(getR().getCantidad()));
                break;
            case 'C':
                modR('C', (-1) * ciudadela.modComida(getR().getCantidad()));
                break;
        }
    }

    @Override
    public void reparar(Edificio edificio) {
        int costeMadera = edificio.costeReparar()[0];
        int costePiedra = edificio.costeReparar()[1];
        int totalMadera = civi.getMadera();
        int totalPiedra = civi.getPiedra();

        civi.modMadera((-1) * costeMadera);
        civi.modPiedra((-1) * costePiedra);

        edificio.modSalud(edificio.getSaludMax());
    }
    
    @Override
    public Edificio construir(String tipo_edificio){
        Edificio ed;
        switch(tipo_edificio){
            case "casa":
                return ed =(Edificio) new Casa(civi);
            case "cuartel":
                return ed =(Edificio) new Cuartel(civi);
            case "ciudadela":
                return ed =(Edificio) new Ciudadela(civi);
            case "torre":
                return ed =(Edificio) new Torre(civi);
            default:
                return null;
        }
    }
}
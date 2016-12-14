/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuenteRecurso;

import Recurso.Madera;

/**
 *
 * @author yago
 */
public final class Bosque extends FuenteRecursos {

    private Madera m;

    @Override
    public Madera getRecurso() {//evito aliasing porque solo quiero que se acceda a través de las funciones de FuenteRecursos
        return new Madera(m.getCantidad());
    }

    @Override
    public void setRecurso(int i) {
        m.setCantidad(i);
        if (m.getCantidad() <= 0) {
            m = null;
        }
    }

    @Override
    public Madera procesar() {
        int cant;
        cant = m.modCantidad((-1) * 20);
        Madera pi;
        return pi = new Madera((-1) * cant);
    }

    @Override
    public int modCantidad(int mod) {
        int ret = m.modCantidad(mod);
        if (m.getCantidad() <= 0) {
            m = null;
        }
        return ret;
    }

    @Override
    public char getTipo() {
        return 'b';
    }
}

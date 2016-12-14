/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuenteRecurso;

import Recurso.Comida;

/**
 *
 * @author yago
 */
public final class Arbusto extends FuenteRecursos{
    private Comida c;
    
    @Override
    public Comida getRecurso() {//evito aliasing porque solo quiero que se acceda a través de las funciones de FuenteRecursos
        return new Comida(c.getCantidad());
    }
    @Override
    public void setRecurso(int i) {
        c.setCantidad(i);
        if (c.getCantidad() <= 0) {
            c = null;
        }
    }
    @Override
    public int modCantidad(int mod) {
        int ret = c.modCantidad(mod);
        if (c.getCantidad() <= 0) {
            c = null;
        }
        return ret;
    }
    
    @Override
    public Comida procesar() {
        int cant;
        cant = c.modCantidad((-1) * 20);
        Comida pi;
        return pi = new Comida((-1) * cant);
    }
    
    @Override
    public char getTipo(){
        return 'a';
    }
}

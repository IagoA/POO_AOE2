/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuenteRecurso;

import Recurso.Piedra;
import Recurso.Recurso;

/**
 *
 * @author yago
 */
public final class Cantera extends FuenteRecursos{
    private Piedra p;
    
    @Override
    public Recurso getRecurso() {//evito aliasing porque solo quiero que se acceda a través de las funciones de FuenteRecursos
        return (Recurso) new Piedra(p.getCantidad());
    }
    @Override
    public void setRecurso(int i) {
        p.setCantidad(i);
        if (p.getCantidad() <= 0) {
            p = null;
        }
    }
    @Override
    public int modCantidad(int mod) {
        int ret = p.modCantidad(mod);
        if (p.getCantidad() <= 0) {
            p = null;
        }
        return ret;
    }
    @Override
    public Piedra procesar(){
        int cant;
        if(p.getCantidad()>(cantInicial - 0.2*cantInicial)) cant = 20;
        else if(p.getCantidad()>(cantInicial - 0.4*cantInicial)) cant = 18;
        else if(p.getCantidad()>(cantInicial - 0.6*cantInicial)) cant = 16;
        else if(p.getCantidad()>(cantInicial - 0.8*cantInicial)) cant = 14;
        else cant = 12;
        cant = p.modCantidad((-1)*cant);
        Piedra pi;
        return pi= new Piedra((-1)*cant);
    }
    
    @Override
    public char getTipo(){
        return 'c';
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuenteRecurso;

import Recurso.Recurso;

/**
 *
 * @author Sergio
 */
public abstract class FuenteRecursos {

    private Recurso r;
    private int cantInicial;

    public FuenteRecursos(char tipofuente, int cantidadrecurso) {
        switch (tipofuente) {
            case 'p':
                r = null;
                break;
            case 'b':
                r = new Recurso('M', cantidadrecurso);
                break;
            case 'c':
                r = new Recurso('P', cantidadrecurso);
                break;
            case 'a':
                r = new Recurso('C', cantidadrecurso);
                break;
        }
        cantInicial = r.getCantidad();
    }

    public FuenteRecursos() {
        r = null;
        cantInicial = 0;
    }

    public Recurso getRecurso() {//evito aliasing porque solo quiero que se acceda a través de las funciones de FuenteRecursos
        return new Recurso(r);
    }

    public void setRecurso(int i) {
        r.setCantidad(i);
        if (r.getCantidad() <= 0) {
            r = null;
        }
    }

    public void setRecurso(char tipo, int i) {
        r.setCantidad(i);
        r.setTipo(tipo);
        if (r.getCantidad() <= 0) {
            r = null;
        }
    }
    
    public void setCantInicial(int c){
        if(c >= 0){
            cantInicial = c;
        }
    }
    
    public int getCantInicial(){
        return cantInicial;
    }

    public char getTipo() {
        if (r == null) {
            return 'p';
        } else {
            switch (r.getTipo()) {
                case 'M':
//          if (r.getCantidad() == 0) {
//            return 'p';
//          }
                    return 'b';
                case 'P':
//          if (r.getCantidad() == 0) {
//            return 'p';
//          }
                    return 'c';
                case 'C':
//          if (r.getCantidad() == 0) {
//            return 'p';
//          }
                    return 'a';
            }
        }
        return 'E';//error
    }

    public int modCantidad(int mod) {
        int ret = r.modCantidad(mod);
        if (r.getCantidad() <= 0) {
            r = null;
        }
        return ret;
    }
}

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

    protected int cantInicial;

    public abstract Recurso getRecurso();

    public abstract void setRecurso(int i);
    
    public abstract int modCantidad(int mod);
    
    public abstract char getTipo();
    
    public abstract Recurso procesar();

    public void setCantInicial(int c){
        if(c >= 0){
            cantInicial = c;
        }
    }

    public int getCantInicial(){
        return cantInicial;
    }

    public boolean esTransitable(){
        return false;
    }
}

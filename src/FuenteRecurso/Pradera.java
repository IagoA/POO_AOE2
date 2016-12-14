/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuenteRecurso;

import Recurso.Recurso;

/**
 *
 * @author yago
 */
public final class Pradera extends FuenteRecursos{
    @Override
    public char getTipo(){
        return 'p';
    }
    
    @Override
    public Recurso getRecurso(){
        //error
        return null;
    }
    @Override
    public void setRecurso(int i){
        //error
    }
    @Override
    public int modCantidad(int mod){
        //error
        return 0;
    }
    @Override
    public boolean esTransitable(){
        return true;
    }
    @Override
    public Recurso procesar(){
        //Error
        return null;
    }
}

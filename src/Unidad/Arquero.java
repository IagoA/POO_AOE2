/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unidad;

import Edificio.Ciudadela;
import Edificio.Edificio;
import FuenteRecurso.FuenteRecursos;

/**
 *
 * @author yago
 */
public class Arquero extends Soldado{
    
    @Override
    public double danhoAtaque(Edificio edificio){
        double dano = (double)(ataque - edificio.getDefensa());
        dano = dano/2d;
        if(dano < 1.0) dano = 1.0;
        return dano;
    }
    
    @Override
    public char getTipo() {
        return 'A';
    }
    
    @Override
    public void recolectar(FuenteRecursos contenedor) {
        //error
    }

    @Override
    public void almacenar(Ciudadela ciudadela) {
        //error
    }

    @Override
    public void reparar(Edificio edificio) {
        //error
    }
    
    @Override
    public Edificio construir(String tipo_edificio){
        //error
        return null;
    }
}

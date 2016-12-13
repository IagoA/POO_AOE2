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
public class Caballero extends Soldado{
    
    @Override
    public int capacidadMovimiento() {
        return 2;
    }
    
    @Override
    public double danhoAtaque(Unidad personaje){
        double dano = (double)(ataque * (1 - personaje.getArmadura()));
        if(dano < 1.0) dano = 1.0;
        if(personaje instanceof Legionario || personaje instanceof Arquero) dano = dano * 2d;
        return dano;
    }
    
    @Override
    public char getTipo() {
        return 'C';
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

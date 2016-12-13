/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unidad;

import Edificio.Ciudadela;
import Edificio.Edificio;
import Recurso.FuenteRecursos;

/**
 *
 * @author yago
 */
public class Legionario extends Soldado{
    @Override
    public char getTipo() {
        return 'L';
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
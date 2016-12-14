/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unidad;

import Edificio.Ciudadela;
import Edificio.Edificio;
import Excepcion.ExcepcionUnidadNoExNoPaisano;


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
    public void almacenar(Ciudadela ciudadela)throws ExcepcionUnidadNoExNoPaisano{
    throw new Excepcion.ExcepcionUnidadNoExNoPaisano("Esta unidad no puede recolectar");
  }


    @Override
    public void reparar(Edificio edificio) throws ExcepcionUnidadNoExNoPaisano{
    throw new Excepcion.ExcepcionUnidadNoExNoPaisano("Esta unidad no puede recolectar");
  }
    
    @Override
    public Edificio construir(String tipo_edificio)throws ExcepcionUnidadNoExNoPaisano{
    throw new Excepcion.ExcepcionUnidadNoExNoPaisano("Esta unidad no puede recolectar");
  }

  @Override
  public void recolectar(FuenteRecurso.FuenteRecursos contenedor)throws ExcepcionUnidadNoExNoPaisano{
    throw new Excepcion.ExcepcionUnidadNoExNoPaisano("Esta unidad no puede recolectar");
  }
}

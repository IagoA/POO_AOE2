/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unidad;

import Edificio.Ciudadela;
import Edificio.Edificio;
import Excepcion.ExcepcionUnidadNoExNoPaisano;
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
  public void recolectar(FuenteRecursos contenedor)throws ExcepcionUnidadNoExNoPaisano{
    throw new Excepcion.ExcepcionUnidadNoExNoPaisano("Esta unidad no puede recolectar");
  }
}

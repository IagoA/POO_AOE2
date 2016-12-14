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
public class Legionario extends Soldado{
    @Override
    public char getTipo() {
        return 'L';
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unidad;

import Edificio.Ciudadela;
import Edificio.Edificio;
import Excepcion.Excepcion;
import Excepcion.ExcepcionGrupoConSoldados;
import Mapa.Civilizacion;
import FuenteRecurso.FuenteRecursos;
import java.util.HashMap;

/**
 *
 * @author yago
 */
public class Grupo extends Unidad {

    private HashMap<String, Unidad> uds;
    private int numPai;
    private int numSol;

    public Grupo(HashMap<String, Unidad> unidades, Civilizacion civi) {
        uds = new HashMap<>(unidades);//hace una copia
        numPai = 0;
        numSol = 0;
        for (String clave : uds.keySet()) {
            if (uds.get(clave) instanceof Paisano) {
                numPai++;
            } else {
                numSol++;
            }
        }
        this.civi = civi;
        civi.setGrupCreados(civi.getGrupCreados() + 1);
        nombre = "Grupo-" + (civi.getGrupCreados());
        ataque = numSol * 5 + numPai * 3;
        armadura = (0.6f * numSol) / (numSol + numPai);
    }

    public boolean actualizarGrupo() {
        numPai = 0;
        numSol = 0;
        if (!uds.isEmpty()) {
            for (String clave : uds.keySet()) {
                if (uds.get(clave) instanceof Paisano) {
                    numPai++;
                } else {
                    numSol++;
                }
            }
        }

        ataque = numSol * 5 + numPai * 3;
        armadura = (0.6f * numSol) / (numSol + numPai);

        return (numPai + numSol) <= 1;
    }

    @Override
    public void modSalud(int mod) {
        Unidad u;
        int s;
        if (!uds.isEmpty()) {
            for (String clave : uds.keySet()) {
                u = uds.get(clave);
                u.modSalud(mod);
            }
        }
    }

    @Override
    public void recolectar(FuenteRecursos contenedor) throws Excepcion {
        if (numSol == 0) {
            for (String clave : uds.keySet()) {
                uds.get(clave).recolectar(contenedor);
            }
        }else{
          throw new ExcepcionGrupoConSoldados();
        }
    }

    @Override
    public void almacenar(Ciudadela ciudadela) throws Excepcion {
        if (numSol == 0) {
            for (String clave : uds.keySet()) {
                if (((Paisano) uds.get(clave)).getR() != null) {
                    uds.get(clave).almacenar(ciudadela);
                }
            }
        } else {
          throw new ExcepcionGrupoConSoldados();
        }
    }

    @Override
    public void reparar(Edificio edificio) throws ExcepcionGrupoConSoldados {
        if (numSol == 0) {
            ((Paisano) (uds.get((String) (uds.keySet().toArray()[0])))).reparar(edificio);
        }else{
          throw new ExcepcionGrupoConSoldados();
        }
    }

    @Override
    public Edificio construir(String tipo_edificio) throws Excepcion {
        throw new Excepcion("Los grupos no pueden construir");
    }

    @Override
    public char getTipo() {
        return 'G';
    }

    @Override
    public double danhoAtaque(Unidad personaje) {
        double dano = 0d;
        for (String clave : uds.keySet()) {
            dano += uds.get(clave).danhoAtaque(personaje);
        }
        return dano;
    }

    @Override
    public double danhoAtaque(Edificio edificio) {
        double dano = 0d;
        for (String clave : uds.keySet()) {
            dano += uds.get(clave).danhoAtaque(edificio);
        }
        return dano;
    }
}

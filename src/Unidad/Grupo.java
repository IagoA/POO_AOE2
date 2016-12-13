/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unidad;

import Edificio.Ciudadela;
import Edificio.Edificio;
import Mapa.Civilizacion;
import Recurso.FuenteRecursos;
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
    public void recolectar(FuenteRecursos contenedor) {
        if (numSol == 0) {
            for (String clave : uds.keySet()) {
                uds.get(clave).recolectar(contenedor);
            }
        }//else Error
    }

    @Override
    public void almacenar(Ciudadela ciudadela) {
        if (numSol == 0) {
            for (String clave : uds.keySet()) {
                if (((Paisano) uds.get(clave)).getR() != null) {
                    uds.get(clave).almacenar(ciudadela);
                }
            }
        }//else Error
    }

    @Override
    public void reparar(Edificio edificio) {
        if (numSol == 0) {
            ((Paisano) (uds.get((String) (uds.keySet().toArray()[0])))).reparar(edificio);
        }//else Error
    }

    @Override
    public Edificio construir(String tipo_edificio) {
        //Error
        return null;
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

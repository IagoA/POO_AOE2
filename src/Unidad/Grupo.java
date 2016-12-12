/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unidad;

import Mapa.Civilizacion;
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
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unidad;

import Edificio.Ciudadela;
import Edificio.Edificio;
import Mapa.Celda;
import Mapa.Civilizacion;
import Punto.Punto;
import Recurso.FuenteRecursos;

/**
 *
 * @author yago
 */
public abstract class Unidad {

    protected String nombre;
    protected Civilizacion civi;
    protected int ataque;
    protected float armadura;
    
    public String getNombre(){
        return nombre;
    }
    
    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        if (ataque < 0) {
            ataque = 0;
        }
        this.ataque = ataque;
    }

    public int getCodCiv() {
        return civi.getCodigo();
    }

    public Civilizacion getCivilizacion() {
        return civi;
    }

    public float getArmadura() {
        return armadura;
    }

    public void setArmadura(float armadura) {
        if (armadura < 0f) {
            armadura = 0f;
        } else if (armadura > 1f) {
            armadura = 0.9f;//no hay dioses.
        }
        this.armadura = armadura;
    }

    public int capacidadMovimiento() {
        return 1;
    }

    public Punto mover(String pto_cardinal) {
        Celda cel = civi.getUdsHash().get(nombre);
        if (cel != null) {
            Punto p = new Punto(cel.getPos());
            switch (pto_cardinal) {
                case "NORTE":
                    p.moverY((-1)*capacidadMovimiento());
                    break;
                case "SUR":
                    p.moverY(capacidadMovimiento());
                    break;
                case "ESTE":
                    p.moverX(capacidadMovimiento());
                    break;
                case "OESTE":
                    p.moverX((-1)*capacidadMovimiento());
                    break;
                default:
                    return null;
            }
            return p;
        } else {
            return null;
        }

    }
    
    public abstract void modSalud(int mod);

    public abstract void recolectar(FuenteRecursos contenedor);

    public abstract void almacenar(Ciudadela ciudadela);

    public abstract void reparar(Edificio edificio);

    public abstract Edificio construir(String tipo_edificio);
    
    public abstract char getTipo();

    public void defender(Edificio edificio) {
        edificio.setAtaque(ataque + edificio.getAtaque());
        edificio.setDefensa(edificio.getDefensa() + 2);
    }
    
    public double danhoAtaque(Unidad personaje){
        double dano = (double)(ataque * (1 - personaje.getArmadura()));
        if(dano < 1.0) dano = 1.0;
        return dano;
    }
    
    public double danhoAtaque(Edificio edificio){
        double dano = (double)(ataque - edificio.getDefensa());
        if(dano < 1.0) dano = 1.0;
        return dano;
    }
    
    public void atacar(Unidad[] personajes){
        for (Unidad personaje : personajes) {
            double danho=danhoAtaque(personaje);
            personaje.modSalud((-1)*(int)danho);
        }
    }
    
    public void atacar(Edificio edificio){
        double danho=danhoAtaque(edificio);
        edificio.modSalud((-1)*(int)danho);
    }
}

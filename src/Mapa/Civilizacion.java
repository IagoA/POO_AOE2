/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Recurso.Recurso;
import java.util.HashMap;

/**
 *
 * @author iago.abad
 */
public class Civilizacion {

    private String nombre;
    private int codigo;
    private HashMap<String, Celda> udsHash;
    private HashMap<String, Celda> edisHash;
    private HashMap<String, Celda> grupHash;
    private int PobMax;
    private int CiudaCreadas;
    private int CuartCreados;
    private int CasasCreadas;
    private int TorresCreadas;
    private int PaiCreados;
    private int SoldCreados;
    private int GrupCreados;
    private Recurso madera;
    private Recurso comida;
    private Recurso piedra;

    public Civilizacion(String nom, int cod) {
        nombre = nom;
        codigo = cod;
        udsHash = new HashMap<>();
        edisHash = new HashMap<>();
        grupHash = new HashMap<>();
        PobMax = 5;
        CiudaCreadas = 0;
        CuartCreados = 0;
        CasasCreadas = 0;
        TorresCreadas = 0;
        PaiCreados = 0;
        SoldCreados = 0;
        GrupCreados = 0;
        madera = new Recurso('M', 20000);
        comida = new Recurso('C', 20000);
        piedra = new Recurso('P', 20000);
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public HashMap<String, Celda> getUdsHash() {
        return udsHash;
    }

    public HashMap<String, Celda> getEdisHash() {
        return edisHash;
    }
    
    public HashMap<String, Celda> getGrupHash() {
        return grupHash;
    }

    public int getCiudaCreadas() {
        return CiudaCreadas;
    }

    public void setCiudaCreadas(int CiudaCreadas) {
        this.CiudaCreadas = CiudaCreadas;
    }

    public int getCuartCreados() {
        return CuartCreados;
    }

    public void setCuartCreados(int CuartCreados) {
        this.CuartCreados = CuartCreados;
    }

    public int getCasasCreadas() {
        return CasasCreadas;
    }

    public void setCasasCreadas(int CasasCreadas) {
        this.CasasCreadas = CasasCreadas;
    }

    public int getTorresCreadas() {
        return TorresCreadas;
    }

    public void setTorresCreadas(int TorresCreadas) {
        this.TorresCreadas = TorresCreadas;
    }

    public int getPaiCreados() {
        return PaiCreados;
    }

    public void setPaiCreados(int PaiCreados) {
        this.PaiCreados = PaiCreados;
    }

    public int getSoldCreados() {
        return SoldCreados;
    }

    public void setSoldCreados(int SoldCreados) {
        this.SoldCreados = SoldCreados;
    }

    public int getGrupCreados() {
        return GrupCreados;
    }

    public void setGrupCreados(int GrupCreados) {
        this.GrupCreados = GrupCreados;
    }

    public int getMadera() {
        return madera.getCantidad();
    }

    public int modMadera(int mod) {
        return madera.modCantidad(mod);
    }

    public void setMadera(int set) {
        madera.setCantidad(set);
    }

    public int getPiedra() {
        return piedra.getCantidad();
    }

    public int modPiedra(int mod) {

        return piedra.modCantidad(mod);
    }

    public void setPiedra(int set) {
        piedra.setCantidad(set);
    }

    public int getComida() {
        return comida.getCantidad();
    }

    public int modComida(int mod) {
        return comida.modCantidad(mod);
    }

    public void setComida(int set) {
        comida.setCantidad(set);
    }

    public int getPobMax() {
        return PobMax;
    }

    public void setPobMax(int a) {
        if (a >= 5) {
            PobMax = a;
        } else {
            PobMax = 5;
        }
    }
    
    
    public boolean perdedor(){
    for (String clave2 : this.getEdisHash().keySet()) {
          if(this.getEdisHash().get(clave2).getEdificio().getTipo()=='C')return false;
        }
    return true;
  }
}

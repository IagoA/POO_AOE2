/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unidad;

/**
 *
 * @author yago
 */
public abstract class Soldado extends Unidad {
    protected int salud;
    protected final int SALUDMAX = 25;
    
    @Override
    public void modSalud(int mod) {
        if (salud + mod >= SALUDMAX) {
            salud = SALUDMAX;
        } else if (salud + mod <= 0) {
            int i = salud;
            salud = 0;
        } else {
            salud += mod;
        }
    }
    
    public int getSALUDMAX() {
        return SALUDMAX;
    }
    
    public int getSalud() {
        return salud;
    }
}

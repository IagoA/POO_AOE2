/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recurso;

/**
 *
 * @author Sergio
 */
public abstract class Recurso {

    //private char tipo;//M,P,C
    protected int cantidad;

    public int getCantidad() {
        return this.cantidad;
    }

    public abstract char getTipo() ;

    public int modCantidad(int mod) {
        if (cantidad + mod < 0) {
            int temp = cantidad * (-1);
            cantidad = 0;
            return temp;
        } else {
            cantidad += mod;
            return mod;
        }
    }

    public void setCantidad(int c) {
        cantidad = c;
    }
}

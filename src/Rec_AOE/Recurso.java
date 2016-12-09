/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rec_AOE;

/**
 *
 * @author Sergio
 */
public class Recurso {

    private char tipo;//M,P,C
    private int cantidad;

    public Recurso(Recurso r) {
        this.tipo = r.tipo;
        this.cantidad = r.cantidad;
    }

    public Recurso(char tipo, int cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public Recurso(char tipo) {
        this.tipo = tipo;
        this.cantidad = 0;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public char getTipo() {
        return this.tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

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

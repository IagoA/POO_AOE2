/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Punto;

public class Punto {

    private int x;
    private int y;

    //Implementamos los contructores
    public Punto() {
        x = 0;
        y = 0;
    }

    public Punto(int ValorX, int ValorY) {
        x = ValorX;
        y = ValorY;
    }
    
    public Punto(Punto p) {
        x = p.x;
        y = p.y;
    }

    //Implementamos los setters y getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int valor) {
        x = valor;
    }

    public void setY(int valor) {
        y = valor;
    }

    //Implementamos los métodos para desplazar las coordenadas del punto
    public void moverX(int valor) {
        x += valor;

    }

    public void moverY(int valor) {
        y += valor;

    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Punto other = (Punto) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.x;
        hash = 17 * hash + this.y;
        return hash;
    }
}
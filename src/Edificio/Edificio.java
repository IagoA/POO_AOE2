/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Edificio;

import Mapa.Civilizacion;

/**
 *
 * @author iago.abad
 */
public abstract class Edificio {

    protected String nombre;
    protected Civilizacion civilizacion;
    protected int salud;
    protected int saludMax;
    protected int ataque;
    protected int defensa;
    protected int capacidad;

//    public Edificio(char tipo, Civilizacion civi) {
//        switch (tipo) {
//            case 'H':
//                saludMax = 50;
//                civilizacion = civi;
//                civi.setCasasCreadas(civi.getCasasCreadas() + 1);
//                nombre = "Casa-" + (civi.getCasasCreadas());
//                salud = saludMax;
//                ataque = 0;
//                defensa = 0;
//                capacidad = 5;
//                this.tipo = tipo;
//
//                break;
//            case 'M':
//                saludMax = 75;
//                civilizacion = civi;
//                civi.setCuartCreados(civi.getCuartCreados() + 1);
//                nombre = "Cuartel-" + (civi.getCuartCreados());
//                salud = saludMax;
//                ataque = 0;
//                defensa = 0;
//                capacidad = 10;
//                this.tipo = tipo;
//                break;
//            case 'C':
//                saludMax = 125;
//                civilizacion = civi;
//                civi.setCiudaCreadas(civi.getCiudaCreadas() + 1);
//                nombre = "Ciudadela-" + (civi.getCiudaCreadas());
//                salud = saludMax;
//                ataque = 0;
//                defensa = 0;
//                capacidad = 10;
//                this.tipo = tipo;
//                break;
//            case 'T':  //Ajustar parametros
//                saludMax = 60;
//                civilizacion = civi;
//                civi.setCasasCreadas(civi.getTorresCreadas() + 1);
//                nombre = "Torre-" + (civi.getCasasCreadas());
//                salud = saludMax;
//                ataque = 10;
//                defensa = 0;
//                capacidad = 5;
//                this.tipo = tipo;
//
//                break;
//            default:
//                System.out.println("No se puede crear ese tipo de edificio");
//        }
//    }
//
//    public Edificio(char tipo, Civilizacion civi, Unidad constructor) {
//        if (constructor.getTipo() == 'P') {
//            int totalMadera = civi.getMadera();
//            int totalPiedra = civi.getPiedra();
//            int costeMadera;
//            int costePiedra;
//            if (constructor.getR() != null && constructor.getR().getTipo() == 'M') {//comprueba si el aldeano lleva madera encima
//                totalMadera += constructor.getR().getCantidad();//si la lleva la añade al total de madera disponible
//            }
//            if (constructor.getR() != null && constructor.getR().getTipo() == 'P') {//lo mismo para piedra
//                totalPiedra += constructor.getR().getCantidad();
//            }
//            switch (tipo) {
//                case 'H':
//                    costeMadera = 30;
//                    if (costeMadera <= totalMadera) {//si el total da para construir
//                        if (constructor.getR() != null && constructor.getR().getTipo() == 'M') {//se le quita a la madera que lleve el aldeano(si tiene)
//                            costeMadera += constructor.modR('M', (-1) * costeMadera);
//                        }
//                        civi.modMadera((-1) * costeMadera);//luego se saca de la ciudadela lo que falte
//                        saludMax = 50;
//                        civilizacion = civi;
//                        civi.setCasasCreadas(civi.getCasasCreadas() + 1);
//                        nombre = "Casa-" + (civi.getCasasCreadas());
//                        civi.setPobMax(civi.getPobMax()+5);
//                        salud = saludMax;
//                        ataque = 0;
//                        defensa = 0;
//                        capacidad = 5;
//                        this.tipo = tipo;
//                    } else {
//                        System.out.println("No hay suficientes recursos");
//                    }
//                    break;
//                case 'M':
//                    costeMadera = 150;
//                    costePiedra = 100;
//                    if (costeMadera <= totalMadera && costePiedra <= totalPiedra) {//si el total da para reparar
//                        if (constructor.getR() != null && constructor.getR().getTipo() == 'M') {//se le quita a la madera que lleve el aldeano(si tiene)
//                            costeMadera += constructor.modR('M', (-1) * costeMadera);
//                        }
//                        if (constructor.getR() != null && constructor.getR().getTipo() == 'P') {//y a la piedra
//                            costePiedra += constructor.modR('P', (-1) * costePiedra);
//                        }
//                        civi.modMadera((-1) * costeMadera);//luego se saca de la ciudadela lo que falte
//                        civi.modPiedra((-1) * costePiedra);
//                        saludMax = 75;
//                        civilizacion = civi;
//                        civi.setCuartCreados(civi.getCuartCreados() + 1);
//                        nombre = "Cuartel-" + (civi.getCuartCreados());
//                        salud = saludMax;
//                        ataque = 0;
//                        defensa = 0;
//                        capacidad = 10;
//                        this.tipo = tipo;
//                    } else {
//                        System.out.println("No hay suficientes recursos");
//                    }
//                    break;
//
//                case 'C':
//                    costeMadera = 275;
//                    costePiedra = 150;
//                    if (costeMadera <= totalMadera && costePiedra <= totalPiedra) {//si el total da para reparar
//                        if (constructor.getR() != null && constructor.getR().getTipo() == 'M') {//se le quita a la madera que lleve el aldeano(si tiene)
//                            costeMadera += constructor.modR('M', (-1) * costeMadera);
//                        }
//                        if (constructor.getR() != null && constructor.getR().getTipo() == 'P') {//y a la piedra
//                            costePiedra += constructor.modR('P', (-1) * costePiedra);
//                        }
//                        civi.modMadera((-1) * costeMadera);//luego se saca de la ciudadela lo que falte
//                        civi.modPiedra((-1) * costePiedra);
//                        saludMax = 125;
//                        civilizacion = civi;
//                        civi.setCiudaCreadas(civi.getCiudaCreadas() + 1);
//                        nombre = "Ciudadela-" + (civi.getCiudaCreadas());
//                        salud = saludMax;
//                        ataque = 0;
//                        defensa = 0;
//                        capacidad = 10;
//                        this.tipo = tipo;
//                        //}
//                        break;
//                    }
//                case 'T':
//                    costeMadera = 50;
//                    costePiedra = 100;
//                    if (costeMadera <= totalMadera && costePiedra <= totalPiedra) {//si el total da para reparar
//                        if (constructor.getR() != null && constructor.getR().getTipo() == 'M') {//se le quita a la madera que lleve el aldeano(si tiene)
//                            costeMadera += constructor.modR('M', (-1) * costeMadera);
//                        }
//                        if (constructor.getR() != null && constructor.getR().getTipo() == 'P') {//y a la piedra
//                            costePiedra += constructor.modR('P', (-1) * costePiedra);
//                        }
//                        civi.modMadera((-1) * costeMadera);//luego se saca de la ciudadela lo que falte
//                        civi.modPiedra((-1) * costePiedra);
//                        saludMax = 60;
//                        civilizacion = civi;
//                        civi.setTorresCreadas(civi.getTorresCreadas() + 1);
//                        nombre = "Torre-" + (civi.getTorresCreadas());
//                        salud = saludMax;
//                        ataque = 10;
//                        defensa = 0;
//                        capacidad = 5;
//                        this.tipo = tipo;
//                        //}
//                        break;
//                    }
//                default:
//                    System.out.println("No se puede crear ese tipo de edificio");
//            }
//        } else {
//            System.out.println("La unidad no es un paisano");
//        }
//    }

    public int modSalud(int mod) {
        if (salud + mod >= saludMax) {
            salud = saludMax;
            return saludMax;
        } else if (salud + mod <= 0) {
            int i = salud;
            salud = 0;
            return i;
        } else {
            salud += mod;
            return mod;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getSalud() {
        return salud;
    }

    public int getSaludMax() {
        return saludMax;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        if (ataque > 0) {
            this.ataque = ataque;
        } else {
            this.ataque = 0;
        }
    }
    
    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        if (defensa > 0) {
            this.defensa = defensa;
        } else {
            this.defensa = 0;
        }
    }

    public int getCapacidad() {
        return capacidad;
    }
    
    public abstract void setCapacidad(int c);//{
//        switch (tipo) {
//                case 'H':
//                    if(c >= 0 && c <= 5) capacidad = c;
//                    else if(c < 0) capacidad = 0;
//                    else capacidad = 5;
//                        
//                    break;
//                case 'M':
//                    if(c >= 0 && c <= 10) capacidad = c;
//                    else if(c < 0) capacidad = 0;
//                    else capacidad = 10;
//                    break;
//                case 'C':
//                    if(c >= 0 && c <= 10) capacidad = c;
//                    else if(c < 0) capacidad = 0;
//                    else capacidad = 10;
//                    break;
//                case 'T':
//                    if(c >= 0 && c <= 5) capacidad = c;
//                    else if(c < 0) capacidad = 0;
//                    else capacidad = 5;
//                    break;
//            }
//    }

    public abstract int[] costeReparar(); //{
//        char t = this.getTipo();
//        int[] ret = {0, 0};
//        float por1 = (float)this.getSalud() / (float)this.getSaludMax();
//        if (por1 != (float) 1.0) {
//          por1=(1f-por1);
//            switch (t) {
//                case 'H':
//                    ret[0] = (int) (30 * por1);//coste madera
//                    break;
//                case 'M':
//                    ret[1] = (int) (100 * por1);//coste piedra
//                    ret[0] = (int) (150 * por1);
//                    break;
//                case 'C':
//                    ret[1] = (int) (150 * por1);
//                    ret[0] = (int) (275 * por1);
//                    break;
//                case 'T':
//                    ret[1] = (int) (100 * por1);
//                    ret[0] = (int) (50 * por1);
//                    break;
//            }
//        }
//        return ret;
//    }

    public int getCodCiv() {
        return civilizacion.getCodigo();
    }

    public Civilizacion getCivilizacion() {
        return civilizacion;
    }
    
    

    @Override
    public boolean equals(Object edi) {
        if (edi == null) {
            return false;
        }
        if (getClass() != edi.getClass()) {
            return false;
        }
        return this.nombre.equals(((Edificio) edi).nombre) && this.civilizacion == (((Edificio) edi).civilizacion);
    }
//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 37 * hash + Objects.hashCode(this.nombre);
//        return hash;
//    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aoe_entrega3;

import Mapa.Mapa;
import java.util.Scanner;

/**
 *
 * @author Sergio
 */
public class AOE_Entrega3 {

    public static void main(String[] args) {
        Mapa m = new Mapa(false);
        

        boolean seguir = true;
        Scanner scanner = new Scanner(System.in);
        while (seguir) {
            System.out.println(m);
            System.out.print("$ ");
            String linea = scanner.nextLine();
            String[] comando = linea.split(" ");
            if (comando.length >= 2) {
                switch (comando[0]) {
                    case "mover":
                        if (comando.length == 3) {
                            m.moverUnidad(comando[1], comando[2]);
                        } else {
                            System.out.println("Comando incorrecto. Solo puedes mover una unidad al NORTE, SUR, ESTE o OESTE");
                        }
                        
                        break;
                    case "listar":
                        if (comando.length == 2) {
                            switch (comando[1]) {
                                case "personajes":
                                    m.listarUnidades();
                                    break;
                                case "edificios":
                                    m.listarEdificios();
                                    break;
                                case "civilizaciones":
                                    m.listarCiv();
                                    break;
                                case "grupos":
                                    m.listarGrup();
                                    break;
                                default:
                                    System.out.println("Comando incorrecto. Solo se pueden listar personajes o edificios");
                                    break;
                            }
                            break;
                        } else {
                            System.out.println("Comando incorrecto. Solo se pueden listar personajes o edificios");
                        }

                    case "describir":
                        if (comando.length == 2) {
                            m.describir(comando[1]);
                        } else if (comando.length == 4) {
                            m.mirar(Integer.parseInt(comando[2]), Integer.parseInt(comando[3]));
                        }
                        break;
                    case "mirar":
                        if (comando.length == 2) {
                            comando[1] = comando[1].substring(1, comando[1].length() - 1);
                            String[] mir = comando[1].split(",");
                            m.mirar(Integer.parseInt(mir[0]), Integer.parseInt(mir[1]));
                        }
                        break;
                    case "construir":
                        if (comando.length == 4) {
                            m.construirEdificio(comando[1], comando[2], comando[3]);
                        } else {
                            System.out.println("Comando incorrecto. Formato: " + '"' + "construir (paisano) (edificio) (direccion)" + '"');
                        }
                        break;
                    case "reparar":
                        if (comando.length == 3) {
                            m.repararEdificio(comando[1], comando[2]);
                        } else {
                            System.out.println("Comando incorrecto. Solo puedes reparar con un paisano hacia su NORTE, SUR, ESTE o OESTE");
                        }
                        break;
                    case "crear":
                        if (comando.length == 3) {
                            m.crear(comando[1], comando[2]);
                        } else {
                            System.out.println("Comando incorrecto. Solo puedes crear en un cuartel un soldado o en la ciudadela un paisano");
                        }
                        break;
                    case "recolectar":
                        if (comando.length == 3) {
                            m.recolectar(comando[1], comando[2],false);
                            m.atacarTorre(Mapa.getCivActual());
                        } else {
                            System.out.println("Comando incorrecto. Solo puedes recolectar con un paisano hacia una dirección");
                        }
                        break;
                    case "almacenar":
                        if (comando.length == 3) {
                            m.almacenar(comando[1], comando[2],false);
                            m.atacarTorre(Mapa.getCivActual());
                        } else {
                            System.out.println("Comando incorrecto. Solo puedes almacenar con un paisano hacia una dirección");
                        }
                        break;
                    case "cambiar":
                        if (comando.length == 2) {
                            m.cambiarCiv(comando[1]);
                        } else {
                            System.out.println("Comando incorrecto. Debe especificar el nombre de la civilizacion a la que cambiar");
                        }
                        
                        break;
                    case "imprimir":
                        if (comando.length == 2 && "mapa".equals(comando[1])) {
                            
                        } else {
                            System.out.println("Comando incorrecto");
                        }
                        
                        break;
                    case "agrupar":
                        if (comando.length == 2) {
                            comando[1] = comando[1].substring(1, comando[1].length() - 1);
                            String[] mir = comando[1].split(",");
                            m.agrupar(Integer.parseInt(mir[0]),Integer.parseInt(mir[1]));
                            
                        } else {
                            System.out.println("Comando incorrecto. Solo se pueden agrupar unidades no agrupadas de una celda");
                        }
                        
                        break;
                    case "desagrupar":
                        if (comando.length == 2) {
                            m.desagrupar(comando[1]);
                        } else {
                            System.out.println("Comando incorrecto. Solo se pueden desagrupar grupos ya existentes");
                        }
                        
                        break;
                    case "desligar":
                        if (comando.length == 3) {
                            m.desligar(comando[1], comando[2]);
                        } else {
                            System.out.println("Comando incorrecto: desligar <nombre_unidad> <nombre_grupo>");
                        }
                        
                        break;
                    case "defender":
                        if (comando.length == 3) {
                            m.defender(comando[1], comando[2]);
                        } else {
                            System.out.println("Comando incorrecto: defender <nombre_unidad> <dereccion>");
                        }
                        
                        break;
                    case "atacar":
                        if (comando.length == 3) {
                            m.atacar(comando[1], comando[2]);
                        } else {
                            System.out.println("Comando incorrecto: atacar <nombre_unidad> <dereccion>");
                        }
                        
                        break;
                    default:
                        System.out.println("Comando incorrecto");
                        break;
                }
            } else {
                switch (comando[0]) {
                    case "civilizacion":
                        if (comando.length == 1) {
                            m.imprimirCivAct();
                        } else {
                            System.out.println("Comando incorrecto. El formato del comando es " + '"' + "civilizacion" + '"');
                        }
                        
                        break;
                    case "salir":
                        seguir = false;
                        break;
                    default:
                        System.out.println("Comando incorrecto");
                        break;
                }
            }
            if(m.ganador()){
              seguir=false;
              System.out.println("Has ganado!");
              System.out.println(m);
            }
        }
    }
}

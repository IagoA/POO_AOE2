/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa_AOE;

import Edi_AOE.Edificio;
import Uni_AOE.Unidad;
import Uni_AOE.Grupos;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Sergio
 */
public class Mapa {

  private Celda[][] cel;
  private final int tamX = 10;
  private final int tamY = 10;
  private HashMap<String, Civilizacion> hashCiv;
  private static Civilizacion civActual;

  public static Civilizacion getCivActual() {
    return civActual;
  }

  public Mapa(boolean fog) {
    cel = new Celda[tamX][tamY];
    Random r = new Random();
    hashCiv = new HashMap<>();
    hashCiv.put("Griegos", new Civilizacion("Griegos", 0));
    hashCiv.put("Romanos", new Civilizacion("Romanos", 1));
    civActual = hashCiv.get("Griegos");
    char[][] m = {{'b', 'a', 'a', 'p', 'a', 'a', 'a', 'c', 'c', 'c'},
    {'b', 'a', 'a', 'p', 'a', 'a', 'p', 'a', 'a', 'a'},
    {'b', 'a', 'p', 'p', 'p', 'p', 'p', 'a', 'a', 'a'},
    {'b', 'p', 'C', 'p', 'p', 'p', 'p', 'c', 'c', 'a'},
    {'b', 'p', 'p', 'p', 'p', 'p', 'p', 'c', 'c', 'a'},
    {'b', 'b', 'p', 'p', 'p', 'p', 'p', 'c', 'c', 'a'},
    {'b', 'b', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'a'},
    {'b', 'b', 'p', 'p', 'p', 'p', 'p', 'p', 'c', 'c'},
    {'b', 'b', 'p', 'p', 'p', 'p', 'p', 'p', 'c', 'c'},
    {'b', 'b', 'p', 'p', 'p', 'p', 'C', 'p', 'c', 'c'}};
    for (int i = 0; i < tamY; i++) {
      for (int j = 0; j < tamX; j++) {
        if (Character.isUpperCase(m[i][j])) {
          cel[i][j] = new Celda(m[i][j], j, i, civActual);
          civActual.getEdisHash().put(cel[i][j].getEdificio().getNombre(), cel[i][j]);
          civActual = hashCiv.get("Romanos");
        } else {
          switch (m[i][j]) {
            case 'a':
              if (fog) {
                cel[i][j] = new Celda(m[i][j], r.nextInt(150 - 100) + 100, j, i, civActual);
              } else {
                cel[i][j] = new Celda(m[i][j], r.nextInt(150 - 100) + 100, j, i);
              }
              break;
            case 'b':
              if (fog) {
                cel[i][j] = new Celda(m[i][j], r.nextInt(90 - 55) + 55, j, i, civActual);
              } else {
                cel[i][j] = new Celda(m[i][j], r.nextInt(90 - 55) + 55, j, i);
              }
              break;
            case 'c':
              if (fog) {
                cel[i][j] = new Celda(m[i][j], r.nextInt(90 - 30) + 30, j, i, civActual);
              } else {
                cel[i][j] = new Celda(m[i][j], r.nextInt(70 - 30) + 30, j, i);
              }
              break;
            case 'p':
              if (fog) {
                cel[i][j] = new Celda(j, i, civActual);
              } else {
                cel[i][j] = new Celda(j, i);
              }
              break;
          }
        }
      }
    }
    Unidad ud = new Unidad('P', hashCiv.get("Romanos"));
    cel[8][7].getUds().put(ud.getNombre(), ud);
    hashCiv.get("Romanos").getUdsHash().put(ud.getNombre(), cel[8][7]);

    ud = new Unidad('P', hashCiv.get("Griegos"));
    cel[3][3].getUds().put(ud.getNombre(), ud);
    hashCiv.get("Griegos").getUdsHash().put(ud.getNombre(), cel[3][3]);

    for (String c : hashCiv.keySet()) {
      Civilizacion civ = hashCiv.get(c);
      for (String clave : civ.getUdsHash().keySet()) {
        Celda celda = civ.getUdsHash().get(clave);
        celda.setVisible(civ, true);
        if (celdaNorte(celda) != null) {
          celdaNorte(celda).setVisible(civ, true);
        }
        if (celdaSur(celda) != null) {
          celdaSur(celda).setVisible(civ, true);
        }
        if (celdaEste(celda) != null) {
          celdaEste(celda).setVisible(civ, true);
        }
        if (celdaOeste(celda) != null) {
          celdaOeste(celda).setVisible(civ, true);
        }
      }
    }

    for (String c : hashCiv.keySet()) {
      Civilizacion civ = hashCiv.get(c);
      for (String clave : civ.getEdisHash().keySet()) {
        Celda celda = civ.getEdisHash().get(clave);
        if (celdaNorte(celda) != null) {
          celdaNorte(celda).setVisible(civ, true);
        }
        if (celdaSur(celda) != null) {
          celdaSur(celda).setVisible(civ, true);
        }
        if (celdaEste(celda) != null) {
          celdaEste(celda).setVisible(civ, true);
        }
        if (celdaOeste(celda) != null) {
          celdaOeste(celda).setVisible(civ, true);
        }
      }
    }
  }

  @Override
  public String toString() {
    String map = new String();
    map = map + "  0 1 2 3 4 5 6 7 8 9\n";
    for (int i = 0; i < tamY; i++) {
      map = map + i + " ";
      for (int j = 0; j < tamX; j++) {
        map = map + cel[i][j].toString();
        if (j == tamX - 1) {
          map = map + " \t\t";
          switch (i) {
            case 0:
              map = map + "\u001B[1mLeyenda:\u001B[0m";//En negrita
              break;
            case 1:
              map = map + "\u001B[33m░ Pradera\u001B[0m  \t\u001B[32m♠ Bosque\u001B[0m\t\u001B[1;32m☁ Arbusto\u001B[0m";
              break;
            case 2:
              map = map + "\u001B[1;30m▓ Cantera\u001B[0m  \t\u001B[1;34m皿 Cuartel\t♛ Ciudadela\u001B[0m";
              break;
            case 3:
              map = map + "\u001B[1;34m♜ Torre \t☗ Casa \u001B[0m";
              break;
            case 4:
              map = map + "\u001B[1;34m☺ Unidad/es\t☻ Grupo/s\t○ Grupo/s y Unidad/es\u001B[0m";
              break;
            case 6:
              map = map + "Madera: " + civActual.getMadera();
              break;
            case 7:
              map = map + "Piedra: " + civActual.getPiedra();
              break;
            case 8:
              map = map + "Comida: " + civActual.getComida();
              break;
            case 9:
              map = map + "Los enemigos se muestran en rojo";
              break;
          }
        } else {
          map = map + " ";
        }
      }
      map = map + "\n";
    }
    return map;
  }

  public char sonAdyacentes(Celda c1, Celda c2) {
    if (c1.getX() == c2.getX()) {
      if (c1.getY() == c2.getY() + 1) {
        return 'N';
      } else if (c1.getY() == c2.getY() - 1) {
        return 'S';
      } else {
        return '0';
      }
    } else if (c1.getY() == c2.getY()) {
      if (c1.getX() == c2.getX() + 1) {
        return 'E';
      } else if (c1.getX() == c2.getX() - 1) {
        return 'O';
      } else {
        return '0';
      }
    } else {
      return '0';
    }
  }

  public final Celda celdaNorte(Celda c1) {
    if (c1.getY() != 0) {
      return cel[c1.getY() - 1][c1.getX()];
    } else {
      return null;
    }
  }

  public final Celda celdaSur(Celda c1) {
    if (c1.getY() != tamY - 1) {
      return cel[c1.getY() + 1][c1.getX()];
    } else {
      return null;
    }
  }

  public final Celda celdaEste(Celda c1) {
    if (c1.getX() != tamX - 1) {
      return cel[c1.getY()][c1.getX() + 1];
    } else {
      return null;
    }
  }

  public final Celda celdaOeste(Celda c1) {
    if (c1.getX() != 0) {
      return cel[c1.getY()][c1.getX() - 1];
    } else {
      return null;
    }
  }

  public void listarUnidades() {
    for (String clave : civActual.getUdsHash().keySet()) {
      Celda celd = civActual.getUdsHash().get(clave);
      System.out.print(clave + " (" + celd.getY() + "," + celd.getX() + ")");
      if (!celd.getUds().containsKey(clave)) {
        System.out.print(" - Agrupado");
      }
      System.out.println();
    }
  }

  public void listarEdificios() {
    for (String clave : civActual.getEdisHash().keySet()) {
      Celda celda = civActual.getEdisHash().get(clave);
      System.out.println(clave + " (" + celda.getY() + "," + celda.getX() + ")");
    }
  }

  public void defender(String unidad, String direccion) {
    Celda c, dest;
    if ((c = civActual.getUdsHash().get(unidad)) != null) {
      if (c.getUnidad(unidad) == null) {
        System.out.println("Esta unidad no se puede mover sola, está en un grupo");
        return;
      }
      switch (direccion) {
        case "NORTE":
          dest = celdaNorte(c);
          if (dest != null && dest.getEdificio() != null) {
            if (1 <= dest.getEdificio().getCapacidad() - dest.getTotalUds()) {
              dest.getUds().put(unidad, c.getUnidad(unidad));
              c.getUds().remove(unidad);
              civActual.getUdsHash().put(unidad, dest);
              if (dest.getUnidad(unidad).getTipo() == 'P' && dest.getEdificio().getTipo() == 'C') {
                dest.getUnidad(unidad).setSalud(dest.getUnidad(unidad).getSALUDMAX());
              } else if (dest.getUnidad(unidad).getTipo() == 'S' && dest.getEdificio().getTipo() == 'M') {
                dest.getUnidad(unidad).setSalud(dest.getUnidad(unidad).getSALUDMAX());
              }
              if (dest != null) {
                dest.getEdificio().setAtaque(dest.getUnidad(unidad).getAtaque() + dest.getEdificio().getAtaque());
                dest.getEdificio().setDefensa(dest.getEdificio().getDefensa() + 2);
              }
              atacarTorre(civActual);
            } else {
              System.out.println("Hay demasiada gente en el edificio");
            }
          } else {
            System.out.println("Comando incorrecto. No se puede mover al edificio");
          }

          break;
        case "SUR":
          dest = celdaSur(c);
          if (dest != null && dest.getEdificio() != null) {
            if (1 <= dest.getEdificio().getCapacidad() - dest.getTotalUds()) {
              dest.getUds().put(unidad, c.getUnidad(unidad));
              c.getUds().remove(unidad);
              civActual.getUdsHash().put(unidad, dest);
              if (dest.getUnidad(unidad).getTipo() == 'P' && dest.getEdificio().getTipo() == 'C') {
                dest.getUnidad(unidad).setSalud(dest.getUnidad(unidad).getSALUDMAX());
              } else if (dest.getUnidad(unidad).getTipo() == 'S' && dest.getEdificio().getTipo() == 'M') {
                dest.getUnidad(unidad).setSalud(dest.getUnidad(unidad).getSALUDMAX());
              }
              if (dest != null) {
                dest.getEdificio().setAtaque(dest.getUnidad(unidad).getAtaque() + dest.getEdificio().getAtaque());
                dest.getEdificio().setDefensa(dest.getEdificio().getDefensa() + 2);
              }
              atacarTorre(civActual);
            } else {
              System.out.println("Hay demasiada gente en el edificio");
            }
          } else {
            System.out.println("Comando incorrecto. No se puede mover al edificio");
          }

          break;
        case "ESTE":
          dest = celdaEste(c);
          if (dest != null && dest.getEdificio() != null) {
            if (1 <= dest.getEdificio().getCapacidad() - dest.getTotalUds()) {
              dest.getUds().put(unidad, c.getUnidad(unidad));
              c.getUds().remove(unidad);
              civActual.getUdsHash().put(unidad, dest);
              if (dest.getUnidad(unidad).getTipo() == 'P' && dest.getEdificio().getTipo() == 'C') {
                dest.getUnidad(unidad).setSalud(dest.getUnidad(unidad).getSALUDMAX());
              } else if (dest.getUnidad(unidad).getTipo() == 'S' && dest.getEdificio().getTipo() == 'M') {
                dest.getUnidad(unidad).setSalud(dest.getUnidad(unidad).getSALUDMAX());
              }
              if (dest != null) {
                dest.getEdificio().setAtaque(dest.getUnidad(unidad).getAtaque() + dest.getEdificio().getAtaque());
                dest.getEdificio().setDefensa(dest.getEdificio().getDefensa() + 2);
              }
              atacarTorre(civActual);
            } else {
              System.out.println("Hay demasiada gente en el edificio");
            }
          } else {
            System.out.println("Comando incorrecto. No se puede mover al edificio");
          }

          break;

        case "OESTE":
          dest = celdaOeste(c);
          if (dest != null && dest.getEdificio() != null) {
            if (1 <= dest.getEdificio().getCapacidad() - dest.getTotalUds()) {
              dest.getUds().put(unidad, c.getUnidad(unidad));
              c.getUds().remove(unidad);
              civActual.getUdsHash().put(unidad, dest);
              if (dest.getUnidad(unidad).getTipo() == 'P' && dest.getEdificio().getTipo() == 'C') {
                dest.getUnidad(unidad).setSalud(dest.getUnidad(unidad).getSALUDMAX());
              } else if (dest.getUnidad(unidad).getTipo() == 'S' && dest.getEdificio().getTipo() == 'M') {
                dest.getUnidad(unidad).setSalud(dest.getUnidad(unidad).getSALUDMAX());
              }
              if (dest != null) {
                dest.getEdificio().setAtaque(dest.getUnidad(unidad).getAtaque() + dest.getEdificio().getAtaque());
                dest.getEdificio().setDefensa(dest.getEdificio().getDefensa() + 2);
              }
              atacarTorre(civActual);
            } else {
              System.out.println("Hay demasiada gente en el edificio");
            }
          } else {
            System.out.println("Comando incorrecto. No se puede mover al edificio");
          }

          break;
        default:
          System.out.println("Comando incorrecto. Solo te puedes mover al NORTE, SUR, ESTE o OESTE");
          return;
      }

    } else if ((c = civActual.getGrupHash().get(unidad)) != null) {
      switch (direccion) {
        case "NORTE":
          dest = celdaNorte(c);
          if (dest != null && dest.getEdificio() != null) {
            if (c.getGrupo(unidad).getUds().size() <= dest.getEdificio().getCapacidad() - dest.getTotalUds()) {
              dest.getGrup().put(unidad, c.getGrupo(unidad));
              c.getGrup().remove(unidad);
              civActual.getGrupHash().put(unidad, dest);
              if (dest.getEdificio().getTipo() == 'C') {
                for (String clave : dest.getGrupo(unidad).getUds().keySet()) {
                  Unidad uu = dest.getGrupo(unidad).getUnidad(clave);
                  if (uu.getTipo() == 'P') {
                    uu.setSalud(uu.getSALUDMAX());
                  }
                }

              } else if (dest.getEdificio().getTipo() == 'M') {
                for (String clave : dest.getGrupo(unidad).getUds().keySet()) {
                  Unidad uu = dest.getGrupo(unidad).getUnidad(clave);
                  if (uu.getTipo() == 'S') {
                    uu.setSalud(uu.getSALUDMAX());
                  }
                }
              }
              atacarTorre(civActual);
            } else {
              System.out.println("Hay demasiada gente en el edificio");
            }
          } else {
            System.out.println("Comando incorrecto. No se puede mover al edificio");
          }

          break;
        case "SUR":
          dest = celdaSur(c);
          if (dest != null && dest.getEdificio() != null) {
            if (c.getGrupo(unidad).getUds().size() <= dest.getEdificio().getCapacidad() - dest.getTotalUds()) {
              dest.getGrup().put(unidad, c.getGrupo(unidad));
              c.getGrup().remove(unidad);
              civActual.getGrupHash().put(unidad, dest);
              if (dest.getEdificio().getTipo() == 'C') {
                for (String clave : dest.getGrupo(unidad).getUds().keySet()) {
                  Unidad uu = dest.getGrupo(unidad).getUnidad(clave);
                  if (uu.getTipo() == 'P') {
                    uu.setSalud(uu.getSALUDMAX());
                  }
                }

              } else if (dest.getEdificio().getTipo() == 'M') {
                for (String clave : dest.getGrupo(unidad).getUds().keySet()) {
                  Unidad uu = dest.getGrupo(unidad).getUnidad(clave);
                  if (uu.getTipo() == 'S') {
                    uu.setSalud(uu.getSALUDMAX());
                  }
                }
              }
              atacarTorre(civActual);
            } else {
              System.out.println("Hay demasiada gente en el edificio");
            }
          } else {
            System.out.println("Comando incorrecto. No se puede mover al edificio");
          }

          break;
        case "ESTE":
          dest = celdaEste(c);
          if (dest != null && dest.getEdificio() != null) {
            if (c.getGrupo(unidad).getUds().size() <= dest.getEdificio().getCapacidad() - dest.getTotalUds()) {
              dest.getGrup().put(unidad, c.getGrupo(unidad));
              c.getGrup().remove(unidad);
              civActual.getGrupHash().put(unidad, dest);
              if (dest.getEdificio().getTipo() == 'C') {
                for (String clave : dest.getGrupo(unidad).getUds().keySet()) {
                  Unidad uu = dest.getGrupo(unidad).getUnidad(clave);
                  if (uu.getTipo() == 'P') {
                    uu.setSalud(uu.getSALUDMAX());
                  }
                }

              } else if (dest.getEdificio().getTipo() == 'M') {
                for (String clave : dest.getGrupo(unidad).getUds().keySet()) {
                  Unidad uu = dest.getGrupo(unidad).getUnidad(clave);
                  if (uu.getTipo() == 'S') {
                    uu.setSalud(uu.getSALUDMAX());
                  }
                }
              }
              atacarTorre(civActual);
            } else {
              System.out.println("Hay demasiada gente en el edificio");
            }
          } else {
            System.out.println("Comando incorrecto. No se puede mover al edificio");
          }
          break;
        case "OESTE":
          dest = celdaOeste(c);
          if (dest != null && dest.getEdificio() != null) {
            if (c.getGrupo(unidad).getUds().size() <= dest.getEdificio().getCapacidad() - dest.getTotalUds()) {
              dest.getGrup().put(unidad, c.getGrupo(unidad));
              c.getGrup().remove(unidad);
              civActual.getGrupHash().put(unidad, dest);
              if (dest.getEdificio().getTipo() == 'C') {
                for (String clave : dest.getGrupo(unidad).getUds().keySet()) {
                  Unidad uu = dest.getGrupo(unidad).getUnidad(clave);
                  if (uu.getTipo() == 'P') {
                    uu.setSalud(uu.getSALUDMAX());
                  }
                }

              } else if (dest.getEdificio().getTipo() == 'M') {
                for (String clave : dest.getGrupo(unidad).getUds().keySet()) {
                  Unidad uu = dest.getGrupo(unidad).getUnidad(clave);
                  if (uu.getTipo() == 'S') {
                    uu.setSalud(uu.getSALUDMAX());
                  }
                }
              }
              atacarTorre(civActual);
            } else {
              System.out.println("Hay demasiada gente en el edificio");
            }
          } else {
            System.out.println("Comando incorrecto. No se puede mover al edificio");
          }
          break;
        default:
          System.out.println("Comando incorrecto. Solo te puedes mover al NORTE, SUR, ESTE o OESTE");
          return;
      }
      if (dest != null) {
        int at = 0;
        for (String clave : dest.getGrupo(unidad).getUds().keySet()) {
          at += dest.getGrupo(unidad).getUnidad(clave).getAtaque();
        }
        dest.getEdificio().setAtaque(at + dest.getEdificio().getAtaque());
        dest.getEdificio().setDefensa(dest.getEdificio().getDefensa() + 2 * dest.getGrupo(unidad).getUds().size());
      }
    } else {
      System.out.println("Comando incorrecto. No se ha encontrado la unidad");
    }
  }

  public void moverUnidad(String unidad, String direccion) {
    Celda c, dest;
    if ((c = civActual.getUdsHash().get(unidad)) != null) {
      if (c.getUnidad(unidad) == null) {
        System.out.println("Esta unidad no se puede mover sola, está en un grupo");
        return;
      }
      switch (direccion) {
        case "NORTE":
          dest = celdaNorte(c);
          if (dest != null && dest.esTransitable()) {
            dest.getUds().put(unidad, c.getUnidad(unidad));
            c.getUds().remove(unidad);
            if (c.getEdificio() != null) {
              c.getEdificio().setAtaque(c.getEdificio().getAtaque() - dest.getUnidad(unidad).getAtaque());
              c.getEdificio().setDefensa(c.getEdificio().getDefensa() - 2);
            }
            civActual.getUdsHash().put(unidad, dest);
            if (celdaNorte(dest) != null) {
              celdaNorte(dest).setVisible(civActual, true);
            }
            if (celdaEste(dest) != null) {
              celdaEste(dest).setVisible(civActual, true);
            }
            if (celdaOeste(dest) != null) {
              celdaOeste(dest).setVisible(civActual, true);
            }
            atacarTorre(civActual);
          } else {
            System.out.println("Comando incorrecto. No se puede mover a la celda");
          }

          break;
        case "SUR":
          dest = celdaSur(c);
          if (dest != null && dest.esTransitable()) {
            dest.getUds().put(unidad, c.getUnidad(unidad));
            c.getUds().remove(unidad);
            if (c.getEdificio() != null) {
              c.getEdificio().setAtaque(c.getEdificio().getAtaque() - dest.getUnidad(unidad).getAtaque());
              c.getEdificio().setDefensa(c.getEdificio().getDefensa() - 2);
            }
            civActual.getUdsHash().put(unidad, dest);
            if (celdaSur(dest) != null) {
              celdaSur(dest).setVisible(civActual, true);
            }
            if (celdaEste(dest) != null) {
              celdaEste(dest).setVisible(civActual, true);
            }
            if (celdaOeste(dest) != null) {
              celdaOeste(dest).setVisible(civActual, true);
            }
            atacarTorre(civActual);
          } else {
            System.out.println("Comando incorrecto. No se puede mover a la celda");
          }

          break;
        case "ESTE":
          dest = celdaEste(c);
          if (dest != null && dest.esTransitable()) {
            dest.getUds().put(unidad, c.getUnidad(unidad));
            c.getUds().remove(unidad);
            if (c.getEdificio() != null) {
              c.getEdificio().setAtaque(c.getEdificio().getAtaque() - dest.getUnidad(unidad).getAtaque());
              c.getEdificio().setDefensa(c.getEdificio().getDefensa() - 2);
            }
            civActual.getUdsHash().put(unidad, dest);
            if (celdaSur(dest) != null) {
              celdaSur(dest).setVisible(civActual, true);
            }
            if (celdaEste(dest) != null) {
              celdaEste(dest).setVisible(civActual, true);
            }
            if (celdaNorte(dest) != null) {
              celdaNorte(dest).setVisible(civActual, true);
            }
            atacarTorre(civActual);
          } else {
            System.out.println("Comando incorrecto. No se puede mover a la celda");
          }
          break;
        case "OESTE":
          dest = celdaOeste(c);
          if (dest != null && dest.esTransitable()) {
            dest.getUds().put(unidad, c.getUnidad(unidad));
            c.getUds().remove(unidad);
            if (c.getEdificio() != null) {
              c.getEdificio().setAtaque(c.getEdificio().getAtaque() - dest.getUnidad(unidad).getAtaque());
              c.getEdificio().setDefensa(c.getEdificio().getDefensa() - 2);
            }
            civActual.getUdsHash().put(unidad, dest);
            if (celdaSur(dest) != null) {
              celdaSur(dest).setVisible(civActual, true);
            }
            if (celdaNorte(dest) != null) {
              celdaNorte(dest).setVisible(civActual, true);
            }
            if (celdaOeste(dest) != null) {
              celdaOeste(dest).setVisible(civActual, true);
            }
            atacarTorre(civActual);
          } else {
            System.out.println("Comando incorrecto. No se puede mover a la celda");
          }
          break;
        default:
          System.out.println("Comando incorrecto. Solo te puedes mover al NORTE, SUR, ESTE o OESTE");
          break;
      }
    } else if ((c = civActual.getGrupHash().get(unidad)) != null) {
      switch (direccion) {
        case "NORTE":
          dest = celdaNorte(c);
          if (dest != null && dest.esTransitable()) {
            Grupos gr = c.getGrupo(unidad);
            dest.getGrup().put(unidad, gr);
            c.getGrup().remove(unidad);
            if (c.getEdificio() != null) {
              int at = 0;
              for (String clave : dest.getGrupo(unidad).getUds().keySet()) {
                at += dest.getGrupo(unidad).getUnidad(clave).getAtaque();
              }
              c.getEdificio().setAtaque(c.getEdificio().getAtaque() - at);
              c.getEdificio().setDefensa(c.getEdificio().getDefensa() - 2 * dest.getGrupo(unidad).getUds().size());
            }
            civActual.getGrupHash().put(unidad, dest);
            if (celdaNorte(dest) != null) {
              celdaNorte(dest).setVisible(civActual, true);
            }
            if (celdaEste(dest) != null) {
              celdaEste(dest).setVisible(civActual, true);
            }
            if (celdaOeste(dest) != null) {
              celdaOeste(dest).setVisible(civActual, true);
            }
            for (String clave : gr.getUds().keySet()) {
              civActual.getUdsHash().put(clave, dest);
            }
            atacarTorre(civActual);
          } else {
            System.out.println("Comando incorrecto. No se puede mover a la celda");
          }

          break;
        case "SUR":
          dest = celdaSur(c);
          if (dest != null && dest.esTransitable()) {
            Grupos gr = c.getGrupo(unidad);
            dest.getGrup().put(unidad, gr);
            c.getGrup().remove(unidad);
            if (c.getEdificio() != null) {
              int at = 0;
              for (String clave : dest.getGrupo(unidad).getUds().keySet()) {
                at += dest.getUnidad(clave).getAtaque();
              }
              c.getEdificio().setAtaque(c.getEdificio().getAtaque() - at);
              c.getEdificio().setDefensa(c.getEdificio().getDefensa() - 2 * dest.getGrupo(unidad).getUds().size());
            }
            civActual.getGrupHash().put(unidad, dest);
            if (celdaSur(dest) != null) {
              celdaSur(dest).setVisible(civActual, true);
            }
            if (celdaEste(dest) != null) {
              celdaEste(dest).setVisible(civActual, true);
            }
            if (celdaOeste(dest) != null) {
              celdaOeste(dest).setVisible(civActual, true);
            }
            for (String clave : gr.getUds().keySet()) {
              civActual.getUdsHash().put(clave, dest);
            }
            atacarTorre(civActual);
          } else {
            System.out.println("Comando incorrecto. No se puede mover a la celda");
          }

          break;
        case "ESTE":
          dest = celdaEste(c);
          if (dest != null && dest.esTransitable()) {
            Grupos gr = c.getGrupo(unidad);
            dest.getGrup().put(unidad, gr);
            c.getGrup().remove(unidad);
            if (c.getEdificio() != null) {
              int at = 0;
              for (String clave : dest.getGrupo(unidad).getUds().keySet()) {
                at += dest.getUnidad(clave).getAtaque();
              }
              c.getEdificio().setAtaque(c.getEdificio().getAtaque() - at);
              c.getEdificio().setDefensa(c.getEdificio().getDefensa() - 2 * dest.getGrupo(unidad).getUds().size());
            }
            civActual.getGrupHash().put(unidad, dest);
            if (celdaSur(dest) != null) {
              celdaSur(dest).setVisible(civActual, true);
            }
            if (celdaEste(dest) != null) {
              celdaEste(dest).setVisible(civActual, true);
            }
            if (celdaNorte(dest) != null) {
              celdaNorte(dest).setVisible(civActual, true);
            }
            for (String clave : gr.getUds().keySet()) {
              civActual.getUdsHash().put(clave, dest);
            }
            atacarTorre(civActual);
          } else {
            System.out.println("Comando incorrecto. No se puede mover a la celda");
          }
          break;
        case "OESTE":
          dest = celdaOeste(c);
          if (dest != null && dest.esTransitable()) {
            Grupos gr = c.getGrupo(unidad);
            dest.getGrup().put(unidad, gr);
            c.getGrup().remove(unidad);
            if (c.getEdificio() != null) {
              int at = 0;
              for (String clave : dest.getGrupo(unidad).getUds().keySet()) {
                at += dest.getUnidad(clave).getAtaque();
              }
              c.getEdificio().setAtaque(c.getEdificio().getAtaque() - at);
              c.getEdificio().setDefensa(c.getEdificio().getDefensa() - 2 * dest.getGrupo(unidad).getUds().size());
            }
            civActual.getGrupHash().put(unidad, dest);
            if (celdaSur(dest) != null) {
              celdaSur(dest).setVisible(civActual, true);
            }
            if (celdaNorte(dest) != null) {
              celdaNorte(dest).setVisible(civActual, true);
            }
            if (celdaOeste(dest) != null) {
              celdaOeste(dest).setVisible(civActual, true);
            }
            for (String clave : gr.getUds().keySet()) {
              civActual.getUdsHash().put(clave, dest);
            }
            atacarTorre(civActual);
          } else {
            System.out.println("Comando incorrecto. No se puede mover a la celda");
          }
          break;
        default:
          System.out.println("Comando incorrecto. Solo te puedes mover al NORTE, SUR, ESTE o OESTE");
          break;
      }
    } else {
      System.out.println("Comando incorrecto. No se ha encontrado la unidad");
    }
  }

  public void describir(String descrito) {
    Celda celd = civActual.getUdsHash().get(descrito);
    if (celd != null) {
      if (!celd.getUds().containsKey(descrito)) {
        for (String clave : celd.getGrup().keySet()) {
          if (celd.getGrupo(clave).getUds().containsKey(descrito)) {
            System.out.println(descrito + " --En el grupo " + clave);
            return;
          }
        }
        System.out.println("error -- no está en ningún lado");
        return;
      }
      Unidad ud = celd.getUds().get(descrito);
      int saludmax, salud, porcsalud;
      saludmax = ud.getSALUDMAX();
      salud = ud.getSalud();
      porcsalud = (int)(((float)salud / (float)saludmax) * 10f);
      System.out.print("Salud ♥ ");
      int i;
      for (i = 0; i < porcsalud; i++) {
        System.out.print("\u001B[31m█\u001B[0m");
      }
      for (; i < 10; i++) {
        System.out.print("\u001B[1;30m░\u001B[0m");
      }
      System.out.println(" " + salud + '/' + saludmax);
      System.out.println("Armadura ♦ " + ud.getArmadura());
      System.out.println("Ataque → " + ud.getAtaque());
      if (ud.getR() != null && ud.getR().getCantidad() > 0) {
        switch (ud.getR().getTipo()) {
          case 'M':
            System.out.print("Madera: ");
            break;
          case 'P':
            System.out.print("Piedra: ");
            break;
          case 'C':
            System.out.print("Comida: ");
            break;
        }
        System.out.println(ud.getR().getCantidad());
      }
    }
    celd = civActual.getEdisHash().get(descrito);
    if (celd != null) {
      Edificio ed = celd.getEdificio();
      int saludmax, salud, porcsalud;
      saludmax = ed.getSaludMax();
      salud = ed.getSalud();
      porcsalud = (int)(((float)salud / (float)saludmax) * 10f);
      System.out.print("Salud ♥ ");
      int i;
      for (i = 0; i < porcsalud; i++) {
        System.out.print("\u001B[31m█\u001B[0m");
      }
      for (; i < 10; i++) {
        System.out.print("\u001B[1;30m░\u001B[0m");
      }
      System.out.println(" " + salud + '/' + saludmax);
      System.out.println("Defensa ♦ " + ed.getDefensa());
      System.out.println("Ataque → " + ed.getAtaque());
      System.out.println("Aforo " + celd.getTotalUds() + "/" + ed.getCapacidad());
      if (ed.getTipo() == 'C') {
        System.out.println("Madera \u001B[32m♠\u001B[0m " + civActual.getMadera());
        System.out.println("Piedra \u001B[1;30m▓\u001B[0m " + civActual.getPiedra());
        System.out.println("Comida \u001B[1;32m☁\u001B[0m " + civActual.getComida());

      }
    }
    celd = civActual.getGrupHash().get(descrito);
    if (celd != null) {
      Grupos gr = celd.getGrupo(descrito);
      System.out.println(descrito + ":");
      System.out.println("Ataque conjunto:   "+gr.getAtaque());
      System.out.println("Armadura conjunta: "+gr.getArmadura());
      for (String clave : gr.getUds().keySet()) {
        Unidad ud = gr.getUnidad(clave);
        if (ud != null) {
          int saludmax, salud, porcsalud;
          saludmax = ud.getSALUDMAX();
          salud = ud.getSalud();
          System.out.print('\t');
          System.out.println(ud.getNombre());
          porcsalud = (int)(((float)salud / (float)saludmax) * 10f);
          System.out.print("Salud    ♥ ");
          int i;
          for (i = 0; i < porcsalud; i++) {
            System.out.print("\u001B[31m█\u001B[0m");
          }
          for (; i < 10; i++) {
            System.out.print("\u001B[1;30m░\u001B[0m");
          }
          System.out.println("  " + salud + '/' + saludmax);
          System.out.println("Armadura ♦ " + ud.getArmadura());
          System.out.println("Ataque   → " + ud.getAtaque());
          if (ud.getR() != null && ud.getR().getCantidad() > 0) {
            switch (ud.getR().getTipo()) {
              case 'M':
                System.out.print("Madera:  ");
                break;
              case 'P':
                System.out.print("Piedra:  ");
                break;
              case 'C':
                System.out.print("Comida:  ");
                break;
            }
            System.out.println(ud.getR().getCantidad());
          }
        }
      }
    }

  }

  public void mirar(int x, int y) {
    Celda celda = cel[x][y];
    if (celda.esVisible(civActual)) {
      switch (celda.getFR().getTipo()) {
        case 'b':
          System.out.println("Bosque:   madera disponible: " + celda.getFR().getRecurso().getCantidad() + " unidades");
          break;
        case 'c':
          System.out.println("Cantera:  piedra disponible: " + celda.getFR().getRecurso().getCantidad() + " unidades");
          break;
        case 'a':
          System.out.println("Arbusto:  comida disponible: " + celda.getFR().getRecurso().getCantidad() + " unidades");
          break;
        case 'p':
          System.out.println("Pradera");
          if (celda.getEdificio() != null) {
            System.out.println("    Edificio: " + celda.getEdificio().getNombre());
          }
          if (!celda.getUds().isEmpty()) {
            System.out.print("    Personajes: ");
            for (String clave : celda.getUds().keySet()) {
              System.out.print(clave + ", ");
            }
            System.out.println();
          }
          if (!celda.getGrup().isEmpty()) {
            System.out.print("    Grupos: ");
            for (String clave : celda.getGrup().keySet()) {
              System.out.print(clave + ", ");
            }
            System.out.println();
          }
      }
    } else {
      System.out.println("Celda no visible");
    }

  }

  public void atacar(String ata, String dir) {
    Celda c, dest;
    if ((c = civActual.getUdsHash().get(ata)) != null) {
      if (c.getUnidad(ata) == null) {
        System.out.println("Esta unidad no puede atacar porque está en un grupo");
        return;
      }
      Unidad u = c.getUnidad(ata);

      switch (dir) {
        case "NORTE":
          dest = celdaNorte(c);
          break;
        case "SUR":
          dest = celdaSur(c);
          break;
        case "ESTE":
          dest = celdaEste(c);
          break;
        case "OESTE":
          dest = celdaOeste(c);
          break;
        default:
          System.out.println("Comando incorrecto. Solo puedes almacenar al NORTE, SUR, ESTE o OESTE de un paisano");
          return;
      }
      if (dest != null) {
        if (dest.getFR().getTipo() == 'p') {
          if (!dest.getUds().isEmpty()) {
            Unidad ud = dest.getUds().get((String) dest.getUds().keySet().toArray()[0]);
            if (ud.getCodCiv() != civActual.getCodigo()) {
              dest.serAtacado('U', u.getAtaque());
              atacarTorre(civActual);
            }
          } else if (!dest.getGrup().isEmpty()) {
            Grupos gr = dest.getGrup().get((String) dest.getGrup().keySet().toArray()[0]);
            if (gr.getCivi() != civActual.getCodigo()) {
              dest.serAtacado('U', u.getAtaque());
              atacarTorre(civActual);
            }
          } else if (dest.getEdificio() != null) {
            Edificio e = dest.getEdificio();
            if (e.getCodCiv() != civActual.getCodigo()) {
              dest.serAtacado('U', u.getAtaque());
              atacarTorre(civActual);
            }
          }
        }
      }
    } else if ((c = civActual.getGrupHash().get(ata)) != null) {
      Grupos g = c.getGrupo(ata);
      switch (dir) {
        case "NORTE":
          dest = celdaNorte(c);
          break;
        case "SUR":
          dest = celdaSur(c);
          break;
        case "ESTE":
          dest = celdaEste(c);
          break;
        case "OESTE":
          dest = celdaOeste(c);
          break;
        default:
          System.out.println("Comando incorrecto. Solo puedes almacenar al NORTE, SUR, ESTE o OESTE de un paisano");
          return;
      }
      if (dest != null) {
        if (dest.getFR().getTipo() == 'p') {
          if (!dest.getUds().isEmpty()) {
            Unidad ud = dest.getUds().get((String) dest.getUds().keySet().toArray()[0]);
            if (ud.getCodCiv() != civActual.getCodigo()) {
              dest.serAtacado('G', g.getAtaque());
              atacarTorre(civActual);
            }
          } else if (!dest.getGrup().isEmpty()) {
            Grupos gr = dest.getGrup().get((String) dest.getGrup().keySet().toArray()[0]);
            if (gr.getCivi() != civActual.getCodigo()) {
              dest.serAtacado('G', g.getAtaque());
              atacarTorre(civActual);
            }
          } else if (dest.getEdificio() != null) {
            Edificio e = dest.getEdificio();
            if (e.getCodCiv() != civActual.getCodigo()) {
              dest.serAtacado('G', g.getAtaque());
              atacarTorre(civActual);
            }
          }
        }
      }
    } else if ((c = civActual.getEdisHash().get(ata)) != null) {
      Edificio e = c.getEdificio();
      switch (dir) {
        case "NORTE":
          dest = celdaNorte(c);
          break;
        case "SUR":
          dest = celdaSur(c);
          break;
        case "ESTE":
          dest = celdaEste(c);
          break;
        case "OESTE":
          dest = celdaOeste(c);
          break;
        default:
          System.out.println("Comando incorrecto. Solo puedes almacenar al NORTE, SUR, ESTE o OESTE de un paisano");
          return;
      }
      if (dest != null && e.getAtaque() > 0) {
        if (dest.getFR().getTipo() == 'p') {
          if (!dest.getUds().isEmpty()) {
            Unidad ud = dest.getUds().get((String) dest.getUds().keySet().toArray()[0]);
            if (ud.getCodCiv() != civActual.getCodigo()) {
              dest.serAtacado('T', e.getAtaque());
              atacarTorre(civActual);
            }
          } else if (!dest.getGrup().isEmpty()) {
            Grupos gr = dest.getGrup().get((String) dest.getGrup().keySet().toArray()[0]);
            if (gr.getCivi() != civActual.getCodigo()) {
              dest.serAtacado('T', e.getAtaque());
              atacarTorre(civActual);
            }
          } else if (dest.getEdificio() != null) {
            Edificio ed = dest.getEdificio();
            if (ed.getCodCiv() != civActual.getCodigo()) {
              dest.serAtacado('T', e.getAtaque());
              atacarTorre(civActual);
            }
          }
        }
      }
    } else {
      System.out.println("No se encontró la unidad");
    }
  }

  public void construirEdificio(String cons, String edi, String dir) {
    Celda c;
    if ((c = civActual.getUdsHash().get(cons)) != null && cons.charAt(0) == 'P') {
      if (c.getUnidad(cons) == null) {
        System.out.println("Esta unidad no puede construir porque está en un grupo");
        return;
      }
      char tipo;
      switch (edi) {
        case "casa":
          tipo = 'H';
          break;
        case "cuartel":
          tipo = 'M';
          break;
        case "ciudadela":
          tipo = 'C';
          break;
        case "torre":
          tipo = 'T';
          break;
        default:
          System.out.println("Comando incorrecto. Solo puedes construir casas, cuarteles, ciudadelas o torres");
          return;
      }
      Celda dest;
      Unidad pai;
      pai = c.getUds().get(cons);
      switch (dir) {
        case "NORTE":
          dest = celdaNorte(c);
          if (dest != null && dest.esTransitable()) {
            if (dest.getUds().isEmpty()) {
              dest.anadirEdificio(tipo, pai, civActual);
              civActual.getEdisHash().put(dest.getEdificio().getNombre(), dest);
              if (celdaNorte(dest) != null) {
                celdaNorte(dest).setVisible(civActual, true);
              }
              if (celdaEste(dest) != null) {
                celdaEste(dest).setVisible(civActual, true);
              }
              if (celdaOeste(dest) != null) {
                celdaOeste(dest).setVisible(civActual, true);
              }
              atacarTorre(civActual);
            } else {
              System.out.println("No se puede construir en esa celda, hay personajes en ella");
            }
          } else {
            System.out.println("No se puede construir en esa celda");
          }
          break;
        case "SUR":
          dest = celdaSur(c);
          if (dest != null && dest.esTransitable()) {
            if (dest.getUds().isEmpty()) {
              dest.anadirEdificio(tipo, pai, civActual);
              civActual.getEdisHash().put(dest.getEdificio().getNombre(), dest);
              if (celdaSur(dest) != null) {
                celdaSur(dest).setVisible(civActual, true);
              }
              if (celdaEste(dest) != null) {
                celdaEste(dest).setVisible(civActual, true);
              }
              if (celdaOeste(dest) != null) {
                celdaOeste(dest).setVisible(civActual, true);
              }
              atacarTorre(civActual);
            } else {
              System.out.println("No se puede construir en esa celda, hay personajes en ella");
            }
          } else {
            System.out.println("No se puede construir en esa celda");
          }
          break;

        case "ESTE":
          dest = celdaEste(c);
          if (dest != null && dest.esTransitable()) {
            if (dest.getUds().isEmpty()) {
              dest.anadirEdificio(tipo, pai, civActual);
              civActual.getEdisHash().put(dest.getEdificio().getNombre(), dest);
              if (celdaNorte(dest) != null) {
                celdaNorte(dest).setVisible(civActual, true);
              }
              if (celdaSur(dest) != null) {
                celdaSur(dest).setVisible(civActual, true);
              }
              if (celdaEste(dest) != null) {
                celdaEste(dest).setVisible(civActual, true);
              }
              atacarTorre(civActual);
            } else {
              System.out.println("No se puede construir en esa celda, hay personajes en ella");
            }
          } else {
            System.out.println("No se puede construir en esa celda");
          }
          break;
        case "OESTE":
          dest = celdaOeste(c);
          if (dest != null && dest.esTransitable()) {
            if (dest.getUds().isEmpty()) {
              dest.anadirEdificio(tipo, pai, civActual);
              civActual.getEdisHash().put(dest.getEdificio().getNombre(), dest);
              if (celdaNorte(dest) != null) {
                celdaNorte(dest).setVisible(civActual, true);
              }
              if (celdaSur(dest) != null) {
                celdaSur(dest).setVisible(civActual, true);
              }
              if (celdaOeste(dest) != null) {
                celdaOeste(dest).setVisible(civActual, true);
              }
              atacarTorre(civActual);
            } else {
              System.out.println("No se puede construir en esa celda, hay personajes en ella");
            }
          } else {
            System.out.println("No se puede construir en esa celda");
          }
          break;
        default:
          System.out.println("Comando incorrecto. Solo puedes construir al NORTE, SUR, ESTE o OESTE de un paisano");
          break;
      }

    } else {
      System.out.println("Comando incorrecto. Solo puedes construir con paisanos disponibles");
    }
  }

  public void repararEdificio(String rep, String dir) {
    Celda c;
    if ((c = civActual.getUdsHash().get(rep)) != null && rep.charAt(0) == 'P') {
      if (c.getUnidad(rep) == null) {
        System.out.println("Esta unidad no puede reparar porque está en un grupo");
        return;
      }
      Unidad pai;
      pai = c.getUnidad(rep);
      Celda dest;
      Edificio ed;
      switch (dir) {
        case "NORTE":
          dest = celdaNorte(c);
          if (dest != null && dest.getEdificio() != null && dest.getEdificio().getSalud() != dest.getEdificio().getSaludMax()) {
            ed = dest.getEdificio();
            pai.repararEdificio(ed, civActual);
            atacarTorre(civActual);
          } else {
            System.out.println("Comando incorrecto. Solo puedes edificios disponibles que hayan sufrido daños");
          }
          break;
        case "SUR":
          dest = celdaSur(c);
          if (dest != null && dest.getEdificio() != null && dest.getEdificio().getSalud() != dest.getEdificio().getSaludMax()) {
            ed = dest.getEdificio();
            pai.repararEdificio(ed, civActual);
            atacarTorre(civActual);
          } else {
            System.out.println("Comando incorrecto. Solo puedes edificios disponibles que hayan sufrido daños");
          }
          break;
        case "ESTE":
          dest = celdaEste(c);
          if (dest != null && dest.getEdificio() != null && dest.getEdificio().getSalud() != dest.getEdificio().getSaludMax()) {
            ed = dest.getEdificio();
            pai.repararEdificio(ed, civActual);
            atacarTorre(civActual);
          } else {
            System.out.println("Comando incorrecto. Solo puedes edificios disponibles que hayan sufrido daños");
          }
          break;
        case "OESTE":
          dest = celdaOeste(c);
          if (dest != null && dest.getEdificio() != null && dest.getEdificio().getSalud() != dest.getEdificio().getSaludMax()) {
            ed = dest.getEdificio();
            pai.repararEdificio(ed, civActual);
            atacarTorre(civActual);
          } else {
            System.out.println("Comando incorrecto. Solo puedes edificios disponibles que hayan sufrido daños");
          }
          break;
        default:
          System.out.println("Comando incorrecto. Solo puedes construir al NORTE, SUR, ESTE o OESTE de un paisano");
          break;
      }
    } else {
      System.out.println("Comando incorrecto. Solo puedes reparar con paisanos disponibles");
    }

  }

  public void crear(String edi, String uni) {
    Celda c;
    if ((c = civActual.getEdisHash().get(edi)) != null) {
      Edificio ed;
      ed = c.getEdificio();
      Celda dest;
      if (celdaNorte(c).esTransitable()) {
        dest = celdaNorte(c);
      } else if (celdaSur(c).esTransitable()) {
        dest = celdaSur(c);
      } else if (celdaEste(c).esTransitable()) {
        dest = celdaEste(c);
      } else if (celdaOeste(c).esTransitable()) {
        dest = celdaOeste(c);
      } else {
        dest = null;
      }
      Unidad un;
      if (ed.getTipo() == 'M' && "soldado".equals(uni)) {
        if (civActual.getComida() >= 80) {
          if (civActual.getUdsHash().size() < civActual.getPobMax()) {
            if (dest != null) {
              civActual.modComida(-80);
              un = new Unidad('S', civActual);
              dest.anhadirUnidad(un);
              civActual.getUdsHash().put(un.getNombre(), dest);
              if (celdaNorte(dest) != null) {
                celdaNorte(dest).setVisible(civActual, true);
              }
              if (celdaSur(dest) != null) {
                celdaSur(dest).setVisible(civActual, true);
              }
              if (celdaEste(dest) != null) {
                celdaEste(dest).setVisible(civActual, true);
              }
              if (celdaOeste(dest) != null) {
                celdaOeste(dest).setVisible(civActual, true);
              }
              atacarTorre(civActual);
            } else {
              System.out.println("No tienes espacio al lado del cuartel para desplegar soldados");
            }
          } else {
            System.out.println("Has alcanzado el máximo de población, construye más casas");
          }
        } else {
          System.out.println("No tienes suficiente comida para crear un soldado");
        }
      } else if (ed.getTipo() == 'C' && "paisano".equals(uni)) {
        if (civActual.getComida() >= 50) {
          if (civActual.getUdsHash().size() < civActual.getPobMax()) {
            if (dest != null) {
              civActual.modComida(-50);
              un = new Unidad('P', civActual);
              dest.anhadirUnidad(un);
              civActual.getUdsHash().put(un.getNombre(), dest);
              if (celdaNorte(dest) != null) {
                celdaNorte(dest).setVisible(civActual, true);
              }
              if (celdaSur(dest) != null) {
                celdaSur(dest).setVisible(civActual, true);
              }
              if (celdaEste(dest) != null) {
                celdaEste(dest).setVisible(civActual, true);
              }
              if (celdaOeste(dest) != null) {
                celdaOeste(dest).setVisible(civActual, true);
              }
              atacarTorre(civActual);
            } else {
              System.out.println("No tienes espacio al lado de la ciudadela para crear paisanos");
            }
          } else {
            System.out.println("Has alcanzado el máximo de población, construye más casas");
          }
        } else {
          System.out.println("No tienes suficiente comida para crear un paisano");
        }
      } else {
        System.out.println("Comando incorrecto. Solo puedes crear paisanos en tu ciudadela o soldados en tus cuarteles");
      }
    } else {
      System.out.println("Comando incorrecto. Solo puedes crear en tu ciudadela o cuarteles disponibles");
    }

  }

  public void recolectar(String nomPais, String dir, boolean permitir) {
    Celda celdaorigen;
    if ((celdaorigen = civActual.getUdsHash().get(nomPais)) != null) {
      Unidad recolector = null;
      if (nomPais.charAt(0) != 'P') {
        System.out.println("Esta unidad no puede recolectar porque es un soldado");
        return;
      }
      if (celdaorigen.getUnidad(nomPais) == null) {
        if (!permitir) {
          System.out.println("Esta unidad no puede recolectar sola porque estÃ¡ en un grupo");
          return;
        } else {
          Grupos gr;
          for (String clave : celdaorigen.getGrup().keySet()) {
            gr = celdaorigen.getGrupo(clave);
            if ((recolector = gr.getUnidad(nomPais)) != null) {
              break;
            }
          }
        }

      } else {
        recolector = celdaorigen.getUds().get(nomPais);
      }
      Celda dest;
      int cant1;
      //if (recolector != null && recolector.getTipo() == 'P') {
      switch (dir) {
        case "NORTE":
          dest = celdaNorte(celdaorigen);
          if (dest != null && dest.getFR().getTipo() != 'p' && dest.getFR().getTipo() != 'E' && dest.getFR().getRecurso().getCantidad() > 0) {
            if (recolector.getR() != null) {
              cant1 = recolector.getCapacidadRecurso() - recolector.getR().getCantidad();
            } else {
              cant1 = recolector.getCapacidadRecurso();
            }
            char tipo = dest.getFR().getRecurso().getTipo();
            int cant2 = dest.getFR().modCantidad((-1) * cant1);
            recolector.modR(tipo, (-1) * cant2);
          } else {
            if (permitir) {
              return;
            }
            System.out.println("Comando incorrecto. La celda destino no contiene recursos");
          }
          break;
        case "SUR":
          dest = celdaSur(celdaorigen);
          if (dest != null && dest.getFR().getTipo() != 'p' && dest.getFR().getTipo() != 'E' && dest.getFR().getRecurso().getCantidad() > 0) {
            if (recolector.getR() != null) {
              cant1 = recolector.getCapacidadRecurso() - recolector.getR().getCantidad();
            } else {
              cant1 = recolector.getCapacidadRecurso();
            }
            char tipo = dest.getFR().getRecurso().getTipo();
            int cant2 = dest.getFR().modCantidad((-1) * cant1);
            recolector.modR(tipo, (-1) * cant2);
          } else {
            if (permitir) {
              return;
            }
            System.out.println("Comando incorrecto. La celda destino no contiene recursos");
          }
          break;
        case "ESTE":
          dest = celdaEste(celdaorigen);
          if (dest != null && dest.getFR().getTipo() != 'p' && dest.getFR().getTipo() != 'E' && dest.getFR().getRecurso().getCantidad() > 0) {
            if (recolector.getR() != null) {
              cant1 = recolector.getCapacidadRecurso() - recolector.getR().getCantidad();
            } else {
              cant1 = recolector.getCapacidadRecurso();
            }
            char tipo = dest.getFR().getRecurso().getTipo();
            int cant2 = dest.getFR().modCantidad((-1) * cant1);
            recolector.modR(tipo, (-1) * cant2);
          } else {
            if (permitir) {
              return;
            }
            System.out.println("Comando incorrecto. La celda destino no contiene recursos");
          }
          break;
        case "OESTE":
          dest = celdaOeste(celdaorigen);
          if (dest != null && dest.getFR().getTipo() != 'p' && dest.getFR().getTipo() != 'E' && dest.getFR().getRecurso().getCantidad() > 0) {
            if (recolector.getR() != null) {
              cant1 = recolector.getCapacidadRecurso() - recolector.getR().getCantidad();
            } else {
              cant1 = recolector.getCapacidadRecurso();
            }
            char tipo = dest.getFR().getRecurso().getTipo();
            int cant2 = dest.getFR().modCantidad((-1) * cant1);
            recolector.modR(tipo, (-1) * cant2);
          } else {
            if (permitir) {
              return;
            }
            System.out.println("Comando incorrecto. La celda destino no contiene recursos");
          }
          break;
        default:
          System.out.println("Comando incorrecto. Solo puedes recolectar al NORTE, SUR, ESTE o OESTE de un paisano");
      }
      //}
    } else if ((celdaorigen = civActual.getGrupHash().get(nomPais)) != null) {
      Grupos grp = celdaorigen.getGrupo(nomPais);
      Celda dest;
      switch (dir) {
        case "NORTE":
          dest = celdaNorte(celdaorigen);
          break;
        case "SUR":
          dest = celdaSur(celdaorigen);
          break;
        case "ESTE":
          dest = celdaEste(celdaorigen);
          break;
        case "OESTE":
          dest = celdaOeste(celdaorigen);
          break;
        default:
          System.out.println("Comando incorrecto. Solo puedes recolectar al NORTE, SUR, ESTE o OESTE de un paisano");
          return;

      }
      if (grp.getNumSol() == 0) {
        for (String clave : grp.getUds().keySet()) {
          if (dest != null && dest.getFR().getTipo() != 'p' && dest.getFR().getTipo() != 'E' && dest.getFR().getRecurso().getCantidad() > 0) {
            recolectar(clave, dir, true);
          } else {
            System.out.println("La celda no contiene recursos");
            break;
          }
        }
      } else {
        System.out.println("El grupo no puede recolectar porque tiene soldados dentro");
      }

    } else {
      System.out.println("Comando incorrecto");
    }

  }

  public void almacenar(String nomPais, String dir, boolean permitir) {
    Celda celdaorigen;
    if ((celdaorigen = civActual.getUdsHash().get(nomPais)) != null) {
      Unidad recolector = null;
      if (nomPais.charAt(0) != 'P') {
        System.out.println("Esta unidad no puede almacenar porque es un soldado");
        return;
      }
      if (celdaorigen.getUnidad(nomPais) == null) {
        if (!permitir) {
          System.out.println("Esta unidad no puede almacenar sola porque estÃ¡ en un grupo");
          return;
        } else {
          Grupos gr;
          for (String clave : celdaorigen.getGrup().keySet()) {
            gr = celdaorigen.getGrupo(clave);
            if ((recolector = gr.getUnidad(nomPais)) != null) {
              break;
            }
          }
        }

      } else {
        recolector = celdaorigen.getUds().get(nomPais);
      }

      //if (recolector != null && recolector.getTipo() == 'P') {
      Celda dest;
      if (recolector.getR() == null) {
        return;
      }
      switch (dir) {
        case "NORTE":
          dest = celdaNorte(celdaorigen);
          if (dest != null && dest.getEdificio().getTipo() == 'C') {
            switch (recolector.getR().getTipo()) {
              case 'M':
                recolector.modR('M', (-1) * civActual.modMadera(recolector.getR().getCantidad()));
                break;
              case 'P':
                recolector.modR('P', (-1) * civActual.modPiedra(recolector.getR().getCantidad()));
                break;
              case 'C':
                recolector.modR('C', (-1) * civActual.modComida(recolector.getR().getCantidad()));
                break;
            }
          } else {
            System.out.println("Comando incorrecto. Solo puedes almacenar en ciudadelas");
          }
          break;
        case "SUR":
          dest = celdaSur(celdaorigen);
          if (dest != null && dest.getEdificio().getTipo() == 'C') {
            switch (recolector.getR().getTipo()) {
              case 'M':
                recolector.modR('M', (-1) * civActual.modMadera(recolector.getR().getCantidad()));
                break;
              case 'P':
                recolector.modR('P', (-1) * civActual.modPiedra(recolector.getR().getCantidad()));
                break;
              case 'C':
                recolector.modR('C', (-1) * civActual.modComida(recolector.getR().getCantidad()));
                break;
            }
          } else {
            System.out.println("Comando incorrecto. Solo puedes almacenar en ciudadelas");
          }
          break;
        case "ESTE":
          dest = celdaEste(celdaorigen);
          if (dest != null && dest.getEdificio().getTipo() == 'C') {
            switch (recolector.getR().getTipo()) {
              case 'M':
                recolector.modR('M', (-1) * civActual.modMadera(recolector.getR().getCantidad()));
                break;
              case 'P':
                recolector.modR('P', (-1) * civActual.modPiedra(recolector.getR().getCantidad()));
                break;
              case 'C':
                recolector.modR('C', (-1) * civActual.modComida(recolector.getR().getCantidad()));
                break;
            }
          } else {
            System.out.println("Comando incorrecto. Solo puedes almacenar en ciudadelas");
          }
          break;
        case "OESTE":
          dest = celdaOeste(celdaorigen);
          if (dest != null && dest.getEdificio().getTipo() == 'C') {
            switch (recolector.getR().getTipo()) {
              case 'M':
                recolector.modR('M', (-1) * civActual.modMadera(recolector.getR().getCantidad()));
                break;
              case 'P':
                recolector.modR('P', (-1) * civActual.modPiedra(recolector.getR().getCantidad()));
                break;
              case 'C':
                recolector.modR('C', (-1) * civActual.modComida(recolector.getR().getCantidad()));
                break;
            }
          } else {
            System.out.println("Comando incorrecto. Solo puedes almacenar en ciudadelas");
          }
          break;
        default:
          System.out.println("Comando incorrecto. Solo puedes almacenar al NORTE, SUR, ESTE o OESTE de un paisano");
          break;
      }
      //}
    } else if ((celdaorigen = civActual.getGrupHash().get(nomPais)) != null) {
      Grupos grp = celdaorigen.getGrupo(nomPais);
      Celda dest;
      switch (dir) {
        case "NORTE":
          dest = celdaNorte(celdaorigen);
          break;
        case "SUR":
          dest = celdaSur(celdaorigen);
          break;
        case "ESTE":
          dest = celdaEste(celdaorigen);
          break;
        case "OESTE":
          dest = celdaOeste(celdaorigen);
          break;
        default:
          System.out.println("Comando incorrecto. Solo puedes almacenar al NORTE, SUR, ESTE o OESTE de un paisano");
          return;
      }
      if (grp.getNumSol() == 0) {
        if (dest != null && dest.getEdificio().getTipo() == 'C') {
        } else {
          System.out.println("La celda no contiene una ciudadela");

        }
        for (String clave : grp.getUds().keySet()) {

          almacenar(clave, dir, true);
        }
      } else {
        System.out.println("El grupo no puede almacenar porque tiene soldados dentro");
      }

    } else {
      System.out.println("Comando incorrecto");
    }
  }

  public void imprimirCivAct() {
    System.out.println("La civilización activa es: " + civActual.getNombre());
  }

  public void cambiarCiv(String nuevaCiv) {
    if (hashCiv.containsKey(nuevaCiv)) {
      if (hashCiv.get(nuevaCiv).perdedor() == false) {
        civActual = hashCiv.get(nuevaCiv);
      } else {
        System.out.println("Esta civilización ha perdido");
      }
    } else {
      System.out.println("Esta civilización no existe");
    }
  }

  public void listarCiv() {
    for (String clave : hashCiv.keySet()) {
      Civilizacion civ = hashCiv.get(clave);
      System.out.println(civ.getCodigo() + " - " + civ.getNombre());
    }
  }

  public void listarGrup() {
    for (String clave : civActual.getGrupHash().keySet()) {
      Celda celd = civActual.getGrupHash().get(clave);
      System.out.println(clave + " (" + celd.getY() + "," + celd.getX() + ")");
    }
  }

  public void agrupar(int x, int y) {
    Celda celda = cel[x][y];
    Grupos gr = celda.agrupar();
    if (gr != null) {
      civActual.getGrupHash().put(gr.getNombre(), celda);
      atacarTorre(civActual);
    }
  }

  public void desagrupar(String nom) {
    Celda celda = civActual.getGrupHash().get(nom);
    if (celda != null) {
      celda.desagrupar(nom);
      civActual.getGrupHash().remove(nom);
      atacarTorre(civActual);
    } else {
      System.out.println("El grupo no existe");
    }
  }

  public void desligar(String nom, String grup) {
    Celda celd = civActual.getUdsHash().get(nom);
    if (celd != null) {
      boolean b = celd.desligar(nom, grup);
      if (b == true) {
        desagrupar(grup);
      } else {
        atacarTorre(civActual);
      }
    } else {
      System.out.println("No se ha encontrado la unidad");
    }
  }

  public boolean ganador() {
    boolean ret = true;
    for (String clave : hashCiv.keySet()) {
      Civilizacion civ = hashCiv.get(clave);
      if (civ.getCodigo() != civActual.getCodigo()) {
        for (String clave2 : civ.getEdisHash().keySet()) {
          if (civ.getEdisHash().get(clave2).getEdificio().getTipo() == 'C') {
            ret = false;
          }
        }
      }
    }
    return ret;
  }
  public void atacarTorre(Civilizacion civi) {
        Civilizacion c;
        for (String clave : hashCiv.keySet()) {
            if (!clave.equals(civi.getNombre())) {
                c = hashCiv.get(clave);
                Edificio ed;
                Celda ce, celAd;
                for (String edis : c.getEdisHash().keySet()) {
                    if (edis.charAt(0) == 'T') {
                        ce = c.getEdisHash().get(edis);
                        ed = ce.getEdificio();
                        celAd = celdaNorte(ce);
                        if (celAd != null && celAd.getEdificio()==null) {
                            if (celAd.getFR().getTipo() == 'p') {
                                if (!celAd.getUds().isEmpty()) {
                                    Unidad ud = celAd.getUds().get((String) celAd.getUds().keySet().toArray()[0]);
                                    if (ud.getCodCiv() == civActual.getCodigo()) {
                                        celAd.serAtacado('T', ed.getAtaque());
                                        System.out.println("Una torre te ha atacado");
                                    }
                                } else if (!celAd.getGrup().isEmpty()) {
                                    Grupos gr = celAd.getGrup().get((String) celAd.getGrup().keySet().toArray()[0]);
                                    if (gr.getCivi() == civActual.getCodigo()) {
                                        celAd.serAtacado('T', ed.getAtaque());
                                        System.out.println("Una torre te ha atacado");
                                    }
                                }
                            }
                        }
                        celAd = celdaSur(ce);
                        if (celAd != null && celAd.getEdificio()==null) {
                            if (celAd.getFR().getTipo() == 'p') {
                                if (!celAd.getUds().isEmpty()) {
                                    Unidad ud = celAd.getUds().get((String) celAd.getUds().keySet().toArray()[0]);
                                    if (ud.getCodCiv() == civActual.getCodigo()) {
                                        celAd.serAtacado('T', ed.getAtaque());
                                        System.out.println("Una torre te ha atacado");
                                    }
                                } else if (!celAd.getGrup().isEmpty()) {
                                    Grupos gr = celAd.getGrup().get((String) celAd.getGrup().keySet().toArray()[0]);
                                    if (gr.getCivi() == civActual.getCodigo()) {
                                        celAd.serAtacado('T', ed.getAtaque());
                                        System.out.println("Una torre te ha atacado");
                                    }
                                }
                            }
                        }
                        celAd = celdaEste(ce);
                        if (celAd != null && celAd.getEdificio()==null) {
                            if (celAd.getFR().getTipo() == 'p') {
                                if (!celAd.getUds().isEmpty()) {
                                    Unidad ud = celAd.getUds().get((String) celAd.getUds().keySet().toArray()[0]);
                                    if (ud.getCodCiv() == civActual.getCodigo()) {
                                        celAd.serAtacado('T', ed.getAtaque());
                                        System.out.println("Una torre te ha atacado");
                                    }
                                } else if (!celAd.getGrup().isEmpty()) {
                                    Grupos gr = celAd.getGrup().get((String) celAd.getGrup().keySet().toArray()[0]);
                                    if (gr.getCivi() == civActual.getCodigo()) {
                                        celAd.serAtacado('T', ed.getAtaque());
                                        System.out.println("Una torre te ha atacado");
                                    }
                                }
                            }
                        }
                        celAd = celdaOeste(ce);
                        if (celAd != null && celAd.getEdificio()==null) {
                            if (celAd.getFR().getTipo() == 'p') {
                                if (!celAd.getUds().isEmpty()) {
                                    Unidad ud = celAd.getUds().get((String) celAd.getUds().keySet().toArray()[0]);
                                    if (ud.getCodCiv() == civActual.getCodigo()) {
                                        celAd.serAtacado('T', ed.getAtaque());
                                        System.out.println("Una torre te ha atacado");
                                    }
                                } else if (!celAd.getGrup().isEmpty()) {
                                    Grupos gr = celAd.getGrup().get((String) celAd.getGrup().keySet().toArray()[0]);
                                    if (gr.getCivi() == civActual.getCodigo()) {
                                        celAd.serAtacado('T', ed.getAtaque());
                                        System.out.println("Una torre te ha atacado");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


//    public void atacar(String ){
//        
//    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Punto.Punto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author Sergio
 */
public class Celda {

  private Punto pos;
  private ArrayList<Integer> visible;
  private FuenteRecursos fr;
  private Edificio ed;
  private HashMap<String, Unidad> uds;
  private HashMap<String, Grupos> grup;

  //constructores
  public Celda(char fuente, int cantidad, int x, int y, Civilizacion civi) {
    fr = new FuenteRecursos(fuente, cantidad);
    uds = new HashMap<>();
    grup = new HashMap<>();
    visible = new ArrayList<>();
    visible.add(civi.getCodigo());
    pos = new Punto(x,y);
  }

  public Celda(char fuente, int cantidad, int x, int y) {
    fr = new FuenteRecursos(fuente, cantidad);
    uds = new HashMap<>();
    grup = new HashMap<>();
    visible = new ArrayList<>();
    pos = new Punto(x,y);
  }

  public Celda(int x, int y) {
    fr = new FuenteRecursos();
    uds = new HashMap<>();
    grup = new HashMap<>();
    visible = new ArrayList<>();
    pos = new Punto(x,y);
  }

  public Celda(int x, int y, Civilizacion civi) {
    fr = new FuenteRecursos();
    uds = new HashMap<>();
    grup = new HashMap<>();
    visible = new ArrayList<>();
    visible.add(civi.getCodigo());
    pos = new Punto(x,y);
  }

  public Celda(char edificio, int x, int y, Civilizacion civi) {
    fr = new FuenteRecursos();
    ed = new Edificio(edificio, civi);
    uds = new HashMap<>();
    grup = new HashMap<>();
    visible = new ArrayList<>();
    visible.add(civi.getCodigo());
    pos = new Punto(x,y);
  }

  public int getTotalUds() {
    int total = 0;
    total += uds.size();
    for (String clave : grup.keySet()) {
      Grupos g = grup.get(clave);
      total += g.getUds().size();
    }
    return total;
  }

  public void setVisible(int civi, boolean set) {
    if (set) {
      this.visible.add(civi);
    } else {
      this.visible.remove(civi);
    }
  }

  public void setVisible(Civilizacion civi, boolean set) {
    if (set) {
      this.visible.add(civi.getCodigo());
    } else {
      this.visible.remove(civi.getCodigo());
    }
  }

  //Los constructores están acualizados en principio
  public FuenteRecursos getFR() {
    return fr;
  }

  public boolean esVisible(Civilizacion civi) {
    return visible.contains(civi.getCodigo());
  }

  public boolean esVisible(int civi) {
    return visible.contains(civi);
  }

  public void anadirEdificio(char edificio, Civilizacion civi) {
    if (ed == null && fr.getTipo() == 'p' && uds.isEmpty()) {
      ed = new Edificio(edificio, civi);
    }
  }

  public void anadirEdificio(char edificio, Unidad constructor, Civilizacion civi) {
    if (ed == null && fr.getTipo() == 'p' && uds.isEmpty() && constructor.getTipo() == 'P' && civi != null) {
      ed = new Edificio(edificio, civi, constructor);
      if (ed == null) {
        System.out.println("No hay suficientes recursos");
      }
    }
  }

  public Edificio getEdificio() {
    if (fr.getTipo() == 'p') {
      return ed;
    }
    return null;
  }

  public HashMap<String, Unidad> getUds() {
    if (fr.getTipo() == 'p') {
      return uds;
    }
    return null;
  }

  public Unidad getUnidad(String nom) {
    if (fr.getTipo() == 'p') {
      Unidad ud;
      ud = uds.get(nom);
      return ud;
    } else {
      return null;
    }
  }

  public HashMap<String, Grupos> getGrup() {
    if (fr.getTipo() == 'p') {
      return grup;
    }
    return null;
  }

  public Grupos getGrupo(String nom) {
    if (fr.getTipo() == 'p') {
      Grupos gr;
      gr = grup.get(nom);
      return gr;
    } else {
      return null;
    }
  }

  public void anhadirUnidad(Unidad uni) {
    if (uni != null) {
      uds.put(uni.getNombre(), uni);
    }
  }

  public void anhadirGrupo(Grupos gr) {
    if (gr != null) {
      grup.put(gr.getNombre(), gr);
    }
  }

  public boolean esTransitable() {
    if (ed != null) {
      return false;
    }
    if (fr.getTipo() == 'p') {
      if (!uds.isEmpty()) {
        Unidad ud = uds.get((String) uds.keySet().toArray()[0]);
        if (ud.getCodCiv() == Mapa.getCivActual().getCodigo()) {
          return true;
        }
      } else {
        if (!grup.isEmpty()) {
          Grupos g = grup.get((String) grup.keySet().toArray()[0]);
          if (g.getCivi() == Mapa.getCivActual().getCodigo()) {
            return true;
          }
        }
        return true;
      }
    }
    return false;
  }

  public int getX() {
    return pos.getX();
  }

  public int getY() {
    return pos.getY();
  }
  
  public Punto getPos(){
    Punto p = new Punto(pos.getX(),pos.getY());
    return p;
  }

  public Grupos agrupar() {
    if (uds.isEmpty()) {
      System.out.println("Esta celda no tiene unidades");
      return null;
    }
    if (uds.size() < 2) {
      System.out.println("Esta celda sólo contiene una unidad");
      return null;
    }
    Unidad ud = uds.get((String) uds.keySet().toArray()[0]);
    if (ud.getCodCiv() != Mapa.getCivActual().getCodigo()) {
      System.out.println("Sólo se pueden crear grupos con unidades de la civilización actual");
      return null;
    }
    Grupos g = new Grupos(uds, Mapa.getCivActual());
    uds.clear();//vacía uds
    grup.put(g.getNombre(), g);
    return g;
  }

  public void desagrupar(String nombre) {
    Grupos g = grup.get(nombre);
    if (g == null) {
      System.out.println("No existe ese grupo");
      return;
    }
    if (g.getCivi() != Mapa.getCivActual().getCodigo()) {
      System.out.println("No se pueden desagrupar personajes de otra civilización");
      return;
    }
//      for (String clave : g.getUds().keySet()) {
//            Unidad ud = g.getUds().get(clave);
//            uds.put(clave,ud);
//      }
    uds.putAll(g.getUds());//aparentemente esto hace lo mismo que lo que puse arriba
    g.getUds().clear();//en teoría en cuanto eliminara este grupo del hashmap de grupos, pero como según tengo entendido el recolector de basura de java no es muy bueno...
    grup.remove(nombre);
  }

  public boolean desligar(String unidad, String grupo) {
    Grupos g = grup.get(grupo);
    if (g == null) {
      System.out.println("No existe ese grupo");
      return false;
    }
    if (g.getCivi() != Mapa.getCivActual().getCodigo()) {
      System.out.println("No se pueden agrupar personajes de otra civilización");
      return false;
    }
    Unidad ud = g.getUnidad(unidad);
    if (ud != null) {
      g.getUds().remove(unidad);
      uds.put(unidad, ud);
      boolean c = g.actualizarGrupo();
      return c;
    } else {
      System.out.println("Esta unidad no está en el grupo");
      return false;
    }
  }

  @Override
  public String toString() {
    String ret = "E";
    if (!this.esVisible(Mapa.getCivActual())) {
      ret = "█";
    } else if (fr.getTipo() == 'p') {
      if (ed != null) {
        if (ed.getCodCiv() == Mapa.getCivActual().getCodigo()) {
          ret = "\u001B[1;34m";
        } else {
          ret = "\u001B[31m";
        }
        switch (ed.getTipo()) {
          case 'C':
            ret += "♛\u001B[0m";
            break;
          case 'H':
            ret += "⌂\u001B[0m";
            break;
          case 'M':
            ret += "☗\u001B[0m";
            break;
          case 'T':
            ret += "♜\u001B[0m";
        }
      } else {
        if (uds.isEmpty()) {
          ret = "\u001B[33m░\u001B[0m";
        } else {
          Unidad ud = uds.get((String) uds.keySet().toArray()[0]);
          if (ud.getCodCiv() == Mapa.getCivActual().getCodigo()) {
            ret = "\u001B[1;34m";
          } else {
            ret = "\u001B[31m";
          }
          ret += "☺\u001B[0m";
        }
        if (!grup.isEmpty()) {
          Grupos gr = grup.get((String) grup.keySet().toArray()[0]);
          if (gr.getCivi() == Mapa.getCivActual().getCodigo()) {
            ret = "\u001B[1;34m";
          } else {
            ret = "\u001B[31m";
          }
          if (!grup.isEmpty()) {
            if (!uds.isEmpty()) {
              ret += "○\u001B[0m";
            } else {
              ret += "☻\u001B[0m";
            }
          }
        }
      }
    } else {
      switch (fr.getTipo()) {
        case 'b':
          ret = "\u001B[32m♠\u001B[0m";
          break;
        case 'a':
          ret = "\u001B[1;32m☁\u001B[0m";
          break;
        case 'c':
          ret = "\u001B[1;30m▓\u001B[0m";
          break;
      }
    }
    return ret;
  }

  public void serAtacado(char tipo, int dano) {
    if (dano > 0 && ed == null) {
      switch (tipo) {
        case 'U':
          if (!uds.isEmpty()) {
            Unidad a = uds.get((String) uds.keySet().toArray()[0]);
            int d = (int) ((-1.0f) * (float) dano * (1.0f - a.getArmadura()));
            if (dano > 0 && d == 0) {
              d = -1;
            }
            a.modSalud(d);
            if (a.getSalud() == 0) {
              Civilizacion c = a.getCivilizacion();
              uds.remove(a.getNombre());
              c.getUdsHash().remove(a.getNombre());
            }
          } else if (!grup.isEmpty()) {
            Grupos g = grup.get((String) grup.keySet().toArray()[0]);
            int d = (int) ((-1.0f) * (float) dano * (1.0f - g.getArmadura()));
            d = d / (g.getNumPai() + g.getNumSol());
            if (dano > 0 && d == 0) {
              d = -1;
            }
            g.modSalud(d);
            Unidad a;
            Civilizacion c = null;
            if (!g.getUds().isEmpty()) {
              ArrayList<Unidad> l = new ArrayList();
              for (String clave : g.getUds().keySet()) {
                a = g.getUds().get(clave);
                c = a.getCivilizacion();
                if (a.getSalud() == 0) {
                  l.add(a);
                }
              }
              for (Unidad ud : l) {
                g.getUds().remove(ud.getNombre());
                g.actualizarGrupo();
                c.getUdsHash().remove(ud.getNombre());
              }
              l.clear();
              l = null;
            }
            if (g.getNumPai() + g.getNumSol() <= 1) {
              if (g.getNumPai() + g.getNumSol() == 1) {
                a = g.getUnidad((String) g.getUds().keySet().toArray()[0]);
                c = a.getCivilizacion();
                uds.put(a.getNombre(), a);
              }
              grup.remove(g.getNombre());
              if (c != null) {
                c.getGrupHash().remove(g.getNombre());
              }
            }
          }
          break;
        case 'G':
          if (!grup.isEmpty()) {
            Grupos g = grup.get((String) grup.keySet().toArray()[0]);
            int d = (int) ((-1.0f) * (float) dano * (1.0f - g.getArmadura()));
            d = d / (g.getNumPai() + g.getNumSol());
            if (dano > 0 && d == 0) {
              d = -1;
            }
            g.modSalud(d);
            Unidad a;
            Civilizacion c = null;
            if (!g.getUds().isEmpty()) {
              ArrayList<Unidad> l = new ArrayList();
              for (String clave : g.getUds().keySet()) {
                a = g.getUds().get(clave);
                c = a.getCivilizacion();
                if (a.getSalud() == 0) {
                  l.add(a);
                }
              }
              for (Unidad ud : l) {
                g.getUds().remove(ud.getNombre());
                g.actualizarGrupo();
                c.getUdsHash().remove(ud.getNombre());
              }
              l.clear();
              l = null;
            }
            if (g.getNumPai() + g.getNumSol() <= 1) {
              if (g.getNumPai() + g.getNumSol() == 1) {
                a = g.getUnidad((String) g.getUds().keySet().toArray()[0]);
                c = a.getCivilizacion();
                uds.put(a.getNombre(), a);
              }
              grup.remove(g.getNombre());
              if (c != null) {
                c.getGrupHash().remove(g.getNombre());
              }
            }
          } else if (!uds.isEmpty()) {
            Unidad a = uds.get((String) uds.keySet().toArray()[0]);
            int d = (int) ((-1.0f) * (float) dano * (1.0f - a.getArmadura()));
            if (dano > 0 && d == 0) {
              d = -1;
            }
            a.modSalud(d);
            if (a.getSalud() == 0) {
              Civilizacion c = a.getCivilizacion();
              uds.remove(a.getNombre());
              c.getUdsHash().remove(a.getNombre());
            }
          }
          break;
        case 'T':
          if (!grup.isEmpty()) {
            Grupos g;
            for (String clave : grup.keySet()) {
              g = grup.get(clave);
              int d = (int) ((-1.0f) * (float) dano * (1.0f - g.getArmadura()));
              if (dano > 0 && d == 0) {
                d = -1;
              }
              g.modSalud(d);
              Unidad a;
              Civilizacion c = null;
              if (!g.getUds().isEmpty()) {
                ArrayList<Unidad> l = new ArrayList();
                for (String clave1 : g.getUds().keySet()) {
                  a = g.getUds().get(clave1);
                  c = a.getCivilizacion();
                  if (a.getSalud() == 0) {
                    l.add(a);
                  }
                }
                for (Unidad ud : l) {
                  g.getUds().remove(ud.getNombre());
                  g.actualizarGrupo();
                  c.getUdsHash().remove(ud.getNombre());
                }
                l.clear();
                l = null;
              }

              if (g.getNumPai() + g.getNumSol() <= 1) {
                if (g.getNumPai() + g.getNumSol() == 1) {
                  a = g.getUnidad((String) g.getUds().keySet().toArray()[0]);
                  c = a.getCivilizacion();
                  uds.put(a.getNombre(), a);
                }
                grup.remove(g.getNombre());
                if (c != null) {
                  c.getGrupHash().remove(g.getNombre());
                }
              }
            }
          }

          if (!uds.isEmpty()) {
            Unidad u;
            ArrayList<Unidad> l1 = new ArrayList();
            for (String cla : uds.keySet()) {
              u = uds.get(cla);
              int d = (int) ((-1.0f) * (float) dano * (1.0f - u.getArmadura()));
              if (dano > 0 && d == 0) {
                d = -1;
              }
              u.modSalud(d);
              if (u.getSalud() == 0) {
                l1.add(u);
              }
            }
            for (Unidad ud : l1) {
              Civilizacion c = ud.getCivilizacion();
              uds.remove(ud.getNombre());
              c.getUdsHash().remove(ud.getNombre());
            }
          }
          break;
      }
    } else if (dano > 0 && ed != null) {
      int d = (int) ((-1.0f) * (float) dano * (1.0f - ed.getDefensa()));
      if (dano > 0 && d == 0) {
        d = -1;
      }
      ed.modSalud(d);
      if (ed.getSalud() == 0) {
        Civilizacion c = ed.getCivilizacion();
        if (ed.getTipo() == 'H') {
          c.setPobMax(c.getPobMax() - 5);
        }
        c.getEdisHash().remove(ed.getNombre());
        ed = null;
      }
    }
  }

  @Override
  public boolean equals(Object otra) {
    if (otra == null || getClass() != otra.getClass()) {
      return false;
    }
    return ((Celda) otra).getX() == this.getX() && ((Celda) otra).getY() == this.getY();
  }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.pos);
        return hash;
    }
}

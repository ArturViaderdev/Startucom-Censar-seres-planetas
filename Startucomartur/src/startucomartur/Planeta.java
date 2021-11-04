/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startucomartur;

import java.util.ArrayList;

/**
 * Define planeta
 * @author arturv
 */
public class Planeta {
    //Nombre del planeta
    private String nombre;
    //Lista de seres del planeta
    private ArrayList<Ser> seres;
    //Lista de razas no admitidas
    private String[] razasnoadmitidas;
    //Número máximo permitido de seres
    //Si es 0 no hay máximo
    private int maxseres;

    /**
     * Devuelve la lista de seres del planeta
     * @return Lista de seres
     */
    public ArrayList<Ser> getSeres() {
        return seres;
    }

    /**
     * Contructor
     * @param nombre Nombre del planeta
     * @param razasnoadmitidas Lista de razas no admitidas
     * @param maxseres Número máximo de seres permitidos, 0 para sin límite
     */
    public Planeta(String nombre, String[] razasnoadmitidas, int maxseres) {
        this.nombre = nombre;
        this.razasnoadmitidas = razasnoadmitidas;
        //Se inicializa la lista de seres
        seres = new ArrayList<>();
    }

    /**
     * Añade un ser al planeta
     * @param nuevoser Nuevo ser
     * @throws Excepcionapp Puede lanzar excepciones si la alta no se admite
     */
    public void anadeser(Ser nuevoser) throws Excepcionapp {
        boolean sal = false;
        boolean encontrado = false;
        int cont = 0;
        do {
            //Se recorren las razas no admitidas para ver si el nuevo ser es admitido o no
            if (cont < razasnoadmitidas.length) {
                switch (razasnoadmitidas[cont]) {
                    case "Andorian":
                        if (nuevoser instanceof Andorian) {
                            encontrado = true;
                            sal = true;
                        }
                        break;
                    case "Human":
                        if (nuevoser instanceof Human) {
                            encontrado = true;
                            sal = true;
                        }
                        break;
                    case "Klingon":
                        if (nuevoser instanceof Klingon) {
                            encontrado = true;
                            sal = true;
                        }
                        break;
                    case "Nibirian":
                        if (nuevoser instanceof Nibirian) {
                            encontrado = true;
                            sal = true;
                        }
                        break;
                    case "Vulcan":
                        if (nuevoser instanceof Vulcan) {
                            encontrado = true;
                            sal = true;
                        }
                        break;
                }
                if(encontrado)
                {
                    //Si el ser tiene una raza de las no admitidas se lanza excepción
                    throw new Excepcionapp(Excepcionapp.NOSERPLANETA);
                }
                else
                {
                    cont++;
                }
            } else {
                sal = true;
            }
        } while (!sal);
        
        //Si hay un máximo de seres permitidos
        if(maxseres>0)
        {
            if(seres.size()+1>maxseres)
            {
                //Si el nuevo ser haría que se superase el máximo se lanza una excepción
                throw new Excepcionapp(Excepcionapp.MAXSERES);
            }
        }
        //Se añade el nuevo ser
        seres.add(nuevoser);
        //Se añade el nuevo ser al archivo del planeta
        Metodos.anadeaserarchivo(nuevoser, nombre);
    }

    /**
     * Get del nombre
     * @return Nombre del planeta
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre del planeta
     * @param nombre Nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Lee el archivo del planeta
     */
    public void readFile() {
        //La lista de seres se lee del archivo con el nombre del planeta
        seres = Metodos.leefichero(nombre);
    }

    /**
     * Graba el archivo del planeta completo
     */
    public void saveFile() {
        //Graba el fichero con el nombre del planeta conteniendo la lista de seres
        Metodos.grabafichero(nombre, seres);
    }

    /**
     * Borra el ser de una posición concreta
     * @param pos Posición del ser a borrar en la lista
     */
    public void borraserpos(int pos) {
        //Se borra el ser de la lista
        seres.remove(pos);
        //Se reescribe el fichero del planeta
        saveFile();
    }
}

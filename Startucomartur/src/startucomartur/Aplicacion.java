/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startucomartur;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa la funcionalidad principal de la aplicación
 * @author alu2017363
 */
public class Aplicacion {
    //El manejador contendrá todos los datos en memoria y leera y escribirá archivos.
    private Manejador manejador;
    
    /**
     * Constructor
     */
    public Aplicacion()
    {
        //Se inicializa el manejador. En este momento se leen los datos de los ficheros si existen.
        manejador = new Manejador();
    }
    
    /**
     * Guarda la salida de la aplicación contenida en el manejador.
     */
    public void guardasalida()
    {
        Metodos.grabasalida(manejador.getSalida());   
    }
    
    /**
     * Procesa una lista de parámetros
     * @param args Parámetros de entrada
     */
    public void procesa(String[] args) {
        String orden;
        try {
            orden = args[0].toLowerCase();
            switch (orden) {
                case "l":
                    //Listar seres
                    if (args.length == 1) {
                        //Si el número de parámetros es 1 se listan los seres
                        manejador.listadoseres();
                    } else {
                        //Si el número de parámetros no es correcto se lanza excepción.
                        throw new Excepcionapp(Excepcionapp.NUMARGUMENTOS);
                    }
                    break;
                case "c":
                    //Censar ser
                    if (args.length == 5) {
                        //Se procede cuando hay cinco parámetros.
                        //Convierto la especie y el planeta a minúsculas
                        manejador.censarser(args[1].toLowerCase(), args[2].toLowerCase(), args[3], args[4]);
                    } else {
                        throw new Excepcionapp(Excepcionapp.NUMARGUMENTOS);
                    }
                    break;
                case "b":
                    //Borrar ser
                    if (args.length == 2) {
                        //El ser se borra si hay 2 parámetros
                        //Se le pasa el nombre del ser, no distingue entre mayúsculas
                        manejador.borraser(args[1]);
                    } else {
                        throw new Excepcionapp(Excepcionapp.NUMARGUMENTOS);
                    }
                    break;
                case "m":
                    //Modificar ser
                    if (args.length == 3) {
                        //Se le pasa el nombre y el nuevo valor
                        manejador.modificaser(args[1], args[2]);
                    } else {
                        throw new Excepcionapp(Excepcionapp.NUMARGUMENTOS);
                    }
                    break;
                case "p":
                    //Ver seres de una especie
                    if (args.length == 2) {
                        //Se le pasa la especie
                        manejador.seresdeespecie(args[1]);
                    } else {
                        throw new Excepcionapp(Excepcionapp.NUMARGUMENTOS);
                    }
                    break;
                case "x":
                    System.out.println("Adios.");
                    break;
                default:
                    //Si la opción es incorrecta se lanza excepción
                    throw new Excepcionapp(Excepcionapp.OPINCORRECTA);
            }
        } catch (Excepcionapp ex) {
            //Si salta una excepción se muestra y añade a la salida en memoria.
            System.out.println(ex.getMessage());
            manejador.addsalida(ex.getMessage());
        }
        
        
    }
}

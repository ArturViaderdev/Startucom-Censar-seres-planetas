/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startucomartur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arturv
 */
public class Metodos {

    public static String carpeta = "datos";

    //Dice si existe la carpeta de datos
    //retorno boolean - Indica si la carpeta existe o no
    public static boolean existecarpeta() {
        File f = new File("." + File.separator + carpeta);
        if (f.exists() && f.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }

    //crea la carpeta con todos los ficheros de deportes en caso de no existir
    public static void creacarpeta() {
        File f;
        //se crea la carpeta
        new File(carpeta).mkdirs();
    }

    /**
     * Lee un fichero de seres
     * @param fichero Fichero de seres
     * @return Lista de seres leida
     */
    public static ArrayList<Ser> leefichero(String fichero) {
        File archivo;
        BufferedReader lector;
        String linea;
        ArrayList<Ser> resultado;
        //Se inicializa la lista del resultado
        resultado = new ArrayList<>();

        //Se prepara el nombre del archivo
        archivo = new File(carpeta + File.separator + fichero + ".txt");
        try {
            lector = new BufferedReader(new FileReader(archivo));
            try {
                //Se recorre el fichero
                do {
                    //Se lee una linea
                    linea = lector.readLine();
                    if (linea != null) {
                        try
                        {
                            //Se añade a la lista de seres el resultado de interpretar la linea
                            resultado.add(interpretalinea(linea));
                        }
                        catch(Excepcionapp ex)
                        {
                            //Si hay alguna excepción de aplicación al leer los seres se muestra
                            System.out.println(ex.getMessage());
                        }
                    }
                } while (linea != null);
                //System.out.println("Leidos " + resultado.size() + " seres en planeta " + fichero);
            } catch (IOException ex) {
                //Si hay un error de lectura de archivo
                System.out.println("Error leyendo archivo.");
            } finally {
                try {
                    //Se cierra el lector
                    lector.close();
                } catch (IOException ex) {

                }
            }
        } catch (FileNotFoundException ex) {
            //Si no se encuentra el archivo
            //System.out.println("Archivo no encontrado para el planeta " + fichero);
        }
        return resultado;
    }

    /**
     * Convierte un texto para tener la primera letra en mayúscula y las demás en minúscula
     * @param palabra Palabra a convertir
     * @return Resultado
     */
    public static String primeramayuscula(String palabra)
    {
        String resultado="";
        //Se recorren los caracteres
        for(int cont=0;cont<palabra.length();cont++)
        {
            if(cont==0)
            {
                //Si es el primer carácter se convierte a mayúsculas
                resultado += Character.toUpperCase(palabra.charAt(cont));
            }
            else
            {
                //Si no es el primer caracter se convierte a minúsculas
                resultado += Character.toLowerCase(palabra.charAt(cont));
            }
        }
        return resultado;
    }
    
    /**
     * Crea una linea para ser grabada en el archivo del planeta a partir de un ser
     * @param ser Ser para convertir a string
     * @return Linea para grabar en el fichero
     */
    private static String crealinea(Ser ser) {
        String linea = null;
        String cadena;
        //Si el ser es andorian se genera el texto aenar o noaenar
        if (ser instanceof Andorian) {
            if (((Andorian) ser).getAenar()) {
                cadena = "aenar";
            } else {
                cadena = "noaenar";
            }
            linea = "Andorian" + "*" + ser.getNombre() + "*" + cadena + System.lineSeparator();
        } else if (ser instanceof Human) {
            //Si el ser es humano
            linea = "Human" + "*" + ser.getNombre() + "*" + String.valueOf(((Human) ser).getEdad()) + System.lineSeparator();
        } else if (ser instanceof Klingon) {
            //Si el ser es klingon
            linea = "Klingon" + "*" + ser.getNombre() + "*" + String.valueOf(((Klingon) ser).getFuerza()) + System.lineSeparator();
        } else if (ser instanceof Nibirian) {
            //Si el ser es nibirian
            if (((Nibirian) ser).isCarnivoro()) {
                cadena = "carnivoro";
            } else {
                cadena = "vegetarian";
            }
            linea = "Nibirian" + "*" + ser.getNombre() + "*" + cadena + System.lineSeparator();
        }
        else if(ser instanceof Vulcan)
        {
            //Si el ser es vulcan
              linea = "Vulcan" + "*" + ser.getNombre() + "*" + String.valueOf(((Vulcan) ser).getMeditacion()) + System.lineSeparator();
        }
        return linea;
    }

    /**
     * Añade un ser a un archivo de planeta
     * @param nuevoser Nuevo ser
     * @param planeta Nombre del planeta
     */
    public static void anadeaserarchivo(Ser nuevoser, String planeta) {
        BufferedWriter writer = null;
        try {
            //Se prepara el nombre del archivo
            File archivo = new File(carpeta + File.separator + planeta + ".txt");
            writer = new BufferedWriter(new FileWriter(archivo, true));
            //Se graba el ser en el fichero creando una linea
            //El ser se añade al final del fichero
            writer.write(crealinea(nuevoser));
        } catch (IOException ex) {
            System.out.println("Error escribiendo archivo.");
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                System.out.println("Error cerrando archivo.");
            }
        }
    }

    /**
     * Guarda la salida de la aplicación en un archivo de texto
     * @param salida Lista de mensajes que ha producido la aplicación
     */
    public static void grabasalida(ArrayList<String> salida)
    {
        BufferedWriter writer = null;
        try {
            //Se prepara el nombre del archivo
            File archivo = new File(carpeta + File.separator + "salida.txt");
            writer = new BufferedWriter(new FileWriter(archivo, false));
            //Para cada mensaje de la lista
            for(String texto:salida)
            {
                //Se guarda en el fichero junto a un salto de linea al final
                writer.write(texto + System.lineSeparator());
            }
        } catch (IOException ex) {
            System.out.println("Error escribiendo archivo de salida.");
        }
        finally{
            try {
                //se cierra el fichero
                writer.close();
            } catch (IOException ex) {
                System.out.println("Error cerrando archivo.");
            }
            
        }
    }
    
    /**
     * Graba el fichero de un planeta completo
     * @param planeta Nombre del planeta
     * @param seres Lista de seres del planeta
     */
    public static void grabafichero(String planeta, ArrayList<Ser> seres) {
        BufferedWriter writer = null;
        boolean primero = true;
        try {
            //Se prepara el nombre del archivo
            File archivo = new File(carpeta + File.separator + planeta + ".txt");
            writer = new BufferedWriter(new FileWriter(archivo, false));
            //Se recorren los seres de la lista
            for (Ser elser : seres) {
                //Para cada ser se crea una linea y se graba en el fichero
                writer.write(crealinea(elser));
            }
            //System.out.println("Fichero del planeta " + planeta + " regrabado.");
        } catch (IOException ex) {
            System.out.println("Error escribiendo archivo.");
        } finally {
            try {
                //Se cierra el fichero
                writer.close();
            } catch (IOException ex) {
                System.out.println("Error cerrando archivo.");
            }
        }
    }

    /**
     * Interpreta una linea de texto y la transforma en un ser
     * @param linea Linea con los datos
     * @return Ser leido
     * @throws Excepcionapp 
     */
    private static Ser interpretalinea(String linea) throws Excepcionapp {
        String[] datos;
        //Se separa el string en un array por el carácter *
        datos = linea.split("\\*");
        //Divide los campos en un array de string

        boolean siono;
        int numero;
        Ser nuevoser = null;
        //Según el primer parámetro que contiene la especie
        switch (datos[0]) {
            case "Andorian":
                //Para andorian se lee aenar
                if (datos[2].equals("aenar")) {
                    siono = true;
                } else {
                    siono = false;
                }
                //Se crea el ser
                nuevoser = new Andorian(datos[1], siono);
                break;
            case "Human":
                //Para human se lee la edad
                numero = Integer.parseInt(datos[2]);
                //Se crea el ser
                nuevoser = new Human(datos[1], numero);
                break;

            case "Klingon":
                //Para klingon se lee la fuerza
                numero = Integer.parseInt(datos[2]);
                //Se crea el ser
                nuevoser = new Klingon(datos[1], numero);
                break;

            case "Nibirian":
                //Para nibirian se lee carnivoro
                if (datos[2].equals("carnivoro")) {
                    siono = true;
                } else {
                    siono = false;
                }
                //Se crea el ser
                nuevoser = new Nibirian(datos[1], siono);
                break;
                
            case "Vulcan":
                //Para vulcan se lee meditación
                numero = Integer.parseInt(datos[2]);
                //Se crea el ser
                nuevoser = new Vulcan(datos[1], numero);
                break;
            default:
        }
        //Se devuelve el ser leido
        return nuevoser;
    }
}

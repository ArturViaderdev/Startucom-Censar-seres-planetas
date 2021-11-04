/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startucomartur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que controla los datos en memoria
 * @author arturv
 */
public class Manejador {
    //Lista de planetas
    ArrayList<Planeta> planetas;
    //Salida de la aplicación que se guarda para luego guardar en archivo de texto
    ArrayList<String> salida;

    //Devuelve la salida (Mensajes al usuario)
    public ArrayList<String> getSalida() {
        return salida;
    }

    //Añade texto a la salida
    public void addsalida(String texto)
    {
        salida.add(texto);
    }
    
    /**
     * Constructor
     */
    public Manejador() {
        //Se crean los planetas
        creaplanetas();
        //Se cargan los datos de los archivos de texto si existen
        cargadatos();
        //Se inicializa la lista de la salida que sirve para guardar los mensajes enviados al usuario
        salida = new ArrayList<String>();
    }

    /**
     * Muestra por pantalla un texto y lo guarda como salida
     * @param texto Texto a añadir y mostrar
     */
    private void muestra(String texto)
    {
        System.out.println(texto);
        addsalida(texto);
    }
    
    /**
     * Muestra los seres que hay de una especia en concreto
     * @param especie Especie de la que se buscarán seres
     * @throws Excepcionapp Puede lanzar una excepción si la especie es incorrecta
     */
    public void seresdeespecie(String especie) throws Excepcionapp {
        ArrayList<Ser> seres;
        int cont = 0;
        String texto;
        try {
            //Se comprueba que la clase existe, si no existe salta una excepción ClassNotFound
            Class.forName("startucomartur." + Metodos.primeramayuscula(especie));
            texto = "<Población por raza " + especie + ">";
            muestra(texto);
            //Se recorren todos los planeta
            for (Planeta elplaneta : planetas) {
                seres = elplaneta.getSeres();
                //Para cada planeta se recorren todos los seres que contiene
                for (Ser elser : seres) {
                    //Se comprueba si la clase del ser es la que se busca
                    if (elser.getClass().getName().toLowerCase().equals(("Startucomartur." + especie).toLowerCase())) {
                        //Se muestra el ser
                        texto = elser.toString() + "-" + elplaneta.getNombre() + System.lineSeparator();
                        muestra(texto);
                        cont++;
                    }
                }
            }
            //Se muestra el número de seres de esa especie encontrados
            texto = "Existen " + cont + " seres de la especie " + especie;
            muestra(texto);
        } catch (ClassNotFoundException ex) {
            throw new Excepcionapp(Excepcionapp.ESPECIEINCORRECTA);
        }

    }

    /**
     * Busca un ser y devuelve su posición
     * @param nombre Nombre del ser
     * @return Posición del ser en una array, el primer elemento es el número de planeta y el segundo el número de ser
     */
    public int[] dameposser(String nombre) {
        boolean encontrado = false;
        boolean sal = false;
        boolean salb;
        int[] resultado;
        ArrayList<Ser> seres;
        int cont = 0;
        int contb = 0;
        do {
            //Se recorren los planetas
            if (cont < planetas.size()) {
                contb = 0;
                salb = false;
                seres = planetas.get(cont).getSeres();
                do {
                    //Se recorren los seres del planeta
                    if (contb < seres.size()) {
                        if (seres.get(contb).getNombre().toLowerCase().equals(nombre.toLowerCase())) {
                            //Si se encuentra el ser buscado
                            salb = true;
                            encontrado = true;
                        } else {
                            contb++;
                        }
                    } else {
                        salb = true;
                    }
                } while (!salb);
                if (encontrado) {
                    sal = true;
                } else {
                    cont++;
                }
            } else {
                sal = true;
            }
        } while (!sal);
        if (encontrado) {
            //Si se ha encontrado el ser se prepara para devolver su posición
            //Número de planeta y número de ser del planeta
            resultado = new int[2];
            resultado[0] = cont;
            resultado[1] = contb;
        } else {
            //Si no se ha encontrado el ser se devuelve un array de un elemento que contiene un -1
            resultado = new int[1];
            resultado[0] = -1;
        }
        return resultado;
    }

    /**
     * Muestra todos los seres ordenados
     * @throws Excepcionapp Puede lanzar una excepción si no hay seres registrados
     */
    public void listadoseres() throws Excepcionapp {
        //Funciona pero aun no ordena bien
        boolean hayseres = false;
        String texto;
        ArrayList<Ser> lista = new ArrayList<>();
        //Se recorren los planetas
        for (Planeta elplaneta : planetas) {
            //Se obtiene la lista de seres del planeta
            lista = elplaneta.getSeres();
            if (lista.size() > 0) {
                //Si existen seres se recuerda que se han encontrado y se muestra la cabecera
                if (!hayseres) {
                    hayseres = true;
                    texto = "<Población por planeta>";
                    muestra(texto);
                }
                //Se muestra el nombre del planeta
                texto = "Planeta " + elplaneta.getNombre();
                muestra(texto);
                //Se ordena la lista de seres
                Collections.sort(lista, new Comparator<Ser>() {
                    public int compare(Ser a, Ser b) {
                        if (a.getClass().getName().equals(b.getClass().getName())) {
                            return a.getClass().getName().compareTo(b.getClass().getName());
                        } else {
                            return a.getNombre().compareTo(b.getNombre());
                        }
                    }
                });
                //Se muestra la lista de seres ordenada
                for (Ser elser : lista) {
                    texto = elser.toString();
                    muestra(texto);
                }
            }

        }
        if (!hayseres) {
            //Si no se han encontrado seres se lanza una excepción
            throw new Excepcionapp(Excepcionapp.NADIEREGISTRADO);
        }
    }

    /**
     * Borra un ser
     * @param nombre Nombre del ser a borrar
     * @throws Excepcionapp Puede lanzar una excepción en caso de no existir el ser buscado
     */
    public void borraser(String nombre) throws Excepcionapp {
        String texto;
        //Se obtiene la posición del ser
        int[] posicion = dameposser(nombre);
        if (posicion.length == 2) {
            //Si se ha encontrado el ser
            //Se borra el ser
            planetas.get(posicion[0]).borraserpos(posicion[1]);
            texto = "Ser borrado.";
            muestra(texto);
        } else {
            //Si no se ha encontrado el ser se lanza excepción
            throw new Excepcionapp(Excepcionapp.NOEXISTESER);
        }
    }

    /**
     * Censar un ser en un planeta
     * @param especie Especie del ser
     * @param planeta Planeta donde censar el ser
     * @param nombre Nombre del ser
     * @param valor Valor característico de la raza
     * @throws Excepcionapp Puede lanzar excepciones
     */
    public void censarser(String especie, String planeta, String nombre, String valor) throws Excepcionapp {
        Ser nuevoser;
        int numero;
        String texto;
        //Se obtiene la posición del ser
        int[] posser = dameposser(nombre);
        try {
            if (posser.length == 1) {
                //Si el ser no existía
                //No diferencio las mayúsculas en la raza
                //Según la raza ase realiza una acción distinta
                switch (especie.toLowerCase()) {
                    case "andorian":
                        //Si el ser es andorian se recoge el valor que puede ser solo aenar o noaenar
                        if (valor.equals("aenar")) {
                            //Se crea el ser
                            nuevoser = new Andorian(nombre, true);
                        } else if (valor.equals("noaenar")) {
                            //Se crea el ser
                            nuevoser = new Andorian(nombre, false);
                        } else {
                            //Si el valor no era aenar o no aenar se lanza excepción
                            throw new Excepcionapp(Excepcionapp.VALORINCORRECTO);
                        }
                        break;
                    case "human":
                        //Para el ser humano se recoge la edad
                        //Puede generar excepción si el valor no es un número, lo mismo en klingon y vulcan
                        //Se crea el ser
                        nuevoser = new Human(nombre, Integer.parseInt(valor));
                        break;
                    case "klingon":
                        //Para el klingon se recoge la fuerza
                        nuevoser = new Klingon(nombre, Integer.parseInt(valor));
                        break;

                    case "nibirian":
                        // if (valor.equals("carnivoro")) {
                        //          nuevoser = new Nibirian(nombre, true);
                        // } else
                        //Un nibirian puede ser vegetarian o novegetarian
                        //sino se lanza excepción
                        if (valor.equals("vegetarian")) {
                            //Se crea el ser
                            nuevoser = new Nibirian(nombre, false);
                        } else if (valor.equals("novegetarian")) {
                            //Se crea el ser
                            nuevoser = new Nibirian(nombre, true);
                        } else {
                            throw new Excepcionapp(Excepcionapp.DATOINCORRECTO);
                        }
                        break;
                    case "vulcan":
                        //Para el vulcan se recoge la meditación
                        //Se crea el ser
                        nuevoser = new Vulcan(nombre, Integer.parseInt(valor));
                        break;
                    default:
                        //Si la especie es incorrecta se lanza excepción
                        throw new Excepcionapp(Excepcionapp.ESPECIEINCORRECTA);
                }
                //Se obtiene la posición del planeta
                //Puede lanzar excepción si no se encuentra
                planetas.get(dameposicionplaneta(planeta)).anadeser(nuevoser);
                texto = "Ser añadido.";
                muestra(texto);
            } else {
                //Si el ser ya existía se lanza una excepción
                throw new Excepcionapp(Excepcionapp.EXISTESER);
            }
        } catch (NumberFormatException ex) {
            //Si ha habido un error de conversión numérica a string
            //Se lanza una excepción distinta dependiendo del tipo de ser
            throw new Excepcionapp(Excepcionapp.DATOINCORRECTO);
            
            /*
            if (especie.toLowerCase().equals("human")) {
                throw new Excepcionapp(Excepcionapp.EDADINCORRECTA);
            } else if (especie.toLowerCase().equals("klingon")) {
                throw new Excepcionapp(Excepcionapp.FUERZAINCORRECTO);
            } else if (especie.toLowerCase().equals("vulcan")) {
                throw new Excepcionapp(Excepcionapp.MEDITACIONINCORRECTO);
            }*/
        }

    }

    /**
     * Modificar el valor propio de la raza de un ser
     * @param nombre Nombre del ser
     * @param nuevodato Nuevo dato del ser
     * @throws Excepcionapp Puede lanzar excepciones
     */
    public void modificaser(String nombre, String nuevodato) throws Excepcionapp {
        int posser[];
        //Se obtiene la posición del ser
        posser = dameposser(nombre);
        Ser elser;
        String texto;
        //Si se encuentra el ser
        try
        {
            
        
        if (posser.length == 2) {
            //Se obtiene el ser
            elser = planetas.get(posser[0]).getSeres().get(posser[1]);
            if (elser instanceof Andorian) {
                //Si el ser es andorian puede ser aenar o no aenar
                if (nuevodato.equals("aenar")) {
                    ((Andorian) elser).setAenar(true);
                } else if (nuevodato.equals("noaenar")) {
                    ((Andorian) elser).setAenar(false);
                } else {
                    throw new Excepcionapp(Excepcionapp.SERNOMODIFICADO);
                }
            } else if (elser instanceof Human) {
                //Si el ser es humano se recoge la edad
         //       try {
                    ((Human) elser).setEdad(Integer.parseInt(nuevodato));
          //      } catch (NumberFormatException ex) {
        //            throw new Excepcionapp(Excepcionapp.EDADINCORRECTA);
        //        }
            } else if (elser instanceof Klingon) {
                //Si el ser es klingon se recoge la fuerza
         //       try {
                    ((Klingon) elser).setFuerza(Integer.parseInt(nuevodato));
         //       } catch (NumberFormatException ex) {
         //           throw new Excepcionapp(Excepcionapp.FUERZAINCORRECTO);
         //       }
            } else if (elser instanceof Nibirian) {
                
              /*  if (nuevodato.equals("carnivoro")) {
                    ((Nibirian) elser).setCarnivoro(true);
                }
                else*/    
                //Si el ser es nibirian puede ser vegetarian o novegetarian
                if (nuevodato.equals("vegetarian")) {
                    ((Nibirian) elser).setCarnivoro(false);
                } else if (nuevodato.equals("novegetarian")) {
                    ((Nibirian) elser).setCarnivoro(true);
                } else {
                    throw new Excepcionapp(Excepcionapp.SERNOMODIFICADO);
                }
            } else if (elser instanceof Vulcan) {
                //Si el ser es vulcan se recoge la meditación
            //    try {
                    ((Vulcan) elser).setMeditacion(Integer.parseInt(nuevodato));

           //     } catch (NumberFormatException ex) {
           //         throw new Excepcionapp(Excepcionapp.MEDITACIONINCORRECTO);
           //     }
            }
            texto = "Ser modificado.";
            muestra(texto);
            //Se graba el archivo del planeta
            planetas.get(posser[0]).saveFile();
        } else {
            //Si no se encuentra el ser se lanza una excepción
            throw new Excepcionapp(Excepcionapp.NOEXISTESER);
        }
        }
        catch(NumberFormatException ex)
        {
            throw new Excepcionapp(Excepcionapp.DATOINCORRECTO);
        }
    }

    /**
     * Devuelve la posición de un planeta
     * @param nombre Nombre del planeta
     * @return Posición del planeta
     * @throws Excepcionapp Puede generar una excepción si el planeta no existe
     */
    private int dameposicionplaneta(String nombre) throws Excepcionapp {
        boolean encontrado = false;
        boolean sal = false;
        int cont = 0;
        do {
            //Se recorren los planetas
            if (cont < planetas.size()) {
                if (planetas.get(cont).getNombre().toLowerCase().equals(nombre)) {
                    //Si se encuentra el planeta
                    encontrado = true;
                    sal = true;
                } else {
                    cont++;
                }
            } else {
                sal = true;
            }
        } while (!sal);
        if (encontrado) {
            //Se devuelve la posición del planeta
            return cont;
        } else {
            //Si no se encuentra se lanza excepción
            throw new Excepcionapp(Excepcionapp.PLANETAINCORRECTO);
        }
    }

    /**
     * Carga los datos en memoria
     */
    private void cargadatos() {
        //Si existe la carpeta se leen los ficheros
        if (Metodos.existecarpeta()) {
            leeficheros();
        } else {
            //Si no existe la carpeta se crea
            Metodos.creacarpeta();
        }
    }

    /**
     * Graba los ficheros de los planetas
     */
    public void grabaficheros() {
        //Para cada planeta se graba su fichero
        for (Planeta elplaneta : planetas) {
            elplaneta.saveFile();
        }
    }

    /**
     * Lee los ficheros de los planetas
     */
    private void leeficheros() {
        //Para cada planeta se lee su fichero
        for (Planeta elplaneta : planetas) {
            elplaneta.readFile();
        }
    }

    /**
     * Se crean los planetas
     */
    private void creaplanetas() {
        //Se inicializa la lista de planetas
        planetas = new ArrayList<>();
        String[] nopermitidos;

        nopermitidos = new String[3];
        nopermitidos[0] = "Andorian";
        nopermitidos[1] = "Nibirian";
        nopermitidos[2] = "Klingon";
        //Se añade el planeta Vulcano que tiene como razas no permitidas Andorian, Nibirian y Klingon
        planetas.add(new Planeta("Vulcano", nopermitidos, 0));
        
        //Se añade el planeta Nibiru que admite todos los tipos de seres
        nopermitidos = new String[0];
        planetas.add(new Planeta("Nibiru", nopermitidos, 0));

        nopermitidos = new String[2];
        nopermitidos[0] = "Vulcan";
        nopermitidos[1] = "Nibirian";
        //Se añade el planeta Andoria que no admite seres vulcan ni nibirian.
        planetas.add(new Planeta("Andoria", nopermitidos, 0));

        nopermitidos = new String[1];
        nopermitidos[0] = "Nibirian";
        //Se añade el planeta Kronos que no admite seres nibirian y tiene un máximo de 30 habitantes
        planetas.add(new Planeta("Kronos", nopermitidos, 30));
    }

}

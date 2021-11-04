/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startucomartur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase principal de la aplicación
 * @author arturv
 */
public class Startucomartur {

    /** Main
     *  Inicio de la aplicación
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Se declara una variable de la clase Aplicacion que contiene todo el funcionamiento de esta
        Aplicacion aplicacion = new Aplicacion();
        String leido="";
        String argumentos[];
        //Si no se han pasado argumentos
        if(args.length==0)
        {
            System.out.println("Versión de consola. Copiar y pegar entrada. La salida se guardará en salida.txt");
            //Se lee de la entrada standard hasta que se introduzca x como primer parámetro
              BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
              do
              {
                  try {
                      //lee una linea
                      leido = lector.readLine();
                      System.out.println(leido);
                      //Separa la linea por el caracter espacio
                      argumentos = leido.split(" ");
                      //La aplicación procesa la lista de argumentos obtenidos
                      aplicacion.procesa(argumentos);
                  } catch (IOException ex) {
                      //Caso de error de entrada
                      Logger.getLogger(Startucomartur.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  
              }while(leido.charAt(0)!='x' && leido.charAt(0)!='X');
              //Cuando se introduce x se termina la aplicación
              //Se guarda la salida de la aplicación en un fichero
              aplicacion.guardasalida();
        }
        else
        {
            //Si se han pasado argumentos se ejecuta la orden y no se guarda salida en archivo.
            aplicacion.procesa(args);
        }
        
    }  
}

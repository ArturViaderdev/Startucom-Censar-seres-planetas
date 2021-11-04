/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startucomartur;

import java.util.Arrays;
import java.util.List;

/**
 *Clase que controla las excepciones
 * @author arturv
 */
public class Excepcionapp extends Exception {
    //Defino variables estáticas para llamar más comodamente al tipo de excepción
    public static final int NADIEREGISTRADO = 0;
    public static final int NUMARGUMENTOS = 1;
    public static final int ESPECIEINCORRECTA = 2;
    public static final int PLANETAINCORRECTO = 3;
    public static final int DATOINCORRECTO = 4;
    public static final int NOSERPLANETA = 5;
    public static final int EXISTESER = 6;
    public static final int NOEXISTESER = 7;
    public static final int SERNOMODIFICADO = 8;
    public static final int OPINCORRECTA = 9;
    public static final int EDADINCORRECTA = 10;
    public static final int MEDITACIONINCORRECTO = 11;
    public static final int FUERZAINCORRECTO = 12;
    public static final int MAXSERES = 13;
    public static final int VALORINCORRECTO = 14;
    
    //Guarda el código de la excepción
    private int codigo;
    
    //Lista de mensajes
    private final List<String> messages = Arrays.asList(
            "Error 000:Nadie registrado.",
            "Error 001:Número de paŕametros incorrecto.",
            "Error 002: Especie incorrecta",
            "Error 003: Planeta incorrecto.",
            "Error 004: Parámetro incorrecto.",
            "Error 005: No se puede registrar un ser de este tipo en este planeta.",
            "Error 006: Ya existe un ser censado con ese nombre.",
            "Error 007: No existe un ser con ese nombre.",
            "Error 008: El ser no permite ser modificado.",
            "Error 009: Operación incorrecta.",
            "Error 010: Edad incorrecta.",
            "Error 011: Nivel de meditación incorrecto.",
            "Error 012: Varlor de fuerza incorrecto.",
            "Error 013: No se pueden añadir más seres a este planeta",
            "Error 014: Valor incorrecto"
    );
    
    /**
     * Constructor
     * @param codigo Código de la excepción debe ser pasado.
     */
    public Excepcionapp(int codigo)
    {
        this.codigo = codigo;
    }
    
    /**
     * Devuelve el mensaje de la excepción.
     * @return Mensaje
     */
    @Override
    public String getMessage() {
        return messages.get(codigo);
    }
    
    
}

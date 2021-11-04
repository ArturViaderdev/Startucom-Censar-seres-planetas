/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startucomartur;

/**
 * Define ser
 * La clase es abstracta, contiene los datos comunes a todos los tipos de seres
 *  no se puede declarar una instancia de esta. Solo se pueden declarar sus hijas.
 * @author arturv
 */
public abstract class Ser {
    //Nombre del ser
    private String nombre;
    /**
     * Constructor
     * @param nombre Nombre del ser
     */
    public Ser(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el nombre del ser
     * @return Nombre del ser
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del ser
     * @param nombre Nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    } 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startucomartur;

/**
 * Define el ser de raza Andorian
 * @author arturv
 */
public class Andorian extends Ser {

    /**
     * Conversión a string
     * @return Clase convertida a string
     */
    @Override
    public String toString() {
        return "Andorian{nombre=" + super.getNombre() + ",aenar=" + aenar + '}';
    }
    
    //Indica si el ser es Aenar o no
    Boolean aenar;

    /**
     * Devuelve aenar
     * @return aenar
     */
    public Boolean getAenar() {
        return aenar;
    }

    /**
     * Set de aenar
     * @param aenar 
     */
    public void setAenar(Boolean aenar) {
        this.aenar = aenar;
    }
    
    /**
     * Constructor
     * @param nombre Nombre del ser
     * @param aenar Si es aenar o no.
     */
    public Andorian(String nombre, Boolean aenar) {
        super(nombre);
        this.aenar=aenar;
    }
    
    
}

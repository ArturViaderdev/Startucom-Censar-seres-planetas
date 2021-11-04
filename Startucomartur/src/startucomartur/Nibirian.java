/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startucomartur;

/**
 * Define el ser de raza nibirian
 * @author arturv
 */
public class Nibirian extends Ser{
    //Convierte la clase a string
    @Override
    public String toString() {
        String veg;
        if(carnivoro)
        {
            veg = "novegetarian";
        }
        else
        {
            veg = "vegetarian";
        }
        return "Nibirian{nombre=" + super.getNombre() + " " + veg + '}';
    }
    //Indica si es carnivoro
    private boolean carnivoro;

    /**
     * Get de carnivoro
     * @return carnivoro
     */
    public boolean isCarnivoro() {
        return carnivoro;
    }

    /**
     * Set de carnivoro
     * @param carnivoro Nuevo valor
     */
    public void setCarnivoro(boolean carnivoro) {
        this.carnivoro = carnivoro;
    }
    
    /**
     * Constructor
     * @param nombre Nombre del ser
     * @param carnivoro Si es carnivoro o no
     */
    public Nibirian(String nombre, Boolean carnivoro) {
        super(nombre);
        this.carnivoro=carnivoro;
    }
    
}

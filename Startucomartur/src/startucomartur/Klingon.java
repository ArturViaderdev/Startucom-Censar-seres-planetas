/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startucomartur;

/**
 * Define el ser de raza klingon
 * @author arturv
 */
public class Klingon extends Ser
{
    /**
     * Convierte la clase a string
     * @return Clase convertida a string
     */
    @Override
    public String toString() {
        return "Klingon{nombre=" + super.getNombre()+ ",fuerza=" + fuerza + '}';
    }
    //Fuerza del klingon
    private int fuerza;

    /**
     * Ger de fuerza
     * @return Fuerza
     */
    public int getFuerza() {
        return fuerza;
    }

    /**
     * Cambia la fuerza
     * @param fuerza Nueva fuerza
     * @throws Excepcionapp Puede generar una excepción si la fuerza no es correcta
     */
    public void setFuerza(int fuerza) throws Excepcionapp {
        //Si la fuerza es menor de 50 o mayor de 350 se genera excepción.
         if (fuerza < 50 || fuerza > 350) {
            throw new Excepcionapp(Excepcionapp.FUERZAINCORRECTO);
        } else {    
            this.fuerza = fuerza;
        }
    }
    
    /**
     * Constructor
     * @param nombre Nombre del klingon
     * @param fuerza Fuerza
     * @throws Excepcionapp Puede generar una excepción si la fuerza no es correcta
     */
    public Klingon(String nombre, int fuerza) throws Excepcionapp {
        super(nombre);
        //Se introduce la fuerza
        setFuerza(fuerza);
    }  
}

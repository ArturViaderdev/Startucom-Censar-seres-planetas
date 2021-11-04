/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startucomartur;

/**
 * Define ser vulcan
 * @author arturv
 */
public class Vulcan extends Ser{
    /**
     * Convierte a string
     * @return Vulcan convertido a string
     */
    @Override
    public String toString() {
        return "Vulcan{nombre=" + super.getNombre() + ",meditacion=" + meditacion + '}';
    }
    //Nivel de meditación
    int meditacion;

    /**
     * Devuelve el nivel de meditación
     * @return Nivel de meditación
     */
    public int getMeditacion() {
        return meditacion;
    }

    /**
     * Cambia el nivel de meditación
     * @param meditacion Nuevo nivel de meditación
     * @throws Excepcionapp Puede lanzar excepción si el nuevo nivel no es correcto
     */
    public void setMeditacion(int meditacion) throws Excepcionapp {
         if (meditacion < 0 || meditacion > 10) {
            throw new Excepcionapp(Excepcionapp.MEDITACIONINCORRECTO);
        } else {    
            this.meditacion = meditacion;
        }
    }
    
    /**
     * Constructor
     * @param nombre Nombre del ser
     * @param meditacion Nivel de meditación
     * @throws Excepcionapp Puede lanzar excepción si el nivel de meditación no es correcto
     */
    public Vulcan(String nombre,int meditacion) throws Excepcionapp {
        super(nombre);
        setMeditacion(meditacion);
    }   
}

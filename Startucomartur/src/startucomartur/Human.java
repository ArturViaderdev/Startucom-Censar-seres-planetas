/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startucomartur;

/**
 * Define el ser de especie humano
 * @author arturv
 */
public class Human extends Ser{

    /**
     * Convierte a string la clase
     * @return Clase convertida a string
     */
    @Override
    public String toString() {
        return "Human{nombre=" + super.getNombre()+ ",edad=" + edad + '}';
    }

    /**
     * Edad del humano
     */
    private int edad;

    /**
     * Get de edat
     * @return La edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Permite cambiar la edad
     * @param edad Nueva edad
     * @throws Excepcionapp Puede generar una excepción si la edad es incorrecta.
     */
    public void setEdad(int edad) throws Excepcionapp {
        //Si la edad es menor de 0 o mayor de 130 se genera excepción.
         if (edad < 0 || edad > 130) {
            throw new Excepcionapp(Excepcionapp.EDADINCORRECTA);
        } else {    
            this.edad = edad;
        }
    }
  
    /**
     * Constructor
     * @param nombre Nombre del humano
     * @param edad Edad del humano
     * @throws Excepcionapp Puede generar una excepción si la edad es incorrecta
     */
    public Human(String nombre, int edad) throws Excepcionapp {
        super(nombre);
        //Se inserta la edad
        setEdad(edad);
    }

}

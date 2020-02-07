/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import java.util.LinkedList;

/**
 *
 * @author obatres_
 */
public class TablaDeSimbolos extends LinkedList<Simbolo>{

    public TablaDeSimbolos() {
        super();
    }
    /**
     * Método que busca una variable en la tabla de símbolos y devuelve su valor.
     * @param id Identificador de la variable que quiere buscarse
     * @return Valor de la variable que se buscaba, si no existe se devuelve nulo
     */
    Object getValor(String id) {
        for(Simbolo s:this){
            if(s.getId().equals(id)){
                return s.getValor();
            }
        }
        System.out.println("La variable "+id+" no existe en este ámbito.");
        return "Valor Desconocido";
    }  
    
    Object getTipo(String id){
        for (Simbolo s : this) {
            if(s.getId().equals(id)){
                return s.getT();
            }
        }
        System.out.println("El valor de la variable"+id+"no existe en este ambito");
        return "Tipo Desconocido";
    }
    
    /**
     * Método que asigna un valor a una variable específica, si no la encuentra 
     * no realiza la asignación y despliega un mensaje de error.
     * @param id Identificador de la variable que quiere buscarse
     * @param valor Valor que quiere asignársele a la variable buscada
     */
    void setValor(String id, Object valor) {
        for(Simbolo s:this){
            if(s.getId().equals(id)){
                s.setValor(valor);
                return;
            }
        }
        System.out.println("La variable "+id+" no existe en este ámbito, por lo "
                + "que no puede asignársele un valor.");
    }    
}

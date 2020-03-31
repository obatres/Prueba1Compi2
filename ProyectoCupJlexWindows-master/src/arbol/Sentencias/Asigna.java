/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Sentencias;

import arbol.Expresion;

/**
 *
 * @author obatres_
 */
public class Asigna {

    /**
     * @return the identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @return the exp
     */
    public Expresion getExp() {
        return exp;
    }
    private String identificador;
    private Expresion exp;

    public Asigna(String identificador, Expresion exp) {
        this.identificador = identificador;
        this.exp = exp;
    }
    

 
    
    
    
}

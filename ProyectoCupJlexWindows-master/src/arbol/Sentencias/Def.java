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
public class Def {

    /**
     * @return the exp
     */
    public Expresion getExp() {
        return exp;
    }
    private Expresion exp;

    public Def(Expresion exp) {
        this.exp = exp;
    }
    
    
}

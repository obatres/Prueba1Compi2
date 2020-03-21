/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones;

import arbol.Expresion;
import arbol.id;

/**
 *
 * @author obatres_
 */
public class Parametro {

    /**
     * @return the ValorParametro
     */
    public Expresion getValorParametro() {
        return ValorParametro;
    }

    /**
     * @param ValorParametro the ValorParametro to set
     */
    public void setValorParametro(Expresion ValorParametro) {
        this.ValorParametro = ValorParametro;
    }

    /**
     * @param IdParametro the IdParametro to set
     */
    public void setIdParametro(String IdParametro) {
        this.IdParametro = IdParametro;
    }

    /**
     * @return the IdParametro
     */
    public String getIdParametro() {
        return IdParametro;
    }
    
    private Expresion ValorParametro;
    private String IdParametro;

    public Parametro(Expresion ValorParametro, String IdParametro) {
        this.ValorParametro = ValorParametro;
        this.IdParametro = IdParametro;
    }

    public Parametro(String IdParametro) {
        this.IdParametro=IdParametro;
    }
    
    
}

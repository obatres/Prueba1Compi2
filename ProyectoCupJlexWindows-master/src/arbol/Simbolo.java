/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;
import arbol.Tipo;
/**
 *
 * @author obatres_
 */
public class Simbolo {

    /**
     * @return the t
     */
    public Tipo getT() {
        return t;
    }

    

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the valor
     */
    public Object getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Object valor) {
        this.valor = valor;
    }
    //private final TipoSimbolo tipo;
    private final String id;
    private final Tipo t;
    private Object valor;

    /**
     *
     * @param id
     * @param tipo
     */
    public Simbolo(String id, Tipo t){
        this.t=t;
        this.id=id;
    }
    public static enum TipoSimbolo {
        NUMERO,
        CADENA
    }
    
}

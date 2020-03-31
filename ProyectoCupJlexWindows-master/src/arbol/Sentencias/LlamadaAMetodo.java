/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Sentencias;

/**
 *
 * @author obatres_
 */
public class LlamadaAMetodo {

    /**
     * @return the Identificador
     */
    public String getIdentificador() {
        return Identificador;
    }

    /**
     * @return the llamada
     */
    public Llamada getLlamada() {
        return llamada;
    }
    private String Identificador;
    private Llamada llamada;

    public LlamadaAMetodo(String Identificador, Llamada llamada) {
        this.Identificador = Identificador;
        this.llamada = llamada;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

/**
 *
 * @author obatres_
 */
public class Single extends Expresion{

    /**
     * @return the Valor
     */
    public String getValor() {
        return Valor;
    }

    /**
     * @param Valor the Valor to set
     */
    public void setValor(String Valor) {
        this.Valor = Valor;
    }

    /**
     * @return the tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    private String Valor;
    private Tipo tipo;

    public Single(String Valor, Tipo tipo) {
        this.Valor = Valor;
        this.tipo = tipo;
    }
    
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        return this.Valor;
    }

    @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        return this.tipo;
    }
    
}

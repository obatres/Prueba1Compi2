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
public class Asignacion extends Instruccion{

    private final String id;
    
    private final Operacion valor;

    public Asignacion(String id, Operacion valor) {
        this.id = id;
        this.valor = valor;
    }
    
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        ts.setValor(id, valor.ejecutar(ts));
        return null;
    }
    
}

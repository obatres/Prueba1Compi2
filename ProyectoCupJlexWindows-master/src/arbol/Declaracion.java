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
public class Declaracion extends Instruccion{

    private final String id;

    Simbolo.Tipo tipo;

    public Declaracion(String a, Simbolo.Tipo t) {
        id = a;
        tipo = t;
    }
       
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
       ts.add(new Simbolo(id,tipo));
       return null;
    }
    
}

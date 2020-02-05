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
public class Imprimir extends Instruccion{
    
    private final Expresion contenido;

    public Imprimir(Expresion contenido) {
        this.contenido = contenido;
    }
    
    /**
     *
     * @param ts
     * @return
     */
    @Override
    public Object ejecutar( TablaDeSimbolos ts ){
        System.out.println(contenido.ejecutar(ts).toString());
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;
 import InterfazGrafica.VentanaPrincipal;
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
        VentanaPrincipal.consola =contenido.ejecutar(ts).toString()+"\n";
        return null;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        String nodo = "nodo" + ++cont;
        builder.append(nodo).append(" [label=\"Imprimir\"];\n");
        builder.append(parent).append(" -> ").append(nodo).append(";\n");

        cont = contenido.Dibujar(builder, nodo, cont);
        return cont;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;
 import InterfazGrafica.VentanaPrincipal;
import java.util.ArrayList;
/**
 *
 * @author obatres_
 */
public class Imprimir extends Instruccion{

    private final Expresion contenido;

    ArrayList<Object> Salida = new ArrayList<Object>();
    
    String sal="";
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
        if (contenido.ejecutar(ts) instanceof ArrayList){
            Salida  = (ArrayList<Object>) contenido.ejecutar(ts);
            for (Object t : Salida) {
                if (t instanceof Expresion){
                    System.out.println(((Expresion)t).ejecutar(ts));
                    sal  += ((Expresion)t).ejecutar(ts).toString()+"\n";                    
                }
            }
            VentanaPrincipal.consola =sal;            
        }else{
            //System.out.println("no se puede imprimir el valor, es desconocido");
            System.out.println(contenido.ejecutar(ts).toString());
            VentanaPrincipal.consola = contenido.ejecutar(ts).toString()+"\n";
        }
        
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

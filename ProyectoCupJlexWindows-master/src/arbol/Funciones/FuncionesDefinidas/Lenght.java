/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones.FuncionesDefinidas;

import InterfazGrafica.VentanaPrincipal;
import arbol.Expresion;
import arbol.Single;
import arbol.TablaDeSimbolos;
import arbol.Tipo;
import java.util.ArrayList;

/**
 *
 * @author obatres_
 */
public class Lenght  extends Expresion{
    private Expresion exp;
    
    boolean banderaVector=true;
    int contador=0;
    public Lenght(Expresion exp) {
        this.exp = exp;
    }
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        if( exp.ejecutar(ts) instanceof ArrayList){
            for (Object o : (ArrayList)exp.ejecutar(ts)) {
                if(o instanceof Single){
                    contador++;
                }
            }
        }
        System.out.println(contador);
        VentanaPrincipal.consola+="Tamaño de "+contador;
        return contador;
    }

    @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        return exp.GetTipo(ts);
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        return 0;
    }
    
}

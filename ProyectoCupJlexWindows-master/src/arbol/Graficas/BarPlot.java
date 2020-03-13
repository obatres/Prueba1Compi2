/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Graficas;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.TablaDeSimbolos;
import java.util.ArrayList;

/**
 *
 * @author obatres_
 */
public class BarPlot extends Instruccion{

    private ArrayList<Object> ParametrosBarPlot;

    public BarPlot(ArrayList<Object> ParametrosBarPlot) {
        this.ParametrosBarPlot = ParametrosBarPlot;
    }  
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

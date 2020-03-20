/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.Nodo;
import arbol.Simbolo;
import arbol.TablaDeSimbolos;
import java.util.ArrayList;
import java.util.LinkedList;


/**
 *
 * @author obatres_
 */
public class LlamadaFuncion extends Instruccion{

    private ArrayList<Expresion> ParametrosLlamada;
    private String identificadorLlamada;
    
    
    LinkedList<Funcion> TablaFunciones = proyectocupjlexwindows.ProyectoCupJlexWindows.tf;
    
    
    ArrayList<Parametro> ParametrosDeclaracion;
    LinkedList<Nodo> InstruccionesDeclaracion;
    public LlamadaFuncion(ArrayList<Expresion> ParametrosLlamada, String identificadorLlamada) {
        this.ParametrosLlamada = ParametrosLlamada;
        this.identificadorLlamada = identificadorLlamada;
    }

    public LlamadaFuncion(String identificadorLlamada) {
        this.identificadorLlamada = identificadorLlamada;   
    }
    
    
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        ArrayList<Object> Vector;
        if (proyectocupjlexwindows.ProyectoCupJlexWindows.tf.Existe(identificadorLlamada)){
            TablaDeSimbolos tablalocal = new TablaDeSimbolos();
            tablalocal.addAll(ts);
            ParametrosDeclaracion=proyectocupjlexwindows.ProyectoCupJlexWindows.tf.getParametros(identificadorLlamada);
            InstruccionesDeclaracion=proyectocupjlexwindows.ProyectoCupJlexWindows.tf.getInstrucciones(identificadorLlamada);
            if(ParametrosLlamada==null&&ParametrosDeclaracion==null){
                System.out.println("funcion sin parametros");
                for (Nodo n : InstruccionesDeclaracion) {
                    if( n instanceof Instruccion){
                        ((Instruccion) n).ejecutar(tablalocal);
                    }
                }
            }else if(ParametrosLlamada.size()==ParametrosDeclaracion.size()){
                for (int i = 0; i < ParametrosLlamada.size(); i++) {
                    if(ParametrosLlamada.get(i).ejecutar(tablalocal) instanceof Default){
                        System.out.println("Valor Default");
                    }else{
                        Vector = new ArrayList<>();
                        Vector.add(0,ParametrosLlamada.get(i).ejecutar(tablalocal));
                        tablalocal.add(new Simbolo(ParametrosDeclaracion.get(i).getIdParametro(), ParametrosLlamada.get(i).GetTipo(tablalocal)));
                        tablalocal.setValor(ParametrosDeclaracion.get(i).getIdParametro(),Vector,ParametrosLlamada.get(i).GetTipo(tablalocal));
                    }
                }                
                for (Nodo n : InstruccionesDeclaracion) {
                    if( n instanceof Instruccion){
                        ((Instruccion) n).ejecutar(tablalocal);
                    }
                }
                System.out.println(tablalocal);
                System.out.println("cantidad de parametros coincide");
            }else{
                System.out.println("cantidad de parametros no coincide");
            }
        }else{
            System.out.println("Esta funcion "+identificadorLlamada+" no esta definida"); 
        }
        return null;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        return 0;
    }
    
}

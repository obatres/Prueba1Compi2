/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.Nodo;
import arbol.Retorno.Retorno;
import arbol.Simbolo;
import arbol.Single;
import arbol.TablaDeSimbolos;
import arbol.Tipo;
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
                //LLAMADA SIN PARAMETROS
                
                // <editor-fold desc="EJECUCION DE INSTRUCCIONES">> 
                for (Nodo n : InstruccionesDeclaracion) {
                    if( n instanceof Instruccion){
                        ((Instruccion) n).ejecutar(tablalocal);                         
                    }else if(n instanceof Expresion){
                            // <editor-fold desc="RETORNO">> 
                            return ((Retorno) n).ejecutar(tablalocal);
                            // </editor-fold>                                                
                    }
                }
                // </editor-fold>

            }else if(ParametrosLlamada.size()==ParametrosDeclaracion.size()){
                // <editor-fold desc="LLAMADA CON PARAMETROS">> 
                for (int i = 0; i < ParametrosLlamada.size(); i++) {
                    
                    if(ParametrosLlamada.get(i).GetTipo(tablalocal).tp.equals(Tipo.tipo.DEF)){
                        // <editor-fold desc="PARAMETROS DEFAULT">> 
                        Vector = new ArrayList<>();
                        Vector.add(0,ParametrosDeclaracion.get(i).getValorParametro().ejecutar(tablalocal));
                        tablalocal.add(new Simbolo(ParametrosDeclaracion.get(i).getIdParametro(), ParametrosDeclaracion.get(i).getValorParametro().GetTipo(tablalocal)));
                        tablalocal.setValor(ParametrosDeclaracion.get(i).getIdParametro(),Vector,ParametrosDeclaracion.get(i).getValorParametro().GetTipo(tablalocal));                       
                        // </editor-fold>
                    }else{
                        // <editor-fold desc="PARAMETROS GUARDADOS">> 
                        Vector = new ArrayList<>();
                        Vector.add(0,ParametrosLlamada.get(i).ejecutar(tablalocal));
                        tablalocal.add(new Simbolo(ParametrosDeclaracion.get(i).getIdParametro(), ParametrosLlamada.get(i).GetTipo(tablalocal)));
                        tablalocal.setValor(ParametrosDeclaracion.get(i).getIdParametro(),Vector,ParametrosLlamada.get(i).GetTipo(tablalocal));
                        // </editor-fold>
                    }
                }
                // </editor-fold>
                
                // <editor-fold desc="EJECUCION DE INSTRUCCIONES">> 
                for (Nodo n : InstruccionesDeclaracion) {
                    if( n instanceof Instruccion){
                        ((Instruccion) n).ejecutar(tablalocal);                         
                    }else if(n instanceof Expresion){
                            // <editor-fold desc="RETORNO">> 
                            return ((Retorno) n).ejecutar(tablalocal);
                            // </editor-fold>                                                     
                    }
                }
                // </editor-fold>
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

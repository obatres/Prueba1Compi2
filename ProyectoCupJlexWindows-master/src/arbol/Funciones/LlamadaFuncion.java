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
                // <editor-fold desc="LLAMADA SIN PARAMETROS">> 
                for (Nodo n : InstruccionesDeclaracion) {
                    if( n instanceof Instruccion){
                        // <editor-fold desc="INSTRUCCION">>    
                        if(n instanceof Retorno){
                            // <editor-fold desc="RETORNO">> 
                            System.out.println("retorno");
                            if (((Retorno) n).ejecutar(ts)==(Object)0){
                                // <editor-fold desc="SIN VALOR">> 
                                return null;
                                // </editor-fold>
                            }else{
                                // <editor-fold desc="CON VALOR">> 
                                System.out.println("retorno valor");
                                // </editor-fold>
                            }
                            // </editor-fold>
                        }else{
                           ((Instruccion) n).ejecutar(tablalocal);                            
                        }
                        // </editor-fold>
                    }
                }
                // </editor-fold>
            }else if(ParametrosLlamada.size()==ParametrosDeclaracion.size()){
                for (int i = 0; i < ParametrosLlamada.size(); i++) {
                    if(ParametrosLlamada.get(i).GetTipo(ts).tp.equals(Tipo.tipo.DEF)){
                        Vector = new ArrayList<>();
                        Vector.add(0,ParametrosDeclaracion.get(i).getValorParametro().ejecutar(tablalocal));
                        tablalocal.add(new Simbolo(ParametrosDeclaracion.get(i).getIdParametro(), ParametrosDeclaracion.get(i).getValorParametro().GetTipo(tablalocal)));
                        tablalocal.setValor(ParametrosDeclaracion.get(i).getIdParametro(),Vector,ParametrosDeclaracion.get(i).getValorParametro().GetTipo(tablalocal));                       
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

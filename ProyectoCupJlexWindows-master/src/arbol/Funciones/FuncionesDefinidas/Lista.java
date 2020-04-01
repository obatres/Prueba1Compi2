/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones.FuncionesDefinidas;

import arbol.Expresion;
import arbol.Funciones.LlamadaFuncionExp;
import arbol.Single;
import arbol.TablaDeSimbolos;
import arbol.Tipo;
import arbol.id;
import java.util.ArrayList;

/**
 *
 * @author obatres_
 */
public class Lista extends Expresion{
    private ArrayList<Object> ListaExp;

    ArrayList<Object> salidatotal = new ArrayList<>();   
    
    public Lista(ArrayList<Object> ListaExp) {
        this.ListaExp = ListaExp;
    }

        
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {

        for (Object t :ListaExp) {
            //System.out.println(t.getClass()+"  TIPO DE ELEMENTO EN LISTA");
            if(t instanceof Single){
                // <editor-fold desc="SINGLE">> 
                salidatotal.add(((Single) t).ejecutar(ts));
                // </editor-fold>
            }else if(t instanceof id){
                // <editor-fold desc="ID">> 
                if(((id) t).ejecutar(ts)instanceof ArrayList){
                    // <editor-fold desc="VIENE VECTOR">> 
                    for (Object object : (ArrayList)((id) t).ejecutar(ts)) {
                        salidatotal.add(object);
                    }
                    // </editor-fold>
                }else if(((id) t).ejecutar(ts)instanceof String){
                    // <editor-fold desc="VIENE STRING-SINGLE">> 
                    salidatotal.add(((id) t).ejecutar(ts).toString());
                    // </editor-fold>
                }else if(((id) t).ejecutar(ts) instanceof Single){
                    // <editor-fold desc="VIENE SINGLE">> 
                    salidatotal.add(((Single) t).ejecutar(ts));
                    // </editor-fold>
                }else if(((id) t).ejecutar(ts) instanceof Lista){
                    System.out.println("VIENE LISTAAAAA");
                }
                // </editor-fold>
            }else if(t instanceof LlamadaFuncionExp){
                // <editor-fold desc="LLAMADA A FUNCION">> 
                if(((LlamadaFuncionExp) t).GetTipo(ts)==null){
                    // <editor-fold desc="DEBERIA SER VECTOR">>
                    if(((LlamadaFuncionExp) t).ejecutar(ts) instanceof ArrayList){
                        // <editor-fold desc="VIENE ARRAY">> 
                        for (Object object : (ArrayList)((LlamadaFuncionExp) t).ejecutar(ts)) {
                            salidatotal.add(object);
                        }
                        // </editor-fold>
                    }
                    // </editor-fold>
                }else{
                    
                }
                // </editor-fold>
            }else if(t instanceof C){
                // <editor-fold desc="C">> 
                //System.out.println(((C) t).ejecutar(ts)+"VALOR C");
                // </editor-fold>
            }else if(t instanceof Lista){
                System.out.println(((Lista) t).ejecutar(ts).getClass()+" CLASE LISTA");
                salidatotal.add(((Lista) t).ejecutar(ts));
            }
                
        }
        return salidatotal;
    }

    @Override
    public Tipo GetTipo(TablaDeSimbolos ts) {
        return new Tipo(Tipo.tipo.LISTA);
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        return cont;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Funciones;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.TablaDeSimbolos;
import java.util.ArrayList;

/**
 *
 * @author obatres_
 */
public class DefinicionDeFuncion extends Instruccion{

    private Funcion f;

    public DefinicionDeFuncion(Funcion f) {
        this.f = f;
    }
    
    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
//        TablaDeSimbolos tablalocal = new TablaDeSimbolos();
//        tablalocal.addAll(ts);
//        for (Simbolo s : tablalocal) {
//            System.out.println(s.getId());
//        }
        if(!proyectocupjlexwindows.ProyectoCupJlexWindows.tf.Existe(f.getIdentificadorFuncion())){
            proyectocupjlexwindows.ProyectoCupJlexWindows.tf.add(f);           
        }else{
            System.out.println("Esta funcion ya esta definida");
        }

//        ArrayList<Parametro> p;
//        for (Funcion funcion : proyectocupjlexwindows.ProyectoCupJlexWindows.tf) {
//            p=funcion.getParametrosFuncion();
//            for (Parametro parametro : p) {
//                System.out.println(parametro.getIdParametro());
//                System.out.println(parametro.getValorParametro().ejecutar(ts));
//            }
//        }
//        
//        System.out.println("va a definir una funcion nueva");
       System.out.println(proyectocupjlexwindows.ProyectoCupJlexWindows.tf);
        return null;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        return 0;
    }
    
}

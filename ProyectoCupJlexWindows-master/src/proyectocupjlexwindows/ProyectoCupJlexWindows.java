/*
 * Ejemplo desarrollado por Erick Navarro
 * Blog: e-navarro.blogspot.com
 * Julio - 2018
 */

package proyectocupjlexwindows;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.Nodo;
import arbol.TablaDeSimbolos;
import java.io.FileInputStream;
import java.util.LinkedList;

/**
 * Clase principal de la aplicación
 * @author Erick
 */
public class ProyectoCupJlexWindows {

    /**
     * @param args argumentos de la linea de comando
     */
    public static void main(String[] args) {
        interpretar("entrada.txt");
    }
    /**
     * Método que interpreta el contenido del archivo que se encuentra en el path
     * que recibe como parámentro
     * @param path ruta del archivo a interpretar
     */
    private static void interpretar(String path) {
        analizadores.Sintactico pars;
        LinkedList<Nodo> AST_arbolSintaxisAbstracta = null;
        try {
            pars=new analizadores.Sintactico(new analizadores.Lexico(new FileInputStream(path)));
            pars.parse();      
            AST_arbolSintaxisAbstracta = pars.getAST();
        } catch (Exception ex) {
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println("Causa: "+ex.getCause());
        } 
        ejecutarAST(AST_arbolSintaxisAbstracta);
    }
    
        /**
     * Recibe una lista de instrucciones y la ejecuta
     * @param ast lista de instrucciones
     */
    private static void ejecutarAST(LinkedList<Nodo> ast) {
        if(ast==null){
            System.out.println("No es posible ejecutar las instrucciones porque\r\n"
                    + "el árbol no fue cargado de forma adecuada por la existencia\r\n"
                    + "de errores léxicos o sintácticos.");
            return;
        }
        //Se crea una tabla de símbolos global para ejecutar las instrucciones.
        TablaDeSimbolos ts=new TablaDeSimbolos();
        //Se ejecuta cada instruccion en el ast, es decir, cada instruccion de 
        //la lista principal de instrucciones.
        for(Nodo ins:ast){
            //Si existe un error léxico o sintáctico en cierta instrucción esta
            //será inválida y se cargará como null, por lo tanto no deberá ejecutarse
            //es por esto que se hace esta validación.
            if(ins!=null)
                if(ins instanceof Expresion)
                {
                    ((Expresion)ins).ejecutar(ts);
                }else if(ins instanceof Instruccion)
                {
                    ((Instruccion)ins).ejecutar(ts);
                }else{
                    
                }
                    //reportar error
                
            
        }
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.Graficas;

import arbol.Expresion;
import arbol.Instruccion;
import arbol.TablaDeSimbolos;
import arbol.Tipo;
import java.util.ArrayList;
import java.lang.NullPointerException;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author obatres_
 */
public class PiePlot extends Instruccion{
    private ArrayList<Object> ParametrosPie;

    ArrayList<Object> Numericos = new ArrayList<>();
    ArrayList<Object> Labels    = new ArrayList<>();
    String main;
    
    public PiePlot(ArrayList<Object> ParametrosPie) {
        this.ParametrosPie = ParametrosPie;
    }

    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        if(ParametrosPie.size()==3){
            System.out.println("Cantidad de parametros correctos");
            if(((Expresion)ParametrosPie.get(0)).ejecutar(ts) instanceof ArrayList){
                if(((Expresion)ParametrosPie.get(0)).GetTipo(ts).tp.equals(Tipo.tipo.DOUBLE)||((Expresion)ParametrosPie.get(0)).GetTipo(ts).tp.equals(Tipo.tipo.INT)){
                    Numericos = (ArrayList<Object>) ((Expresion)ParametrosPie.get(0)).ejecutar(ts);
                                    //System.out.println(Numericos);
                }else{
                    //Parametro incorrecto, se esperaba un listado de numeros
                }
            }
            if(((Expresion)ParametrosPie.get(1)).ejecutar(ts) instanceof ArrayList){
                if(((Expresion)ParametrosPie.get(1)).GetTipo(ts).tp.equals(Tipo.tipo.STRING)){
                    Labels = (ArrayList<Object>) ((Expresion)ParametrosPie.get(1)).ejecutar(ts);
                    //System.out.println(Labels);
                }else{
                    //Parametro incorrecto, se esperaba un listado de labels
                }
            }
            if(((Expresion)ParametrosPie.get(2)).ejecutar(ts) instanceof String){
                main=((Expresion)ParametrosPie.get(2)).ejecutar(ts).toString();
                //System.out.println(main);
            }else{
                //Parametro incorrecto, se esperaba una cadena
            }
        }else{
            //CANTIDAD DE PARAMETROS INCORRECTOS
        }
        
        DefaultPieDataset data = new DefaultPieDataset();
        if(Numericos.size()==Labels.size()){
            for (int i = 0; i < Numericos.size(); i++) {
                data.setValue(((Expresion)Labels.get(i)).ejecutar(ts).toString(),Double.parseDouble(((Expresion)Numericos.get(i)).ejecutar(ts).toString()));
            }            
        }else if(Numericos.size()>Labels.size()){
            //REPORTAR ERROR DE TAMAÑO DE VECTORES
            for (int i = 0; i < Labels.size(); i++) {
                data.setValue(((Expresion)Labels.get(i)).ejecutar(ts).toString(),Double.parseDouble(((Expresion)Numericos.get(i)).ejecutar(ts).toString()));   
            }
            for (int i = Labels.size(); i < Numericos.size(); i++) {
                data.setValue("Desconocido"+i,Double.parseDouble(((Expresion)Numericos.get(i)).ejecutar(ts).toString()));
            }
        }

        
        JFreeChart chart2 = ChartFactory.createPieChart(main, data,true,true,false);
        ChartPanel panel2 = new ChartPanel(chart2);
        JFrame ventana2 = new JFrame("grafica");
        ventana2.getContentPane().add(panel2);
        ventana2.pack();
        ventana2.setVisible(true);
        ventana2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return null;
    }

    @Override
    public int Dibujar(StringBuilder builder, String parent, int cont) {
        return 0;
    }
    
    
    
    
}

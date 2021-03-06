/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import arbol.Expresion;
import arbol.Funciones.TabladeFunciones;
import arbol.Instruccion;
import arbol.Nodo;
import arbol.TablaDeSimbolos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author obatres_
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
    
    public static String consola="";
    public static TabladeFunciones tf = new TabladeFunciones();
    public VentanaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextoDeEntrada = new javax.swing.JEditorPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextoSalida = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        AbrirArchivo = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        EjecutaJfelxCup = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(TextoDeEntrada);

        jTabbedPane1.addTab("tab1", jScrollPane1);

        TextoSalida.setEditable(false);
        TextoSalida.setBackground(new java.awt.Color(255, 255, 255));
        TextoSalida.setSelectedTextColor(new java.awt.Color(51, 153, 0));
        jScrollPane2.setViewportView(TextoSalida);

        jLabel1.setText("Salida:");

        jLabel2.setText("Entrada:");

        jLabel3.setText("ARIT IDE");

        jMenu2.setText("Archivo");

        AbrirArchivo.setText("Abrir");
        AbrirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirArchivoActionPerformed(evt);
            }
        });
        jMenu2.add(AbrirArchivo);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Ejecutar");

        EjecutaJfelxCup.setText("Jflex/CUP");
        EjecutaJfelxCup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EjecutaJfelxCupActionPerformed(evt);
            }
        });
        jMenu3.add(EjecutaJfelxCup);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(242, 242, 242)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(29, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void interpretar() {
        analizadores.Sintactico pars;
        LinkedList<Nodo> AST_arbolSintaxisAbstracta = null;
        String consola=null;
        try {
            pars=new analizadores.Sintactico(new analizadores.Lexico(new StringReader(TextoDeEntrada.getText())));
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
    public void ejecutarAST(LinkedList<Nodo> ast) {
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
                if(ins instanceof Expresion)//Para todas las instacias de la clase Expresion se ejecuta el metodo "ejecutar()" 
                {                           //que recibe como parametro la tabla de simbolos de la misma instancia dentro del arbol
                    ((Expresion)ins).ejecutar(ts); //y devuelve valores de operaciones aritmeticas y logicas
                }else if(ins instanceof Instruccion)
                {                           //Para todas las instancias de la clase Instruccion se ejecuta el metodo "ejecutar()"
                    ((Instruccion)ins).ejecutar(ts);//que recibe como parametro la tabla de simbolos de la misma instancia dentro del arbol
                }else{                              //y devuelve la ejecucion de instrucciones como condicionales o ciclos
                    System.out.println("Error al ejecutar un nivel en el arbol AST");  //reportar error
                }
        this.TextoSalida.setText(consola+"\n");
        }
    }
    
    private void AbrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirArchivoActionPerformed
        JFileChooser jf= new JFileChooser();
        jf.setFileSelectionMode( JFileChooser.FILES_ONLY );
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de Texto (*.txt)", "txt");
        jf.setFileFilter(filtro);
        int seleccion = jf.showOpenDialog(this);
        if(seleccion == JFileChooser.APPROVE_OPTION )
        {       
            try {
                BufferedReader bufferreader;                 
                File file = jf.getSelectedFile();
                bufferreader = new BufferedReader(new FileReader(file.getAbsolutePath()));  
                String linea, contenido= "";
                
                //leeendo linea a linea
                while ((linea = bufferreader.readLine())!=null)
                {
                    contenido += linea +"\n";
                }
                            
                //Agregando texto al editor
                this.TextoDeEntrada.setText(contenido);                
                bufferreader.close();
            } 
            catch (IOException ex) 
            {Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);}
        }
    }//GEN-LAST:event_AbrirArchivoActionPerformed

    private void EjecutaJfelxCupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EjecutaJfelxCupActionPerformed
        this.TextoSalida.setText("");
        consola="";
        interpretar();
    }//GEN-LAST:event_EjecutaJfelxCupActionPerformed

    
  
    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AbrirArchivo;
    private javax.swing.JMenuItem EjecutaJfelxCup;
    private javax.swing.JEditorPane TextoDeEntrada;
    private javax.swing.JTextPane TextoSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import java.util.ArrayList;

/**
 *
 * @author Cristhyan De Marchena & Jorge Díaz
 * <p>
 * Codigos de estudiante:
 * Cristhyan De Marchena:   200082385.
 * Jorge Díaz:              200073025.
 * </p>
 * @see <code>javax.swing.JFrame</code>
 */
public class DisplayWindow extends javax.swing.JFrame{
    public static int axis, ayis;
    public static int[][] MATCHES;
    public static Display canvas;
    private static Thread t;
    public static ArrayList<Structures.Node> NODES;

    /**
     * @throws java.awt.HeadlessException 
     * <p>
     * This function sets the default look that the window which the data of the
     * graph will be displayed.
     * </p>
     */
    public DisplayWindow() throws java.awt.HeadlessException {
        setLayout(null);
        setSize(610, 524);
        setResizable(false);
        setTitle("Click Me <3");
        getContentPane().setBackground(java.awt.Color.black);
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/4 - 305, dim.height/2 - 262);
        
        canvasSetUp();
        
        setVisible(true);
        
        t = new Thread(canvas, "graphicThread");
        t.start();
    }
    
    /**
     * <p>
     * This method initializes the <code>Visual.Display</code> that is going to be
     * used.
     * </p>
     */
    public void canvasSetUp(){
        canvas = new Display();
        canvas.setSize(608, 522);
        canvas.setLocation(1, 1);
        add(canvas);
    }
    
    /**
     * 
     * @param millis It is the time that the thread is going to be sleeping.
     * @throws InterruptedException 
     */
    public static void sleep(long millis) throws InterruptedException{
        t.sleep(millis);
    }
}

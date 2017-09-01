/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author Cristhyan De Marchena & Jorge Díaz
 * Codigos de estudiante:
 * Cristhyan De Marchena:   200082385.
 * Jorge Díaz:              200073025.
 * @see java.awt.Canvas
 */
public class Display extends java.awt.Canvas implements Runnable{
    static int c;
    public static float ZOOM = 1.0f;
    
    /**
     * <p>
     * In order to create an image that the canvas can manipulate to do a zoom 
     * in or out, this method draws the graph information, in other words the 
     * nodes, their information, their connectios among each others.
     * </p>
     * @return <code>img</code> It is an image that the canvas can manipulate in
     * order to zoom in or to zoom out.
     */
    private BufferedImage getDisplay() {
        BufferedImage img = new BufferedImage(608, 522, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        
            g.setColor(java.awt.Color.red);
            if(DisplayWindow.MATCHES.length != 0){
                for (int i = 0; i < DisplayWindow.MATCHES.length; i++) {
                    for (int j = 0; j < DisplayWindow.MATCHES.length; j++) {
                        if (DisplayWindow.MATCHES[i][j] == 1) {
                            if(i < DisplayWindow.NODES.size()){
                                if (DisplayWindow.NODES.get(i) != null) {
                                    int x_1 = DisplayWindow.NODES.get(i).getX() +Structures.Node.SIZE/2;
                                    int y_1 = DisplayWindow.NODES.get(i).getY() +Structures.Node.SIZE/2;

                                    if(j < DisplayWindow.NODES.size()){
                                        if (DisplayWindow.NODES.get(j) != null) {
                                            int x_2 = DisplayWindow.NODES.get(j).getX() +Structures.Node.SIZE/2;
                                            int y_2 = DisplayWindow.NODES.get(j).getY() +Structures.Node.SIZE/2;
                                            g.drawLine(x_1, y_1, x_2, y_2);
                                        }
                                    }
                                } 
                            }
                        }
                    }
                }
            }
            

            for (int i = 0; i < DisplayWindow.NODES.size(); i++) {
                DataWindow.current.setText("Actual: " +DisplayWindow.NODES.get(c%DisplayWindow.NODES.size()).getName());
                g.setColor(java.awt.Color.gray.darker());
                g.fillOval(DisplayWindow.NODES.get(i).getX(), DisplayWindow.NODES.get(i).getY(), Structures.Node.SIZE, Structures.Node.SIZE);
                g.setColor(java.awt.Color.white.brighter());
                g.setFont(new java.awt.Font("TimesNewRoman", java.awt.Font.BOLD, 16));
                if(i < DisplayWindow.NODES.size())
                    g.drawString(DisplayWindow.NODES.get(i).getName(), DisplayWindow.NODES.get(i).getX() +18, DisplayWindow.NODES.get(i).getY() +29);
                
            }
        
        return img;
    }
    
    /**
     * <p>
     * This method overrides the run method from the Thread in order to update 
     * all the information related with the nodes and also to do a zoom in or out.
     * </p>
     */
    @Override
    public void run() {
        c = 0;
        createBufferStrategy(3);
        float[] palette = new float[3];
        java.awt.Color.RGBtoHSB(219, 248, 225, palette);
        setBackground(java.awt.Color.getHSBColor(palette[0], palette[1], palette[2]));
        
        
        addMouseListener(new Mouse());
        addMouseMotionListener(new Mouse());
        
        addMouseWheelListener(new Mouse());
        
        while (true) {            
            java.awt.Graphics g = getBufferStrategy().getDrawGraphics();
            if (DisplayWindow.NODES != null) {
                 g.clearRect(0, 0, 608, 522);
                g.drawImage(getDisplay(),(608-(int)(608*ZOOM))/2,(522-(int)(522*ZOOM))/2,(int)(608*ZOOM),(int)(522*ZOOM),null);
                getBufferStrategy().show();
            }
        }
    }
    
    
    
    /**
     * <p>
     * It is a class that extends the <code>javax.swing.event.MouseInputAdapter</code>
     * class in order to detect when the mouse is clicked and pressed at the same time
     * in order to avoid repetitive process ejecutation.
     * </p>
     * @see <code>javax.swing.event.MouseInputAdapter</code>
     */
    private class Mouse extends javax.swing.event.MouseInputAdapter{
        
        @Override
        public void mousePressed(java.awt.event.MouseEvent e){
            if (DisplayWindow.NODES != null) {
                int x = e.getX();
                int y = e.getY();
                DisplayWindow.NODES.get(c%DisplayWindow.NODES.size()).setCoords(x -(Structures.Node.SIZE/2), y -(Structures.Node.SIZE/2));
                c++;
            }
        }
        
        @Override
        public void mouseDragged(java.awt.event.MouseEvent e){
            updatePos(e);
        }
        
        @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() < 0) {
                    if (ZOOM >= 5) {
                        ZOOM += 0.01f ;
                    }else{
                        ZOOM += 0.5;
                    }
                }else{
                    if (ZOOM > 0.5) {
                        ZOOM -= 0.5f;
                    }else if(ZOOM > 0.25){
                        ZOOM -= 0.05;
                    }
                }
            }
        
        public void updatePos(java.awt.event.MouseEvent e){
            if (DisplayWindow.NODES != null) {
                int x = e.getX();
                int y = e.getY();
                DisplayWindow.NODES.get(c%DisplayWindow.NODES.size()).setCoords(x -(Structures.Node.SIZE/2), y -(Structures.Node.SIZE/2));
                c++;
            }
        }
        
    }
}
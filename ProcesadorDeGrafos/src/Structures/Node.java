/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures;

/**
 *
 * @author Cristhyan De Marchena & Jorge Díaz
 * Codigos de estudiante:
 * Cristhyan De Marchena:   200082385.
 * Jorge Díaz:              200073025.
 */
public class Node {
    String name;
    int x, y;
    public final static int SIZE = 48;

    /**
     * <p>
     * This method creates a node with a given <code>name</code> information 
     * </p>
     * @param name It is the information that will indentfy the node that will 
     * be created.
     */
    public Node(String name) {
        this.name = name;
        this.x = (int)(Math.random() * 514) +25;
        this.y = (int)(Math.random() * 428) +25;
    }
    
    /**
     * <p>
     * This method sets the x, y coordinates of the node in the X and Y axis.
     * </p>
     * @param x It is the X coordinate in which the node will be located in the 
     * X-Axis
     * @param y It is the Y coordinate in which the node will be located in the 
     * Y-Axis
     */
    public void setCoords(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * <p>
     * This methods holds the information of the node.
     * </p>
     * @return <code>name</code>  the variable that indicates de information of 
     * the node.
     */
    public String getName(){
        return this.name;
    } 
    
    /**
     * <p>
     * This methods holds the coordinate of the node in the X axis.
     * </p>
     * @return <code>x</code>  the variable that indicates de X coordinate of 
     * the node.
     */
    public int getX(){
        return this.x;
    }
    
    /**
     * <p>
     * This methods holds the coordinate of the node in the Y axis.
     * </p>
     * @return <code>y</code>  the variable that indicates de Y coordinate of 
     * the node.
     */
    public int getY(){
        return this.y;
    }
}

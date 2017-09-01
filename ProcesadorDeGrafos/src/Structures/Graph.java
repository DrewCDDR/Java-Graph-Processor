package Structures;

import java.util.ArrayList;

/**
 * @author Cristhyan De Marchena & Jorge Díaz
 * Codigos de estudiante:
 * Cristhyan De Marchena:   200082385.
 * Jorge Díaz:              200073025.
 */
public class Graph {
    boolean di;
    boolean normal;
    int cant;
    public int[][] conecctions;
    Node start, end;
    public ArrayList<Node> nodes;

    /**
     * <p>
     * This method creates a graph given a number of N nodes.
     * </p>
     * @param cant It is the number of nodes that the graph will contain when 
     * it's created. 
     */
    public Graph(int cant) {
        this.cant = cant;
        this.start = null;
        this.normal = true;
        this.di = false;
        this.nodes = new ArrayList<Node>();
    }
    
    /**
     * <p>
     * This method sets the adjacency matrix of the graph.
     * </p>
     * @param m It is the bidimensional array that represents the adjacency 
     * matrix of the graph.
     */
    public void setConnections(int[][] m){
        this.conecctions = m;
    }
    
    /**
     * <p>
     * As it names specifies, this method adds a node to the graph.
     * </p>
     * @param node It is the object <code>Structures.Node</code> to be added.
     */
    public void add(Node node){
        this.cant++;
        if (start == null) {
            start = node;
        }else{
            end = node;
        }
        this.nodes.add(node);
    }
    
    /**
     * <p>
     * As it names specifies, this method removes a node given a specific index
     * of reference.
     * </p>
     * @param index It is the position of the node that is going to be removed.
     */
    public void remove(int index){
        this.cant--;
        nodes.remove(index);
    }
}

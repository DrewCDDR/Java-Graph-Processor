/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import java.awt.Rectangle;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 * @author Cristhyan De Marchena & Jorge Díaz
 * <p>
 * Codigos de estudiante:
 * Cristhyan De Marchena:   200082385.
 * Jorge Díaz:              200073025.
 * </p>
 * @see <code>javax.swing.JFrame</code>
 */
public class DataWindow extends javax.swing.JFrame {

    public int hits;
    public static Structures.Graph graph;
    public static javax.swing.JLabel current;
    javax.swing.JLabel nameLabel;
    javax.swing.JButton add, remv, search, addrnd, remvrnd;
    javax.swing.JTextField name;
    javax.swing.JTextArea info;
    javax.swing.JTable table;
    
    /**
     * @throws java.awt.HeadlessException 
     * <p>
     * This function sets the default look that the window which the data of the
     * graph is contained
     * </p>
     */
    public DataWindow() throws java.awt.HeadlessException {
        hits = 0;
        setLayout(null);
        setSize(610, 524);
        setResizable(false);
        setTitle("The Data Goes Here <3");
        getContentPane().setBackground(java.awt.Color.black);
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setDataUI();
        java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width * 3 / 4 - 305, dim.height / 2 - 262);

        graph = new Structures.Graph(0);

        setVisible(true);
    }

    /**
     * <p>
     * This method initializes and adds all the objects that the window may use,
     * such as buttons, labels, text fields, a table, etc, to the window.
     * </p>
     */
    public void setDataUI() {
        nameLabel = new javax.swing.JLabel("Info: ");
        nameLabel.setBackground(java.awt.Color.gray);
        nameLabel.setForeground(java.awt.Color.white);
        nameLabel.setSize(55, 20);
        nameLabel.setLocation(10, 420);
        add(nameLabel);
        
        current = new javax.swing.JLabel("Actual: ");
        current.setBackground(java.awt.Color.gray);
        current.setForeground(java.awt.Color.white);
        current.setSize(100, 20);
        current.setLocation(10, 465);
        add(current);

        name = new javax.swing.JTextField("");
//        name.setFocusable(false);
        name.setSize(80, 20);
        name.setLocation(70, 420);
        add(name);

        add = new javax.swing.JButton("Agregar");
        add.setFocusable(false);
        add.setSize(80, 25);
        add.setLocation(300, 420);
        add.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
                hits++;
            }
        });
        add(add);

        addrnd = new javax.swing.JButton("+");
        addrnd.setFocusable(false);
        addrnd.setSize(80, 25);
        addrnd.setLocation(300, 455);
        addrnd.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddRandomActionPerformed(evt);
                hits++;
            }
        });
        add(addrnd);

        remv = new javax.swing.JButton("Quitar");
        remv.setFocusable(false);
        remv.setSize(80, 25);
        remv.setLocation(400, 420);
        remv.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveActionPerformed(evt);
            }
        });
        add(remv);
        
        remvrnd = new javax.swing.JButton("-");
        remvrnd.setFocusable(false);
        remvrnd.setSize(80, 25);
        remvrnd.setLocation(400, 455);
        remvrnd.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveRandomActionPerformed(evt);
            }
        });
        add(remvrnd);

        search = new javax.swing.JButton("Buscar");
        search.setFocusable(false);
        search.setSize(80, 60);
        search.setLocation(500, 420);
        search.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
                
            }
        });
        add(search);

        info = new javax.swing.JTextArea();
        info.setLocation(180, 420);
        info.setSize(90, 60);
        info.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        info.setText("\"1\" conecta nodos, \"0\" los desconecta.");
        info.setEditable(false);
        info.setBackground(java.awt.Color.black);
        info.setForeground(java.awt.Color.gray.brighter());
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setVisible(true);
        this.add(info);

        table = new javax.swing.JTable() {

        };

        table.setModel(new CustomModel(new String[]{"Nombres"}, 0));
        table.getTableHeader().setForeground(java.awt.Color.gray.darker());
        table.setDragEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(35);
        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.setSize(600, 400);
        table.setLocation(0, 0);
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.putClientProperty("termianteEditOnFocusLost", true);
        table.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                int x = table.getSelectedRow();
                int y = table.getSelectedColumn();
                if (table.getValueAt(x, y).equals("1")) {
                    DisplayWindow.MATCHES[x][y - 1] = 1;
                    graph.conecctions[x][y - 1] = 1;
                } else {
                    DisplayWindow.MATCHES[x][y - 1] = 0;
                    graph.conecctions[x][y - 1] = 0;
                }
            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });

        javax.swing.JScrollPane movement = new javax.swing.JScrollPane(table, javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        movement.setSize(600, 400);
        movement.setLocation(1, 1);
        add(movement);
    }

    /**
     * @param e is the event that occurs when the Add button is clicked.
     * <p>
     * This method creates a node with the info of the text field and then it 
     * adds it to the graph and finally it updates the table.
     * </p>
     * @see <code>java.awt.event.ActionEvent</code>
     */
    public void AddActionPerformed(java.awt.event.ActionEvent e) {
        String validation = name.getText();
        if (!validation.equals("")) {
            add(validation, name.getText());
            generateConnections();
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "El nodo a añadir no tiene informarción.", "Error No. 1", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param e is the event that occurs when the Remove button is clicked.
     * <p>
     * This method deletes a node with the info of the selected row of the table
     * from the graph and then it updates the table.
     * </p>
     * @see <code>java.awt.event.ActionEvent</code>
     */
    public void RemoveActionPerformed(java.awt.event.ActionEvent e) {
        int rowIndex = table.getSelectedRow();
        remove(rowIndex);
    }

    /**
     * @param e is the event that occurs when the Search button is clicked.
     * <p>
     * This method find out whether or not is a node with a given info in the 
     * graph.
     * </p>
     * @see <code>java.awt.event.ActionEvent</code>
     */
    public void SearchActionPerformed(java.awt.event.ActionEvent e) {
        Search(name.getText());
    }

    /**
     * @param e is the event that occurs when the  '+' button is clicked.
     * <p>
     * This method creates N random nodes in order to be added to the graph,
     * then it updates the table.
     * </p>
     * @see <code>java.awt.event.ActionEvent</code>
     */
    public void AddRandomActionPerformed(java.awt.event.ActionEvent e) {
        String validation = name.getText();
        if (!validation.equals("")) {
            try {
                int cant = Integer.parseInt(validation);
                if(graph.nodes.isEmpty()){
                    for (int i = 0; i < cant; i++) {
                        add("" + i, "" + i);
                    }
                }else{
                    int top = graph.nodes.size();
                    for (int i = top; i < (cant +top); i++) {
                        add("" + i, "" + i);
                    }
                }
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(null, "La cantidad de nodos a crear no es un número.", "Error No. 3", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            fillConnectionsRandomly();
            generateConnections();
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "El campo de 'Info:' esta vacío.", "Error No. 2", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * @param e is the event that occurs when the  '+' button is clicked.
     * <p>
     * This method deletes N random nodes from the graph, then it updates the 
     * table.
     * </p>
     * @see <code>java.awt.event.ActionEvent</code>
     */
    public void RemoveRandomActionPerformed(java.awt.event.ActionEvent e) {
        String validation = name.getText();
        if (!validation.equals("")) {
            try {
                int cant = Integer.parseInt(validation);
                
                    for (int i = 0; i < cant; i++) {
                        if(graph.nodes.size() > 1){
                            int index = (int)((Math.random() *(graph.nodes.size() -1)) -1);
                            if (index == graph.nodes.size())
                                index--;
                            remove(index);
                        }else if(graph.nodes.size() == 1){
                            remove(0);
                        }
                    }
                
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(null, "La cantidad de nodos a quitar no es un número.", "Error No. 3", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            generateConnections();
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "El campo de 'Info:' esta vacío.", "Error No. 2", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * <p>
     * This method randomly fills the bi-dimensional array that represents the 
     * adjacency in order to create the connections in a randomly way.
     * </p>
     */
    public void fillConnectionsRandomly() {
        int val;
        javax.swing.table.DefaultTableModel m = (javax.swing.table.DefaultTableModel) table.getModel();
        for (int i = 0; i < m.getRowCount(); i++) {
            for (int j = 1; j < m.getColumnCount(); j++) {
                if (!((i + 1) == j)) {
                    if (Math.random() < 0.5) {
                        val = 0;
                    } else {
                        val = 1;
                    }
                    table.setValueAt("" + val, i, j);
                }
            }
        }
    }

    /**
     * <p>
     * This method creates a node with the given information, <code>validation
     * </code> and zero connections, then adds it to the graph and updates the 
     * table.
     * </p>
     * @param validation if a String that holds whether the text field is empty 
     * or not
     * @param nombre is a String that holds the info of the node.
     */
    public void add(String validation, String nombre) {
        int j;
        for (j = 0; j < table.getRowCount() && !table.getValueAt(j, 0).toString().equals(nombre); j++) {}
        if (j < table.getRowCount() && table.getValueAt(j, 0).toString().equals(nombre)) {
            javax.swing.JOptionPane.showMessageDialog(null, "Este dato ya se encuentra en el grafo ", "Error #4", javax.swing.JOptionPane.ERROR_MESSAGE);
        } else {
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) table.getModel();
            Object[] columns = new Object[table.getRowCount()];
            Object[] rows = new Object[table.getColumnCount()];
            for (int i = 0; i < columns.length; i++) {
                columns[i] = "0";
            }
            model.addColumn(validation, columns);
            for (int i = 1; i < rows.length; i++) {
                rows[i] = "0";
            }
            rows[0] = validation;
            model.addRow(rows);
            javax.swing.table.DefaultTableCellRenderer center = new javax.swing.table.DefaultTableCellRenderer();
            center.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            for (int i = 0; i < rows.length; i++) {
                table.setValueAt("0", i, i + 1);
            }
            for (int i = 0; i <= rows.length; i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(center);
            }
            name.setText("");
            Structures.Node node = new Structures.Node(validation);
            graph.add(node);
        }

    }
    
    /**
     * @param rowIndex is the index that represents where the node that is about
     * to be eliminated is in the table.
     * <p>
     * This method deletes the node at an <code>rowIndex</code> from the table 
     * and from the graph.
     * </p>
     */
    public void remove(int rowIndex){
        if (rowIndex >= 0) {
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) table.getModel();
            model.removeRow(rowIndex);
            ((CustomModel) model).removeColumn(rowIndex + 1);

            graph.remove(rowIndex);
            
            generateConnections();
        }
    }

    /**
     * <p>
     * This method fills all the nodes connections in the graph randomly with a 
     * value of one (has connection) or zero (do not has connection).
     * </p>
     */
    public void generateConnections() {
        javax.swing.table.DefaultTableModel m = (javax.swing.table.DefaultTableModel) table.getModel();
        int[][] adys = new int[m.getRowCount()][m.getRowCount()];

        try {
            for (int i = 0; i < m.getRowCount(); i++) {
                for (int j = 1; j < m.getColumnCount(); j++) {
                    if (table.getValueAt(i, j).equals("1")) {
                        adys[i][j - 1] = 1;
                    } else {
                        adys[i][j - 1] = 0;
                    }
                }
            }

            graph.setConnections(adys);

            if (DisplayWindow.NODES != null) {
                for (int i = 0; i < Integer.min(graph.nodes.size(), DisplayWindow.NODES.size()); i++) {
                    graph.nodes.get(i).setCoords(DisplayWindow.NODES.get(i).getX(), DisplayWindow.NODES.get(i).getY());
                }
            }
            DisplayWindow.NODES = graph.nodes;
            DisplayWindow.MATCHES = adys;

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Digite números validos.", "Error #2", javax.swing.JOptionPane.ERROR_MESSAGE);
        } finally {
            for (int i = 0; i < m.getRowCount(); i++) {
                table.setValueAt("X", i, i + 1);
            }
        }
    }

    /**
     * <p>
     * This method searches throug the grpah whether a node with an information
     * <code>nombre</code> is in the graph, if it is in it, it tells the jtable 
     * to go to the specified cell and select it.
     * </p>
     * @param nombre It is the info of the node that is going to be searched.
     */
    public void Search(String nombre) {
            int i;
            for (i = 0; i < table.getRowCount() && !table.getValueAt(i, 0).toString().equals(nombre); i++) {
            }
            if (i < table.getRowCount() && table.getValueAt(i, 0).toString().equals(nombre)) {
                table.setRowSelectionInterval(i, i);
                table.scrollRectToVisible(new Rectangle(table.getCellRect(i, 0, true)));
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "El nodo no se encuentra en el grafo", "Error #4", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
    }

    /**
     * <p>
     * This class is a modification to the 
     * <code>javax.swing.table.DefaultTableModel</code> class in order to
     * do certain things as remove a column, set some cells to uneditable.
     * </p>
     * @see <code>javax.swing.table.DefaultTableModel</code>
     */
    private class CustomModel extends javax.swing.table.DefaultTableModel {

        /**
         * <p>
         * This method sends to the <code>javax.swing.table.DefaultTableModel</code>
         * the information to create a mode for the table.
         * </p>
         * @param columnNames It is the headers or column info.
         * @param rowCount It is the number of rows in the table.
         */
        public CustomModel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        /**
         * <p>
         * This method looks whether a cell is editable or not.
         * </p>
         * @param row the row index of the cell.
         * @param column the column index of the cell.
         * @return True if the specified cell in a specified row and column is
         * editable or False if not.
         */
        @Override
        public boolean isCellEditable(int row, int column) {
            return (column != 0) && (column - 1 != row);
        }

        /**
         * <p>
         * This methods deletes or removes a specified column in a specified
         * index from the table.
         * </p>
         * @param columnIndex It is the index of the column that is desired to 
         * remove.
         */
        public void removeColumn(int columnIndex) {
            if (columnIndex != getColumnCount() - 1) {
                for (int i = 0; i < this.getRowCount(); i++) {
                    java.util.Vector v = (java.util.Vector) dataVector.elementAt(i);
                    v.setElementAt(v.elementAt(columnIndex + 1), columnIndex);
                }
            }
            columnIdentifiers.remove(columnIndex);
            fireTableStructureChanged();
        }
    }
}

/*
 *  ============================================================================================
 *  TableModelAdapter which defines data structures for holding the data to be presented by a 
 *  JTable. The JTable displays the contents of a list of children shapes based on the selected 
 *  nested shape (i.e. parent) from a JTree.
 *  YOUR UPI: xtu424
 *  Student Name: Xinge Tu
 *  Date: 06/06
 *  ============================================================================================
 */

import javax.swing.table.*;


class TableModelAdapter extends AbstractTableModel{
    private NestedShape nestedShape;
    private static String[] columnNames = {"Type", "X-pos", "Y-pos", "Width","Height"};
    public TableModelAdapter(NestedShape a){
        nestedShape = a;
    }
    public TableModelAdapter(){}
    public int getColumnCount(){return columnNames.length;}
    public int getRowCount(){return nestedShape.getSize();}
    public String getColumnName(int column){return columnNames[column];}
    public Object getValueAt(int rowIndex, int columnIndex){
        if(columnIndex == 0){return nestedShape.getShapeAt(rowIndex).getClass().getName();}
        else if(columnIndex == 1){return nestedShape.getShapeAt(rowIndex).getX();}
        else if(columnIndex == 2){return nestedShape.getShapeAt(rowIndex).getY();}
        else if(columnIndex == 3){return nestedShape.getShapeAt(rowIndex).getWidth();}
        else{return nestedShape.getShapeAt(rowIndex).getHeight();}
    }
    public void setNestedShape(Shape s){
        nestedShape = (NestedShape)s;
    }
}

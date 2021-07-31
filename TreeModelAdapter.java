/*
 *  ============================================================================================
 *  TreeModelAdapter which defines data structures for holding the data to be presented by a JTree. 
 *  The JTree displays the hierarchical list of shapes in the program.
 *  YOUR UPI: xtu424
 *  Student Name: Xinge Tu
 *  Date: 06/06
 *  ============================================================================================
 */

import javax.swing.event.*;
import java.util.ArrayList;
import javax.swing.tree.*;

class TreeModelAdapter implements TreeModel {
    private NestedShape nestedShape;
    private ArrayList<TreeModelListener> treeModelListeners = new ArrayList<TreeModelListener>();
    public TreeModelAdapter(NestedShape a){
        nestedShape = a;
    }
    public NestedShape getRoot(){return nestedShape;}
    public boolean isLeaf(Object node){
        Shape n = (Shape)node;
        if(n.getClass().getName().equals("NestedShape")){return false;}
        else{return true;}
    }
    public Shape getChild(Object parent, int index){
    	
    	if (parent instanceof NestedShape) {
        NestedShape n = (NestedShape)parent;
        if(index > n.getSize()-1) {return null;}
        return n.getShapeAt(index);}else {return null;}
        
    }
    public int getChildCount(Object parent){
        if(parent.getClass().getName().equals("NestedShape")){
            NestedShape n = (NestedShape)parent;
            return n.getSize();}
        else return 0;
    }
    public int getIndexOfChild(Object parent, Object child){
    	if (parent instanceof NestedShape) {
        NestedShape n = (NestedShape)parent;
        Shape c = (Shape)child;
        return n.indexOf(c);} else return -1;
        
    }
    public void addTreeModelListener(final TreeModelListener modelListener) {
        treeModelListeners.add(modelListener);
    }
	public void removeTreeModelListener(final TreeModelListener modelListener) {
	    treeModelListeners.remove(modelListener);
	}
	public void fireTreeNodesInserted(Object source, Object[] path,int[] childIndices,Object[] children){
	    TreeModelEvent event = new TreeModelEvent(source, path, childIndices, children);
	    for(TreeModelListener i: treeModelListeners){
	        i.treeNodesInserted(event);
	    }
	}
	public void fireTreeNodesRemoved(Object source, Object[] path, int[] childIndices, Object[] children){
	    TreeModelEvent event = new TreeModelEvent(source, path, childIndices, children);
	    for(TreeModelListener i: treeModelListeners){
	        i.treeNodesRemoved(event);
	    }
	}
	public void fireTreeStructureChanged(final Object source, final Object[] path, final int[] childIndices, final Object[] children){
	    TreeModelEvent event = new TreeModelEvent(source, path, childIndices, children);
	    for(TreeModelListener i: treeModelListeners){
	        i.treeStructureChanged(event);
	    }
	}
	public void addToRoot(Shape s){
//		s.setParent(nestedShape);
		int numberOfChildren = nestedShape.getSize();
		nestedShape.add(s);
		fireTreeNodesInserted(this, new Object[]{nestedShape}, new int[]{numberOfChildren},new Object[]{s});
    }
    public boolean addNode(TreePath selectedPath, ShapeType st){
	    Shape pi = (Shape) selectedPath.getLastPathComponent();
	    if( !(pi.getClass().getName().equals("NestedShape"))){return false;}
	    NestedShape p =(NestedShape)pi; 
        int numberOfChildren = p.getSize();
        p.createAddInnerShape(st);
        Object newNode = p.getShapeAt(p.getSize()-1);
        fireTreeNodesInserted(this, selectedPath.getPath(), new int[]{numberOfChildren},new Object[]{newNode});
        return true;
    }
    public boolean removeNodeFromParent(TreePath selectedPath){
	    Shape selectedNode = (Shape) selectedPath.getLastPathComponent();
	    if(selectedNode.getParent()==null){return false;}
        NestedShape p = (NestedShape)selectedNode.getParent();
        int childIndex = p.indexOf(selectedNode);
        p.remove(selectedNode);
        fireTreeNodesRemoved(this, selectedPath.getParentPath().getPath(), new int[]{childIndex},new Object[]{selectedNode});
        return true;
	    
    }
    
	public void fireTreeNodesChanged(TreeModelEvent e){}
	public void valueForPathChanged(TreePath path, Object newValue) {}
}





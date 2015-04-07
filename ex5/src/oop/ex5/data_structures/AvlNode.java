package oop.ex5.data_structures;

/** 
 * @author Aviad Levy
 */
public class AvlNode {
	
	public AvlNode left,right,parent;
	public int data,height;
	
	/**
	 * default constructor
	 */
	public AvlNode(){
		left = null;
		right = null;
		parent = null;
		data = 0;
		height = 0;
	}

	/**
	 * constructor that get the value of the node
	 * 
	 * @param val the value of the new node
	 */
	public AvlNode(int val) {
		left = null;
		right = null;
		parent = null;
		data = val;
		height = 0;
	}
	
	/**
	 * found the succesor of the node (next minimum value that is bigger then
	 * current node). 
	 * 
	 * @return the succesor of the node.
	 */
	protected AvlNode succesor() {
		if(this.right != null){
			AvlNode tempNode = this.right;
			while(tempNode.left != null) {
				tempNode = tempNode.left;
			}
			return tempNode;
		} else
			return null;
	}
}

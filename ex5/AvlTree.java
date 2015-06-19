package oop.ex5.data_structures;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author win7
 *
 */
public class AvlTree {

	private AvlNode root;
	private final static int BALANCE_THE_LEFT = 2, BALANCE_THE_RIGHT = -2, 
			VALUE_IS_NOT_HERE = -1;
	
	
	/**
	 * A default constructor.
	 */
	public AvlTree() {
	      this.root = null;
	}
	   
    /**
     * A data constructor -
     * a constructor that builds the tree by adding the elements in the input
     * array one-by-one. If the same value appears twice (or more) in the list
     * it is ignored.
     *
     * @param data values to add to tree
     */
    public AvlTree(int[] data) {
    	root = null;
    	for(int val : data){
    		add(val);
    	}
    }
	   
    /**
     * A copy constructor -
     * a constructor that builds the tree a copy of an existing tree.
     */
    public AvlTree(AvlTree tree) {
    	root = null;
    	Iterator<Integer> iter = tree.iterator();
    	while(iter.hasNext()){
    		add(iter.next());
    	}
    }

    /**
     * Add a new node with key newValue into the tree.
     *
     * @param newValue new value to add to the tree.
     * @return false iff newValue already exist in the tree.
     */
    public boolean add(int newValue) {
        if(contains(newValue) != VALUE_IS_NOT_HERE)
        	return false;
        else {
        	add(newValue,root,null);
        	this.updateRoot();
        	return true;
        }
    }
    
    private void add(int val, AvlNode node, AvlNode parent) {
    	if(node == null){
    		node = new AvlNode(val);
    		node.parent = parent;
    	} else {
    		if (val < node.data) {
    			if(node.left == null){
    				node.left = new AvlNode(val);
    				node.left.parent = node;
	    			balance(node);
    			} else {
    				add(val,node.left,node);
    			}
	    	} else { //only else because no chance that is equal
	    		if(node.right==null){
	    			node.right = new AvlNode(val);
	    			node.right.parent = node;
	    			balance(node);
	    		} else {
	    			add(val,node.right,node);
	    		}
	    	}
    	}
	    node.height = Math.max(height(node.left),height(node.right)) + 1;
    	this.root = node;
    }
    
    /**
     * Does tree contain a given input value
     * 
     * @param serchVal value to search for
     * @return if searchVal is found in the tree, reutrn the depth of its node 
     * (where 0 is the root). Otherwise return -1.
     */
    public int contains(int searchVal) {
    	return contains(root,searchVal);
    }
    
    private int contains(AvlNode node, int searchVal) {
    	boolean isHere = false;
    	int heightOfSearchVal = 0;
    	while (node != null && (!isHere)) { 
    		if(node.data == searchVal){
    			isHere = true;
    			break;
    		} else if(node.data < searchVal){
    			node = node.right;
    			heightOfSearchVal++;
    		} else {
    			node = node.left;
    			heightOfSearchVal++;
    		}
    	}
    	if(isHere)
    		return heightOfSearchVal;
    	else
    		return VALUE_IS_NOT_HERE;
    }
    
    /**
     * Remove a node from the tree' of it exists.
     * 
     * @param toDelete value to delete.
     * @return true iff toDelete is found and deleted
     */
    public boolean delete(int toDelete) {
    	if(contains(toDelete) == VALUE_IS_NOT_HERE)
        	return false;
        else {
        	delete(toDelete,root);
        	this.updateRoot();
        	return true;
        }
    }
    
    /**
     * helping method for delete(int toDelete)
     * 
     * @param node current node.
     * @param toDelete value we need to delete
     */
    private void delete(int toDelete, AvlNode node) {
    	AvlNode parent = null;
		parent = node.parent;
    	while (node != null) { 
    		if(node.data == toDelete){
    			//no child, easy as pie
    			if(node.left == null && node.right == null){
    				node = node.parent;
    				if(node != null) {
    					if(node.left != null){
	    					if(node.left.data == toDelete)
	    						node.left = null;
	    					else
	    						node.right = null;
    					} else {
    							node.right = null;
    					}
	    					
    				}
    				this.root = node;
    			} 
    			//only one child
    			else if((node.left == null && node.right != null) || 
    					(node.right == null && node.left != null)) {
    				AvlNode child = (node.left == null) ? node.right : node.left;
    				if(child.data < parent.data){
    					parent.left = child;
    				} else {
    					parent.right = child;
    				}
    				node = null;
    				this.root = parent;
    			} 
    			//two children
    			else {
    				AvlNode dataOfSuccesor = node.succesor();
    				node.data = dataOfSuccesor.data;
    				if(dataOfSuccesor.right != null){
    					node.succesor().parent.left = dataOfSuccesor.right;
    				} else { //remove succesor
    					node = node.succesor().parent;
    					node.left = null;
    					parent = node;
    				}
    				this.root = node;
    			}
    		} else if(node.data > toDelete){
    			parent = node;
    			node = node.left;
    		} else {
    			parent = node;
    			node = node.right;
    		}
    	}
    	if(parent!= null)
    		balance(parent);
    }
    
    
    /**
     * check and balance if needed
     * 
     * @param node current node we balancing
     */
    private void balance(AvlNode node) {
    	// check the balance    	
    	int balance = height(node.right) - height(node.left);

    	//if left is bigger
    	if(balance==BALANCE_THE_RIGHT) {
    	 
    		if(height(node.left.left) >= height(node.left.right)) {
    			node = rotateTheRight(node);
    		} else {
    			node = doubleRotateTheRight(node);
    		}
    	} else if(balance==BALANCE_THE_LEFT) {
    		if(height(node.right.right) >= height(node.right.left)) {
    			node = rotateTheLeft(node);
    		} else {
    			node = doubleRotateTheLeft(node);
    		}
    	}
    	//we're not done yet. let's go to the parent
    	if(node.parent != null)
    		balance(node.parent);
    	else
    		this.root = node;
	 }
    
    /**
     * rotate to the left.
     * 
     * @param node current node we rotating
     * @return the node after rotation
     */
    private AvlNode rotateTheLeft(AvlNode node){
    	AvlNode temp = node.right;
    	temp.parent = node.parent;
    	node.right = temp.left;
    	if(node.right != null)  //connect the rest of the tree, if needed.
    		node.right.parent = node;
    	temp.left = node;
    	node.parent = temp;
    	if(temp.parent != null){  //update the parent of temp (if needed)
    		if(temp.parent.right == node){
    			temp.parent.right = temp;
    		} else if(temp.parent.left == node){
    			temp.parent.left = temp;
    		}
    	}
    	node.height = Math.max(height(node.left),height(node.right)) + 1;
    	temp.height = Math.max(height(temp.right),height(temp.left)) + 1;
    	return temp;
    }
    
    /**
     * rotate to the right.
     * 
     * @param node current node we rotating
     * @return the node after rotation
     */
    private AvlNode rotateTheRight(AvlNode node) {
    	AvlNode temp = node.left;
    	temp.parent = node.parent;
    	node.left = temp.right;
    	if(node.left != null)  //connect the rest of the tree, if needed.
    		node.left.parent = node;
    	temp.right = node;
    	node.parent = temp;
    	if(temp.parent != null){  //update the parent of temp (if needed)
    		if(temp.parent.right == node){
    			temp.parent.right = temp;
    		} else if(temp.parent.left == node){
    			temp.parent.left = temp;
    		}
    	}
    	//update the height of the node (after placing)
    	node.height = Math.max(height(node.left),height(node.right)) + 1;
    	temp.height = Math.max(height(temp.left),height(temp.right)) + 1;
    	return temp;
    }
    
   private AvlNode doubleRotateTheRight(AvlNode node) {
	   //first rotate the left, then the right
	   node.left = rotateTheLeft(node.left);
	   return rotateTheRight(node);
   }
   
   private AvlNode doubleRotateTheLeft(AvlNode node) {
	   //first rotate the right, then the left
	   node.right = rotateTheRight(node.right);
	   return rotateTheLeft(node);
   }

    /**
     * @return number of nodes in the tree
     */
    public int size(){
    	return size(root);
    }
    
    /**
     * helping method. count the tree recursively.
     * 
     * @param node current node we counting.
     * @return number of nodes in the tree.
     */
    private int size(AvlNode node) {
    	if(node == null) {
    		return 0;
    	} else {
    		int count = 1;
    		count += size(node.right);
    		count += size(node.left);
    		return count;
    	}
    }
    
    /**
     * @return iterator to the Avl Tree. The returned iterator can pass over the
     * tree nodes in ascending order.
     */
    public Iterator<Integer> iterator() {
		Iterator<Integer> iter = new Iterator<Integer>() {
			
			//build array in ascending order. then it's easier to iterate.
			private ArrayList<AvlNode> array = buildArrayInOrder(root, new ArrayList<AvlNode>());
			
			@Override
			public void remove() {}
			
			@Override
			public Integer next() {
				//remove first item in array (the smallest) and return its data.
				return array.remove(0).data;
			}
			
			@Override
			public boolean hasNext() {
				if(array.size() == 0){
					return false;
				}
				return true;
			}
		};
		return iter;
	}
    
    /**
     * This method calculates the minimum number of nodes in an AVL tree of height h
     * 
     * @param h height of the tree (non-negative number).
     * @return minimum number of nodes in the tree
     */
    public static int findMinNodes(int h) {
		switch (h) {
		case 0:  //not a magic numbers. they are part of the required equation.
			return 1;
		case 1:
			return 2;
		default:
			return 1 + findMinNodes(h-1) + findMinNodes(h-2);
		}
    }
    
    /**
     * helping method that build array in ascending order. 
     * 
     * @param node current node we dealing with.
     * @param array the array we building.
     */
    private ArrayList<AvlNode> buildArrayInOrder(AvlNode node,ArrayList<AvlNode> array){
    	if(node == null)
    		return null;
    	buildArrayInOrder(node.left, array);
    	array.add(node);
    	buildArrayInOrder(node.right, array);
    	return array;
    }
    
    /**
     * check the hieght of node. this method needed in order to prevent null errors.
     * 
     * @param node the node to check
     * @return the height.
     */
    private int height(AvlNode node) {
    	if(node==null) {
    	   return -1;
    	}
    	if(node.left==null && node.right==null) { //is leaf
    		return 0;
    	} else {
    		return 1+ Math.max(height(node.left),height(node.right));
    	}
    }
    
    /**
     * if this.root is no the real root of the tree (after adding and deleting),
     * update this.root to be the real root.
     */
    private void updateRoot() {
    	if(this.root == null){
    		return;
    	}
    	AvlNode temp = this.root;
    	while(temp.parent != null) {
    		temp = temp.parent;
    	}
    	this.root = temp;
    }
}
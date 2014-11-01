package WarmUp;

//Terry Schmidt, November 2014

public class TrinaryTree {

	public static void main(String[] args) {
		int nodesToInsert[] = {5, 4, 9, 5, 7, 2, 2}; // element 0 of this array becomes root.
		TrinaryTree TriTree = new TrinaryTree();
		TrinaryTree.prepForInsertion(nodesToInsert);
	}
	
	
	static class Node {
		Node left;  // Left child < node value
		Node middle;  // Middle child == to node value
		Node right;  // Right child > node value
		int value;  // Node value

		public Node(int value) {  
			this.value = value;
		}
	}

	
	public static void prepForInsertion(int[] valuesToBeInserted) {
		if(valuesToBeInserted.length == 0) {
			System.out.println("You gotta pass me an int array that isn't empty, yo!");
			System.exit(-1);
		}
		
		Node root = new Node(valuesToBeInserted[0]); 

		for (int i = 1; i < valuesToBeInserted.length; i++) {
			insert(root, valuesToBeInserted[i]);
		}
		System.out.println("Tree:");
		Print(root);
		
		//testing delete function below
		
		System.out.println("");
		System.out.println("Tree after deletion of 9:");
		delete(root, 9);
		Print(root);
		
		System.out.println("");
		System.out.println("Tree after deletion of 5:");
		delete(root, 5);
		Print(root);
		
		System.out.println("");
		System.out.println("Tree after deletion of 4:");
		delete(root, 4);
		Print(root);
		
		System.out.println("");
		System.out.println("Tree after deletion of 5:");
		delete(root, 5);
		Print(root);
	}
	

	public static void insert(Node root, int newValue) {
		
		if (newValue < root.value) { //if the value to be inserted is less than the node value, then we insert it as left child (if it does not exist), or if it does exist, recursively call the the insert on the left child
			if (root.left != null) { // this is the case where value is < node.value and where a left child exists.  Keep recursively going down tree until a left child does not exist.
				insert(root.left, newValue);
			}
		
			else { // this is the case where a left child does not exist, yay!  Now we create the left child by making it a new node.
				root.left = new Node(newValue);
			}
		}
		
		
		
		else if (newValue > root.value) {  
			if (root.right != null) {  // this is the case where value > node.value and a right child exists.  Keep recursively going down tree until a right child does not exist
				insert(root.right, newValue);
			} else {
				root.right = new Node(newValue); // we found the part of the tree to put this value.  Create a node for it.
			}
		}
		
		
		
		else {
			if (root.middle != null) {  // if this node has a middle child
				insert(root.middle, newValue);  // keep recursiely going down the tree until we get to the point where there is no middle child
			} else {
				root.middle = new Node(newValue);  // create the new node there!
			}
		}
	}
	
	
	public static Node delete(Node node, int value) {
		if (node.value > value) {  // if the value to be deleted is less than the root, find it in the left
			node.left = delete(node.left, value);
		} 
		
		else if(node.value < value) {  // if the value to be deleted is greater than the root, find it in the right
			node.right = delete(node.right, value);
		} 
		
		else {
				if (node.middle != null) {  
					node.middle = delete(node.middle, value);
				} 
				
				else if (node.right != null) {
					int min = minimum(node.right).value;
					node.value = min;
					node.right = delete(node.right, min);
				} 
				
				else {
				node = node.left;
				}
		}
		return node;
	}
	
	
	public static void Print(Node root) {
		if (root != null) {
			System.out.println(root.value);
			Print(root.left);  // recursively print left children
			Print(root.middle); // recursively print middle children
			Print(root.right);  // recursively print right children
		}
	}
	
	
	public static Node minimum(Node node) {
		if (node != null) {  // if the node passed is not null
			while (node.left != null) {  // while the left child of the node passed exists
				return minimum(node.left);  // recursively go down the tree until we find a node that does not have a left child
			}
		}
		return node;  // return that node, its the minimum in the tree
	}
}

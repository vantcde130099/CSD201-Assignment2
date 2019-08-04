/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

/**
 *
 * @author thinh
 * @param <E>
 */
public class AVLBinaryTree<E extends Comparable<E>> {

    public Node<E> root;

    //check tree is empty or not
    boolean isEmpty() {
        return root == null;
    }

    // delete(or clear) the tree.
    void clear() {
        root = null;
    }

    // A utility function to get the height of the tree 
    int height(Node N) {
        if (N == null) {
            return 0;
        }

        return N.height;
    }

    // A utility function to get maximum of two integers 
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y 
    // See the diagram given above. 
    Node<E> rightRotate(Node<E> y) {
        Node<E> x = y.left;
        Node<E> T2 = x.right;

        // Perform rotation 
        x.right = y;
        y.left = T2;

        // Update heights 
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root 
        return x;
    }

    // A utility function to left rotate subtree rooted with x 
    // See the diagram given above. 
    Node<E> leftRotate(Node x) {
        Node<E> y = x.right;
        Node<E> T2 = y.left;

        // Perform rotation 
        y.left = x;
        x.right = T2;

        //  Update heights 
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root 
        return y;
    }

    // Get Balance factor of node N 
    int getBalance(Node N) {
        if (N == null) {
            return 0;
        }

        return height(N.left) - height(N.right);
    }

    //insert object to tree
    Node<E> insert(Node<E> node, E key) {
        /* 1.  Perform the normal BST insertion */
        if (node == null) 
            return (new Node(key)); 
  
        if (key.compareTo(node.info) < 0) 
            node.left = insert(node.left, key); 
        else if (key.compareTo(node.info) > 0) 
            node.right = insert(node.right, key); 
        else // Duplicate keys not allowed 
            return node; 
  
        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left), 
                              height(node.right)); 
  
        /* 3. Get the balance factor of this ancestor 
              node to check whether this node became 
              unbalanced */
        int balance = getBalance(node); 
  
        // If this node becomes unbalanced, then there 
        // are 4 cases Left Left Case 
        if (balance > 1 && key.compareTo(node.left.info) < 0) 
            return rightRotate(node); 
  
        // Right Right Case 
        if (balance < -1 && key.compareTo(node.right.info) > 0) 
            return leftRotate(node); 
  
        // Left Right Case 
        if (balance > 1 && key.compareTo(node.left.info) > 0) { 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        } 
  
        // Right Left Case 
        if (balance < -1 && key.compareTo(node.right.info) < 0) { 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        } 
  
        /* return the (unchanged) node pointer */
        return node; 
    }

    public void insertTo(E x) {
        this.root = insert(this.root, x);
    }

    /*Given a non-empty binary search tree, return the  
    node with minimum key value found in that tree.  
    Note that the entire tree does not need to be  
    searched. */
    Node<E> minValueNode(Node<E> node) {
        Node<E> current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    Node<E> deleteNode(Node<E> root, E key) {
         if (root == null)  
            return root;  
  
        // If the key to be deleted is smaller than  
        // the root's key, then it lies in left subtree  
        if (key.compareTo(root.info) < 0)  
            root.left = deleteNode(root.left, key);  
  
        // If the key to be deleted is greater than the  
        // root's key, then it lies in right subtree  
        else if (key.compareTo(root.info) > 0)  
            root.right = deleteNode(root.right, key);  
  
        // if key is same as root's key, then this is the node  
        // to be deleted  
        else
        {  
  
            // node with only one child or no child  
            if ((root.left == null) || (root.right == null))  
            {  
                Node<E> temp = null;  
                if (temp == root.left)  
                    temp = root.right;  
                else
                    temp = root.left;  
  
                // No child case  
                if (temp == null)  
                {  
                    temp = root;  
                    root = null;  
                }  
                else // One child case  
                    root = temp; // Copy the contents of  
                                // the non-empty child  
            }  
            else
            {  
  
                // node with two children: Get the inorder  
                // successor (smallest in the right subtree)  
                Node<E> temp = minValueNode(root.right);  
  
                // Copy the inorder successor's data to this node  
                root.info = temp.info;  
  
                // Delete the inorder successor  
                root.right = deleteNode(root.right, temp.info);  
            }  
        }  
  
        // If the tree had only one node then return  
        if (root == null)  
            return root;  
  
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE  
        root.height = max(height(root.left), height(root.right)) + 1;  
  
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether  
        // this node became unbalanced)  
        int balance = getBalance(root);  
  
        // If this node becomes unbalanced, then there are 4 cases  
        // Left Left Case  
        if (balance > 1 && getBalance(root.left) >= 0)  
            return rightRotate(root);  
  
        // Left Right Case  
        if (balance > 1 && getBalance(root.left) < 0)  
        {  
            root.left = leftRotate(root.left);  
            return rightRotate(root);  
        }  
  
        // Right Right Case  
        if (balance < -1 && getBalance(root.right) <= 0)  
            return leftRotate(root);  
  
        // Right Left Case  
        if (balance < -1 && getBalance(root.right) > 0)  
        {  
            root.right = rightRotate(root.right);  
            return leftRotate(root);  
        }  
  
        return root;  
    }
    
    public void delete(E key){
        this.root = deleteNode(this.root, key);
    }
    
    private void visit(Node<E> p){
        System.out.print(p.info);
    }

    public void preOrder(Node<E> p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }
    
    //7.Inorder
    public void inOrder(Node<E> p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    //8.PostOrder
    public void postOrder(Node<E> p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        inOrder(p.right);
        visit(p);
    }
    
    //5. Traverse a tree.
    public void breadthTravel() {
        int h = height(root);

        //System.out.println("Level Order Tree Traversal: ");
        for (int i = 1; i <= h; i++) {
            printLevel(root, i);
        }
    }

    // caculate height of binary tree
    int getHeight(Node<E> root) {
        if (root == null) {
            return 0;
        }
        // count height in each subtree
        int lheight = height(root.left);
        int rheight = height(root.right);
        // return higher value
        if (lheight > rheight) {
            return lheight + 1;
        } else {
            return rheight + 1;
        }
    }

    // print Node
    void printLevel(Node<E> root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            System.out.print(root.info);
        } else if (level > 1) {
            printLevel(root.left, level - 1);
            printLevel(root.right, level - 1);
        }
    }
    
    // Count and return number of nodes in the tree
    public int countNode() {
        if (root == null) {
            return 0;
        } else {
            return getSize(root);
        }
    }

    int getSize(Node<E> p) {
        int count = 1;
        if (p.left != null) {
            count += getSize(p.left);
        }
        if (p.right != null) {
            count += getSize(p.right);
        }
        return count;
    }
    
    //3. Search node having value x.
    // Return value if yes, null if not found.
    public Node search(Node<E> p, E x) {
        if (p == null) {
            return (null);
        }
        if (p.info.compareTo(x) == 0) {
            return p;
        } else if (p.info.compareTo(x) < 0) {
            return search(p.right, x);
        } else {
            return search(p.left, x);
        }
    }
}
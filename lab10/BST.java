import java.util.LinkedList;
import java.util.Queue;

public class BST {
    Node root;

    //we will have nodes on our binary search tree
    //each node will have a key, and two children
    //right child will be greater than the parent
    //left child will be less than the parent
    private static class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    //root of the binary search tree
    BST(){
        root = null;
    }

    //INSERTION
    /*
    we will have two insert methods, one public and one private
    the public method will call the private method
    because our node class is private, we need a public insert that only takes the key as a parameter
    however since our tree is constructed with the nodes, we need a private method to work with nodes
    */
    public void insert(int key) {
        root = insert(root, key);
    }

    /*insert method works like this:
        if the root is null, we create a new node with the key and return it
        if the key is less than the root, we insert the key to the left
        if the key is greater than the root, we insert the key to the right
        this is a recursive method called until it finds a null node to insert
     */
    private Node insert(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.key) {
            root.left = insert(root.left, key);
        } else {
            root.right = insert(root.right, key);
        }
        return root;
    }

    //DELETION
    /*
    we will have two delete methods, one public and one private
    the public method will call the private method
    because our node class is private, we need a public delete that only takes the key as a parameter
    however since our tree is constructed with the nodes, we need a private method to work with nodes
     */
    public void delete(int key) {
        root = delete(root, key);
    }

    /*delete method works like this:
        if the root is null, we return the root
        if the key is less than the root, we delete the key to the left
        if the key is greater than the root, we delete the key to the right
        if the key is equal to the root, we have three cases:
            1. if the root has no children, we return null
            2. if the root has one child, we return the child
            3. if the root has two children, we find the minimum value of the right subtree, replace the root with the minimum value, and delete the minimum value from the right subtree
        this is a recursive method called until it finds the key to delete
     */
    private Node delete(Node root, int key) {
        if (root == null) {
            return root;
        }
        if (key < root.key) {
            root.left = delete(root.left, key);
        } else if (key > root.key) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.key = minValue(root.right);
            root.right = delete(root.right, root.key);
        }
        return root;
    }

    //SEARCH
    /*
    we will have two search methods, one public and one private
    we will also have methods to find maximum and minimum values in the tree
     */

    /*
    search method works like this:
        if the root is null, we return false
        if the key is equal to the root, we return true
        if the key is less than the root, we search the left subtree
        if the key is greater than the root, we search the right subtree
    */

    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(Node root, int key) {
        if (root == null) {
            return false;
        }
        if (key == root.key) {
            return true;
        }
        if (key < root.key) {
            return search(root.left, key);
        }
        return search(root.right, key);
    }

    public int minValue() {
        return minValue(root);
    }

    private int minValue(Node root) {
        int min = root.key;
        while (root.left != null) {
            min = root.left.key;
            root = root.left;
        }
        return min;
    }

    public int maxValue() {
        return maxValue(root);
    }

    private int maxValue(Node root) {
        int max = root.key;
        while (root.right != null) {
            max = root.right.key;
            root = root.right;
        }
        return max;
    }

    //print as levels using bfs on different lines
    //use - to represent null nodes
    public void print() {
        if (root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int count = q.size();
            while (count > 0) {
                Node node = q.poll();
                if (node == null) {
                    System.out.print("- ");
                } else {
                    System.out.print(node.key + " ");
                    q.add(node.left);
                    q.add(node.right);
                }
                count--;
            }
            System.out.println();
        }
    }
}

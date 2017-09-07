package Lab3;

public class SearchTree {

    private Node root;      //first node of tree

    //------------------------------------------------------------------------------------------------------
    private SearchTree() {

        this.root = null;   //no nodes in tree yet
    }
    //--------------------------------------------------------------------------------------------------------
    private boolean find(int key) {

        Node current = root;        //start at root

        while(current.data != key) {    //while no match

            if(key < current.data)      //go left
                current = current.left;
            else
                current = current.right;    //go right

            if(current == null)         //if no child, nothing found
                return false;
        }
        return true;             //key found
    }
    //--------------------------------------------------------------------------------------------------------
    private void insert(int key) {

        Node newNode = new Node(key);

        //if root null, insert in root
        if(root == null) {

            root = newNode;
            return;
        }
        Node current = root;    //start at root
        Node parent;

        while(true) {

            parent = current;

            if(key < current.data) {        //go left

                current = current.left;

                if (current == null) {       //if end of line, insert left

                    parent.left = newNode;
                    return;
                }
            } else {                          //go right

                    current = current.right;

                    if(current == null) {   //if end of line, insert right

                        parent.right = newNode;
                        return;
                    }
                }
        }
    }
    //-----------------------------------------------------------------------------------------------------------
    private Node getSuccessor(Node delNode) {

        Node successor = null;
        Node successorParent = null;
        Node current = delNode.right;       //go to right child until no more left children

        while(current != null) {

            successorParent = successor;
            successor = current;
            current = current.left;         //go left child
        }
        //if successor not right child, make connections
        if(successor != delNode.right) {

            successorParent.left = successor.right;
            successor.right = delNode.right;
        }
        return successor;
    }
    //-----------------------------------------------------------------------------------------------------------
    private boolean delete(int key) {

        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;

        while(current.data != key) {

            parent = current;

            if(key < current.data) {        //go left

                isLeftChild = true;
                current = current.left;
            }
            else {                          //go right

                isLeftChild = false;
                current = current.right;
            }

            if(current == null)             //not found
                return false;
        }
        //if no children, delete it
        if(current.left == null && current.right == null) {

            if(current == root)
                root = null;        //tree is empty if root
            else if(isLeftChild)
                parent.left = null; //disconnect from parent
            else
                parent.right = null;
        }
        else if(current.right == null) {    //if no right child, replace with left subtree

            if(current == root)
                root = current.left;
            else if(isLeftChild)
                parent.left = current.left;
            else
                parent.right = current.left;
        }
        else if(current.left == null) {     //if no left child, replace with right subtree

            if(current == root)
                root = current.right;
            else if(isLeftChild)
                parent.left = current.right;
            else
                parent.right = current.right;
        }
        else {                              //two children, replace with inorder successor
            //get successor of node to delete
            Node successor = getSuccessor(current);

            //connect parent of current to successor
            if(current == root)
                root = successor;
            else if(isLeftChild)
                parent.left = successor;
            else
                parent.right = successor;

            //connect successor to current left child
            successor.left = current.left;
        }
        return true;
    }
    //-------------------------------------------------------------------------------------------------------------
    private void display(Node root) {

        if(root != null) {

            display(root.left);
            System.out.print(" " + root.data);
            display(root.right);
        }
    }
    //-------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {

        SearchTree tree = new SearchTree();

        //insert numbers 1 - 10
        tree.insert(6);
        tree.insert(4);
        tree.insert(3);
        tree.insert(8);
        tree.insert(2);
        tree.insert(1);
        tree.insert(5);
        tree.insert(10);
        tree.insert(9);
        tree.insert(7);

        System.out.println("Original Tree:");
        tree.display(tree.root);
        System.out.println("");

        System.out.println("Was the number 5 found?");
        System.out.println(tree.find(5));
        System.out.println("");

        System.out.println("5 was found, so delete 5");
        tree.delete(5);
        System.out.println("");

        System.out.println("New Tree:");
        tree.display(tree.root);

    }

}

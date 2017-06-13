package TAD;

public class AVL {
	 
    private Node arrel;
 
    private class Node {
        private int key;
        private int balance;
        private int height;
        private Node left, right, parent;
 
        Node(int k, Node p) {
            key = k;
            parent = p;
        }
    }
 
    public boolean insert(int key) {
        if (arrel == null)
            arrel = new Node(key, null);
        else {
            Node n = arrel;
            Node parent;
            while (true) {
                if (n.key == key)
                    return false;
 
                parent = n;
 
                boolean goLeft = n.key > key;
                n = goLeft ? n.left : n.right;
 
                if (n == null) {
                    if (goLeft) {
                        parent.left = new Node(key, parent);
                    } else {
                        parent.right = new Node(key, parent);
                    }
                    rebalance(parent);
                    break;
                }
            }
        }
        return true;
    }
 
    private void delete(Node node){
        if(node.left == null && node.right == null)
        {
            if(node.parent == null) arrel = null;
            else
            {
                Node parent = node.parent;
                if(parent.left == node){
                    parent.left = null;
                }else parent.right = null;
                rebalance(parent);
            }
            return;
        }
        if(node.left!=null){
            Node child = node.left;
            while (child.right!=null) child = child.right;
            node.key = child.key;
            delete(child);
        }else{
            Node child = node.right;
            while (child.left!=null) child = child.left;
            node.key = child.key;
            delete(child);
        }
    }
 
    public void delete(int delKey) {
        if (arrel == null)
            return;
        Node node = arrel;
        Node child = arrel;
 
        while (child != null) {
            node = child;
            child = delKey >= node.key ? node.right : node.left;
            if (delKey == node.key) {
                delete(node);
                return;
            }
        }
    }
 
    private void rebalance(Node n) {
        setBalance(n);
 
        if (n.balance == -2) {
            if (height(n.left.left) >= height(n.left.right))
                n = rotateRight(n);
            else
                n = rotateLeftThenRight(n);
 
        } else if (n.balance == 2) {
            if (height(n.right.right) >= height(n.right.left))
                n = rotateLeft(n);
            else
                n = rotateRightThenLeft(n);
        }
 
        if (n.parent != null) {
            rebalance(n.parent);
        } else {
            arrel = n;
        }
    }
 
    private Node rotateLeft(Node a) {
 
        Node b = a.right;
        b.parent = a.parent;
 
        a.right = b.left;
 
        if (a.right != null)
            a.right.parent = a;
 
        b.left = a;
        a.parent = b;
 
        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }
 
        setBalance(a);
        setBalance(b);

        return b;
    }
 
    private Node rotateRight(Node a) {
 
        Node b = a.left;
        b.parent = a.parent;
 
        a.left = b.right;
 
        if (a.left != null)
            a.left.parent = a;
 
        b.right = a;
        a.parent = b;
 
        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }
 
        setBalance(a);
        setBalance(b);

        return b;
    }
 
    private Node rotateLeftThenRight(Node n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }
 
    private Node rotateRightThenLeft(Node n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }
 
    private int height(Node n) {
        if (n == null)
            return -1;
        return n.height;
    }
 
    private void setBalance(Node n) {
            reheight(n);
			n.balance = height(n.right) - height(n.left);
    }
 
    public void printBalance() {
        printBalance(arrel);
    }
 
    private void printBalance(Node n) {
        if (n != null) {
            printBalance(n.left);
            System.out.printf("%s ", n.balance);
            printBalance(n.right);
        }
    }
 
    private void reheight(Node node){
        if(node!=null){
            node.height=1 + Math.max(height(node.left), height(node.right));
        }
    }
}
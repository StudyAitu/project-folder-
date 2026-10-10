
public class BST {
    private static class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }
    }

    private Node root;

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = insertRec(node.left, key);
        } else if (key > node.key) {
            node.right = insertRec(node.right, key);
        }

        // Duplicate keys are ignored.
        return node;
    }

    public boolean search(int key) {
        Node current = root;

        while (current != null) {
            if (current.key == key) {
                return true;
            }

            if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    public boolean delete(int key) {
        if (!search(key)) {
            return false;
        }

        root = deleteRec(root, key);
        return true;
    }

    private Node deleteRec(Node node, int key) {
        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = deleteRec(node.left, key);
        } else if (key > node.key) {
            node.right = deleteRec(node.right, key);
        } else {
            // Case 1: no left child.
            if (node.left == null) {
                return node.right;
            }

            // Case 2: no right child.
            if (node.right == null) {
                return node.left;
            }

            // Case 3: two children.
            Node successor = findMin(node.right);
            node.key = successor.key;
            node.right = deleteRec(node.right, successor.key);
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }
}

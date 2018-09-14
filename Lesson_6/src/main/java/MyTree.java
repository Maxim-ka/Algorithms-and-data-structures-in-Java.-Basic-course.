import java.util.ArrayList;

public class MyTree<T> {

    private class TreeNode{

        private int id;
        private T object;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(T object, int id) {
            this.object = object;
            this.id = id;
        }
    }

    private TreeNode root;

    public T find(int key) {
        TreeNode current = root;
        while (current.id != key) {
            if (key < current.id) current = current.left;
            else current = current.right;
            if (current == null)  return null;
        }
        return current.object;
    }

    public void insert(T object, int id) {
        TreeNode node = new TreeNode(object, id);
        if (root == null) root = node;
        else {
            TreeNode current = root;
            TreeNode previous;
            while (current != null) {
                previous = current;
                if (node.id < current.id) {
                    current = current.left;
                    if (current == null) previous.left = node;
                } else {
                    current = current.right;
                    if (current == null) previous.right = node;
                }
            }
        }
    }

    public void displayTree() {
        inOrderTravers(root);
    }

    private void inOrderTravers(TreeNode current) {
        if (current != null) {
            inOrderTravers(current.left);
            System.out.println(current.object);
            inOrderTravers(current.right);
        }
    }

    public boolean delete(int key) {
        TreeNode current = root;
        TreeNode parent = root;
        boolean isLeftChild = true;
        while (current.id != key) {
            parent = current;
            if (key < current.id) {
                current = current.left;
                isLeftChild = true;
            } else {
                current = current.right;
                isLeftChild = false;
            }
            if (current == null) return false;
        }
        //if node is a leaf
        if (current.left == null && current.right == null) {
            if (current == root) root = null;
            else if (isLeftChild)  parent.left = null;
            else parent.right = null;
        }
        // if one successor
        else if (current.right == null) {
            if (isLeftChild) parent.left = current.left;
            else  parent.right = current.left;
        }else if (current.left == null) {
            if (isLeftChild)  parent.left = current.right;
            else parent.right = current.right;
        }
        // if both successors exist
        else {
            TreeNode successor = getSuccessor(current);
            if (current == root) root = successor;
            else if (isLeftChild) parent.left = successor;
            else parent.right = successor;
            successor.left = current.left;
            successor.right = current.right;
        }
        return true;
    }

    private TreeNode getSuccessor(TreeNode node) {
        TreeNode current = node.right;
        TreeNode successor = node;
        TreeNode parent = node;
        while (current != null) {
            parent = successor;
            successor = current;
            current = current.left;
        }
        if (successor != node.right) {
            parent.left = successor.right;
        }
        return successor;
    }

    private int postOrderTravers(TreeNode node){
        int heightLeft = -1;
        int heightRight = -1;
        if (node != null){
            if (node.left != null) heightLeft = postOrderTravers(node.left);
            if (node.right != null) heightRight = postOrderTravers(node.right);
            if (Math.abs(heightLeft - heightRight) > 1) return 2 ;
        }
        return heightLeft - heightRight;
    }

    public boolean checkBalance(){
        int balance = postOrderTravers(root);
        return balance <= 1 && balance >= -1;
    }

    private void levelOrderTravers(ArrayList<TreeNode> list, StringBuilder stringBuilder) {
        ArrayList<TreeNode> level = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            TreeNode node = list.get(i);
            stringBuilder.append(node.object).append(" ");
            if (node.left != null)level.add(node.left);
            if (node.right != null)level.add(node.right);
        }
        stringBuilder.append("\n");
        if (!level.isEmpty()) levelOrderTravers(level, stringBuilder);
    }

    public void showTree(){
        if (root == null){
            System.out.println("null");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<TreeNode> level = new ArrayList<>();
        level.add(root);
        levelOrderTravers(level, stringBuilder);
        System.out.println(stringBuilder.toString());
    }
}

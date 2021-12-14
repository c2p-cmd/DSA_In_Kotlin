package non.linear.trees;

public class TreeNode<T> {
    private T data;
    private TreeNode<T> leftChild, rightChild;

    public TreeNode(final T dataInput) {
        this.data = dataInput;
    }

    public boolean isLeaf() {
        return this.getLeftChild() == null && this.getRightChild() == null;
    }

    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public TreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(final TreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }
}

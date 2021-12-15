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

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null || getClass() != otherObject.getClass()) return false;

        TreeNode<?> treeNode = (TreeNode<?>) otherObject;

        if (!getData().equals(treeNode.getData())) return false;
        if (getLeftChild() != null ? !getLeftChild().equals(treeNode.getLeftChild()) : treeNode.getLeftChild() != null)
            return false;
        return getRightChild() != null ? getRightChild().equals(treeNode.getRightChild()) : treeNode.getRightChild() == null;
    }

    @Override
    public int hashCode() {
        int result = getData().hashCode();
        result = 31 * result + (getLeftChild() != null ? getLeftChild().hashCode() : 0);
        result = 31 * result + (getRightChild() != null ? getRightChild().hashCode() : 0);
        return result;
    }
}

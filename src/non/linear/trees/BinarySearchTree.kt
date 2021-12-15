package non.linear.trees

object BinarySearchTree {
    /*
     * Complexity for insertion & lookup:
     *      If the tree is skewed(either too much to the left or too much to the right)
     *       then, O(N)
     *      else O(logN)
     *      N: Number of nodes in tree
     */
    @JvmStatic
    fun insertInto(
        headNode: TreeNode<Int>?, nodeToInsert: TreeNode<Int>
    ): TreeNode<Int> {
        if (headNode == null)
            return nodeToInsert

        if (nodeToInsert.data <= headNode.data)
            headNode.leftChild = insertInto(headNode.leftChild, nodeToInsert)
        else
            headNode.rightChild = insertInto(headNode.rightChild, nodeToInsert)

        return headNode
    }

    @JvmStatic
    fun lookUpFrom(
        headNode: TreeNode<Int>?, dataToSearch: Int
    ): Boolean {
        // is current node in recursive call is null (not found)
        if (headNode == null)
            return false
        // if found
        if (headNode.data == dataToSearch)
            return true

        // recursively traversing through the subtrees
        return if (headNode.data >= dataToSearch)
            lookUpFrom(headNode.leftChild, dataToSearch)
        else
            lookUpFrom(headNode.rightChild, dataToSearch)
    }
}
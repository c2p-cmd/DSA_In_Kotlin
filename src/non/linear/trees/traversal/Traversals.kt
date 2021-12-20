package non.linear.trees.traversal

import non.linear.trees.TreeNode
import java.util.*


object Traversals {
    // recursive printing
    // left, right, root
    fun <T> getPostOrderOf(
        treeNode: TreeNode<T>?, builder: StringBuilder = StringBuilder()
    ): String {
        if (treeNode == null)
            return builder.toString()

        getPostOrderOf(treeNode.leftChild, builder)
        getPostOrderOf(treeNode.rightChild, builder)
        return builder.append("-> ${treeNode.data} ").toString()
    }

    // left, root, right
    fun <T> getInOrderOf(
        treeNode: TreeNode<T>?, builder: StringBuilder = StringBuilder()
    ): String {
        if (treeNode == null)
            return builder.toString()

        getInOrderOf(treeNode.leftChild, builder)
        builder.append("-> ${treeNode.data} ")
        return getInOrderOf(treeNode.rightChild, builder)
    }

    // root, left, right
    fun <T> getPreOrderOf(
        treeNode: TreeNode<T>?, builder: StringBuilder = StringBuilder()
    ): String {
        if (treeNode == null)
            return builder.toString()

        builder.append("-> ${treeNode.data} ")
        getPreOrderOf(treeNode.leftChild, builder)
        return getPreOrderOf(treeNode.rightChild, builder)
    }

    /*
     * Iterative:
     *      root
     *  left    right
     */
    fun <T> breadthFirstPrint(treeRoot: TreeNode<T>): Unit =
        LinkedList<TreeNode<T>?>().apply { add(treeRoot) }.let { nodeQueue ->
            while (!nodeQueue.isEmpty()) {
                var nodeCounter = nodeQueue.size

                while (nodeCounter > 0) {
                    nodeQueue.peek()?.let { currentNode ->
                        print("${currentNode.data} ")
                        nodeQueue.remove()

                        if (currentNode.leftChild != null)
                            nodeQueue.add(currentNode.leftChild)
                        if (currentNode.rightChild != null)
                            nodeQueue.add(currentNode.rightChild)
                    }
                    nodeCounter--
                }
                println()
            }
        }
}